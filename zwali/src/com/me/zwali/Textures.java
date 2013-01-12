package com.me.zwali;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Textures {
	
	 Texture CrosshairIM;
	 Texture Crosshair2IM;
	 Texture CrossUp;
	 Texture CrossSide;
	 Texture bulletIM;
	 Texture playerPistolIM;
	 Texture playerMachineGunIM;
	 Texture playerShotGunIM;
	 Texture playerBuilderIM;
	 Texture zombie_type1;
	 Texture zombie_type2;
	 Texture backgroundIM;
	 Texture ExplosionIM;
	 Texture Red;
	 Texture BarrelIM;
	 Texture BarrelUnIM;
	 Texture Res1;
	 Texture Res2;
	 Texture Res3;
	 Texture Res4;
	 Texture Res5;
	 
	 //Items
	 Texture Armor;
	 Texture Ammo;
	 Texture Power;
	 Texture Accuracy;
	 Texture Resources;
	 Texture pistol;
	 Texture minigun;
	 Texture shotgun;
	 Texture Medkit;
	 Texture rdmBuff;
	 //End of Items
	 
	 //explosion
	 static Texture exp1;
	 static Texture exp2;
	 static Texture exp3;
	 
	 //wavein
	 Texture wv1;
	 Texture wv2;
	 
	 Texture pausemenu;
	 Texture environment_Tree;
	 Texture mainmenuIM;
	 Texture mainmenu_btnPlay;
	 Texture mainmenu_btnPlay_h;
	 Texture mainmenu_btnHowtoPlay;
	 Texture mainmenu_btnHowtoPlay_h;
	 Texture mainmenu_btnAbout;
	 Texture mainmenu_btnAbout_h;
	 Texture mainmenu_dificuldade;
	 Texture mainmenu_dificuldade_rockie;
	 Texture mainmenu_dificuldade_regular;
	 Texture mainmenu_dificuldade_veteran;
	 Texture mainmenu_dificuldade_survival;
	 Texture mainmenu_dificuldade_nonsurvival;
	 
	 //Tutorial pages - textures
	 Texture page1;
	 Texture page2;
	 Texture page3;
	 Texture page4;
	 Texture page5;
	 Texture page6;
	 Texture btnNext;
	 Texture btnNext_h;
	 Texture btnPrev;
	 Texture btnPrev_h;
	 Texture btnmainMenu;
	 Texture btnmainMenu_h;
	 
	 Texture builder_bar;
	 Texture builder_bar_btm;
	 Texture wizard;
	 Texture box;
	 
	 //Font textures
	 Texture A;
	 Texture B;
	 Texture C;
	 Texture D;
	 Texture E;
	 Texture F;
	 Texture G;
	 Texture H;
	 Texture I;
	 Texture J;
	 Texture K;
	 Texture L;
	 Texture M;
	 Texture N;
	 Texture O;
	 Texture P;
	 Texture Q;
	 Texture R;
	 Texture S;
	 Texture T;
	 Texture U;
	 Texture V;
	 Texture W;
	 Texture X;
	 Texture Y;
	 Texture Z;
	 Texture SPACE;
	 Texture pfinal;
	 Texture pvirgula;
	 Texture doisp;
	 Texture minus;
	 Texture plus;
	 Texture times;
	 Texture slash;
	 Texture pa;
	 Texture pf;
	 Texture pra;
	 Texture prf;
	 Texture ca;
	 Texture cf;
	 Texture aspas;
	 Texture aspp;
	 Texture arroba;
	 Texture ecomer;
	 Texture cifrao;
	 Texture percentagem;
	 Texture interrogacao;
	 Texture exclamacao;
	 Texture equal;
	 Texture maior;
	 Texture menor;
	 Texture _1;
	 Texture _2;
	 Texture _3;
	 Texture _4;
	 Texture _5;
	 Texture _6;
	 Texture _7;
	 Texture _8;
	 Texture _9;
	 Texture _0;
	 
	 
	 //Bottom bar
	 Texture barIM;
	 Texture bar_ammo;
	 Texture bar_medkit;
	 Texture bar_outofammo;
	 Texture bar_stroke;
	 Texture bar_buildmodeon;
	 Texture bar_buildmodeoff;
	 
	 static Sprite expl1,expl2,expl3;
	public void loadTextures() throws FileNotFoundException
	{
		wv1 = loadTexture("/menus/wave_in");
		wv2 = loadTexture("/menus/wave_in2");
		exp1 = loadTexture("/explosion/exp1");
		expl1 = new Sprite(exp1);
		exp2 = loadTexture("/explosion/exp2");
		expl2 = new Sprite(exp2);
		exp3 = loadTexture("/explosion/exp3");
		expl3 = new Sprite(exp3);
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
		this.loadFont();
		
	}
	
	private void loadFont() throws FileNotFoundException
	{
		A = loadTexture("/fonts/OSD/A");
		B = loadTexture("/fonts/OSD/B");
		C = loadTexture("/fonts/OSD/C");
		D = loadTexture("/fonts/OSD/D");
		E = loadTexture("/fonts/OSD/E");
		F = loadTexture("/fonts/OSD/F");
		G = loadTexture("/fonts/OSD/G");
		H = loadTexture("/fonts/OSD/H");
		I = loadTexture("/fonts/OSD/I");
		J = loadTexture("/fonts/OSD/J");
		K = loadTexture("/fonts/OSD/K");
		L = loadTexture("/fonts/OSD/L");
		M = loadTexture("/fonts/OSD/M");
		N = loadTexture("/fonts/OSD/N");
		O = loadTexture("/fonts/OSD/O");
		P = loadTexture("/fonts/OSD/P");
		Q = loadTexture("/fonts/OSD/Q");
		R = loadTexture("/fonts/OSD/R");
		S = loadTexture("/fonts/OSD/S");
		T = loadTexture("/fonts/OSD/T");
		U = loadTexture("/fonts/OSD/U");
		V = loadTexture("/fonts/OSD/V");
		W = loadTexture("/fonts/OSD/W");
		X = loadTexture("/fonts/OSD/X");
		Y = loadTexture("/fonts/OSD/Y");
		Z = loadTexture("/fonts/OSD/Z");
		SPACE = loadTexture("/fonts/OSD/SPACE");
		 pfinal = loadTexture("/fonts/OSD/pontos/pfinal");
		  pvirgula = loadTexture("/fonts/OSD/pontos/pvirgula");
		  doisp = loadTexture("/fonts/OSD/pontos/doisp");
		  minus = loadTexture("/fonts/OSD/pontos/minus");
		  plus = loadTexture("/fonts/OSD/pontos/plus");
		  times = loadTexture("/fonts/OSD/pontos/mult");
		  slash = loadTexture("/fonts/OSD/pontos/barra");
		  pa = loadTexture("/fonts/OSD/pontos/pa");
		  pf = loadTexture("/fonts/OSD/pontos/pf");
		  pra = loadTexture("/fonts/OSD/pontos/pra");
		  prf = loadTexture("/fonts/OSD/pontos/prf");
		  ca = loadTexture("/fonts/OSD/pontos/ca");
		  cf = loadTexture("/fonts/OSD/pontos/cf");
		  aspas = loadTexture("/fonts/OSD/pontos/aspas");
		  aspp = loadTexture("/fonts/OSD/pontos/aspp");
		  arroba = loadTexture("/fonts/OSD/pontos/arroba");
		  ecomer = loadTexture("/fonts/OSD/pontos/ecomer");
		  cifrao = loadTexture("/fonts/OSD/pontos/cifrao");
		  percentagem = loadTexture("/fonts/OSD/pontos/per");
		  interrogacao = loadTexture("/fonts/OSD/pontos/inter");
		  exclamacao = loadTexture("/fonts/OSD/pontos/excla");
		  equal = loadTexture("/fonts/OSD/pontos/equal");
		  maior = loadTexture("/fonts/OSD/pontos/maior");
		  menor = loadTexture("/fonts/OSD/pontos/menor");
		  _1 = loadTexture("/fonts/OSD/numeros/1");
		  _2 = loadTexture("/fonts/OSD/numeros/2");
		  _3 = loadTexture("/fonts/OSD/numeros/3");
		  _4 = loadTexture("/fonts/OSD/numeros/4");
		  _5 = loadTexture("/fonts/OSD/numeros/5");
		  _6 = loadTexture("/fonts/OSD/numeros/6");
		  _7 = loadTexture("/fonts/OSD/numeros/7");
		  _8 = loadTexture("/fonts/OSD/numeros/8");
		  _9 = loadTexture("/fonts/OSD/numeros/9");
		  _0 = loadTexture("/fonts/OSD/numeros/0");
	}

	
	
	private Texture loadTexture(String key) throws FileNotFoundException
	{
		try{
		Texture a = new Texture(Gdx.files.internal("res/textures/" + key +".png"));
		a.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return (a);
		}
		finally{
		
		}
	}
	
//	public void draw(Texture t, SpriteBatch batch)
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
