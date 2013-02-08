package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animacao {

	int time;
	int frames;
	List <Sprite> animSprites = new ArrayList<Sprite>(5);
	
	int counter=0;
	int timer=0;
	int timer_max;
	boolean done = false;
	
	public Animacao(int time,int frames,Sprite anim, Vector regionSize, Vector imgSize)
	{
		this.time = time * 60;
		this.frames = frames;
		timer_max = this.time / frames;
		
		
			for(int i=0;i<frames;i++)
			{
			TextureRegion tr = new TextureRegion(anim,(int)(regionSize.x)*i,0,(int)(regionSize.x),(int)(regionSize.y));
			Sprite a = new Sprite(tr);
			a.setSize((float)imgSize.x, (float)imgSize.y);
			animSprites.add(a);
			a = null;
			}
			System.out.println("Anim created!");
		
	}
	
	public Sprite getIm()
	{
		if(timer >= timer_max)
		{
			counter++;
			timer=0;
			if((counter) == frames){
				counter = 0;
				
			}
			else if(counter == (frames -1)) done = true;
			else done = false;
		}
		timer++;
		return animSprites.get(counter);
	}
	
	
}
