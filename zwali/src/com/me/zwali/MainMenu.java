package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenu implements Screen{

	private Stage stage;
	private Table table;
	Conceito maingame;
	public MainMenu(Conceito main)
	{
		maingame = main;
		stage = new Stage();
        
		TextureRegion bg = new TextureRegion(Textures.mainmenuIM,0,0,(int)Textures.mainmenuIM.getWidth(),(int)Textures.mainmenuIM.getHeight());
		
		table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(bg));
        
        
		
		TextButton button1 = new TextButton("Survival", Textures.btnBlue);
		button1.setBounds(450, 180, 150, 35);
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
		stage.addActor(table);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();

	       // Table.drawDebug(stage);
	       
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


}

