package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class FlashScreen implements Screen
{
	int time;
	int timer = 0;
	
	Vector btnPlay = new Vector(300,0);
	Vector btnHowtoplay = new Vector(70,-20);
	Vector btnAbout = new Vector(530,-20);
	
	Vector mpos = new Vector(0,0);
	Quest GMl;
	Conceito MainGame;
	Player Player1;
	MyInputProcessor inputProcessor;

	boolean up = true;
	
	float alpha = 0.0f;
	Sprite image;
	Screen nextScreen;

	public FlashScreen(Conceito main, Sprite flashImage, Screen next, int timeOn)
	{
		MainGame = main;
		image = flashImage;
		nextScreen = next;
		this.time = timeOn;
	}

	
	
	private void animateBanner()
	{
		if(up)
		{
			
			if(alpha >= 0.99f)
			{
				alpha = 1.0f;
				if(timer >=time)
				{
					up = false;
				}
				else timer++;
			}
			else
			{
				alpha += 0.01f;
			}
		}
		else
		{
			alpha -= 0.01f;
			if(alpha <= 0.01f)
			{
				this.MainGame.setScreen(nextScreen);
			}
		}
		
		image.setPosition(0,0);
		image.setSize(800,600);
		image.draw(Conceito.batch,alpha);
		
		Gdx.app.log("flashScreen data", String.valueOf(alpha) + " " + up);
	}
	
	@Override
	public void render(float delta) 
	{
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) MainGame.setScreen(this.nextScreen);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Conceito.batch.begin();
		this.animateBanner();
		Conceito.batch.end();
		
		
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