
package com.me.zwali;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ruthlessgames.api.StylesManager;

public class Conceito extends Game {
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static Preferences prefs,achiev_prefs;
	public static boolean hasKeyboard;
	public BitmapFont font;
	public static Random rdm;
	public boolean sound = true;
	public static boolean debug = false;
	public static long startTime =  System.currentTimeMillis()/60000;
	
	GameOver gameover;
	Howtoplay howtoplaymenu;
	Shop shop;
	public static Stats stats;
	ScreenChooser questsScreen;
	MainMenu mainmenu;
	Constants constDump = new Constants();
	public static Achievements achievs_screen;
	public static AchieveChecker achiev_checker;
	
	LoadScreen loading_screen;
	AssetManager assetsManager;
	@Override
	public void create() {		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		System.out.print(w + " " +h);
		batch = new SpriteBatch();
		batch.getTransformMatrix().setToTranslation(-1*(w/2), -1*(h/2), 0);
		shapeRenderer = new ShapeRenderer();
		prefs = Gdx.app.getPreferences("Player-prefs");
		achiev_prefs = Gdx.app.getPreferences("AchiveLists");
		
		batch.setProjectionMatrix(camera.combined);
		
		//create styles for UI's
		StylesManager stylesmanager = new StylesManager("assets");
		
		font = new BitmapFont(Gdx.files.internal("assets/UI/fonts/arial.fnt"),
		         Gdx.files.internal("assets/UI/fonts/arial.png"), false);
		
		assetsManager = new AssetManager();
		loading_screen = new LoadScreen(this,this.assetsManager);
		stats = new Stats();
		setScreen(loading_screen);
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
			float qLevel = prefs.getFloat("qLevel",0);
			int money = prefs.getInteger("Pmoney",0);
			int xp = prefs.getInteger("Pexp",0);
			hasGun[0] = true;
			hasGun[1] = prefs.getBoolean("hasgun1", false);
			hasGun[2] = prefs.getBoolean("hasgun2", false);
			
			Player temp = new Player(new Vector(256, 256),armor);
			temp.qLevel = qLevel;
			temp.Health = health;
			temp.setXP(xp);
			temp.setMoney(money);
			
			
			temp.addGun(wp1);
			temp.addGun(wp2);
			temp.addGun(wp3);
			temp.hasGun = hasGun;
			
			temp.UpgPwrPistol = prefs.getInteger("UpgPistol", 0);
			temp.UpgPwrShotgun = prefs.getInteger("UpgShotgun", 0);
			temp.UpgPwrMinigun = prefs.getInteger("UpgMinigun", 0);
			//temp.UpgACC = prefs.getInteger("UpgACC", 0);
			
			Gdx.app.log("Savegame", "loaded");
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
		prefs.putInteger("Pmoney",ScreenChooser.Player1.getMoney());
		prefs.putInteger("Pexp",ScreenChooser.Player1.getXP());
		prefs.putBoolean("hasgun0", ScreenChooser.Player1.hasGun[0]);
		prefs.putBoolean("hasgun1", ScreenChooser.Player1.hasGun[1]);
		prefs.putBoolean("hasgun2", ScreenChooser.Player1.hasGun[2]);
		prefs.putInteger("UpgPistol", ScreenChooser.Player1.UpgPwrPistol);
		prefs.putInteger("UpgShotgun", ScreenChooser.Player1.UpgPwrShotgun);
		prefs.putInteger("UpgMinigun", ScreenChooser.Player1.UpgPwrMinigun);
		prefs.putInteger("UpgACC", ScreenChooser.Player1.UpgACC);
		prefs.putFloat("qLevel", ScreenChooser.Player1.qLevel);
		prefs.flush();
		Gdx.app.log("savegame","saved");
		
		achiev_checker.saveAch();
		
		this.ClearAchievData();
	}
	
	void ClearSaveData()
	{
		prefs.clear();
		prefs.flush();
		Gdx.app.log("savedata", "wiped");
	}
	
	void ClearAchievData()
	{
		achiev_checker.achisave.clear();
		achiev_checker.achisave.flush();
		Gdx.app.log("achievements data", "wiped");
	}
	
	void ClearUserData()
	{
		this.ClearUserData();
		this.ClearAchievData();
	}

	@Override
	public void dispose() {
		
		Savegame();
		
		batch.dispose();
		loading_screen.textures_inst.dispose();
		loading_screen.sounds_inst.dispose();
		questsScreen.dispose();
		shop.dispose();
		howtoplaymenu.dispose();
		mainmenu.dispose();
		
	}
	
	public static int timeElapsed(){
		return (int)(System.currentTimeMillis()/60000 - startTime);
	}

}
