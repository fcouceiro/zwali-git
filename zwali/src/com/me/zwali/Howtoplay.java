package com.me.zwali;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;


public class Howtoplay 
{
	private List<Texture> Pages = new ArrayList<Texture>(10);
	Texture pageIM;
	Texture btnNextIM, btnPrevIM, btnNext_hIM, btnPrev_hIM, btnmainMenuIM, btnmainMenu_hIM;
	Vector nextPos,prevPos, mainMenuPos, nextSize, prevSize, mainMenuSize;
	private int mx,my,timeclick,timerclick;
	public int curPage;
	Sound soundmanager;
	
	public void setCurPage(int n)
	{
		int pageIndex = n;
		if(pageIndex == -1) pageIndex = Pages.size() - 1;
		else if(pageIndex == Pages.size()) pageIndex = 0;
		
		if(pageIndex < Pages.size() && pageIndex >= 0)
		{
			this.curPage = pageIndex;
		}
		else System.out.println("---Erro na page index!---");
	}
	
	public Howtoplay(Textures imgsource, Sound sndmngr)
	{
		Pages.add(imgsource.page1);
		Pages.add(imgsource.page2);
		Pages.add(imgsource.page3);
		Pages.add(imgsource.page4);
		Pages.add(imgsource.page5);
		Pages.add(imgsource.page6);
		
		this.curPage = 0;
		this.timeclick = 0;
		this.timerclick = 15;
		
		this.soundmanager = sndmngr;
		
		btnNextIM = imgsource.btnNext;
		btnNext_hIM = imgsource.btnNext_h;
		btnPrevIM = imgsource.btnPrev;
		btnPrev_hIM = imgsource.btnPrev_h;
		btnmainMenuIM = imgsource.btnmainMenu;
		btnmainMenu_hIM = imgsource.btnmainMenu_h;
		
		
		nextPos = new Vector(632,500);
		nextSize = new Vector(btnNextIM.getImageWidth(),btnNextIM.getImageHeight());
		prevPos = new Vector(90,500);
		prevSize = new Vector(btnPrevIM.getImageWidth(),btnPrevIM.getImageHeight());
		mainMenuPos = new Vector(330, 520);
		mainMenuSize = new Vector(btnmainMenuIM.getImageWidth(),btnmainMenuIM.getImageHeight());
	}
	
	
	
	public boolean update()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		mx=Mouse.getX();
		my=600 - Mouse.getY();
		
		timeclick++;
		pageIM = Pages.get(curPage);
		
		this.draw();
		
		if(Mouse.isButtonDown(0))
		{
			if (this.timeclick >= this.timerclick) 
			{
				this.timeclick = 0;
				if (mx>nextPos.x && mx<(nextPos.x + nextSize.x - 50) && my>nextPos.y && my<(nextPos.y + nextSize.y - 100)) {
					this.setCurPage(curPage + 1);
				} else if (mx>prevPos.x && mx<(prevPos.x + prevSize.x-5) && my>prevPos.y && my<(prevPos.y + prevSize.y - 100)) {
					this.setCurPage(curPage - 1);
				} else if (mx>mainMenuPos.x && mx<(mainMenuPos.x + mainMenuSize.x - 73) && my>mainMenuPos.y && my<(mainMenuPos.y + mainMenuSize.y - 36))
					return false;
			}
			
		}
		else
		{
				if (mx > nextPos.x && mx < (nextPos.x + nextSize.x)
						&& my > nextPos.y && my < (nextPos.y + nextSize.y)) {
					soundmanager.playFXOnce(soundmanager.btnHover);
				} else if (mx > prevPos.x && mx < (prevPos.x + prevSize.x)
						&& my > prevPos.y && my < (prevPos.y + prevSize.y)) {
					soundmanager.playFXOnce(soundmanager.btnHover);
				}
		}
		return true;
	}
	
	
	private void drawNext()
	{
		glLoadIdentity();
		glPushMatrix();
		//btnNext
				if(mx>nextPos.x && mx<(nextPos.x + nextSize.x - 50) && my>nextPos.y && my<(nextPos.y + nextSize.y - 100)) btnNext_hIM.bind();
				else btnNextIM.bind();
				glTranslatef((float)nextPos.x,(float)nextPos.y, 0.0f);
				glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 0); // Upper-left
				glTexCoord2f(1, 0);
				glVertex2i((int)nextSize.x, 0); // Upper-right
				glTexCoord2f(1, 1);
				glVertex2i((int)nextSize.x,(int)nextSize.y); // Bottom-right
				glTexCoord2f(0, 1);
				glVertex2i(0, (int)nextSize.y);
				
				glEnd();
				glPopMatrix();
	}
	
	private void drawPrev()
	{
		glLoadIdentity();
		glPushMatrix();
		//btnPrev
				if(mx>prevPos.x && mx<(prevPos.x + prevSize.x-5) && my>prevPos.y && my<(prevPos.y + prevSize.y - 100)) btnPrev_hIM.bind();
				else btnPrevIM.bind();
				glTranslatef((float)prevPos.x,(float)prevPos.y, 0.0f);
				glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 0); // Upper-left
				glTexCoord2f(1, 0);
				glVertex2i((int)prevSize.x, 0); // Upper-right
				glTexCoord2f(1, 1);
				glVertex2i((int)prevSize.x,(int)prevSize.y); // Bottom-right
				glTexCoord2f(0, 1);
				glVertex2i(0, (int)prevSize.y);
				
				glEnd();
				glPopMatrix();
	}
	private void drawmainMenu()
	{
		glLoadIdentity();
		glPushMatrix();
		
				if(mx>mainMenuPos.x && mx<(mainMenuPos.x + mainMenuSize.x - 73) && my>mainMenuPos.y && my<(mainMenuPos.y + mainMenuSize.y - 36)) 
					btnmainMenu_hIM.bind();
				else 
					btnmainMenuIM.bind();
				glTranslatef((float)mainMenuPos.x,(float)mainMenuPos.y, 0.0f);
				glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2i(0, 0); // Upper-left
				glTexCoord2f(1, 0);
				glVertex2i((int)mainMenuSize.x, 0); // Upper-right
				glTexCoord2f(1, 1);
				glVertex2i((int)mainMenuSize.x,(int)mainMenuSize.y); // Bottom-right
				glTexCoord2f(0, 1);
				glVertex2i(0, (int)mainMenuSize.y);
				glEnd();
				glPopMatrix();
	}
	
	private void draw()
	{
		this.drawBG();
		this.drawmainMenu();
		this.drawNext();
		this.drawPrev();
		
	}
	
	private void drawBG()
	{
		glLoadIdentity();
		glPushMatrix();
		
		//Page
		pageIM.bind();
		glTranslatef(0.0f,0.0f, 0.0f);
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