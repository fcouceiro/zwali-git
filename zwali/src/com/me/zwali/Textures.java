package com.me.zwali;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Textures {
	
	static TextButtonStyle btnGreen,btnBlue,btnGray;
	
	private static List<Sprite> disposable = new ArrayList<Sprite>(5);
	
	static Sprite qHome;
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
	 static Sprite pistol;
	 static Sprite minigun;
	 static Sprite shotgun;
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
	 static Sprite buttonsRegion;
	 static Sprite ruthlessLogo;
	public static void loadTextures() throws FileNotFoundException
	{
		ruthlessLogo = loadTexture("/other/rlogo");
		buttonsRegion = loadTexture("/other/buttons");
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
		Armor = loadTexture("/menus/shop/items/armor");
		Power = loadTexture("/menus/shop/items/power");
		Accuracy = loadTexture("/menus/shop/items/accuracy");
		Ammo = loadTexture("/menus/shop/items/ammo");
		Resources = loadTexture("/menus/shop/items/resources");
		pistol = loadTexture("/thumbs/weapons/wp1");
		minigun = loadTexture("/thumbs/weapons/wp2");
		shotgun = loadTexture("/thumbs/weapons/wp3");
		Medkit = loadTexture("/menus/shop/items/medkit");
		rdmBuff = loadTexture("/menus/shop/items/randombuff");
		barIM = loadTexture("/menus/invmenu");
		bar_ammo = loadTexture("/menus/invmenu_ammo");
		//bar_medkit = loadTexture("/menus/invmenu_medkit");
		bar_outofammo = loadTexture("/menus/invmenu_outofammo");
		bar_stroke = loadTexture("/menus/invmenu_stroke");
		bar_buildmodeon = loadTexture("/menus/invmenu_buildmodeon");
		bar_buildmodeoff = loadTexture("/menus/invmenu_buildmodeoff");
		mainmenuIM = loadTexture("/menus/mainmenu");
		mainmenu_btnPlay = loadTexture("/menus/mainmenu_playbtn");
		mainmenu_btnPlay_h = loadTexture("/menus/mainmenu_playbtn_h");
		mainmenu_btnHowtoPlay = loadTexture("/menus/mainmenu_htpbtn");
		mainmenu_btnHowtoPlay_h = loadTexture("/menus/mainmenu_htpbtn_h");
		mainmenu_btnAbout = loadTexture("/menus/mainmenu_aboutbtn");
		mainmenu_btnAbout_h = loadTexture("/menus/mainmenu_aboutbtn_h");
		pausemenu = loadTexture("/menus/pausemenu");
		environment_Tree = loadTexture("/environment/tree");
		builder_bar = loadTexture("/other/builder/barra");
		builder_bar_btm = loadTexture("/other/builder/barra_btm");
		shopIM = loadTexture("/menus/shop/shop");
		mainmenu_dificuldade = loadTexture("/menus/dificuldade");
		mainmenu_dificuldade_rockie = loadTexture("/menus/dificuldade_rockie");
		mainmenu_dificuldade_regular = loadTexture("/menus/dificuldade_regular");
		mainmenu_dificuldade_veteran = loadTexture("/menus/dificuldade_veteran");
		mainmenu_dificuldade_survival = loadTexture("/menus/dificuldade_survival");
		mainmenu_dificuldade_nonsurvival = loadTexture("/menus/dificuldade_nonsurvival");
		//tutorial
		page1 = loadTexture("/tutorial/page1");
		page2 = loadTexture("/tutorial/page2");
		page3 = loadTexture("/tutorial/page3");
		page4 = loadTexture("/tutorial/page4");
		page5 = loadTexture("/tutorial/page5");
		page6 = loadTexture("/Red");
		btnNext = loadTexture("/tutorial/btnNext");
		btnNext_h = loadTexture("/tutorial/btnNext_h");
		btnPrev = loadTexture("/tutorial/btnPrev");
		btnPrev_h = loadTexture("/tutorial/btnPrev_h");
		btnmainMenu = loadTexture("/tutorial/btnMainmenu");
		btnmainMenu_h = loadTexture("/tutorial/btnMainmenu_h");
		
		loadStyles();
	}
	
	private static void loadStyles()
	{
		btnGreen = new TextButtonStyle();
		btnBlue = new TextButtonStyle();
		btnGray = new TextButtonStyle();
		
		BitmapFont buttonFont = new BitmapFont(Gdx.files.internal("assets/fonts/arial.fnt"),
		         Gdx.files.internal("assets/fonts/arial.png"), false);
		
		
		TextureRegion normal = new TextureRegion(Textures.buttonsRegion,0,57,256,58);
		TextureRegion normal_h = new TextureRegion(Textures.buttonsRegion,0,0,256,58);
		
		btnGreen.up = new TextureRegionDrawable(normal);
		btnGreen.down = new TextureRegionDrawable(normal_h);
		btnGreen.font = buttonFont;
		
		normal = new TextureRegion(Textures.buttonsRegion,256,57,254,58);
		normal_h = new TextureRegion(Textures.buttonsRegion,256,0,254,58);
		
		btnBlue.up = new TextureRegionDrawable(normal);
		btnBlue.down = new TextureRegionDrawable(normal_h);
		btnBlue.font = buttonFont;
		
		normal_h = new TextureRegion(Textures.buttonsRegion,2*255,56,256,58);
		normal = new TextureRegion(Textures.buttonsRegion,2*255,0,256,58);
		
		btnGray.up = new TextureRegionDrawable(normal);
		btnGray.down = new TextureRegionDrawable(normal_h);
		btnGray.font = buttonFont;
	}
	
	private static Sprite loadTexture(String key) throws FileNotFoundException
	{
		try{
		Texture a = new Texture(Gdx.files.internal("assets/gfx" + key +".png"));
		a.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Sprite b = new Sprite(a);
		disposable.add(b);
		return (b);
		}
		finally{
		
		}
	}
	
	public static void dispose()
	{
		for(Sprite a:disposable) a.getTexture().dispose();
		
	}
	
	public static void draw(Sprite t, SpriteBatch batch)
	{
	
	t.setSize(50, 50);
	t.setPosition((float)(160),(float)(3));
	t.draw(batch);
	
	}
}
