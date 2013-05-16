package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Explosion extends Entity{
	
	int power;
	boolean Alive;
	int timer;
	int lifetime;
	
	public Explosion (Vector pos, Vector size, Sprite T)
	{
		super(pos, size, true, T);	
	}
	
	public void SetExp (int power, int lifetime)
	{
		this.power = power;
		this.lifetime = lifetime;
		this.timer=0;
		this.Alive = true;
	}
	
	void UP()
	{
		this.timer++;
		if(this.timer > this.lifetime)
		{
			this.Alive = false;
		}
	}
	
	Vector Impact( Entity A)
	{
		Vector D = new Vector(0.0,0.0);
		if(this.Collide(A))
		{
			D = new Vector(A.pos.x - this.pos.x, A.pos.y - this.pos.y);
			D.normalize();
			D = D.mult(power);
		}
		return D;
	}
}
