package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ScreenChooser implements Screen{

	Conceito maingame;
	MyInputProcessor inputProcessor;
	public static Player Player1;
	Quest curQuest;
	List<QuestThumb> quests = new ArrayList<QuestThumb>(5);
	BitmapFont font;
	
	public ScreenChooser(Conceito main)
	{
		maingame = main;
		font = new BitmapFont(Gdx.files.internal("res/fonts/arial.fnt"),
		         Gdx.files.internal("res/fonts/arial.png"), false);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		input();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Conceito.shapeRenderer.begin(ShapeType.Rectangle);
		
		for(QuestThumb q:quests)
		{
			Vector2 pos = q.thumbnailPos;
			Vector2 size = q.size;
			Conceito.shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
		}
		
		
		Conceito.shapeRenderer.end();
		
		Conceito.batch.begin();
		drawText();
		Conceito.batch.end();
	}

	void drawText()
	{
		for(QuestThumb q:quests)
		{
			
			Vector2 pos = q.thumbnailPos;
			font.draw(Conceito.batch, q.questName, pos.x, pos.y - 20);
		}
		
	}
	
	Screen generateScreen(Cenario c)
	{
		Quest temp = new Quest(this.maingame);
		temp.backG.image = c.background;
		
		if(c.Objects != null)
		{
			for(StaticObj s:c.Objects)
			{
				s.image.setSize(80, 80);
				temp.backG.Objects.add(s);
			}
		}
		
		inputProcessor = null;
		inputProcessor = new MyInputProcessor(temp);
		
		temp.Player1 = Player1;
		temp.Player1.pos.x = 1024;
		temp.Player1.pos.y = 1024;
		
		Gdx.input.setInputProcessor(inputProcessor);
		
		System.out.println("Quest created successfuly");
		Gdx.app.log("cenario size barril", String.valueOf(c.Objects.get(0).image.getWidth()));

		return temp;
	}
	
	void input()
	{
		int mx = Gdx.input.getX();
		int my = 600 - Gdx.input.getY();
		
		for(QuestThumb q:quests)
		{
			if(q.hit(mx, my))
			{
				if(q.questName == "MainMenu" && Gdx.input.justTouched()) maingame.setScreen(maingame.mainmenu);
				else if(q.questName == "Shop" && Gdx.input.justTouched()) maingame.setScreen(maingame.shop);
				else if(Gdx.input.justTouched()) maingame.setScreen(this.generateScreen(q.cenario));
				
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

class QuestThumb
{
	boolean completed;
	Sprite thumbnail;
	CharSequence questName;
	Vector2 thumbnailPos;
	Vector2 size;
	Cenario cenario;
	
	public QuestThumb(Sprite t,CharSequence name, Vector2 Pos, Vector2 Size, Cenario c)
	{
		this.size = Size;
		this.thumbnail = t;
		this.questName = name;
		this.thumbnailPos = Pos;
		this.cenario = c;
	}
	
	boolean hit(int x, int y)
	{
		if(x >= thumbnailPos.x && x <= thumbnailPos.x + size.x && y >= thumbnailPos.y && y <= thumbnailPos.y + size.y  )
		return true;
		
		return false;
	}
}

class Cenario
{
	Sprite background;
	List <StaticObj> Objects;
	List <UnStaticObj> UnObjects;
	
	Cenario(Sprite bg)
	{
		this.background = bg;
	}
}