package com.me.zwali;

import java.io.FileNotFoundException;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.LoadingScreen;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;

public class LoadScreen extends UI implements LoadingScreen{

	private AssetManager asm = null;
	private Conceito maingame;
	private Slider progress_bar;
	
	public Textures textures_inst;
	public Sounds sounds_inst;
	
	public LoadScreen(Conceito maingame,AssetManager asm)
	{
		super(Conceito.batch,maingame.font,false);
		this.asm = asm;
		this.maingame = maingame;
		this.pop_UI();
		this.setLoad();
	}
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(asm.update())
		{
			try {
				
				textures_inst = new Textures(asm);
				sounds_inst = new Sounds(maingame);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.createGame();
			maingame.setScreen(maingame.mainmenu);
		}
		
		float progress = asm.getProgress();
		progress_bar.setValue(progress);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	
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
	public void setAssetManager(AssetManager asm) {
		// TODO Auto-generated method stub
		this.asm = asm;
	}


	@Override
	public void pop_UI() {
		// TODO Auto-generated method stub
		Table load = new Table();
		
		Texture pro = new Texture(Gdx.files.internal("assets/gfx/other/progressbar.png"));
		pro.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Texture bg = new Texture(Gdx.files.internal("assets/gfx/other/rlogo.png"));
		bg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		table.setBackground(new TextureRegionDrawable(new Sprite(bg)));
		
		SliderStyle a = new SliderStyle();
		a.background = new TextureRegionDrawable(new TextureRegion(pro,0,20,140,10));
		a.knob = null;
		a.knobBefore = new TextureRegionDrawable(new TextureRegion(pro,0,10,140,10));
		
		progress_bar = new Slider(0.0f,1.0f,0.01f,false,a);
		progress_bar.setTouchable(null);
		progress_bar.setSize(150, 40);
		
		
		Label labLoad = new Label("Loading",StylesManager.skin);
		labLoad.setPosition(75 - labLoad.getWidth() /2, 30);
		
		load.addActor(progress_bar);
		load.addActor(labLoad);
		
		load.setPosition(Gdx.graphics.getWidth() /2 - 75, 10);
		
		stage.addActor(load);
	}

	@Override
	public void setLoad()
	{
		TextureParameter param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		param.magFilter = TextureFilter.Linear;
		
		asm.load("assets/gfx/menus/cloud.png",Texture.class,param);
		asm.load("assets/gfx/menus/bottomui.png", Texture.class,param);
		asm.load("assets/gfx/gameplay/balloon.png", Texture.class,param);
		asm.load("assets/gfx/gameplay/barrel_bar.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/money.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/xp.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/hit.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/money_bar.png", Texture.class,param);
		 asm.load("assets/gfx/other/logbg.png", Texture.class,param);
		 asm.load("assets/gfx/other/progressbar.png", Texture.class,param);
		 asm.load("assets/gfx/other/backframe.png", Texture.class,param);
		 asm.load("assets/gfx/other/arrUp.png", Texture.class,param);
		 asm.load("assets/gfx/other/arrLeft.png", Texture.class,param);
		 asm.load("assets/gfx/other/arrDown.png", Texture.class,param);
		 asm.load("assets/gfx/other/arrRight.png", Texture.class,param);
		 asm.load("assets/gfx/other/box_achiev_scroll.png", Texture.class,param);
		 asm.load("assets/gfx/menus/bg_sangue.png", Texture.class,param);
		 asm.load("assets/gfx/other/withsound.png", Texture.class,param);
		 asm.load("assets/gfx/other/nosound.png", Texture.class,param);
		 asm.load("assets/gfx/menus/gameoverbg.png", Texture.class,param);
		 asm.load("assets/gfx/other/rlogo.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/quest-bgs/Home.png", Texture.class,param);
		 asm.load("assets/gfx/thumbs/Home.png", Texture.class,param);
		 asm.load("assets/gfx/thumbs/questThumbnail.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/btns-shop.png", Texture.class,param);
		 asm.load("assets/gfx/other/pistol_artifact.png", Texture.class,param);
		 asm.load("assets/gfx/other/radar.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1_anim/falling.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1_anim/dead.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1_anim/falling.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1_anim/dead.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1_anim/falling.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1_anim/dead.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/sangue/b3.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/sangue/b2.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/sangue/b1.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/player/player_anim/player_anim_walking.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/player/player_anim/player_anim_idle.png", Texture.class,param);
		
		 asm.load("assets/gfx/other/CrossUp.png", Texture.class,param);
		 asm.load("assets/gfx/other/CrossSide.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/player/player_pistol.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/player/player_machinegun.png", Texture.class,param);
		
		 asm.load("assets/gfx/gameplay/player/player_shotgun.png", Texture.class,param);
		
		 asm.load("assets/gfx/gameplay/player/player_builder.png", Texture.class,param);
		 asm.load("assets/gfx/other/bullet.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type1.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type2.png", Texture.class,param);
		 asm.load("assets/gfx/gameplay/zombies/type3.png", Texture.class,param);
		 asm.load("assets/gfx/Red.png", Texture.class,param);

		 asm.load("assets/gfx/environment/barrel.png", Texture.class,param);
		 asm.load("assets/gfx/environment/barrelun.png", Texture.class,param);
		 asm.load("assets/gfx/menus/screen_chooser_bg.png", Texture.class,param);
		 asm.load("assets/gfx/menus/control_box.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/items/armor.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/items/power.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/items/accuracy.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/items/ammo.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/items/resources.png", Texture.class,param);
		
		 asm.load("assets/gfx/menus/shop/items/medkit.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/items/randombuff.png", Texture.class,param);
		 asm.load("assets/gfx/menus/invmenu.png", Texture.class,param);
		 asm.load("assets/gfx/menus/invmenu_ammo.png", Texture.class,param);
	
		 asm.load("assets/gfx/menus/mainmenu.png", Texture.class,param);
		 asm.load("assets/gfx/environment/tree.png", Texture.class,param);
		 asm.load("assets/gfx/other/builder/barra.png", Texture.class,param);
		 asm.load("assets/gfx/other/builder/barra_btm.png", Texture.class,param);
		 asm.load("assets/gfx/menus/shop/shop.png", Texture.class,param);
		 
		 asm.load("assets/gfx/thumbs/weapons/wp1.png",Texture.class,param);
		 asm.load("assets/gfx/thumbs/weapons/wp2.png",Texture.class,param);
		 asm.load("assets/gfx/thumbs/weapons/wp3.png",Texture.class,param);
	
		 asm.load("assets/UI/buttons.png",Texture.class,param);
		 asm.load("assets/UI/btn_close.png",Texture.class,param);
		 asm.load("assets/UI/text_holder.png",Texture.class,param);
	}
	
	@Override
	public void createGame() {
		// TODO Auto-generated method stub

		maingame.howtoplaymenu = new Howtoplay(maingame);
		
		//create achiev screen
		Conceito.achievs_screen = new Achievements(maingame);
		
		Conceito.achiev_prefs = Gdx.app.getPreferences("AchiveLists");
		Conceito.achiev_checker = new AchieveChecker(Conceito.achiev_prefs);
		maingame.achiev_checker.loadAch();
		
		//add all campaign quests and populate the questsScreen
		maingame.questsScreen = new ScreenChooser(maingame);
		maingame.questsScreen.quests.add(maingame.constDump.Home());
		maingame.questsScreen.quests.add(maingame.constDump.Home());
		maingame.questsScreen.quests.add(maingame.constDump.Home());
		maingame.questsScreen.quests.add(maingame.constDump.Home());
	
		maingame.questsScreen.popButtons();
		
		ScreenChooser.Player1 = maingame.getPlayer();
		maingame.shop = new Shop(maingame);
		
		//flash screens
		
		maingame.gameover = new GameOver(maingame);
		Conceito.rdm = new Random();
		
		maingame.mainmenu = new MainMenu(maingame);
	}

}
