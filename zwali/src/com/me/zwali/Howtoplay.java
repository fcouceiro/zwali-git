package com.me.zwali;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ruthlessgames.api.CutScene;
import com.ruthlessgames.api.Pane;
import com.ruthlessgames.api.StylesManager;



public class Howtoplay extends CutScene
{
	
	Howtoplay(Conceito main)
	{
		super(Conceito.batch,main.questsScreen,"assets/cutscenes/teste.xml");
		//this.pop_panes(); //create panes on current cut scene
	}	
	
	private void pop_panes()
	{
		Pane hello_label = new Pane(60*4);
		Label test = new Label("Hello",StylesManager.arial15);
		test.setPosition(70, 70);
		test.addAction(Actions.fadeOut(2));
		Label test_fade = new Label("Fade in de 20",StylesManager.arial15);
		test_fade.setPosition(200, 70);
		test_fade.getColor().a = 0f;
		test_fade.addAction(Actions.sequence(Actions.fadeIn(1), Actions.delay(1), Actions.fadeOut(1)));
		hello_label.add_Actor(test);
		hello_label.add_Actor(test_fade);
		addPane(hello_label);
		
		Pane teste = new Pane(60*4);
		TextButton t = new TextButton("x",StylesManager.btnLock);
		t.addAction(Actions.fadeOut(3));
		teste.add_Actor(t);
		addPane(teste);
		
		
	}
	
}
