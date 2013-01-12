package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Builder 
{
	Sprite builder_bar_btm;
	Sprite builder_bar;
	Textures T;
	
	public Builder(Textures t)
	{
		builder_bar_btm = new Sprite(t.builder_bar_btm);
		builder_bar_btm.setOrigin(0,0);
		builder_bar_btm.setSize(80.0f,60.0f);
		
		builder_bar = new Sprite(t.builder_bar);
		builder_bar.setOrigin(0,0);
		builder_bar.setSize(80.0f,60.0f);
		
		T=t;
	}
	
	public boolean addItem(Background backG, Vector Pos)
	{
		Vector disp = backG.getDisp();
		backG.addUnOBJ( new UnStaticObj( new Vector(Pos.x + disp.x, Pos.y + disp.y), new Vector( 80, 80), T.BarrelUnIM , 800));
		
		return true;
	}
	
	public void drawbar(Vector pos, float contador, float contador_max,SpriteBatch batch)
	{
		
		builder_bar_btm.setPosition((float)pos.x -45 , (float)pos.y -55);
		builder_bar_btm.draw(batch);
		
				
		float auxiliar = ((contador/contador_max) * 80);
		
		builder_bar.setSize(auxiliar, 60);
		builder_bar.setPosition((float)pos.x -45 , (float)pos.y -55);
		builder_bar.draw(batch);

	}
	
}
