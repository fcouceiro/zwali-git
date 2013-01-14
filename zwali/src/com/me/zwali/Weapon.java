package com.me.zwali;

public class Weapon
{
	public int power,power2;
	public int FIRERATE;
	public int MAXCAR;
	public int ammo;
	public int ammoTotal;
	public int Type;
	public int powerbuff;
	private int speed;
	private int fireTimer;
	private int reloadTimer;
	private int RELOADTIME;
	public boolean buff;
	boolean reloading;
	boolean lethalarea;
	Vector size;
	Vector pos;
	
	public Weapon( int type, int difficulty)
	{
		this.Type = type;
		reloading = false;
		buff = false;
		switch (type)
		{
		case 0:
			powerbuff = 35;
			power = 25;
			power2 = 25;
			if(difficulty == 3)
			{
				power += 6;
				power2 += 6;
			}
			speed = 30;
			FIRERATE = 30;
			MAXCAR = 15;
			RELOADTIME = 120;
			lethalarea = false;
			ammoTotal = 150;
			this.size = new Vector(30,10);
			break;
		case 1:
			powerbuff = 13;
			power = 10;
			power2 = 10;
			if(difficulty == 3)
			{
				power += 2;
				power2 += 2;
			}
			speed = 20;
			FIRERATE = 4;
			MAXCAR = 300;
			RELOADTIME = 300;
			lethalarea = false;
			ammoTotal = 1500;
			this.size = new Vector(60,30);
			break;
		case 2:
			powerbuff = 35;
			power = 25;
			power2 = 25;
			if(difficulty == 3)
			{
				power += 4;
				power2 += 4;
			}
			speed = 25;
			FIRERATE = 30;
			MAXCAR = 12;
			RELOADTIME = 180;
			lethalarea = true;
			ammoTotal = 90;
			this.size = new Vector(60,15);
			break;
		}

		ammo = MAXCAR;
		
		
	}
	
	public boolean Shoot()
	{
		if (!buff) 
		{
			if (ammo > 0 && fireTimer >= FIRERATE && !reloading) {
				fireTimer = 0;
				ammo--;
				if (ammo == 0)
					this.Reload();
				return true;
			}

			else if (ammo == 0 && fireTimer >= FIRERATE && ammoTotal > 0) {
				this.Reload();
				return false;
			}
			
		}
		else 
		{
			return true;
		}
		return false;
	}
	
	public void Reload()
	{
				
		if( !reloading  && ammoTotal > 0)
		{
			int ammoneed = MAXCAR - ammo;
			reloading = true;
			reloadTimer = 0;
			ammo += minv( ammoneed, ammoTotal);
			ammoTotal -=ammoneed;
			if(ammoTotal < 0)
			{
				ammoTotal = 0;
			}
		}
	}
	
	public void Update()
	{
		
		if( ammo==0 && fireTimer >= FIRERATE && ammoTotal >0)
		{
			this.Reload();
		}
		
	
		if( reloading)
		{
			InvMenu.reloading = true;
			reloadTimer++;
			if( reloadTimer >= RELOADTIME)
			{
				InvMenu.reloading = false;
				reloading = false;
			}
		}
		else
			fireTimer++;
		
	}

	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public int minv(int a, int b)
	{
		return (a<b)?a:b;
	}

}


