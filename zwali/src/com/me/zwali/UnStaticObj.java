package com.me.zwali;
import org.newdawn.slick.opengl.Texture;


public class UnStaticObj extends StaticObj
{
	int Health;
	boolean alive;

	
	public UnStaticObj ( Vector pos, Vector size, Texture im, int Health)
	{
		super ( pos, size, im);
		this.Health = Health;
		alive = true;
	}
	
	public void DecreaseHealth( int p)
	{
		System.out.println(Health);
		Health-= p;
		if( Health <= 0)
		{
			alive = false;
		}
	}
	
	public void draw(Vector Disp)
	{
		if( alive)
		{
			super.draw(Disp);
		}
	}
	
	

}
