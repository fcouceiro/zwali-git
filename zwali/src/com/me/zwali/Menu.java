package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Menu implements Screen{

	private Stage stage;
	Conceito maingame;
	public Menu(Conceito main)
	{
		maingame = main;
		stage = new Stage();
        

        Table table = new Table();
        table.setFillParent(true);
        
        
        TextureRegion upRegion = new TextureRegion(Textures.buttonsRegion,0,0,219,29);
		TextureRegion downRegion = new TextureRegion(Textures.buttonsRegion,0,3*29 +1,219,29);
		BitmapFont buttonFont = main.font;

		TextButtonStyle style = new TextButtonStyle();
		style.up = new TextureRegionDrawable(upRegion);
		style.down = new TextureRegionDrawable(downRegion);
		
		style.font = buttonFont;

		LabelStyle styleLab = new LabelStyle();
		styleLab.font = main.font;
		
		Label lab = new Label("Welcome to Zwali!", styleLab);
		lab.setPosition(300, 400);
		stage.addActor(lab);
		
		TextButton button1 = new TextButton("Survival", style);
		button1.setBounds(400, 300, 150, 20);
		table.addActor(button1);

		TextButton button2 = new TextButton("Campaign", style);
		button2.setBounds(200, 300, 150, 20);
		button2.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	maingame.setScreen(maingame.questsScreen);
	        }
		});
		
		table.addActor(button2);
		stage.addActor(table);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();

	        Table.drawDebug(stage);
	       
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


}

