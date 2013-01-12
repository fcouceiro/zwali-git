package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Explosion extends Entity
{
	
	Vector Pos;
	Vector Size;
	int expf = 0;
	static Sprite exp1,exp2,exp3;
	
	public Explosion(Vector Pos, Vector Size)
	{
		super(Pos, Size, false,Textures.exp1);
		exp1 = Textures.expl1;
		exp2 = Textures.expl2;
		exp3 = Textures.expl3;
	}
	
	public void animate(double angle2)
	{
		this.angle = angle2;
		switch(expf)
		{
		case 0:
			expf++;
			this.image = exp1;
			break;
		case 1:
			expf++;
			this.image = exp2;
			break;
		case 2:
			expf++;
			this.image = exp3;
			break;
		case 3:
			expf=0;
			this.image = exp3;
			break;
		}
	}
	
}
