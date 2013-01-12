package com.me.zwali;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;



class Player extends Entity
	{
	 	Textures t;
		Vector constraint;
		Vector PlayerVel;
		int constspeed = 3;
		int KillstreakCont;
		
		boolean hasGun[] = new boolean[4];
		
		int ACCMAX; //MAXIMA ABERTURA
		int ACCMIN; //ABERTURA MINIMA	
		int accuracy;
		int ACCdefault;
		
		
		List<Buff> buffsList = new ArrayList<Buff>(8);
		List <Weapon> InvListWeapons = new ArrayList<Weapon>(10);
		
	 	Constants consts =  new Constants();
		Matriz matriz = new Matriz();
		//UI ui;
		int STATE;
		int XP;
		int money;
		
		boolean timeron;
		boolean ragemode;
		int rageTime = 300;
		int rageTimer;
		int timer;
		int DTime;
		int MaxHp;
		int CurGun;
		int nSpeed;
		int buildQuant;
		int armor;
		int xpenemy1 = 8;
		int xpenemy2 = 12;
		int moneybuff = 0;
		boolean alive;
	 	
		//Upgrades;
		int UpgPwrPistol;
		int UpgPwrMinigun;
		int UpgPwrShotgun;
		int UpgACC;
		
		
		Player(Vector pos, Textures t, int armor)
		{
			super( pos, new Vector (90,90), true,  t.playerPistolIM );
			this.t = t;
			this.speed = 5;
			this.alive = true;
			this.nSpeed = 5;
			this.armor = armor;
			
			this.money = 0;
			this.XP = 0;
			
			this.ACCMAX = 25; //MAXIMA ABERTURA
			this.ACCMIN = 10; //ABERTURA MINIMA	
			this.accuracy = 10;
			this.ACCdefault = 10;
			
			this.STATE = 0;
			this.timeron = false;
			this.timer = 0;
	
			for(int i = 0; i<3; i++)
			{
				this.hasGun[i] = false;
			}
			
			this.hasGun[0] = true;
			this.PlayerVel = new Vector(0,0);			
			
			this.Health = Integer.parseInt(matriz.getFromMatrizHeroi(consts.HEROI, 1));
			this.MaxHp = this.Health;
			this.vel = new Vector(0,0);
			ragemode = false;
			rageTimer = 0;
			
			this.UpgACC = 0;
			this.UpgPwrPistol = 0;
			this.UpgPwrShotgun = 0;
			this.UpgPwrMinigun = 0;
		
			
			buffsList.add(new Buff(t._0,0)); 
			buffsList.add(new Buff(t._0,1)); 
			buffsList.add(new Buff(t._0,2)); 
			buffsList.add(new Buff(t._0,3)); 
			buffsList.add(new Buff(t._0,4));
			buffsList.add(new Buff(t._0,5)); 
			buffsList.add(new Buff(t._0,6)); 
			buffsList.add(new Buff(t._0,7)); 
		}

		public void recoil( Vector dir, int temp)
		{
			this.STATE = 1; // RECOIL MODE
			this.constraint = dir;
			this.timeron = true;
			this.DTime = temp;
		}
		
		public boolean Update( Vector Disp, Background BACK)
		{	
			//Buff area
			for(Buff b:buffsList)
			{
				if(b.activeBuff)
				{
					int t = b.type;
					if(b.active())
					{
						switch(t)
						{
						case 0: //speed
							this.speed = 7;
							break;
						case 1: //ammo
							InvListWeapons.get(CurGun).buff = true;
							break;
						case 2: //power
							InvListWeapons.get(CurGun).power = InvListWeapons.get(CurGun).powerbuff;
							break;
						case 3: //accuracy
							this.ACCMIN = this.accbuff();
							this.ACCMAX = this.accbuff()+15;
							break;
						case 4: //money
							this.moneybuff = 5;
							break;
						case 5: //exp
							this.xpenemy1 = 12;
							this.xpenemy2 = 16;
							break;
						case 6: //ragemode
							this.ragemode = true;
							break;
						case 7:
							break;
						}
					}
				}
				else
				{
					this.speed = 5;
					for(int i=0;i<=2;i++)
					{
						this.InvListWeapons.get(i).buff = false;
						InvListWeapons.get(i).power = InvListWeapons.get(i).power2;
					}
					this.ACCMIN = this.ACCdefault;
					this.ACCMAX = this.ACCdefault + 15;
					this.moneybuff = 0;
					this.xpenemy1 = 8;
					this.xpenemy2 = 12;
					this.ragemode = false;
				}
			}
			//End
			
			this.accuracy-=1;
			if( this.accuracy < this.ACCMIN)
			{
				this.accuracy = this.ACCMIN;
			}
			
			if( this.Health<=0 )
			{
				this.alive = false;
				return false;
			}
			
			if(timeron)
			{
				if(timer< DTime)
				{
					timer++;
				}
				else
				{
					timeron = false;
					DTime = 0;
					timer = 0;
				}
			}
			
			vel = PlayerVel;
			
			
			if(timeron)
			{
				vel = constraint;
				speed = constspeed;
			}
			else speed = nSpeed;
			
			this.UpdatePos(BACK);
		
			int MouseX = Mouse.getX();
			int MouseY = consts.HEIGHT - Mouse.getY();
			
			Vector DispMouse = new Vector (MouseX-( pos.x - Disp.x), MouseY - ( pos.y - Disp.y) );
			
			angle = Math.atan2( DispMouse.y, DispMouse.x);
			angle = 90+((angle*180)/(Math.PI));
			
			DispMouse.normalize();
			InvListWeapons.get(CurGun).Update();
			
			if(ragemode)
			{
				if(rageTimer <= rageTime) 
				{
					InvListWeapons.get(CurGun).power = InvListWeapons.get(CurGun).powerbuff;
					rageTimer++;
				}
				else
				{
					for(int i=0;i<=2;i++)
					{
						InvListWeapons.get(i).power = InvListWeapons.get(i).power2;
					}
					rageTimer =0;
					ragemode = false;
				}
			}
			
			return alive;
		}
		
		private int accbuff()
		{
			return (this.ACCdefault-2);
		}
		
		void setVelX ( double x)
		{
			PlayerVel.x = x;
		}
		
		void setVelY ( double y)
		{
			PlayerVel.y = y;
		}
		
		void setVel( Vector a)
		{
			PlayerVel = a;
		}
		
		Vector getPos()
		{
			return pos;
		}
		
		
		Vector getSize()
		{
			return size;
			
		}
		
		int getHealthInicial()
		{
			return Integer.parseInt(matriz.getFromMatrizHeroi(consts.HEROI, 1));
			
		}
		
		int getHealth()
		{
			return Health;
			
		}
		
		void addHealth(int quantidade)
		{
			if(Health <= getHealthInicial() && Health > 0)
			{
			Health = Health + quantidade;
			System.out.println("HP update (add): " + quantidade);
			System.out.println("HP: " + Health);
			}
			else 
			{
				System.out.println("HP error:" + Health);
				
			}
			
		}
		
		void subHealth(int quantidade)
		{
			if( Health > 0)
			{
				if(armor > 0 && armor >= quantidade * 0.65)
				{
					armor -= quantidade * 0.65;
					Health -= 0.35*quantidade;
				}
				else if(armor < quantidade*0.65)
				{
					
					Health -= (quantidade*0.65 - armor);
					Health -= (armor*0.35);
					armor = 0;
				}
				else
					Health -= quantidade;
				
				if(armor <= 0)
				{
					armor = 0;
				}
				
				if(Health <= 0) 
				{
					System.out.println("Morri. HP = " + Health);
					Health = 0;
					alive = false;
				}
				else
				{
					System.out.println("HP update (subtract): " + quantidade);
					System.out.println("HP: " + Health);
				}
			}
			else 
			{
				System.out.println("HP error:" + Health);
				
			}

		}
		
		
		public void draw( Vector Disp)
		{
			super.draw( Disp);
		}
		
		public void addGun( Weapon w)
		{
			this.InvListWeapons.add(w);
		}
		
		public int ShootSpeed()
		{
			return InvListWeapons.get(CurGun).getSpeed();
		}
		
		public int ShootPower()
		{
			return InvListWeapons.get(CurGun).power;
		}
		
		public boolean Shoot()
		{
			return 	InvListWeapons.get(CurGun).Shoot();
		}
		
		public boolean setCurGun(int n)
		{
			
			if( n < InvListWeapons.size() && hasGun[n] == true)
			{
				CurGun = n;
				return true;
			}
			return false;
		}
		
		public void ReloadWeapon()
		{
			InvListWeapons.get(CurGun).Reload();
			
		}
		

		
}
 