package com.me.zwali;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class ScreenChooser extends UI{

	Conceito maingame;
	public static Player Player1;
	Cenario cur_cenario;
	ArrayList<Cenario> quests = new ArrayList<Cenario>();
	ArrayList<TextButton> btns_Q = new ArrayList<TextButton>();
	
	private Vector2 center = new Vector2(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 2);
	private Slider slider;
	private Vector2 qTHumbsPos[];
	private ScrollPane scrollPane2;
	private boolean wait_for_score = false;
	
	public ScreenChooser(Conceito main)
	{
		super(Conceito.batch,main.font,false);
		TextureRegion bg = new TextureRegion(Textures.questsBG,0,0,(int)Textures.questsBG.getWidth(),(int)Textures.questsBG.getHeight());
        table.setBackground(new TextureRegionDrawable(bg));
        
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
		
		temp.Player1 = Player1;
		temp.Player1.pos.x = temp.backG.size.x / 2;
		temp.Player1.pos.y = temp.backG.size.y / 2;
		temp.Player1.vel.x = 0;
		temp.Player1.vel.y = 0;
		temp.difficulty = 1;
		
		temp.sounds = maingame.loading_screen.sounds_inst;
		System.out.println("Quest created successfuly");
		return temp;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	public void reset_player()
	{
		if(Player1.Health <= 0){
			Player1.Health = Player1.MaxHp;
			Player1.alive = true;
			}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		this.reset_player();
		
		table.getColor().a = 0;
		table.addAction(Actions.fadeIn(0.5f));
		
		
		if(!Sounds.main_s.isPlaying() && maingame.sound){
		Sounds.main_s.setLooping(true);
		Sounds.main_s.play();
		}
		
		//update stats scroll pane
		String pis,shot,mach;
		pis = Player1.hasGun[0] ? "yes" : "no";
		shot = Player1.hasGun[1] ? "yes" : "no";
		mach = Player1.hasGun[2] ? "yes" : "no";
		
		String[] listStats = {"Level: " + (int)Player1.qLevel,"Health: " +Player1.Health,"Armor: "+Player1.armor,"Money: " + Player1.getMoney(),"XP: " + Player1.getXP(),"Pistol: " + pis,"Shotgun: " + shot, "Machine gun: " + mach};
		
		VerticalGroup vg = new VerticalGroup();
		for(int i=0;i<8;i++)
		{
			Label temp = new Label(listStats[i],StylesManager.skin);
			vg.addActor(temp);
		}
		
		listStats = null;
		scrollPane2.setWidget(vg);
		stage.setScrollFocus(scrollPane2);
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

		//add control box image
		Image control_box = new Image();
		TextureRegion image = new TextureRegion(Textures.control_menu,0,0,(int)Textures.control_menu.getWidth(),(int)Textures.control_menu.getHeight());
		control_box.setDrawable(new TextureRegionDrawable(image));
		control_box.setBounds(108, -5, (int)Textures.control_menu.getWidth(), (int)Textures.control_menu.getHeight());
		table.addActor(control_box);

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
		btn.setBounds(155 +2*150+2*20, 65, 150, 35);
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
		btn2.setBounds(155 + 150 +20, 65, 150, 35);
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

		//add achieves button
		final TextButton btn3 = new TextButton("Achievements",StylesManager.btnGray);
		btn3.setBounds(155, 65, 150, 35);
		btn3.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(x < btn3.getWidth() && x >0 && y<btn3.getHeight() && y > 0)
					maingame.setScreen(Conceito.achievs_screen);
			}
		});
		table.addActor(btn3);

		Label plLevel = new Label("Stats:",StylesManager.skin);
		plLevel.setX(50);
		plLevel.setY(Gdx.graphics.getHeight() - 50);
		table.addActor(plLevel);

		//scroll pane test
		scrollPane2 = new ScrollPane(null, StylesManager.skin);
		scrollPane2.setHeight(100);
		scrollPane2.setWidth(160);
		scrollPane2.setPosition(50, Gdx.graphics.getHeight()-150);
		scrollPane2.setSmoothScrolling(true);
		scrollPane2.setFadeScrollBars(true);
		scrollPane2.setFlickScroll(false);
		scrollPane2.setScrollbarsOnTop(true);
		scrollPane2.setupFadeScrollBars(2, 1.5f);
	
		table.addActor(scrollPane2);

		//Generate quests thumbs and add listenners


		for(final Cenario c:quests)
		{
			final TextButton btnQ = new TextButton(c.name,StylesManager.btnBlue);
			btnQ.setWidth(150);
			btnQ.setHeight(35);
			btnQ.addListener(new InputListener() {
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

					return true;
				}

				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					if(x < btnQ.getWidth() && x >0 && y<btnQ.getHeight() && y > 0){
						wait_for_score = true;
						cur_cenario = c;
						maingame.setScreen(generateScreen(c));
					}
				}
			});
			this.btns_Q.add(btnQ);
			table.addActor(btnQ);
		}

		TextureRegion n_s = new TextureRegion(Textures.sound,0,0,128,128);	//with sound texture
        TextureRegionDrawable n_s_d = new TextureRegionDrawable(n_s);
        n_s = new TextureRegion(Textures.no_sound,0,0,128,128); //no sound texture
        TextureRegionDrawable n_s_d2 = new TextureRegionDrawable(n_s);
        ImageButtonStyle style = new ImageButtonStyle(); //create the style
        style.up = n_s_d;
        style.checked = n_s_d2;
        
		final ImageButton no_sound = new ImageButton(style);
	
		no_sound.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < no_sound.getWidth() && x >0 && y<no_sound.getHeight() && y > 0){
	        		if(maingame.sound){
	        			Sounds.main_s.stop();
	        			maingame.sound = false;
	        			showToast("No sound.", 0,new Vector2(200,400),true);
	        		
	        		}
	        		else{
	        			Sounds.main_s.setLooping(true);
	        			Sounds.main_s.play();
	        			maingame.sound=true;
	        		}
	        	}
	        }
		});
		no_sound.setBounds(726, 10, 64, 64);
		table.addActor(no_sound);
		
		//update first position
		slider.setValue(170);
		this.updatePos(170);
	}


	public void setScore(float score,float max_score) {
		// TODO Auto-generated method stub
		if(wait_for_score)
		{
			float toAdd = (score * Player1.maxLevel) / max_score;
			Player1.qLevel += (float) (Math.round(toAdd*100.0)/100.0);
			this.anime_score(score,max_score);
			wait_for_score = false;
		}
	}


	private void anime_score(float score,float max_score) {
		// TODO Auto-generated method stub
		
		Gdx.app.log("Your score", score +" qlevel: "+ Player1.qLevel);
		
	}

}

class Cenario
{
	Sprite background;
	ArrayList <Vector> Objects = new ArrayList<Vector>(5);
	ArrayList <Vector> UnObjects  = new ArrayList<Vector>(5);
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