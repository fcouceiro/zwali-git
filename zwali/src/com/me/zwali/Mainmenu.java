package com.me.zwali;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;


public class Mainmenu
{
	Textures imagesource;
	boolean start;
	Sound sound;
	int time = 0;
	int timer = 30;
	
	Vector btnPlay = new Vector(300,360);
	Vector btnHowtoplay = new Vector(70,380);
	Vector btnAbout = new Vector(530,380);
	Vector mpos = new Vector(0,0);
	

	public Mainmenu(Textures img, Sound s)
	{
		this.sound = s;
		this.imagesource = img;
		this.start = false;
	}
	
	public int update()
	{
		mpos.x = Mouse.getX();
		mpos.y = 600 - Mouse.getY();
		
		//if(Mouse.isButtonDown(0))
		if(Mouse.isButtonDown(0))
		{
			if(!start && mpos.x > (this.btnPlay.x + 20) && mpos.x < (this.btnPlay.x + 177) && mpos.y > (this.btnPlay.y + 30) && mpos.y < (this.btnPlay.y + 74))
			{
				//start = true;
				//sound.stopAll();
				//sound.playOnce(sound.startsound);
				this.start = true;
				
			}
			else if(!start && mpos.x > (this.btnHowtoplay.x + 18) && mpos.x < (this.btnHowtoplay.x + 178) && mpos.y > (this.btnHowtoplay.y + 38) && mpos.y < (this.btnHowtoplay.y + 76))
			{
				
				return 5;
			}
			else if(mpos.x > (this.btnAbout.x + 18) && mpos.x < (this.btnAbout.x + 178) && mpos.y > (this.btnAbout.y + 38) && mpos.y < (this.btnAbout.y + 76))
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
				sound.stopAll();
				sound.playOnce(sound.maintheme);
				start = false;
			}
			else if(start && mpos.x > 550)
			{
				sound.stopAll();
				sound.playOnce(sound.maintheme);
				start = false;
			}
			else if(start && mpos.y < 330)
			{
				sound.stopAll();
				sound.playOnce(sound.maintheme);
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
			if(mpos.x > (this.btnPlay.x + 20) && mpos.x < (this.btnPlay.x + 177) && mpos.y > (this.btnPlay.y + 30) && mpos.y < (this.btnPlay.y + 74)) return true;
			break;
		case 1:
			if(mpos.x > (this.btnHowtoplay.x + 18) && mpos.x < (this.btnHowtoplay.x + 178) && mpos.y > (this.btnHowtoplay.y + 38) && mpos.y < (this.btnHowtoplay.y + 76)) return true;
			break;
		case 2:
			if(mpos.x > (this.btnAbout.x + 18) && mpos.x < (this.btnAbout.x + 178) && mpos.y > (this.btnAbout.y + 38) && mpos.y < (this.btnAbout.y + 76)) return true;
			break;
		}
		return false;
	}
	
	private void drawBtn(int btn)
	{
		if (!start) {
		Texture btnImg=null;
		Vector Pos = null;
		
		switch(btn)
		{
		case 0:
			
				if (this.collide(0)) {
					btnImg = imagesource.mainmenu_btnPlay_h;
				} else
					btnImg = imagesource.mainmenu_btnPlay;
				Pos = this.btnPlay;
			
			
			break;
		case 1:
			if(this.collide(1))
				btnImg = imagesource.mainmenu_btnHowtoPlay_h;	
			else
				btnImg = imagesource.mainmenu_btnHowtoPlay;
			Pos = this.btnHowtoplay;
			break;
		case 2:
			if(this.collide(2))
				btnImg = imagesource.mainmenu_btnAbout_h;	
			else
				btnImg = imagesource.mainmenu_btnAbout;
			Pos = this.btnAbout;
			break;
		}
		
		
			glLoadIdentity();
			glPushMatrix();
			btnImg.bind();
			glTranslatef((float) Pos.x, (float) Pos.y, 0.0f);
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(0, 0); // Upper-left
			glTexCoord2f(1, 0);
			glVertex2i(200, 0); // Upper-right
			glTexCoord2f(1, 1);
			glVertex2i(200, 200); // Bottom-right
			glTexCoord2f(0, 1);
			glVertex2i(0, 200);
			glEnd();
			glPopMatrix();
		}
		else this.drawDificuldade();
		
	}
	
	private void drawDificuldade()
	{
		Texture imgtobind=imagesource.mainmenu_dificuldade;
		if(mpos.x > 325 && mpos.x < 475)
		{
			if(mpos.y > 432 && mpos.y < 444) imgtobind = imagesource.mainmenu_dificuldade_rockie;
			if(mpos.y > 450 && mpos.y < 462) imgtobind = imagesource.mainmenu_dificuldade_regular;
			if(mpos.y > 470 && mpos.y < 482) imgtobind = imagesource.mainmenu_dificuldade_veteran;
			if(mpos.y > 498 && mpos.y < 522) imgtobind = imagesource.mainmenu_dificuldade_survival;
			
		}
		else imgtobind = imagesource.mainmenu_dificuldade;
		
		glLoadIdentity();
		glPushMatrix();
		imgtobind.bind();
		glTranslatef((float)150,(float)330, 0.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(0, 0); // Upper-left
		glTexCoord2f(1, 0);
		glVertex2i(512, 0); // Upper-right
		glTexCoord2f(1, 1);
		glVertex2i(512,256); // Bottom-right
		glTexCoord2f(0, 1);
		glVertex2i(0, 256);
		glEnd();
		glPopMatrix();
	}
	
	private void draw()
	{
		glLoadIdentity();
		glPushMatrix();
		imagesource.mainmenuIM.bind();
		glTranslatef(-20.0f,0.0f, 0.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(0, 0); // Upper-left
		glTexCoord2f(1, 0);
		glVertex2i(800, 0); // Upper-right
		glTexCoord2f(1, 1);
		glVertex2i(800,600); // Bottom-right
		glTexCoord2f(0, 1);
		glVertex2i(0, 600);
		glEnd();
		glPopMatrix();
	}
	
}