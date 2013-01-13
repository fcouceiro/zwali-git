package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Mainmenu
{

	boolean start;
	int time = 0;
	int timer = 30;
	
	Vector btnPlay = new Vector(300,0);
	Vector btnHowtoplay = new Vector(70,-20);
	Vector btnAbout = new Vector(530,-20);
	Vector mpos = new Vector(0,0);
	SpriteBatch batch;

	public Mainmenu(SpriteBatch batch)
	{
		this.batch = batch;
		this.start = false;
	}
	
	public int update(SpriteBatch batch)
	{
		mpos.x = Gdx.input.getX();
		mpos.y = Gdx.input.getY();
		
		//if(Mouse.isButtonDown(0))
		if(Gdx.input.justTouched())
		{
			if(!start && mpos.x > (this.btnPlay.x + 20) && mpos.x < (this.btnPlay.x + 177) && mpos.y > (this.btnPlay.y + 434) && mpos.y < (this.btnPlay.y + 470))
			{
				//start = true;
				//sound.stopAll();
				//sound.playOnce(sound.startsound);
				this.start = true;
			}
			else if(!start && mpos.x > (this.btnHowtoplay.x + 18) && mpos.x < (this.btnHowtoplay.x + 178) && mpos.y > (this.btnHowtoplay.y + 474) && mpos.y < (this.btnHowtoplay.y + 512))
			{
				
				return 5;
			}
			else if(mpos.x > (this.btnAbout.x + 18) && mpos.x < (this.btnAbout.x + 178) && mpos.y > (this.btnAbout.y + 474) && mpos.y < (this.btnAbout.y + 512))
			{
				if(time >= timer)
				{
				time = 0;
				java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		            try {
		                java.net.URI uri = new java.net.URI( "http://www.facebook.com/pages/Lower-Scientist-Games/335430736543423");
		                desktop.browse( uri );
		            }
		            catch ( Exception e ) {
		                System.err.println( e.getMessage() );
		                }
				}
			}
			else if(start && mpos.x < 250)
			{
				start = false;
			}
			else if(start && mpos.x > 550)
			{
				start = false;
			}
			else if(start && mpos.y < 330)
			{
				start = false;
			}
			else if(start && mpos.x > 325 && mpos.x < 475)
			{
				if(mpos.y > 432 && mpos.y < 444) return 1;
				if(mpos.y > 450 && mpos.y < 462) return 2;
				if(mpos.y > 470 && mpos.y < 482) return 3;
				if(mpos.y > 498 && mpos.y < 522) return 4;
				
			}
			
		}
		else
			time++;
		
		this.draw();
		this.drawBtn(0);
		this.drawBtn(1);
		this.drawBtn(2);
		return 0;
	}
	
	private boolean collide(int btn)
	{
		
		
		switch(btn)
		{
		case 0:
			if(mpos.x > (this.btnPlay.x + 20) && mpos.x < (this.btnPlay.x + 177) && mpos.y > (this.btnPlay.y + 434) && mpos.y < (this.btnPlay.y + 470)) return true;
			break;
		case 1:
			if(mpos.x > (this.btnHowtoplay.x + 18) && mpos.x < (this.btnHowtoplay.x + 178) && mpos.y > (this.btnHowtoplay.y + 474) && mpos.y < (this.btnHowtoplay.y + 512)) return true;
			break;
		case 2:
			if(mpos.x > (this.btnAbout.x + 18) && mpos.x < (this.btnAbout.x + 178) && mpos.y > (this.btnAbout.y + 474) && mpos.y < (this.btnAbout.y + 512)) return true;
			break;
		}
		return false;
	}
	
	private void drawBtn(int btn)
	{
		if (!start) {
		Sprite btnImg=null;
		Vector Pos = null;
		
		switch(btn)
		{
		case 0:
			
				if (this.collide(0)) {
					btnImg = Textures.mainmenu_btnPlay_h;
				} else
					btnImg = Textures.mainmenu_btnPlay;
				Pos = this.btnPlay;
			
			
			break;
		case 1:
			if(this.collide(1))
				btnImg = Textures.mainmenu_btnHowtoPlay_h;	
			else
				btnImg = Textures.mainmenu_btnHowtoPlay;
			Pos = this.btnHowtoplay;
			break;
		case 2:
			if(this.collide(2))
				btnImg = Textures.mainmenu_btnAbout_h;	
			else
				btnImg = Textures.mainmenu_btnAbout;
			Pos = this.btnAbout;
			break;
		}
		
			btnImg.setSize(200, 200);
			btnImg.setPosition((float) Pos.x, (float) Pos.y);
			btnImg.draw(batch);

		}
		else this.drawDificuldade();
		
	}
	
	private void drawDificuldade()
	{
		Sprite imgtobind=Textures.mainmenu_dificuldade;
		if(mpos.x > 325 && mpos.x < 475)
		{
			if(mpos.y > 292 && mpos.y < 307) imgtobind = Textures.mainmenu_dificuldade_rockie;
			if(mpos.y > 310 && mpos.y < 325) imgtobind = Textures.mainmenu_dificuldade_regular;
			if(mpos.y > 328 && mpos.y < 343) imgtobind = Textures.mainmenu_dificuldade_veteran;
			if(mpos.y > 355 && mpos.y < 380) imgtobind = Textures.mainmenu_dificuldade_survival;
			
		}
		else imgtobind = Textures.mainmenu_dificuldade;
		
		imgtobind.setSize(512, 256);
		imgtobind.setPosition((float)150,(float)150);
		imgtobind.draw(batch);
	}
	
	private void draw()
	{
		Textures.mainmenuIM.setPosition(0,0);
		Textures.mainmenuIM.setSize(800,600);
		Textures.mainmenuIM.draw(batch);
	}
	
}