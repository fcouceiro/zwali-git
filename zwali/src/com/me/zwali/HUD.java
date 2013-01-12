package com.me.zwali;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD extends Entity
{
	List <Entity> enemSpotted = new ArrayList<Entity>(10);
	Vector posE;
	Sprite x;
	
	HUD(Vector pos, Vector size, Texture T) 
	{
		super(pos, size,false, T);
		this.x.setSize((float)pos.x, (float)pos.y);
		x.setOrigin(0,0);
		this.x = new Sprite(T);
	}
	
	public void AddEnem(Entity ent)
	{
		enemSpotted.add(ent);
	}
	
	public boolean Update(Vector PPOS,SpriteBatch batch)
	{
		
		for(Entity enem:enemSpotted)
		{
			Vector pos = new Vector((PPOS.x - enem.pos.x),(PPOS.y - enem.pos.y));
			this.posE = new Vector((((-1)*pos.x*200)/800)+700,(((-1)*pos.y*200)/600)+100);
			if(Math.sqrt(pos.SizeSQ()) < 400)
			{
			this.drawEnem(batch);
			
			}
		}
		
		return true;
		
	}
	
	private void drawEnem(SpriteBatch batch)
	{
		x.setSize(10,10);
		x.setPosition((float)posE.x, (float)posE.y);
		x.draw(batch);

	}
}
