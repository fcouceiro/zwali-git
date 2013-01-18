package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Mainmenu implements Screen
{

	boolean start = false;
	boolean prepared = false;
	int time = 0;
	int timer = 30;
	
	Vector btnPlay = new Vector(300,0);
	Vector btnHowtoplay = new Vector(70,-20);
	Vector btnAbout = new Vector(530,-20);
	Vector mpos = new Vector(0,0);
	SpriteBatch batch = Conceito.batch;
	GameLoop GMl;
	Conceito MainGame;
	Player Player1;
	MyInputProcessor inputProcessor;
	
	public Mainmenu(Conceito main)
	{
		MainGame = main;
	}
	
	public int update(SpriteBatch batch)
	{
		prepareGameLoop();
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
			else if(start && mpos.y < 291)
			{
				start = false;
			}
			else if(start && mpos.y > 381)
			{
				start = false;
			}
			else if(start && mpos.x > 325 && mpos.x < 475)
			{
				if(mpos.y > 292 && mpos.y < 307) return 1;
				if(mpos.y > 310 && mpos.y < 325) return 2;
				if(mpos.y > 328 && mpos.y < 343) return 3;
				if(mpos.y > 355 && mpos.y < 380) return 4;
				
			}
			
		}
		else
			time++;
		
	
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

	private void prepareGameLoop()
	{
		if(start)
		{
			if(!prepared)
			{
				GMl = null;
				inputProcessor = null;
				GMl = new GameLoop(MainGame);
				inputProcessor = new MyInputProcessor(GMl);
				Player1 = GMl.Player1;
				Player1.money = 9000;
				Gdx.input.setInputProcessor(inputProcessor);
				System.out.println("GameLoop created successfuly");
				prepared = true;
			}
		}
	}
	
	@Override
	public void render(float delta) 
	{
		// TODO Auto-generated method stub
	
		batch.setProjectionMatrix(Conceito.camera.combined);
		Conceito.batch.begin();
		this.draw();
		this.drawBtn(0);
		this.drawBtn(1);
		this.drawBtn(2);
		if(update(batch) == 0)
		 {

		 }
		 else if(update(batch) == 1) 
		 {
			 
			GMl.difficulty = 1;
			this.MainGame.setScreen(GMl);
		 }
		 else if(update(batch) == 2) 
		 {
			
			 GMl.difficulty = 2;
			 
				this.MainGame.setScreen(GMl);
		 }
		 else if(update(batch) == 3) 
		 {
			
			 GMl.difficulty = 3;
			 this.MainGame.setScreen(GMl);
		 }
		 else if(update(batch) == 4) 
		 { 
			 GMl.difficulty = 2;//Se for survival tem a dificuldade de regular. (Não esquecer de rondas infinitas)
			 GMl.survival = true;
			 this.MainGame.setScreen(GMl);
		 }
		 
		 else if(update(batch) == 5) 
		 {
			 this.MainGame.setScreen(MainGame.howtoplaymenu);
		 }
		 
		Conceito.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		this.prepareGameLoop();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		start = false;
		prepared = false;
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