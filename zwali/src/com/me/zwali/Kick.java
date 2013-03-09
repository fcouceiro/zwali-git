package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Kick extends Entity {

	double Cons = 30;
	double D = 50;
	
	Kick(Vector pos, Vector size, boolean circle, Sprite T) {
		super(pos, size, circle, T);
	}
	
	Vector Impact( Entity A)
	{
		Vector Imp = new Vector(0.0,0.0);
		if(this.Collide(A))
		{
			Imp.x = Math.cos((angle+90)*Math.PI/180) * Cons;
			Imp.y = Math.sin((angle+90)*Math.PI/180) * Cons;
		}
		return Imp;
	}
	
	void Up( Vector Ppos, double Pang)
	{
		Vector Disp = new Vector( Math.cos((Pang+90)*Math.PI/180), Math.sin((Pang+90) * Math.PI/180));
		Disp = Disp.mult(D);
		this.angle = Pang;
		this.pos = new Vector( Ppos.x + Disp.x, Ppos.y + Disp.y);
	}
	

}
