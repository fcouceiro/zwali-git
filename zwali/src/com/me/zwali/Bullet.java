package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Bullet extends Entity
	{
		private boolean alive;

		private boolean explode;
		int timerExplode;
		int exp = 0;
		Bullet( Vector pos, Vector velT, Sprite t, int speed, int power)
		{
			super( pos, new Vector (3,12),false,  t);
			this.alive = true;
			this.vel = velT;
			this.speed = speed;
			this.power = power;
		
		}
		
		
		boolean getAlive()
		{
			return this.alive || this.explode;
		}
		
		void kill(boolean Explo)
		{
			vel.x = 0;
			vel.y = 0;
			if( Explo)
			{
				timerExplode = 0;
				explode = true;
			}
			this.alive = false;
		}
		
		
		boolean Collide (Enemy john)
		{
			Vector posk = john.getPos();
			Vector sizek = john.getSize();
			
			boolean a = ( this.pos.x - this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x - this.size.x/2 <= posk.x + sizek.x/2 	&& this.pos.y - this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y - this.size.y/2 <= posk.y+sizek.y/2);
			boolean b = ( this.pos.x + this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x + this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y - this.size.y/2 >=posk.y-sizek.y/2 	&& this.pos.y - this.size.y/2 <= posk.y+sizek.y/2);
			boolean c = ( this.pos.x + this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x + this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y + this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y + this.size.y/2 <= posk.y+sizek.y/2);
			boolean d = ( this.pos.x - this.size.x/2 >= posk.x - sizek.x/2		&& this.pos.x - this.size.x/2 <= posk.x + sizek.x/2		&& this.pos.y + this.size.y/2 >=posk.y-sizek.y/2	&& this.pos.y + this.size.y/2 <= posk.y+sizek.y/2);
						
			return (a||b||c||d);
		}
		
		Vector getPos()
		{
			return this.pos;
		}
		
		
		
		public boolean Update(Background BACK, Player pl)
		{
			this.angle = pl.angle;
			if(explode)
			{
				timerExplode++;
				if(timerExplode > 10)
				{explode = false; timerExplode = 0;}
			}
			
			if( this.Continuous(BACK))
			{
				this.kill( true );
				return false;
			}
			
			return true;
		}
		
		public void draw( Vector Disp, SpriteBatch batch)
		{
			
				super.draw(Disp, batch);
			
			
		}
		
		private boolean Continuous(Background BACK)
		{
			
			Vector deltaPos = new Vector( vel.x * speed, vel.y * speed);
			
			Vector intersection = new Vector( 99999,99999);
			
			for(StaticObj obj: BACK.Objects)
			{
				// First edge
				if( deltaPos.x!=0)
				{
					float k = (float) ((obj.pos.x - obj.size.x/2 - this.pos.x)/deltaPos.x);
					if( k>0 && k<=1)
					{
						if( pos.y+k*deltaPos.y >= obj.pos.y - obj.size.y/2 && pos.y+k*deltaPos.y <= obj.pos.y + obj.size.y/2)
						{
							Vector Dist = new Vector( obj.pos.x - obj.size.x/2 - pos.x, pos.y+k*deltaPos.y - pos.y);
							if( Dist.SizeSQ() < intersection.SizeSQ())
							{
								intersection = Dist;
							}
						}
					}
					
					k = (float) ((obj.pos.x + obj.size.x/2 - this.pos.x)/deltaPos.x);
					if( k>0 && k<=1)
					{
						if( pos.y+k*deltaPos.y >= obj.pos.y - obj.size.y/2 && pos.y+k*deltaPos.y <= obj.pos.y + obj.size.y/2)
						{
							Vector Dist = new Vector( obj.pos.x + obj.size.x/2 - pos.x, pos.y+k*deltaPos.y - pos.y);
							if( Dist.SizeSQ() < intersection.SizeSQ())
							{
								intersection = Dist;
							}
						}
					}
				}
				
				if( deltaPos.y != 0)
				{
					float k = (float) ((obj.pos.y - obj.size.y/2 - this.pos.y)/deltaPos.y);
					if( k>0 && k<=1)
					{
						if( pos.x+k*deltaPos.x >= obj.pos.x - obj.size.x/2 && pos.x+k*deltaPos.x <= obj.pos.x + obj.size.x/2)
						{
							Vector Dist = new Vector( pos.x+k*deltaPos.x - pos.x,  obj.pos.y - obj.size.y/2 - pos.y);
							if( Dist.SizeSQ() < intersection.SizeSQ())
							{
								intersection = Dist;
							}
						}
					}
					
					k = (float) ((obj.pos.y + obj.size.y/2 - this.pos.y)/deltaPos.y);
					if( k>0 && k<=1)
					{
						if( pos.x+k*deltaPos.x >= obj.pos.x - obj.size.x/2 && pos.x+k*deltaPos.x <= obj.pos.x + obj.size.x/2)
						{
							Vector Dist = new Vector(  pos.x+k*deltaPos.x - pos.x , obj.pos.y + obj.size.y/2 - pos.y);
							if( Dist.SizeSQ() < intersection.SizeSQ())
							{
								intersection = Dist;
							}
						}
					}
				}	
			}
			/*
			for( UnStaticObj obj: BACK.UnObjects)
			{
				if(obj.alive)
				{
					// First edge
					if( deltaPos.y!=0)
					{
						float k = (float) ((obj.pos.x - obj.size.x/2 - this.pos.x)/deltaPos.x);
						if( k>0 && k<=1)
						{
							if( pos.y+k*deltaPos.y >= obj.pos.y - obj.size.y/2 && pos.y+k*deltaPos.y <= obj.pos.y + obj.size.y/2)
							{
								Vector Dist = new Vector( obj.pos.x - obj.size.x/2 - pos.x, pos.y+k*deltaPos.y - pos.y);
								if( Dist.SizeSQ() < intersection.SizeSQ())
								{
									intersection = Dist;
								}
							}
						}
						
						k = (float) ((obj.pos.x + obj.size.x/2 - this.pos.x)/deltaPos.x);
						if( k>0 && k<=1)
						{
							if( pos.y+k*deltaPos.y >= obj.pos.y - obj.size.y/2 && pos.y+k*deltaPos.y <= obj.pos.y + obj.size.y/2)
							{
								Vector Dist = new Vector( obj.pos.x + obj.size.x/2 - pos.x, pos.y+k*deltaPos.y - pos.y);
								if( Dist.SizeSQ() < intersection.SizeSQ())
								{
									intersection = Dist;
								}
							}
						}
					}
					
					if( deltaPos.y != 0)
					{
						float k = (float) ((obj.pos.y - obj.size.y/2 - this.pos.y)/deltaPos.y);
						if( k>0 && k<=1)
						{
							if( pos.x+k*deltaPos.x >= obj.pos.x - obj.size.x/2 && pos.x+k*deltaPos.x <= obj.pos.x + obj.size.x/2)
							{
								Vector Dist = new Vector( pos.x+k*deltaPos.x - pos.x,  obj.pos.y - obj.size.y/2 - pos.y);
								if( Dist.SizeSQ() < intersection.SizeSQ())
								{
									intersection = Dist;
								}
							}
						}
						
						k = (float) ((obj.pos.y + obj.size.y/2 - this.pos.y)/deltaPos.y);
						if( k>0 && k<=1)
						{
							if( pos.x+k*deltaPos.x >= obj.pos.x - obj.size.x/2 && pos.x+k*deltaPos.x <= obj.pos.x + obj.size.x/2)
							{
								Vector Dist = new Vector(  pos.x+k*deltaPos.x - pos.x , obj.pos.y + obj.size.y/2 - pos.y);
								if( Dist.SizeSQ() < intersection.SizeSQ())
								{
									intersection = Dist;
								}
							}
						}
					}		
				}
			}
			*/
			if( intersection.x != 99999)
			{
				for( UnStaticObj obj: BACK.UnObjects)
				{
					if( intersection.x + pos.x <= obj.pos.x + obj.size.x/2 && intersection.x + pos.x >= obj.pos.x - obj.size.x/2
							&& intersection.y + pos.y <= obj.pos.y + obj.size.y/2 && intersection.y + pos.y >= obj.pos.y - obj.size.y/2)
					{
						obj.DecreaseHealth(this.power);
					}
				}
				
				pos.x += intersection.x;
				pos.y += intersection.y;
				return true;
			}
			pos.x += deltaPos.x;
			pos.y += deltaPos.y;
			return false;
			
		}
	}