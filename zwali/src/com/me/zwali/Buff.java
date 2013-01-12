package com.me.zwali;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Buff 
{
	Sprite buffIM;
	int type, timebuff;
	int timerbuff = 3600;
	boolean activeBuff;
	
	public Buff(Texture t,int type)
	{
		this.activeBuff = false;
		this.buffIM = new Sprite(t);
		this.buffIM.setOrigin(0,0);
		this.buffIM.setSize((float)t.getWidth(), (float)t.getHeight());
		this.timebuff = 0;
		timebuff=0;
		this.type = type;
	}
	
	public boolean active()
	{
		if(timebuff >= timerbuff)
		{
			timebuff = 0;
			return false;
		}
		else
		{
			timebuff++;
			return true;
		}
	}
}
