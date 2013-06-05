package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.StylesManager;
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
		
		table.getColor().a = 0;
		table.addAction(Actions.fadeIn(0.5f));
		
		if(!Sounds.main_s.isPlaying()&& maingame.sound)
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
		final TextButton btnOpt= new TextButton("Options", StylesManager.btnGray);
		final TextButton btnCloud=new TextButton("Cloud", StylesManager.btnGray);
		final TextButton btnPlay= new TextButton("Play", StylesManager.btnBlue);
		final TextButton btnSur = new TextButton("Survival", StylesManager.btnGreen);
		final TextButton btnCamp = new TextButton("Campaign", StylesManager.btnGreen);
		final Image imgCloud = new Image(Textures.cloud);
		imgCloud.setPosition(570, -140);
		
		//game modes btns
		
		btnSur.setBounds(250, 180, 150, 35);
		btnSur.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < btnSur.getWidth() && x >0 && y<btnSur.getHeight() && y > 0){
	        		maingame.setScreen(maingame.questsScreen.generateScreen(maingame.constDump.Survival(maingame.constDump.Home())));
	        		btnSur.addAction(Actions.fadeOut(1));
	        		btnCamp.addAction(Actions.fadeOut(1));
	        		btnPlay.setVisible(true);
	        		btnPlay.addAction(Actions.fadeIn(1));
	        	}
	      
	        }
		});
		btnSur.getColor().a = 0;
		table.addActor(btnSur);

		
		btnCamp.setBounds(410, 180, 150, 35);
		btnCamp.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < btnCamp.getWidth() && x >0 && y<btnCamp.getHeight() && y > 0){
	        	maingame.setScreen(maingame.questsScreen);
	 
        		btnSur.addAction(Actions.fadeOut(1));
        		btnCamp.addAction(Actions.fadeOut(1));
        		btnPlay.setVisible(true);
        		btnPlay.addAction(Actions.fadeIn(1));
	        	}
	        
	        }
		});
		btnCamp.getColor().a = 0;
		table.addActor(btnCamp);
		
		
	
		
		//other btns
		btnOpt.setBounds(330, 130, 150, 35);
		btnOpt.getColor().a = 0.8f;
		btnOpt.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < btnOpt.getWidth() && x >0 && y<btnOpt.getHeight() && y > 0)
	        	{
	        		btnSur.addAction(Actions.fadeOut(1));
	        		btnCamp.addAction(Actions.fadeOut(1));
	        		btnPlay.setVisible(true);
	        		btnPlay.addAction(Actions.fadeIn(1));
	        	}
	        }
		});
		
		table.addActor(btnOpt);
		
		
		btnCloud.setBounds(650, 20, 90, 30);
		btnCloud.getColor().a = 0.7f;
		btnCloud.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < btnCloud.getWidth() && x >0 && y<btnCloud.getHeight() && y > 0){
	        		btnSur.addAction(Actions.fadeOut(1));
	        		btnCamp.addAction(Actions.fadeOut(1));
	        		btnPlay.setVisible(true);
	        		btnPlay.addAction(Actions.fadeIn(1));
	        		
	        		SequenceAction img_action = new SequenceAction();
	        		img_action.addAction(Actions.moveTo(570, -40, 1, Interpolation.linear));
	        		img_action.addAction(Actions.delay(0.2f));
	        		img_action.addAction(Actions.moveTo(570, -140, 1, Interpolation.linear));
	        		
	        		imgCloud.addAction(img_action);
	        	}
	        	
	        }
		});
		
		table.addActor(imgCloud);
		table.addActor(btnCloud);
		
		btnPlay.setBounds(155*2 +20, 180, 150, 35);
		btnPlay.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < btnPlay.getWidth() && x >0 && y<btnPlay.getHeight() && y > 0){
	        		btnSur.addAction(Actions.fadeIn(1));
	        		btnCamp.addAction(Actions.fadeIn(1));
	        		btnPlay.addAction(Actions.fadeOut(1));
	        		btnPlay.setVisible(false);
	        	}
	        }
		});
		
		table.addActor(btnPlay);
		
	}


}

