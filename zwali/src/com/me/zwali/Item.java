package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item 
{
	String Name;
	String Info;
	String Style;
	Vector SizeI;
	Vector Size;
	Vector PosI;
	Vector Pos;
	Sprite img;
	
	boolean Drag;
	boolean canDrop;
	boolean equiped;
	int Type;
	int price;
	int ammoType;
	double angle;
	
	boolean alive;
	
	public Item (int type, Player pl)
	{
		this.Type = type;
		this.Size = new Vector(25,25);
		this.Pos = new Vector(0,0);
		switch( type)
		{
		case 0: //Medic kit
			this.price = 30;
			this.img = Textures.Medkit;
			this.Name = "Medic Kit";
			//this.ammoType = 0;
			break;
		case 1: //ammo
			this.price = 20;
			this.Name = "Ammo";
			this.img = Textures.Ammo; 
			//this.ammoType = 1;
			break;
		case 2: //resources
			this.price = 40;
			this.Name = "Resources";
			this.img = Textures.Resources;
			break;
			//this.ammoType = 2;
		case 3://armor
			this.price = 30; 
			this.Name = "Armor";
			this.img = Textures.Armor;
			break;
		case 4://power
			this.Name = "Power";
			this.img = Textures.Power;
			break;	
		case 5://Accuracy
			this.Name = "Accuracy";
			this.img = Textures.Accuracy;
			break;	
		case 6: //Random buff
			this.price = 60;
			this.Name = "Random buff";
			this.img = Textures.rdmBuff;
			break;	
		case 7: //Shotgun
			this.price = 1500;
			this.Name = "Shotgun";
			//this.img = (Sprite) Textures.shotgun;
			break;	
		case 8: //Minigun
			this.price = 7500;
			this.Name = "Minigun";
			//this.img = (Sprite) Textures.minigun;
			break;	
		}
		this.alive = true;
		
		
	}
	

	
	public void setPos(Vector pos)
	{
		this.Pos = pos;
	}
	
	public boolean getAlive()
	{
		return this.alive;
	}
	
	
	
	
	public void draw(Vector disp, SpriteBatch batch)
	{
		img.setPosition((float)(Pos.x - disp.x), (float)(Pos.y - disp.y));
		img.setSize(32,32);
		img.draw(batch);
		
	}
	
	boolean Collide (Player player1)
	{
		Vector posk = player1.getPos();
		Vector sizek = player1.getSize();
		
		boolean a = ( this.Pos.x - this.Size.x/2 >= posk.x - sizek.x/2		&& this.Pos.x - this.Size.x/2 <= posk.x + sizek.x/2 	&& this.Pos.y - this.Size.y/2 >=posk.y-sizek.y/2	&& this.Pos.y - this.Size.y/2 <= posk.y+sizek.y/2);
		boolean b = ( this.Pos.x + this.Size.x/2 >= posk.x - sizek.x/2		&& this.Pos.x + this.Size.x/2 <= posk.x + sizek.x/2		&& this.Pos.y - this.Size.y/2 >=posk.y-sizek.y/2 	&& this.Pos.y - this.Size.y/2 <= posk.y+sizek.y/2);
		boolean c = ( this.Pos.x + this.Size.x/2 >= posk.x - sizek.x/2		&& this.Pos.x + this.Size.x/2 <= posk.x + sizek.x/2		&& this.Pos.y + this.Size.y/2 >=posk.y-sizek.y/2	&& this.Pos.y + this.Size.y/2 <= posk.y+sizek.y/2);
		boolean d = ( this.Pos.x - this.Size.x/2 >= posk.x - sizek.x/2		&& this.Pos.x - this.Size.x/2 <= posk.x + sizek.x/2		&& this.Pos.y + this.Size.y/2 >=posk.y-sizek.y/2	&& this.Pos.y + this.Size.y/2 <= posk.y+sizek.y/2);
					
		return (a||b||c||d);
	}
}
