package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;


public class StaticObj extends Entity 
{
	StaticObj( Vector pos, Vector size, double angle, Sprite im)
	{
		super ( pos, size,false,  im);
		this.angle = angle;
	}
}
