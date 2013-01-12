package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Explosion extends Entity
{
	Textures t;
	Vector Pos;
	Vector Size;
	int expf = 0;
	static Sprite exp1,exp2,exp3;
	
	public Explosion(Vector Pos, Vector Size, Textures exp)
	{
		super(Pos, Size, false,exp.exp1);
		exp1 = ;
	}
	
	public void animate(double angle2)
	{
		this.angle = angle2;
		switch(expf)
		{
		case 0:
			expf++;
			this.image = new Sprite(t.exp1);
			break;
		case 1:
			expf++;
			this.image = t.exp2;
			break;
		case 2:
			expf++;
			this.image = t.exp3;
			break;
		case 3:
			expf=0;
			this.image = t.exp3;
			break;
		}
	}
	
}