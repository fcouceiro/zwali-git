package com.me.zwali;


import java.lang.Math;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RealCross extends Crosshair {

	Sprite Side;
	Vector Sidesize;
	Vector Upsize;
	int radii;
	
	public RealCross( Vector pos, Vector size, Sprite Side)
	{
		super(pos, size, Side);
		this.Side = new Sprite(Side);
		Upsize = size;
		Sidesize = new Vector( size.y, size.x);
	}
	
	public void setOpen( Player PL, Vector Disp)
	{
		Vector dist = new Vector( this.pos.x-(PL.pos.x - Disp.x), this.pos.y-(PL.pos.y-Disp.y));
		this.radii = (int) (Math.tan(PL.accuracy*Math.PI/180)*dist.Size());
	}
	
	public void draw(SpriteBatch batch)
	{
		
		
		Side.setSize((float)Upsize.x, (float)Upsize.y);
		Side.setPosition((float)pos.x, (float)pos.y + radii);
		Side.draw(batch);
		
		

		Side.setSize((float)Upsize.x, (float)Upsize.y);
		Side.setPosition((float)(pos.x), (float)(pos.y- Upsize.y) - radii +2);
		Side.draw(batch);
		
		Side.setSize((float)Sidesize.x, (float)Sidesize.y);
		Side.setPosition((float)(pos.x + radii), (float)pos.y );
		Side.draw(batch);
		
		Side.setSize((float)Sidesize.x, (float)Sidesize.y);
		Side.setPosition((float)(pos.x- Sidesize.x)  - radii +2, (float)pos.y );
		Side.draw(batch);
		
		
		
	}
}
