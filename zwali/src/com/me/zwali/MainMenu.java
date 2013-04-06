package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.UI;

public class MainMenu extends UI{

	Conceito maingame;
	public MainMenu(Conceito main)
	{
		super(Conceito.batch,main.font,true);
		maingame = main;
        
		TextureRegion bg = new TextureRegion(Textures.mainmenuIM,0,0,(int)Textures.mainmenuIM.getWidth(),(int)Textures.mainmenuIM.getHeight());
        table.setBackground(new TextureRegionDrawable(bg));
        this.popButtons();
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		
		if(!Sounds.main_s.isPlaying())
		{
			Sounds.main_s.setLooping(true);
			Sounds.main_s.play();
		}
	}

	@Override
	public void hide() {

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
		stage.dispose();
	}

	private void popButtons() {
		// TODO Auto-generated method stub
		final TextButton button1 = new TextButton("Survival", Textures.btnBlue);
		button1.setBounds(450, 180, 150, 35);
		button1.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < button1.getWidth() && x >0 && y<button1.getHeight() && y > 0){
	        		maingame.setScreen(maingame.questsScreen.generateScreen(maingame.constDump.Survival(maingame.constDump.Home())));
	        	}
	        	
	        }
		});
		table.addActor(button1);

		final TextButton button2 = new TextButton("Campaign", Textures.btnGreen);
		button2.setBounds(200, 180, 150, 35);
		button2.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < button2.getWidth() && x >0 && y<button2.getHeight() && y > 0)
	        	maingame.setScreen(maingame.questsScreen);
	        }
		});
		
		table.addActor(button2);
	}


}

