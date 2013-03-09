package com.me.zwali;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class InvMenu
{
	Vector Pos;
	Vector Size;
	static boolean reloading = false;
	
	public CharSequence string;
	List <Sprite> topImages = new ArrayList<Sprite>(10);
	SpriteBatch batch;
	
	public InvMenu(SpriteBatch batch) 
	{
		this.Pos = new Vector(0,-740);
		this.Size = new Vector(800,800);
		topImages.add(Textures.barIM);
		topImages.add(Textures.bar_ammo);
		topImages.add(Textures.bar_medkit);
		topImages.add(Textures.bar_outofammo);
		topImages.add(Textures.bar_stroke);
		topImages.add(Textures.bar_buildmodeon);
		topImages.add(Textures.bar_buildmodeoff);
		this.batch = batch;
	}
	
	public void update(boolean buildMode)
	{
		this.drawBAR();
		if(InvMenu.reloading)
		this.drawOutofammo();
		this.drawWp();
		this.drawStroke();
	}

	
	private void drawWp()
	{
		topImages.get(1).setPosition((float)117, (float)-32);
		topImages.get(1).setSize(64,64);
		topImages.get(1).draw(batch);
	}
	
	private void drawStroke()
	{
		topImages.get(4).setPosition((float)Pos.x, (float)Pos.y);
		topImages.get(4).setSize((float)Size.x,(float)Size.y);
		topImages.get(4).draw(batch);
	}
	
	private void drawOutofammo()
	{
		topImages.get(3).setPosition((float)10, (float)0);
		topImages.get(3).setSize((float)90,(float)50);
		topImages.get(3).draw(batch);
	}
	
	private void drawBAR()
	{
		topImages.get(0).setPosition((float)Pos.x, (float)Pos.y);
		topImages.get(0).setSize((int)Size.x, (int)Size.y);
		topImages.get(0).draw(batch);
	}

}
