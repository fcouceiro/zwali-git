package com.me.zwali;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class Achievements extends UI
{
	Conceito maingame;
	ArrayList<AchievBtn> list;
	
	public Achievements(Conceito main)
	{
		super(Conceito.batch,main.font,true);
		this.maingame = main;
		TextureRegion bg = new TextureRegion(Textures.bg_sangue,0,0,(int)Textures.bg_sangue.getWidth(),(int)Textures.bg_sangue.getHeight());
		table.setBackground(new TextureRegionDrawable(bg));
		this.list = new ArrayList<AchievBtn>();
		this.popButtons();
	}
	
	public void setAchiev(Constants.achiev_types type)
	{
		for(AchievBtn btn:list)
			if(btn.type == type){
				btn.setObtained(true);
				btn.setColor(0, 1, 0.6f, 1);
			}
	}
	
	private void popButtons()
	{
		VerticalGroup listNoob = new VerticalGroup();
		VerticalGroup listHardcore = new VerticalGroup();
		VerticalGroup listOther = new VerticalGroup();
		
		int y_init=Gdx.graphics.getHeight() -100;
		int x_init=70;
		
		int counter=0;
	
		for(Constants.achiev_types type:Constants.achiev_types.values())
		{
			AchievBtn btn = new AchievBtn(type.toString(),StylesManager.skin,type);
			btn.setBounds(x_init, y_init, 150, 35);
			
			y_init -= 40;
			list.add(btn);
			
			if(counter < 20)
			listNoob.addActor(btn);
			else if(counter <40)
				listHardcore.addActor(btn);
			else listOther.addActor(btn);
			
			counter++;
		}
		
		
		//add back button
				final TextButton btn = new TextButton("Back",StylesManager.btnGray);
				btn.setBounds(Gdx.graphics.getWidth()/2 -75, 20, 150, 35);
				btn.addListener(new InputListener() {
			        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			               	
			                return true;
			        }
			        
			        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			        	if(x < btn.getWidth() && x >0 && y<btn.getHeight() && y > 0)
			        	maingame.setScreen(maingame.questsScreen);
			        }
				});
				table.addActor(btn);
				
				
				ScrollPane sp1 = new ScrollPane(listNoob, StylesManager.skin);
				ScrollPane sp2 = new ScrollPane(listHardcore, StylesManager.skin);
				ScrollPane sp3 = new ScrollPane(listOther, StylesManager.skin);
				setSP(sp1);
				sp1.setPosition(73, 92);
				table.addActor(sp1);
				setSP(sp2);
				sp2.setPosition(72 + 212, 92);
				table.addActor(sp2);
				setSP(sp3);
				sp3.setPosition(71 + 2*212, 92);
				table.addActor(sp3);
	}
	
	private void setSP(ScrollPane sp)
	{
		ScrollPaneStyle aux_st = sp.getStyle();
		TextureRegion bg = new TextureRegion(Textures.scroll_box_bg,0,0,256,512);
		TextureRegionDrawable bg_d=new TextureRegionDrawable(bg);
		aux_st.background = bg_d;
		aux_st.vScroll = null;
		sp.setStyle(aux_st);
		sp.setHeight(415);
		sp.setWidth(212);
		sp.setSmoothScrolling(true);
		sp.setFadeScrollBars(true);
		sp.setFlickScroll(false);
		sp.setScrollbarsOnTop(true);
		sp.setupFadeScrollBars(2, 1.5f);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		
		table.getColor().a = 0;
		table.addAction(Actions.fadeIn(0.5f));
	}
}

class AchievBtn extends TextButton{

	Constants.achiev_types type;
	private boolean obtained = false;
	
	public AchievBtn(String text, Skin skin,Constants.achiev_types type) {
		super(text, skin);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public boolean isObtained() {
		return obtained;
	}

	public void setObtained(boolean obtained) {
		this.obtained = obtained;
	}
}