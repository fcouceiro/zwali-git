package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Crosshair extends Entity
{

	Crosshair(Vector pos, Vector size, Sprite T) 
	{
		super(pos, size, false, T);		
	}
	
	void setPos ( Vector pos)
	{
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}

}
