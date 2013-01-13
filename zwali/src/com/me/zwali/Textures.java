package com.me.zwali;


import java.io.FileNotFoundException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Textures {
	
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
	 static Sprite backgroundIM;
	 static Sprite ExplosionIM;
	 static Sprite Red;
	 static Sprite BarrelIM;
	 static Sprite BarrelUnIM;
	 static Sprite Res1;
	 static Sprite Res2;
	 static Sprite Res3;
	 static Sprite Res4;
	 static Sprite Res5;
	 
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
	 static Sprite wizard;
	 static Sprite box;
	 

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
	 
	 static Sprite expl1,expl2,expl3;
	 
	public static void loadTextures() throws FileNotFoundException
	{
		wv1 = loadTexture("/menus/wave_in");
		wv2 = loadTexture("/menus/wave_in2");
		expl1 = new Sprite(loadTexture("/explosion/exp1"));
		expl2 = new Sprite(loadTexture("/explosion/exp2"));
		expl3 = new Sprite(loadTexture("/explosion/exp3"));
		Crosshair2IM = loadTexture("crosshair");
		CrosshairIM = loadTexture("crosshair2");
		CrossUp = loadTexture("CrossUp");
		CrossSide = loadTexture("CrossSide");
		playerPistolIM = loadTexture("/player/player_pistol");
		playerMachineGunIM = loadTexture("/player/player_machinegun");
		playerShotGunIM = loadTexture("/player/player_shotgun");
		playerBuilderIM = loadTexture("/player/player_builder");
		bulletIM = loadTexture("bullet");
		zombie_type1 = loadTexture("/zombies/type1");
		zombie_type2 = loadTexture("/zombies/type2");
		Red = loadTexture("Red");
		backgroundIM = loadTexture("/environment/bg-ver_0_1x2");
		ExplosionIM = loadTexture( "explosion");
		BarrelIM = loadTexture("barrel");
		BarrelUnIM = loadTexture("barrelun");
		Armor = loadTexture("/items/armor");
		Power = loadTexture("/items/power");
		Accuracy = loadTexture("/items/accuracy");
		Ammo = loadTexture("/items/ammo");
		Resources = loadTexture("/items/resources");
		pistol = loadTexture("/weapons/wp1");
		minigun = loadTexture("/weapons/wp2");
		shotgun = loadTexture("/weapons/wp3");
		Medkit = loadTexture("/items/medkit");
		rdmBuff = loadTexture("/items/randombuff");
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
		builder_bar = loadTexture("/builder/barra");
		builder_bar_btm = loadTexture("/builder/barra_btm");
		wizard = loadTexture("/environment/wizard");
		box = loadTexture("/environment/box");
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
		page6 = loadTexture("Red");
		btnNext = loadTexture("/tutorial/btnNext");
		btnNext_h = loadTexture("/tutorial/btnNext_h");
		btnPrev = loadTexture("/tutorial/btnPrev");
		btnPrev_h = loadTexture("/tutorial/btnPrev_h");
		btnmainMenu = loadTexture("/tutorial/btnMainmenu");
		btnmainMenu_h = loadTexture("/tutorial/btnMainmenu_h");
	
		
	}
	
	
	private static Sprite loadTexture(String key) throws FileNotFoundException
	{
		try{
		Texture a = new Texture(Gdx.files.internal("res/textures/" + key +".png"));
		a.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Sprite b = new Sprite(a);
	
		return (b);
		}
		finally{
		
		}
	}
	
	public static void dispose()
	{
		
		
	}
	
//	public void draw(static Sprite t, SpriteBatch batch)
//	{
//	Sprite a = new Sprite(t);
//	glLoadIdentity();
//	glPushMatrix();
//	
//	t.bind();
//	glTranslatef((float)(160),(float)(545), 0.0f);
//	glBegin(GL_QUADS);
//	glTexCoord2f(0, 0);
//	glVertex2i(0, 0); // Upper-left
//	glTexCoord2f(1, 0);
//	glVertex2i(50, 0); // Upper-right
//	glTexCoord2f(1, 1);
//	glVertex2i(50,50); // Bottom-right
//	glTexCoord2f(0, 1);
//	glVertex2i(0, 50);
//	glEnd();
//	glPopMatrix();
//	}
}
