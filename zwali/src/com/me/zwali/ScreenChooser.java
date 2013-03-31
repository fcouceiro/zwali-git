package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class ScreenChooser implements UI{

	Conceito maingame;
	MyInputProcessor inputProcessor;
	public static Player Player1;
	Quest curQuest;
	List<Cenario> quests = new ArrayList<Cenario>();
	
	public ScreenChooser(Conceito main)
	{
		maingame = main;
		table.setFillParent(true);
		table.debug();
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Conceito.batch.begin();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);
		Conceito.batch.end();
	}
	
	Screen generateScreen(Cenario c)
	{
		Quest temp = new Quest(this.maingame,c.Wave1Pos,c.Wave2Pos);
		temp.backG.image = c.background;
		
		
		if(c.Objects.size() != 0)
		{
			for(Vector s:c.Objects)
			{
				temp.backG.Objects.add(new StaticObj(new Vector(s.x,s.y), new Vector(90,90),0,Textures.BarrelIM));
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
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		if(Player1.Health <= 0){
		Player1.Health = Player1.MaxHp;
		Player1.alive = true;
		}
		
		if(!Sounds.main_s.isPlaying()){
		Sounds.main_s.setLooping(true);
		Sounds.main_s.play();
		}
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

	@Override
	public void popButtons() {
		// TODO Auto-generated method stub
		
		//Generate quests thumbs and add listenners
		int counter=0;
		for(final Cenario c:quests)
		{
			final TextButton btn = new TextButton(c.name,StylesManager.btnBlue);
			btn.setBounds(250 + 160*counter, 350, 150, 35);
			btn.addListener(new InputListener() {
		        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		               	
		                return true;
		        }
		        
		        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		        	if(x < btn.getWidth() && x >0 && y<btn.getHeight() && y > 0)
		        	maingame.setScreen(generateScreen(c));
		        }
			});
			table.addActor(btn);
			
		}
		
		//add back button
		final TextButton btn = new TextButton("Back",StylesManager.btnGray);
		btn.setBounds(Gdx.graphics.getWidth()/2 - 75, 100, 150, 35);
		btn.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < btn.getWidth() && x >0 && y<btn.getHeight() && y > 0)
	        	maingame.setScreen(maingame.mainmenu);
	        }
		});
		table.addActor(btn);
		
		//add back button
		final TextButton btn2 = new TextButton("Shop",StylesManager.btnGray);
		btn2.setBounds(Gdx.graphics.getWidth()/4 *3, 200, 150, 35);
		btn2.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(x < btn2.getWidth() && x >0 && y<btn2.getHeight() && y > 0)
					maingame.setScreen(maingame.shop);
			}
		});
		table.addActor(btn2);
	}

}

class Cenario
{
	Sprite background;
	List <Vector> Objects = new ArrayList<Vector>(5);
	List <Vector> UnObjects  = new ArrayList<Vector>(5);
	Vector Wave1Pos;
	Vector Wave2Pos;
	String name = "not defined";
	
	Cenario(Sprite bg, Vector Wave1Pos, Vector Wave2Pos)
	{
		this.background = bg;
		this.Wave1Pos = Wave1Pos;
		this.Wave2Pos = Wave2Pos;
	}
}