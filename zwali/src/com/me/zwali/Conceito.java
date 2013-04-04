
package com.me.zwali;
import java.io.FileNotFoundException;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ruthlessgames.api.StylesManager;

public class Conceito extends Game {
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static Preferences prefs;
	public static boolean hasKeyboard;
	public BitmapFont font;
	public static Random rdm;
	
	private FlashScreen ruthlessLogoScreen;
	Howtoplay howtoplaymenu;
	Shop shop;
	ScreenChooser questsScreen;
	MainMenu mainmenu;
	Constants constDump = new Constants();
	
	@Override
	public void create() {		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		try {
			Textures.loadTextures();
			Sounds.loadSounds();
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
		
		//create styles for UI's
		StylesManager.create("assets/gfx/other/");
		
		font = new BitmapFont(Gdx.files.internal("assets/fonts/arial.fnt"),
		         Gdx.files.internal("assets/fonts/arial.png"), false);
		howtoplaymenu = new Howtoplay(this);
		shop = new Shop(this);
		
		//add all campaign quests and populate the questsScreen
		questsScreen = new ScreenChooser(this);
		questsScreen.quests.add(constDump.Home());
		questsScreen.quests.add(constDump.Home());
		questsScreen.quests.add(constDump.Home());
		questsScreen.quests.add(constDump.Home());
	
		questsScreen.popButtons();
		
		ScreenChooser.Player1 = getPlayer();

		mainmenu = new MainMenu(this);
	
		//flash screens
		
		ruthlessLogoScreen = new FlashScreen(this,Textures.ruthlessLogo,this.mainmenu,120);
		rdm = new Random();
		setScreen(this.ruthlessLogoScreen);

	}

	
	Player getPlayer()
	{
		boolean savegame = prefs.getBoolean("savegame",false);
		//Mudar a dificuldade, segundo parametro
		Weapon wp1 = new Weapon(0, 1);
		Weapon wp2 = new Weapon(1, 1);
		Weapon wp3 = new Weapon(2, 1);
		
		if(savegame)
		{
			boolean hasGun[] = new boolean[3];
			int health = prefs.getInteger("Phealth",80);
			int armor = prefs.getInteger("Parmor",60);
			int qLevel = prefs.getInteger("qLevel",0);
			int money = prefs.getInteger("Pmoney",0);
			int xp = prefs.getInteger("Pexp",0);
			hasGun[0] = true;
			hasGun[1] = prefs.getBoolean("hasgun1", false);
			hasGun[2] = prefs.getBoolean("hasgun2", false);
			
			Player temp = new Player(new Vector(256, 256),armor);
			temp.qLevel = qLevel;
			temp.Health = health;
			temp.XP = xp;
			temp.money = money;
			
			
			temp.addGun(wp1);
			temp.addGun(wp2);
			temp.addGun(wp3);
			temp.hasGun = hasGun;
			
			temp.UpgPwrPistol = prefs.getInteger("UpgPistol", 0);
			temp.UpgPwrShotgun = prefs.getInteger("UpgShotgun", 0);
			temp.UpgPwrMinigun = prefs.getInteger("UpgMinigun", 0);
			
			Gdx.app.log("Savegame", "loaded");
			Gdx.app.log("sad", qLevel + "");
			return temp;
		}
		Player novo = new Player( new Vector(1024, 1024), 60);
		
		novo.addGun(wp1);
		novo.addGun(wp2);
		novo.addGun(wp3);
		Gdx.app.log("Savegame", "not loaded");
		return novo;
	}
	
	void Savegame()
	{
		prefs.putBoolean("savegame", true);
		prefs.putInteger("Phealth",ScreenChooser.Player1.Health);
		prefs.putInteger("Parmor",ScreenChooser.Player1.armor);
		prefs.putInteger("Pmoney",ScreenChooser.Player1.money);
		prefs.putInteger("Pexp",ScreenChooser.Player1.XP);
		prefs.putBoolean("hasgun0", ScreenChooser.Player1.hasGun[0]);
		prefs.putBoolean("hasgun1", ScreenChooser.Player1.hasGun[1]);
		prefs.putBoolean("hasgun2", ScreenChooser.Player1.hasGun[2]);
		prefs.putInteger("UpgPistol", ScreenChooser.Player1.UpgPwrPistol);
		prefs.putInteger("UpgShotgun", ScreenChooser.Player1.UpgPwrShotgun);
		prefs.putInteger("UpgMinigun", ScreenChooser.Player1.UpgPwrMinigun);
		prefs.putInteger("qLevel", 8);
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

}
