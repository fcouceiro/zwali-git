package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Howtoplay 
{
	private List<Sprite> Pages = new ArrayList<Sprite>(10);
	Sprite pageIM;
	Sprite btnNextIM, btnPrevIM, btnNext_hIM, btnPrev_hIM, btnmainMenuIM, btnmainMenu_hIM;
	Vector nextPos,prevPos, mainMenuPos, nextSize, prevSize, mainMenuSize;
	private int mx,my,timeclick,timerclick;
	public int curPage;
	SpriteBatch batch;
	
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
	
	public Howtoplay(SpriteBatch batch)
	{
		this.batch = batch;
		Pages.add(Textures.page1);
		Pages.add(Textures.page2);
		Pages.add(Textures.page3);
		Pages.add(Textures.page4);
		Pages.add(Textures.page5);
		Pages.add(Textures.page6);
		
		this.curPage = 0;
		this.timeclick = 0;
		this.timerclick = 15;
		
		btnNextIM = Textures.btnNext;
		btnNext_hIM = Textures.btnNext_h;
		btnPrevIM = Textures.btnPrev;
		btnPrev_hIM = Textures.btnPrev_h;
		btnmainMenuIM = Textures.btnmainMenu;
		btnmainMenu_hIM = Textures.btnmainMenu_h;
		
		
		nextPos = new Vector(632,500);
		nextSize = new Vector(btnNextIM.getWidth(),btnNextIM.getHeight());
		prevPos = new Vector(90,500);
		prevSize = new Vector(btnPrevIM.getWidth(),btnPrevIM.getHeight());
		mainMenuPos = new Vector(330, 520);
		mainMenuSize = new Vector(btnmainMenuIM.getWidth(),btnmainMenuIM.getHeight());
	}
	
	
	
	public boolean update()
	{
		
		mx=Gdx.input.getX();
		my=600 - Gdx.input.getY();
		
		timeclick++;
		pageIM = Pages.get(curPage);
		
		this.draw();
		
		if(Gdx.input.isTouched())
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
		return true;
	}
	
	
	private void drawNext()
	{

				Sprite a = null;
				if(mx>nextPos.x && mx<(nextPos.x + nextSize.x - 50) && my>nextPos.y && my<(nextPos.y + nextSize.y - 100)) a = btnNext_hIM;
				else a = btnNextIM;
				a.setPosition((float)nextPos.x,(float)nextPos.y);
				a.setSize((float)nextSize.x,(float)nextSize.y);
				a.draw(batch);
				
	}
	
	private void drawPrev()
	{

				Sprite a = null;
				if(mx>prevPos.x && mx<(prevPos.x + prevSize.x-5) && my>prevPos.y && my<(prevPos.y + prevSize.y - 100)) a=btnPrev_hIM;
				else a=btnPrevIM;
				a.setPosition((float)prevPos.x,(float)prevPos.y);
				a.setSize((int)prevSize.x,(int)prevSize.y);
				a.draw(batch);
				
	}
	private void drawmainMenu()
	{
				Sprite a = null;
				if(mx>mainMenuPos.x && mx<(mainMenuPos.x + mainMenuSize.x - 73) && my>mainMenuPos.y && my<(mainMenuPos.y + mainMenuSize.y - 36)) 
					a = btnmainMenu_hIM;
				else 
					a = btnmainMenuIM;
				a.setPosition((float)mainMenuPos.x,(float)mainMenuPos.y);
				a.setSize((int)mainMenuSize.x,(int)mainMenuSize.y);
				a.draw(batch);
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
		//Page
		pageIM.setPosition(0,0);
		pageIM.setSize(800,600);
		pageIM.draw(batch);

	}
	
	
}
