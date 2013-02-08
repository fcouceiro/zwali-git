package com.me.zwali;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class UnStaticObj extends StaticObj
{
	int Health;
	boolean alive;

	
	public UnStaticObj ( Vector pos, Vector size, Sprite im, int Health)
	{
		super (pos, size, im);
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
	
	public void draw(Vector Disp, SpriteBatch batch)
	{
		if( alive)
		{
			super.draw(Disp, batch);
		}
	}
	
	

}
