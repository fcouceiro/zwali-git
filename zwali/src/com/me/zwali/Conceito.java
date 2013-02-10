
package com.me.zwali;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Conceito extends Game {
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static Preferences prefs;
	public static boolean hasKeyboard;
	
	Mainmenu mainmenu;
	Howtoplay howtoplaymenu;
	Shop shop;
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
		prefs = Gdx.app.getPreferences("Player-prefs");
		
		batch.setProjectionMatrix(camera.combined);
		
		mainmenu = new Mainmenu(this);
		howtoplaymenu = new Howtoplay(this);
		shop = new Shop(this);
		
		questsScreen = new ScreenChooser(this);
		questsScreen.quests.add(new QuestThumb(Textures.tHome,"Home", new Vector2(355,300), new Vector2(200,200),new Vector2(60,25),Home()));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"Farm", new Vector2(155,300), new Vector2(140,140),new Vector2(35,20),null));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"Garage", new Vector2(555,300), new Vector2(140,140),new Vector2(30,20),null));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"MainMenu", new Vector2(355,200), new Vector2(140,140),new Vector2(30,20),null));
		questsScreen.quests.add(new QuestThumb(Textures.Red,"Shop", new Vector2(600,100), new Vector2(140,140),new Vector2(35,20),null));
		
		ScreenChooser.Player1 = getPlayer();

		setScreen(questsScreen);

	}

	
	Player getPlayer()
	{
		boolean savegame = prefs.getBoolean("savegame",false);
		
		if(savegame)
		{
			int health = prefs.getInteger("Phealth",80);
			int armor = prefs.getInteger("Parmor",60);
			int money = prefs.getInteger("Pmoney",0);
			int xp = prefs.getInteger("Pexp",0);
			Player temp = new Player(new Vector(1024, 1024),armor);
			temp.Health = health;
			temp.money = money;
			temp.XP = xp;
			Gdx.app.log("Savegame", "loaded");
			return temp;
		}
		
		Gdx.app.log("Savegame", "not loaded");
		return new Player( new Vector(1024, 1024), 60);
	}
	
	void Savegame()
	{
		prefs.putBoolean("savegame", true);
		prefs.putInteger("Phealth",ScreenChooser.Player1.Health);
		prefs.putInteger("Parmor",ScreenChooser.Player1.armor);
		prefs.putInteger("Pmoney",ScreenChooser.Player1.money);
		prefs.putInteger("Pexp",ScreenChooser.Player1.XP);
		prefs.flush();
		Gdx.app.log("savegame","saved");
	}
	
	@Override
	public void dispose() {
		
		Savegame();
		batch.dispose();
		Textures.dispose();
		questsScreen.dispose();
		shop.dispose();
		howtoplaymenu.dispose();
		mainmenu.dispose();
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
