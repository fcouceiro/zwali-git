package com.me.zwali;
import org.newdawn.slick.opengl.Texture;

public class StaticObj extends Entity 
{
	StaticObj( Vector pos, Vector size, Texture im)
	{
		super ( pos, size,false,  im);
	}
}
