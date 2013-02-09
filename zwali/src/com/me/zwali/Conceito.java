
package com.me.zwali;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Conceito extends Game {
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static boolean hasKeyboard;
	
	Mainmenu mainmenu;
	Howtoplay howtoplaymenu;
	Quest gameloop;
	ScreenChooser questsScreen;

	
	@Override
	public void create() {		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		try {
			Textures.loadTextures();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		camera = new OrthographicCamera(w, h);
		System.out.print(w + " " +h);
		batch = new SpriteBatch();
		batch.getTransformMatrix().setToTranslation(-1*(w/2), -1*(h/2), 0);
		shapeRenderer = new ShapeRenderer();
		batch.setProjectionMatrix(camera.combined);
		
		mainmenu = new Mainmenu(this);
		howtoplaymenu = new Howtoplay(this);
		
		questsScreen = new ScreenChooser(this);
		questsScreen.quests.add(new QuestThumb(Textures.Red,"Home", new Vector2(355,300), new Vector2(90,60),Home()));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"Farm", new Vector2(155,300), new Vector2(60,40),null));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"Garage", new Vector2(555,300), new Vector2(60,40),null));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"MainMenu", new Vector2(355,200), new Vector2(60,40),null));
		
		Player Player1 = new Player( new Vector(1024, 1024), 60);
		ScreenChooser.Player1 = Player1;
		Player1.money = 9000;
		setScreen(questsScreen);

	}

	@Override
	public void dispose() {
		batch.dispose();
		Textures.dispose();

	}
	
	Cenario Home()
	{
		Cenario temp = new Cenario(Textures.backgroundIM);
				temp.Objects = new ArrayList<StaticObj>(10);
				temp.Objects.add( new StaticObj( new Vector( 834, 834), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 914, 834), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 834, 914), new Vector( 80, 80), Textures.BarrelIM ));
				
				temp.Objects.add( new StaticObj( new Vector( 1214, 834), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 1134, 834), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 1214, 914), new Vector( 80, 80), Textures.BarrelIM ));
				
				temp.Objects.add( new StaticObj( new Vector( 834, 1214), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 834, 1134), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 914, 1214), new Vector( 80, 80), Textures.BarrelIM ));
				
				temp.Objects.add( new StaticObj( new Vector( 1214, 1214), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 1134, 1214), new Vector( 80, 80), Textures.BarrelIM ));
				temp.Objects.add( new StaticObj( new Vector( 1214, 1134), new Vector( 80, 80), Textures.BarrelIM ));
		
		return temp;
	}
	
	

}
