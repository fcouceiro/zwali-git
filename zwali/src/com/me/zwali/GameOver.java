package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class GameOver extends UI{

	Conceito maingame;
	
	public GameOver(Conceito main) {
		super(Conceito.batch, main.font, false);
		// TODO Auto-generated constructor stub
		maingame = main;
		TextureRegion bg = new TextureRegion(Textures.game_over_bg,0,0,(int)Textures.game_over_bg.getWidth(),(int)Textures.game_over_bg.getHeight());
        table.setBackground(new TextureRegionDrawable(bg));
		
		this.pop_buttons();
	}
	
	private void pop_buttons()
	{
		int cx = Gdx.graphics.getWidth()/2;
		int cy = Gdx.graphics.getHeight()/2;
		
		Label lGameover = new Label("You suck!", StylesManager.skin);
		
		lGameover.setPosition(cx-lGameover.getWidth()/2, cy);
		table.addActor(lGameover);
		
		final TextButton retry = new TextButton("Retry",StylesManager.skin);
		final TextButton menu = new TextButton("Menu",StylesManager.skin);
		final TextButton shop = new TextButton("Shop",StylesManager.btnGreen);
		final TextButton buy = new TextButton("Buy this mission",StylesManager.btnLock);
		
		buy.setBounds(cx + 10, cy - 50, 185, 44);
		buy.getLabel().setPosition(0, 0);
		table.addActor(buy);
		
		retry.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < retry.getWidth() && x >0 && y<retry.getHeight() && y > 0){
	        		maingame.questsScreen.reset_player();
	        		maingame.setScreen(maingame.questsScreen.generateScreen(maingame.questsScreen.cur_cenario));
	        	}
	        
	        }
		});
		retry.setBounds(cx - 190, cy - 50, 180, 42);
		table.addActor(retry);
		
		menu.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < menu.getWidth() && x >0 && y<menu.getHeight() && y > 0){
	        		maingame.setScreen(maingame.questsScreen);
	        	}
	        
	        }
		});
		menu.setBounds(cx - 160, cy - 135, 150, 35);
		table.addActor(menu);
		
		shop.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < shop.getWidth() && x >0 && y<shop.getHeight() && y > 0){
	        		maingame.setScreen(maingame.shop);
	        	}
	        
	        }
		});
		shop.setBounds(cx +10, cy - 135, 150, 35);
		table.addActor(shop);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Sounds.zo_ahaha.play();
	}
}
