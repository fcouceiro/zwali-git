package com.me.zwali;


import java.io.FileNotFoundException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Textures {
	
	AssetManager asm;
	
	public Textures(AssetManager asm) throws FileNotFoundException
	{
		this.asm = asm;
		this.loadTextures();
	}
	
	static TextButtonStyle btnMinigun, imgMinigun, btnShotgun, imgShotgun, btnPistol, imgPistol;
	static TextButtonStyle btnHealth, btnArmor, btnAmmo, btnRes, btnACC,btnArrUp,btnArrDown,btnArrLeft,btnArrRight;
	
	static Sprite progressbar;
	static Sprite backframe_ui;
	static Sprite qHome;
	static Sprite questsBG;
	static Sprite game_over_bg;
	static Sprite control_menu;
	 static Sprite enemy1_falling;
	 static Sprite radar;
	 static Sprite enemy1_dead;
	 static Sprite enemy2_falling;
	 static Sprite enemy2_dead;
	 static Sprite enemy3_falling;
	 static Sprite enemy3_dead;
	 static Sprite Sangue_3;
	 static Sprite Sangue_2;
	 static Sprite Sangue_1;
	 static Sprite CrosshairIM;
	 static Sprite Crosshair2IM;
	 static Sprite CrossUp;
	 static Sprite CrossSide;
	 static Sprite bulletIM;
	 static Sprite playerPistolIM;
	 static Sprite playerMachineGunIM;
	 static Sprite playerShotGunIM;
	 static Sprite playerBuilderIM;
	 static Sprite zombie_type1;
	 static Sprite zombie_type2;
	 static Sprite zombie_type3;

	 static Sprite Red;
	 static Sprite BarrelIM;
	 static Sprite BarrelUnIM;
	 static Sprite Res1;
	 static Sprite Res2;
	 static Sprite Res3;
	 static Sprite Res4;
	 static Sprite Res5;
	 static Sprite player_walking;
	 static Sprite player_idle;
	 //Items
	 static Sprite Armor;
	 static Sprite Ammo;
	 static Sprite Power;
	 static Sprite Accuracy;
	 static Sprite Resources;
	 static Drawable pistol;
	 static Drawable minigun;
	 static Drawable shotgun;
	 static Sprite Medkit;
	 static Sprite rdmBuff;
	 //End of Items
	 
	 static Sprite shopBtns;
	 
	 //wavein
	 static Sprite wv1;
	 static Sprite wv2;
	 
	 static Sprite pausemenu;
	 static Sprite environment_Tree;
	 static Sprite mainmenuIM;
	 static Sprite mainmenu_btnPlay;
	 static Sprite mainmenu_btnPlay_h;
	 static Sprite mainmenu_btnHowtoPlay;
	 static Sprite mainmenu_btnHowtoPlay_h;
	 static Sprite mainmenu_btnAbout;
	 static Sprite mainmenu_btnAbout_h;
	 static Sprite mainmenu_dificuldade;
	 static Sprite mainmenu_dificuldade_rockie;
	 static Sprite mainmenu_dificuldade_regular;
	 static Sprite mainmenu_dificuldade_veteran;
	 static Sprite mainmenu_dificuldade_survival;
	 static Sprite mainmenu_dificuldade_nonsurvival;
	 
	 //Tutorial pages - textures
	 static Sprite page1;
	 static Sprite page2;
	 static Sprite page3;
	 static Sprite page4;
	 static Sprite page5;
	 static Sprite page6;
	 static Sprite btnNext;
	 static Sprite btnNext_h;
	 static Sprite btnPrev;
	 static Sprite btnPrev_h;
	 static Sprite btnmainMenu;
	 static Sprite btnmainMenu_h;
	 
	 static Sprite builder_bar;
	 static Sprite builder_bar_btm;
	 static Sprite shopIM;
	 
	 //thumbs
	 static Sprite tHome;

	 static Sprite _1;
	 static Sprite _2;
	 static Sprite _3;
	 static Sprite _4;
	 static Sprite _5;
	 static Sprite _6;
	 static Sprite _7;
	 static Sprite _8;
	 static Sprite _9;
	 static Sprite _0;
	 
	 
	 //Bottom bar
	 static Sprite barIM;
	 static Sprite bar_ammo;
	 static Sprite bar_medkit;
	 static Sprite bar_outofammo;
	 static Sprite bar_stroke;
	 static Sprite bar_buildmodeon;
	 static Sprite bar_buildmodeoff;
	 
	 //static Sprite expl1,expl2,expl3;
	 static Sprite bul_art;
	 static Sprite questThumb;
	 static Sprite ruthlessLogo;
	 
	 static Sprite sound,no_sound;
	 static Sprite scroll_box_bg;
	 static Sprite bg_sangue;
	 static Sprite arrUp,arrDown,arrLeft,arrRight;
	 static Sprite logbg;
	 
	 static Sprite money,xp,hit,money_bar,barrel_bar,balloon;
	 static Sprite bottomui;
	 
	public void loadTextures() throws FileNotFoundException
	{
		bottomui = loadTexture("/menus/bottomui");
		balloon = loadTexture("/gameplay/balloon");
		barrel_bar = loadTexture("/gameplay/barrel_bar");
		money = loadTexture("/gameplay/money");
		xp = loadTexture("/gameplay/xp");
		hit = loadTexture("/gameplay/hit");
		money_bar = loadTexture("/gameplay/money_bar");
		logbg = loadTexture("/other/logbg");
		progressbar = loadTexture("/other/progressbar");
		backframe_ui = loadTexture("/other/backframe");
		arrUp = loadTexture("/other/arrUp");
		arrLeft = loadTexture("/other/arrLeft");
		arrDown = loadTexture("/other/arrDown");
		arrRight = loadTexture("/other/arrRight");
		scroll_box_bg = loadTexture("/other/box_achiev_scroll");
		bg_sangue = loadTexture("/menus/bg_sangue");
		sound = loadTexture("/other/withsound");
		no_sound = loadTexture("/other/nosound");
		game_over_bg = loadTexture("/menus/gameoverbg");
		ruthlessLogo = loadTexture("/other/rlogo");
		qHome = loadTexture("/gameplay/quest-bgs/Home");
		tHome = loadTexture("/thumbs/Home");
		questThumb = loadTexture("/thumbs/questThumbnail");
		shopBtns = loadTexture("/menus/shop/btns-shop");
		bul_art = loadTexture("/other/pistol_artifact");
		radar = loadTexture("/other/radar");
		enemy1_falling = loadTexture("/gameplay/zombies/type1_anim/falling");
		enemy1_dead = loadTexture("/gameplay/zombies/type1_anim/dead");
		enemy2_falling = loadTexture("/gameplay/zombies/type1_anim/falling");
		enemy2_dead = loadTexture("/gameplay/zombies/type1_anim/dead");
		enemy3_falling = loadTexture("/gameplay/zombies/type1_anim/falling");
		enemy3_dead = loadTexture("/gameplay/zombies/type1_anim/dead");
		Sangue_3 = loadTexture("/gameplay/sangue/b3");
		Sangue_2 = loadTexture("/gameplay/sangue/b2");
		Sangue_1 = loadTexture("/gameplay/sangue/b1");
		player_walking = loadTexture("/gameplay/player/player_anim/player_anim_walking");
		player_idle = loadTexture("/gameplay/player/player_anim/player_anim_idle");
		
		CrossUp = loadTexture("/other/CrossUp");
		CrossSide = loadTexture("/other/CrossSide");
		playerPistolIM = loadTexture("/gameplay/player/player_pistol");
		playerMachineGunIM = loadTexture("/gameplay/player/player_machinegun");
		playerMachineGunIM.setSize(90, 90);
		playerShotGunIM = loadTexture("/gameplay/player/player_shotgun");
		playerShotGunIM.setSize(90, 90);
		playerBuilderIM = loadTexture("/gameplay/player/player_builder");
		bulletIM = loadTexture("/other/bullet");
		zombie_type1 = loadTexture("/gameplay/zombies/type1");
		zombie_type2 = loadTexture("/gameplay/zombies/type2");
		zombie_type3 = loadTexture("/gameplay/zombies/type3");
		Red = loadTexture("/Red");

		BarrelIM = loadTexture("/environment/barrel");
		BarrelUnIM = loadTexture("/environment/barrelun");
		questsBG = loadTexture("/menus/screen_chooser_bg");
		control_menu = loadTexture("/menus/control_box");
		Armor = loadTexture("/menus/shop/items/armor");
		Power = loadTexture("/menus/shop/items/power");
		Accuracy = loadTexture("/menus/shop/items/accuracy");
		Ammo = loadTexture("/menus/shop/items/ammo");
		Resources = loadTexture("/menus/shop/items/resources");
		pistol = new TextureRegionDrawable(loadTexture("/thumbs/weapons/wp1"));
		minigun = new TextureRegionDrawable(loadTexture("/thumbs/weapons/wp2"));
		shotgun = new TextureRegionDrawable(loadTexture("/thumbs/weapons/wp3"));
		Medkit = loadTexture("/menus/shop/items/medkit");
		rdmBuff = loadTexture("/menus/shop/items/randombuff");
		barIM = loadTexture("/menus/invmenu");
		bar_ammo = loadTexture("/menus/invmenu_ammo");


		mainmenuIM = loadTexture("/menus/mainmenu");
	
		builder_bar = loadTexture("/other/builder/barra");
		builder_bar_btm = loadTexture("/other/builder/barra_btm");
		shopIM = loadTexture("/menus/shop/shop");
	
		
		loadStyles();
	}
	
	private void loadStyles()
	{
		btnMinigun = new TextButtonStyle();
		imgMinigun =  new TextButtonStyle();
		btnShotgun = new TextButtonStyle();
		imgShotgun =  new TextButtonStyle();
		btnPistol = new TextButtonStyle();
		imgPistol =  new TextButtonStyle();
		btnHealth = new TextButtonStyle();
		btnArmor = new TextButtonStyle(); 
		btnAmmo = new TextButtonStyle(); 
		btnRes = new TextButtonStyle();
		btnACC= new TextButtonStyle();
		btnArrUp = new TextButtonStyle();
		btnArrDown = new TextButtonStyle();
		btnArrLeft = new TextButtonStyle();
		btnArrRight = new TextButtonStyle();
		
		BitmapFont buttonFont = new BitmapFont(Gdx.files.internal("assets/UI/fonts/arial.fnt"),
		         Gdx.files.internal("assets/UI/fonts/arial.png"), false);
		
		
		TextureRegion normal;
		TextureRegion normal_h;
		
		
		normal = new TextureRegion(Textures.arrDown,0,0,32,32);
		normal_h = new TextureRegion(Textures.arrDown,32,0,32,32);
		
		btnArrDown.up = new TextureRegionDrawable(normal);
		btnArrDown.down = new TextureRegionDrawable(normal);
		btnArrDown.font = buttonFont;
		btnArrDown.over = new TextureRegionDrawable(normal_h);
		
		normal = new TextureRegion(Textures.arrUp,0,0,32,32);
		normal_h = new TextureRegion(Textures.arrUp,32,0,32,32);
		
		btnArrUp.up = new TextureRegionDrawable(normal);
		btnArrUp.down = new TextureRegionDrawable(normal);
		btnArrUp.font = buttonFont;
		btnArrUp.over = new TextureRegionDrawable(normal_h);
		
		normal = new TextureRegion(Textures.arrLeft,0,0,32,32);
		normal_h = new TextureRegion(Textures.arrLeft,32,0,32,32);
		
		btnArrLeft.up = new TextureRegionDrawable(normal);
		btnArrLeft.down = new TextureRegionDrawable(normal);
		btnArrLeft.font = buttonFont;
		btnArrLeft.over = new TextureRegionDrawable(normal_h);
		
		normal = new TextureRegion(Textures.arrRight,0,0,32,32);
		normal_h = new TextureRegion(Textures.arrRight,32,0,32,32);
		
		btnArrRight.up = new TextureRegionDrawable(normal);
		btnArrRight.down = new TextureRegionDrawable(normal);
		btnArrRight.font = buttonFont;
		btnArrRight.over = new TextureRegionDrawable(normal_h);
		
		btnMinigun.up = Textures.minigun;
		btnMinigun.font = buttonFont;
		imgMinigun.font = buttonFont;
		
		btnShotgun.up = Textures.shotgun;
		btnShotgun.font = buttonFont;
		imgShotgun.font = buttonFont;
		
		btnPistol.up = Textures.pistol;
		btnPistol.font = buttonFont;
		imgPistol.font = buttonFont;
		
		btnHealth.up =  new TextureRegionDrawable(Textures.Medkit);
		btnHealth.font = buttonFont;
		
		btnArmor.up =  new TextureRegionDrawable(Textures.Armor);
		btnArmor.font = buttonFont;
		
		btnAmmo.up =  new TextureRegionDrawable(Textures.Ammo);
		btnAmmo.font = buttonFont;
		
		btnRes.up =  new TextureRegionDrawable(Textures.Resources);
		btnRes.font = buttonFont;
		
		btnACC.up =  new TextureRegionDrawable(Textures.Accuracy);
		btnACC.font = buttonFont;
	}
	
	private Sprite loadTexture(String key)
	{
		
		try{
			Sprite temp = new Sprite(asm.get("assets/gfx" + key +".png", Texture.class));
			return temp;
		}
		finally{
		
		}
	}
	
	public void dispose()
	{
		asm.clear();
	}
}
