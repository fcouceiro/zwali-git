package com.me.zwali;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class Achievements extends UI
{
	Conceito maingame;
	List<AchievBtn> list;
	
	public Achievements(Conceito main)
	{
		super(Conceito.batch,main.font,false);
		this.maingame = main;
		this.list = new ArrayList<AchievBtn>();
		this.popButtons();
	}
	
	public void setAchiev(Constants.achiev_types type)
	{
		for(AchievBtn btn:list)
			if(btn.type == type) btn.setObtained(true);
	}
	
	private void popButtons()
	{
		int y_init=Gdx.graphics.getHeight() -100;
		int x_init=70;
		
		for(Constants.achiev_types type:Constants.achiev_types.values())
		{
			AchievBtn btn = new AchievBtn("",StylesManager.skin,type);
			btn.setBounds(x_init, y_init, 150, 35);
			
			y_init -= 40;
			list.add(btn);
			table.addActor(btn);
		}
		
		//add back button
				final TextButton btn = new TextButton("Back",StylesManager.btnGray);
				btn.setBounds(Gdx.graphics.getWidth()/2 -160, 100, 150, 35);
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