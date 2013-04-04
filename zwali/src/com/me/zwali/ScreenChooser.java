package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class ScreenChooser extends UI{

	Conceito maingame;
	MyInputProcessor inputProcessor;
	public static Player Player1;
	Quest curQuest;
	List<Cenario> quests = new ArrayList<Cenario>();
	List<TextButton> btns_Q = new ArrayList<TextButton>();
	
	private Vector2 center = new Vector2(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 2);
	private Slider slider;
	private Vector2 qTHumbsPos[];
	
	public ScreenChooser(Conceito main)
	{
		super(Conceito.batch,main.font,true);
		
		maingame = main;
		this.qTHumbsPos = this.creatPos();
	}
	
	
	Screen generateScreen(Cenario c)
	{
		Quest temp = new Quest(this.maingame,c.Wave1Pos,c.Wave2Pos,c.maxWaves);
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
		
		this.getLabelByName("plLevel").setText("Your level: " + Player1.qLevel);
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
	
	
	private Vector2[] creatPos()
	{
		
		
		float raio1 = 1;
		
		
			Vector2 pos[] = new Vector2[9];
			for(int i=0;i<9;i++) pos[i] = new Vector2(0,0);
			
			pos[0].set(0, 0);
			pos[1].set(0, raio1);
			pos[2] = pos[1].cpy().rotate(360/3);
			pos[3] = pos[1].cpy().rotate(-1*(360/3));
			
			Vector2 aux = new Vector2(0, -2*raio1);
			pos[4] = aux.cpy().rotate(36);
			pos[5] = pos[4].cpy().rotate((360/5));
			pos[6] = pos[4].cpy().rotate((2*360/5));
			pos[7] = pos[4].cpy().rotate((3*360/5));
			pos[8] = pos[4].cpy().rotate((4*360/5));
			
			
			return pos;
		
	}

	private void updatePos(float raio)
	{
		int i=0;
		for(TextButton btn:this.btns_Q)
		{
			qTHumbsPos[i].nor();
			qTHumbsPos[i].scl(raio);
			btn.setX(this.qTHumbsPos[i].x + center.x - 150/2);
			btn.setY(this.qTHumbsPos[i].y + center.y - 35/2);
			i++;
		}
	}
	
	public void popButtons() {
		// TODO Auto-generated method stub
		
		//Generate quests thumbs and add listenners
		

		for(final Cenario c:quests)
		{
			final TextButton btn = new TextButton(c.name,StylesManager.btnBlue);
			btn.setWidth(150);
			btn.setHeight(35);
			btn.addListener(new InputListener() {
		        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		               	
		                return true;
		        }
		        
		        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		        	if(x < btn.getWidth() && x >0 && y<btn.getHeight() && y > 0)
		        	maingame.setScreen(generateScreen(c));
		        }
			});
			this.btns_Q.add(btn);
			table.addActor(btn);
		}
		
		
		//add slider
		slider = new Slider(90, 250, 2, false, StylesManager.skin);
		slider.setX(Gdx.graphics.getWidth()/2 -70);
		slider.setY(30);
		
		slider.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				float val = slider.getValue();
				updatePos(val);
				
			}
		});
		
		table.addActor(slider);
		//add back button
		final TextButton btn = new TextButton("Back",StylesManager.btnGray);
		btn.setBounds(Gdx.graphics.getWidth()/2 -160, 100, 150, 35);
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
		
		//add shop button
		final TextButton btn2 = new TextButton("Shop",StylesManager.btnGreen);
		btn2.setBounds(Gdx.graphics.getWidth()/2 +10, 100, 150, 35);
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
		
		Label plLevel = new Label("",StylesManager.skin);
		plLevel.setName("plLevel");
		plLevel.setX(50);
		plLevel.setY(Gdx.graphics.getHeight() - 50);
		this.addLabel(plLevel);
		//update first position
		this.updatePos(slider.getMinValue());
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
	int maxWaves;
	
	Cenario(int maxWaves,Sprite bg, Vector Wave1Pos, Vector Wave2Pos)
	{
		this.maxWaves = maxWaves;
		this.background = bg;
		this.Wave1Pos = Wave1Pos;
		this.Wave2Pos = Wave2Pos;
	}
}