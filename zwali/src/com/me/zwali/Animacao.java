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
	
	public Animacao(int time,int frames,Sprite anim)
	{
		this.time = time * 60;
		this.frames = frames;
		timer_max = this.time / frames;
		
		
			for(int i=0;i<frames;i++)
			{
			TextureRegion tr = new TextureRegion(anim,256*i,0,256,128);
			Sprite a = new Sprite(tr);
			a.setSize(90, 90);
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
			if((counter) == frames) counter = 0;
		}
		timer++;
		return animSprites.get(counter);
	}
}
