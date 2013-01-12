package com.me.zwali;

import com.badlogic.gdx.graphics.Texture;



public class Crosshair extends Entity
{

	Crosshair(Vector pos, Vector size, Texture T) 
	{
		super(pos, size, false, T);		
	}
	
	void setPos ( Vector pos)
	{
		this.pos = pos;
	}

}
