package com.me.zwali;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class sangue
{
	Sprite blood;
	Vector pos;
	int time = 300;
	int timer = 0;
	int time_anim = 400;
	float alpha = 1.0f;
	
	public sangue(Sprite a, Vector pos)
	{
		this.blood = a;
		this.pos = pos;
	}
	
	void update(Background backG)
	{
		if(timer >= time)
		{
			if(timer >= time_anim);
			else{
				timer++;
				blood.setPosition((float)(pos.x - backG.Display.x), (float)(pos.y - backG.Display.y));
				blood.draw(Conceito.batch,alpha);
				alpha -= 0.01f;
			}
		}
		else
		{
			timer++;
			blood.setPosition((float)(pos.x - backG.Display.x), (float)(pos.y - backG.Display.y));
			blood.draw(Conceito.batch);
			
		}
	}
}
