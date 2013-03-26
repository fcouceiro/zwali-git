package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
		font = main.font;
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		input();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Conceito.shapeRenderer.begin(ShapeType.Rectangle);

		Conceito.shapeRenderer.end();
		
		Conceito.batch.begin();
		for(QuestThumb q:quests)
		{
			Vector2 pos = q.thumbnailPos;
			Vector2 size = q.size;
			//Conceito.shapeRenderer.rect(pos.x, pos.y, size.x, size.y);
			Textures.questThumb.setPosition(pos.x, pos.y);
			Textures.questThumb.setSize(size.x, size.y);
			Textures.questThumb.draw(Conceito.batch);
			
			if(q.thumbnail != Textures.Red){
				q.thumbnail.setPosition(pos.x +10, pos.y +40);
				q.thumbnail.setSize(128, 128);
				q.thumbnail.draw(Conceito.batch);
			}
		}
		drawText();
		Conceito.batch.end();
	}

	void drawText()
	{
		for(QuestThumb q:quests)
		{
			Vector2 pos = q.thumbnailPos;
			font.draw(Conceito.batch, q.questName, pos.x+ q.txtPosRel.x, pos.y+ q.txtPosRel.y);
		}
		
	}
	
	Screen generateScreen(Cenario c)
	{
		Quest temp = new Quest(this.maingame,c.Wave1Pos,c.Wave2Pos);
		temp.backG.image = c.background;
		
		
		if(c.Objects.size() != 0)
		{
			for(Vector s:c.Objects)
			{
				temp.backG.Objects.add(new StaticObj(new Vector(s.x,s.y), new Vector(90,90),Textures.BarrelIM));
			}
		}
		
		inputProcessor = null;
		inputProcessor = new MyInputProcessor(temp);
		
		temp.Player1 = Player1;
		temp.Player1.pos.x = temp.backG.size.x / 2;
		temp.Player1.pos.y = temp.backG.size.y / 2;
		temp.difficulty = 1;
		Gdx.input.setInputProcessor(inputProcessor);
		temp.shop = this.maingame.shop;
		System.out.println("Quest created successfuly");
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
				if(q.questName == "Back" && Gdx.input.justTouched()) maingame.setScreen(maingame.mainmenu);
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
		if(Player1.Health <= 0){
		Player1.Health = Player1.MaxHp;
		Player1.alive = true;
		}
		Sounds.main_s.setLooping(true);
		Sounds.main_s.play();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Sounds.main_s.stop();
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
	Vector2 txtPosRel;
	
	public QuestThumb(Sprite t,CharSequence name, Vector2 Pos, Vector2 Size,Vector2 txtPosRel, Cenario c)
	{
		this.size = Size;
		this.thumbnail = t;
		this.questName = name;
		this.thumbnailPos = Pos;
		this.cenario = c;
		this.txtPosRel = txtPosRel;
	}
	
	boolean hit(int x, int y)
	{
		if(x >= thumbnailPos.x && x <= thumbnailPos.x + (size.x * 0.78) && y >= thumbnailPos.y && y <= thumbnailPos.y + (size.y *0.33) ){
			if(!Sounds.mhover_s.isPlaying())Sounds.mhover_s.play();
			return true;
		}
		return false;
	}
}

class Cenario
{
	Sprite background;
	List <Vector> Objects = new ArrayList<Vector>(5);
	List <Vector> UnObjects  = new ArrayList<Vector>(5);
	Vector Wave1Pos;
	Vector Wave2Pos;
	
	Cenario(Sprite bg, Vector Wave1Pos, Vector Wave2Pos)
	{
		this.background = bg;
		this.Wave1Pos = Wave1Pos;
		this.Wave2Pos = Wave2Pos;
	}
}