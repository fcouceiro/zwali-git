package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;


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
	
	public Item (int type, Textures t, Player pl)
	{
		this.Type = type;
		this.Size = new Vector(25,25);
		this.Pos = new Vector(0,0);
		switch( type)
		{
		case 0: //Medic kit
			this.price = 30;
			this.img = new Sprite(t.Medkit);
			this.Name = "Medic Kit";
			//this.ammoType = 0;
			break;
		case 1: //ammo
			this.price = 20;
			this.Name = "Ammo";
			this.img = new Sprite(t.Ammo); 
			//this.ammoType = 1;
			break;
		case 2: //resources
			this.price = 40;
			this.Name = "Resources";
			this.img = new Sprite(t.Resources);
			break;
			//this.ammoType = 2;
		case 3://armor
			this.price = 30; 
			this.Name = "Armor";
			this.img = new Sprite(t.Armor);
			break;
		case 4://power
			this.Name = "Power";
			this.img = new Sprite(t.Power);
			break;	
		case 5://Accuracy
			this.Name = "Accuracy";
			this.img = new Sprite(t.Accuracy);
			break;	
		case 6: //Random buff
			this.price = 60;
			this.Name = "Random buff";
			this.img = new Sprite(t.rdmBuff);
			break;	
		case 7: //Shotgun
			this.price = 1500;
			this.Name = "Shotgun";
			this.img = new Sprite(t.shotgun);
			break;	
		case 8: //Minigun
			this.price = 7500;
			this.Name = "Minigun";
			this.img = new Sprite(t.minigun);
			break;	
		}
		this.alive = true;
		
		
	}
	
	/*public Item (Item a)
	{
		this.Pos = a.Pos;
		this.Size = a.Size;
		this.PosI = a.PosI;
		this.SizeI = a.SizeI ;
		this.Name = a.Name;
		this.Style = a.Style;
		this.img = a.img;
		this.Drag = a.Drag;
		this.canDrop = a.canDrop;
		this.Type = a.Type;
		this.Info = a.Info;
	}
	
	public Item(String name, String style, Texture im, int type)
	{
		this.Pos = new Vector(0,0);
		this.Size = new Vector(0,0);
		this.PosI = new Vector(0,0);
		this.SizeI = new Vector(32,32);
		this.Name = name;
		this.Style = style;
		this.img = im;
		this.Drag = false;
		this.canDrop = false;
		this.Type = type;
		this.Info = "info info";
		
	}*/
	
	public void setPos(Vector pos)
	{
		this.Pos = pos;
	}
	
	public boolean getAlive()
	{
		return this.alive;
	}
	
	
	
	/*public void draw(int State)
	{
			if (State == 3) 
			{
				glLoadIdentity();
				glPushMatrix();
				img.bind();
				glTranslatef((float) PosI.x, (float) PosI.y, 0.0f);
				glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 0); // Upper-left
				glTexCoord2f(1, 0);
				glVertex2i((int) (SizeI.x), 0); // Upper-right
				glTexCoord2f(1, 1);
				glVertex2i((int) (SizeI.x), (int) (SizeI.y)); // Bottom-right
				glTexCoord2f(0, 1);
				glVertex2i(0, (int) (SizeI.y));
				glEnd();
				glPopMatrix();
			}
			else if(State == 0)
			{
				glLoadIdentity();
				glPushMatrix();
				img.bind();
				glTranslatef((float) Pos.x, (float) Pos.y, 0.0f);
				glRotatef((float) angle, 0.0f, 0.0f, 1.0f);
				glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 0); // Upper-left
				glTexCoord2f(1, 0);
				glVertex2i((int) (Size.x), 0); // Upper-right
				glTexCoord2f(1, 1);
				glVertex2i((int) (Size.x), (int) (Size.y)); // Bottom-right
				glTexCoord2f(0, 1);
				glVertex2i(0, (int) (Size.y));
				glEnd();
				glPopMatrix();
			}
			
	}*/
	
	public void draw(Vector disp)
	{
		glLoadIdentity();
		glPushMatrix();
		img.bind();
		glTranslatef((float)(Pos.x - disp.x), (float)(Pos.y - disp.y), 0.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(0, 0); // Upper-left
		glTexCoord2f(1, 0);
		glVertex2i(32, 0); // Upper-right
		glTexCoord2f(1, 1);
		glVertex2i(32,32); // Bottom-right
		glTexCoord2f(0, 1);
		glVertex2i(0, 32);
		glEnd();
		glPopMatrix();
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
