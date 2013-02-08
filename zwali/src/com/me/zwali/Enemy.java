package com.me.zwali;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;


	public class Enemy extends Entity
	{
		Animacao falling, dead;
		private boolean alive;
		boolean right;
		int MaxHealth;
		
		float SightAngle;
		float SightRadiiSQ;
		
		int State;
		int AttackTimer;
		int ATTACKT;
		
		int a=0;
		int type;
		int rotCount = 0;
		
		boolean falling_anim = true,ready = false;
		
		boolean rotleft,rotright;
		
		int radioactivetimer1 = 60;
		int radioactivetime1 = 0;
		int timer = 0;
		int time_dead = 350;
		float alpha = 1.0f;
		
		Vector dVel = new Vector( 0, 0);
		
		Random rdm;
		
		Enemy( Vector pos,Sprite T, int type, int difficulty)
		{
			super( pos, new Vector(68, 60),true,  T );
			falling = new Animacao(3,5,Textures.enemy1_falling,new Vector(128,128), new Vector(68,60));
			dead = new Animacao(5,1,Textures.enemy1_dead,new Vector(256,128), new Vector(136,60));
			
			this.type = type;
			switch(type)
			{
			case 1:
				this.speed = 3;
				this.Health = (int) (115 * difficulty * 0.5);
				this.power = (int) (15 * difficulty * 0.5);
				break;
			case 2:
				this.speed = 5;
				this.Health = (int) (60 * difficulty * 0.5);
				this.power = (int) (10 * difficulty * 0.5);
				break;
			case 3:
				this.speed = 4;
				this.Health = (int) (85* difficulty * 0.5);
				this.power = (int) (13* difficulty * 0.5);
				break;
			}
			
			
			alive = true;
			this.vel = new Vector(0,0);
			
			this.MaxHealth = this.Health;
			this.prevpos = pos;
			
			this.rotleft=true;
			this.rotright=false;
			
			this.right = true;
			
			this.rdm = new Random();
			
			
			
			this.SightAngle = (float) 1;
			this.SightRadiiSQ = 100000;
			
		
			
			this.State = 0; // Move
			
			this.ATTACKT = 25;
			this.AttackTimer = 0;
		}
		
		Vector getPos()
		{
			return pos;
		}
		
		Vector getSize()
		{
			return size;
		}
		
		void kill()
		{
			alive = false;
		}
		
		boolean getAlive()
		{
			return alive;
		}
		
		void Update( Vector target, List<Enemy> enem, int i, Background BACK)
		{
			
	
			if( State != 1)
			{
					vel = new Vector( target.x - pos.x, target.y- pos.y);
					vel.normalize();
				Vector deltaPos = new Vector( vel.x*speed, vel.y*speed);
				
				for( int k = 0; k<enem.size(); k++)
				{
					if(k!=i)
					{
						Vector4 A = collisions.Coll(this, enem.get(k));
						if(A.B <1)
						{
							Vector proj = deltaPos.proj(A.A);
							Vector perp = deltaPos.proj(new Vector ( -A.A.y, A.A.x));
							if( proj.SizeSQ() > A.B*A.B)
							{
								Vector M = new Vector(A.A.x*A.B, A.A.y*A.B);
								if( M.dot(proj) <0)
								{
									deltaPos = new Vector( perp.x, perp.y);
								}
								else
								{
									deltaPos = new Vector(A.A.x*A.B + perp.x, A.A.y*A.B + perp.y);
								}
							}
						}
					}
					
				}
				deltaPos.normalize();
				vel = deltaPos;
				
				this.UpdatePos(BACK);
					
			
				angle = Math.atan2( vel.y, vel.x);
				angle = (angle*360)/(2*Math.PI);
				this.animate();
			}
			
			else if( State == 1)
			{
				if( AttackTimer >= ATTACKT)
				{
					State = 0;
					AttackTimer = 0;
				}
				
				else
				{
					AttackTimer++;
				}
			}
			
		}
			
		void dead_anim(Vector Disp)
		{
			if(!falling.done && falling_anim)
			{
			falling.getIm().setRotation((float) angle);
			falling.getIm().setOrigin(34, 30);
			falling.getIm().setPosition((float)pos.x - (float)Disp.x, (float)pos.y - (float)Disp.y);	
			falling.getIm().draw(Conceito.batch);
			}
			else{
				falling_anim = false;
				dead.getIm().setRotation((float) angle);
				dead.getIm().setOrigin(34, 30);
				dead.getIm().setPosition((float)pos.x - (float)Disp.x, (float)pos.y - (float)Disp.y);	
				dead.getIm().draw(Conceito.batch,alpha);
				
				if(timer >= time_dead) alpha -= 0.01;
				else timer++;
				
				if(alpha <= 0) ready = true;
			}
		}
		
		
		private void animate()
		{
			rotCount++;
				if(right)
				{
					a++;
					
				}
				else
				{
					a--;
				}
				if( a>10) right = false;
				if( a<-10) right = true;
				this.angle += a;
		}

		boolean Collide (Player pl)
		{
			Vector posk = pl.getPos();
			Vector sizek = pl.getSize();
			
			boolean a = ( this.pos.x - this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x - this.size.x/2 <= posk.x + sizek.x/2 		&& this.pos.y - this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y - this.size.y/2 <= posk.y+sizek.y/2);
			boolean b = ( this.pos.x + this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x + this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y - this.size.y/2 >=posk.y-sizek.y/2 	&& this.pos.y - this.size.y/2 <= posk.y+sizek.y/2);
			boolean c = ( this.pos.x + this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x + this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y + this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y + this.size.y/2 <= posk.y+sizek.y/2);
			boolean d = ( this.pos.x - this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x - this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y + this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y + this.size.y/2 <= posk.y+sizek.y/2);
						
			return (a||b||c||d);
		}
		
		boolean CollideUnstatic (UnStaticObj obj)
		{
			Vector posk = obj.pos;
			Vector sizek = obj.size;
			
			boolean a = ( this.pos.x - this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x - this.size.x/2 <= posk.x + sizek.x/2 		&& this.pos.y - this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y - this.size.y/2 <= posk.y+sizek.y/2);
			boolean b = ( this.pos.x + this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x + this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y - this.size.y/2 >=posk.y-sizek.y/2 	&& this.pos.y - this.size.y/2 <= posk.y+sizek.y/2);
			boolean c = ( this.pos.x + this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x + this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y + this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y + this.size.y/2 <= posk.y+sizek.y/2);
			boolean d = ( this.pos.x - this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x - this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y + this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y + this.size.y/2 <= posk.y+sizek.y/2);
						
			return (a||b||c||d);
		}
		
		
		public void Attack()
		{
			State = 1; // ATTACK
			AttackTimer = 0;
		}
		
		/*private boolean SeeTarget( Vector Target, Background BACK)
		{
			Vector Sep = new Vector( Target.x - this.pos.x, Target.y - this.pos.y);
			
			float RADII = this.SightRadiiSQ;
			
			if( this.SeePlayer)
			{
				RADII = 4*this.SightRadiiSQ;
			}
			
			
			if( Sep.SizeSQ() <= RADII)
			{
				
				Vector Sight = new Vector( Math.cos(this.angle * Math.PI/ 180), Math.sin(this.angle * Math.PI/ 180));
								
				Vector MAX = new Vector( Sight.x*Math.cos(SightAngle) - Sight.y * Math.sin(SightAngle), Sight.x*Math.sin(SightAngle) + Sight.y * Math.cos(SightAngle) );
				Vector MIN = new Vector( Sight.x*Math.cos(-SightAngle) - Sight.y * Math.sin(-SightAngle), Sight.x*Math.sin(-SightAngle) + Sight.y * Math.cos(-SightAngle) );
				
				
				MAX.normalize();
				MIN.normalize();
				Sep.normalize();
				
				//System.out.println(MAX.cross(Sep) + "   " + Sep.cross(MIN));
				
				if( MAX.cross(Sep) <= 0 && Sep.cross(MIN) <= 0 )
				{
					System.out.println("FOUND");
					this.SeePlayer = true;
					return true;
				}
					
			}
			this.SeePlayer = false;
			return false;
		}*/
		
	
	
		public void DecreaseHealth(int power, Vector dir)
		{
			this.Health -= power;
			this.angle = Math.atan2( -dir.y, -dir.x);
			this.angle *= 180/Math.PI; 
			
		}
		
		public void isRadioActive()
		{
			if(this.pos.x <= 180 || this.pos.x >= (2048-180) || this.pos.y <= 180 || this.pos.y >= (2048-180) || ((this.pos.x >= 180 && this.pos.x <= 680) && (this.pos.y >= 180 && this.pos.y <= 600)))
			{
				
				if(this.radioactivetime1 == this.radioactivetimer1)
				{
					System.out.println("Enemies a arder");
					this.Health -= 20;
					if(this.Health <= 0)
					{
						this.kill();
					}
					radioactivetime1 = 0;
				}
				else
					radioactivetime1++;
			
			}
		}

	}