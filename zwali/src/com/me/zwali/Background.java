package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Background 
{
	
	List <StaticObj> Objects = new ArrayList<StaticObj>(10); 
	List <UnStaticObj> UnObjects = new ArrayList<UnStaticObj>(10);
	Sprite image;
	Vector Display;
	Vector size;
	static Constants CON  = new Constants();

	Background( Sprite img, Vector size) 
	{
		this.image = img;
		this.image.setOrigin(0,0);
		this.image.setSize((float)size.x, (float)size.y);
		this.Display = new Vector( 624 , 724 );	
		this.size = size;
		
		
	}
	
	public void Update(Vector PPOS, Vector MPOS)
	{
		Constants consts = new Constants();
			
		Display.x = ((MPOS.x + Display.x)*0.2 + PPOS.x*0.8) - consts.WIDTH/2;
		Display.y = ((MPOS.y + Display.y)*0.2 + PPOS.y*0.8) - consts.HEIGHT/2;

		
		if( Display.x <0) {Display.x = 0;}
		else if( Display.x > size.x - consts.WIDTH)
		{
			Display.x = size.x - consts.WIDTH;
		}
	
		
		if( Display.y <0) {Display.y = 0;}
		else if( Display.y > size.y - consts.HEIGHT) 
		{
			Display.y = size.y - consts.HEIGHT;
		}
		
		

	}
	
	public void draw(SpriteBatch batch)
	{
		image.setPosition(-(float)Display.x, -(float)Display.y);
		image.draw(batch);

        for(StaticObj obj: Objects)
		{
			obj.draw(Display,batch);
		}
        
        for( UnStaticObj obj: UnObjects)
        {
        	obj.draw(Display,batch);
        }
		
	}
	
	Vector getDisp()
	{
		return Display;
	}
	
	void setDisp( Vector Disp)
	{
		this.Display = Disp;
	}
	
	void addOBJ(StaticObj obj)
	{
		Objects.add ( obj);
	}
	
	void addUnOBJ( UnStaticObj obj)
	{
		UnObjects.add(obj);
	}

}
