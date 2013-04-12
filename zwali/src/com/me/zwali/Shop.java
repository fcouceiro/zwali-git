package com.me.zwali;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.UI;




public class Shop extends UI{

	public BitmapFont shopfont;
	Conceito MainGame;
	

	Vector mpos = new Vector(0,0);
	Vector exit = new Vector(0,0);
	Vector btnExitPos = new Vector(35, 50);
	
	List <Item> itens = new ArrayList<Item>(10);
	
	boolean highlight[] = new boolean[3];
	boolean selected[] = new boolean[9];
	static boolean wizardmode = false;
	static Random rdm = new Random();
	
	
	public Shop(Conceito Main)
	{
		super(Conceito.batch, Main.font,false);
	
		MainGame = Main;
		shopfont = Main.font;
		
		
		TextureRegion bg = new TextureRegion(Textures.shopIM,0,0,(int)Textures.shopIM.getWidth(),(int)Textures.shopIM.getHeight());
        table.setBackground(new TextureRegionDrawable(bg));
  
		//exit
		final TextButton buttonExit = new TextButton("Exit", Textures.btnGray);
		buttonExit.setBounds((float)btnExitPos.x, (float)btnExitPos.y, 110, 65);
		buttonExit.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonExit.getWidth() && x >0 && y<buttonExit.getHeight() && y > 0)
	        	MainGame.setScreen(MainGame.questsScreen);
	        	}
	        });
		
		
		
		//Buy
		final TextButton buttonBuy = new TextButton("Buy", Textures.btnGreen);
		buttonBuy.setBounds((float)btnExitPos.x+130, (float)btnExitPos.y, 110, 65);
		buttonBuy.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonBuy.getWidth() && x >0 && y<buttonBuy.getHeight() && y > 0)
		        	{
			        	if(selected[0])
			        	{
			        		if(ScreenChooser.Player1.UpgPwrPistol < 3 && ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrPistol+1)*100+ (ScreenChooser.Player1.UpgPwrPistol*100)))
			        		{
			        			ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgPwrPistol+1*100) + (ScreenChooser.Player1.UpgPwrPistol*100) ;
								ScreenChooser.Player1.UpgPwrPistol += 1;	
								ScreenChooser.Player1.InvListWeapons.get(0).power += 5;
								ScreenChooser.Player1.InvListWeapons.get(0).power2 += 5;
			        		}
			        	}
			        	else if(selected[1])	{
				        	if(ScreenChooser.Player1.hasGun[2])
				        	{
				        		if(ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrShotgun+1)*250 + ScreenChooser.Player1.UpgPwrShotgun*100))
								{
									if(ScreenChooser.Player1.UpgPwrShotgun < 3 && ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrPistol+1)*100*2))
									{
										ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgPwrShotgun+1)*250 + ScreenChooser.Player1.UpgPwrShotgun*100 ;
										ScreenChooser.Player1.UpgPwrShotgun += 1;	
										ScreenChooser.Player1.InvListWeapons.get(2).power += 3;
										ScreenChooser.Player1.InvListWeapons.get(2).power2 += 3;
									
									}
								}
				        	}
				        	else
				        	{
				        		if(ScreenChooser.Player1.money >= itens.get(7).price ) 
								{
										ScreenChooser.Player1.money -= itens.get(7).price;
										ScreenChooser.Player1.CurGun = 2;
										ScreenChooser.Player1.hasGun[2] = true;
								}
				        	}
				        }
			        	else if(selected[2]){
			        		if(ScreenChooser.Player1.hasGun[1])
				        	{
			        			if( ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrMinigun+1)*500 + ScreenChooser.Player1.UpgPwrMinigun*200))
								{
									if(ScreenChooser.Player1.UpgPwrMinigun < 3)
									{
										ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgPwrMinigun+1)*500 + ScreenChooser.Player1.UpgPwrMinigun*200 ;
										ScreenChooser.Player1.UpgPwrMinigun += 1;	
										ScreenChooser.Player1.InvListWeapons.get(2).power += 1;
										ScreenChooser.Player1.InvListWeapons.get(2).power2 += 1;
									}
								}
				        	}
			        		else
			        		{
			        			if(ScreenChooser.Player1.money >= itens.get(8).price) //add botoes
								{
									ScreenChooser.Player1.money -= itens.get(8).price;
									ScreenChooser.Player1.hasGun[1] = true;
								}
			        		}
			        	}
			        	else if(selected[3])
			        	{			        		
							if(ScreenChooser.Player1.money > itens.get(0).price && ScreenChooser.Player1.Health < ScreenChooser.Player1.MaxHp)
							{
								ScreenChooser.Player1.money -= itens.get(0).price;
								ScreenChooser.Player1.Health += 20;
								if(ScreenChooser.Player1.Health > ScreenChooser.Player1.MaxHp)
									ScreenChooser.Player1.Health = ScreenChooser.Player1.MaxHp;
							}
							
			        	}
			        	else if(selected[4])
			        	{
							if(ScreenChooser.Player1.money > itens.get(3).price && ScreenChooser.Player1.armor < ScreenChooser.Player1.MaxArmor)
							{
								ScreenChooser.Player1.money -= itens.get(3).price;
								ScreenChooser.Player1.armor += 10;
								if(ScreenChooser.Player1.armor > ScreenChooser.Player1.MaxArmor)
									ScreenChooser.Player1.armor = ScreenChooser.Player1.MaxArmor;
							}	
			        	}
			        	else if(selected[5])
			        	{
			        		
							if(ScreenChooser.Player1.money > itens.get(1).price && ScreenChooser.Player1.Health < ScreenChooser.Player1.MaxHp)
							{
								ScreenChooser.Player1.money -= itens.get(1).price;
								ScreenChooser.Player1.Health += 20;
								if(ScreenChooser.Player1.Health > ScreenChooser.Player1.MaxHp)
									ScreenChooser.Player1.Health = ScreenChooser.Player1.MaxHp;
							}
						}
			        	else if(selected[6])
			        	{
			        		
							if(ScreenChooser.Player1.money > itens.get(2).price && ScreenChooser.Player1.buildQuant < 10)
							{
								ScreenChooser.Player1.money -= itens.get(2).price;
								ScreenChooser.Player1.buildQuant += 1;
							}
							
			        	}
			        	else if(selected[7])
			        	{
							if(ScreenChooser.Player1.XP > (ScreenChooser.Player1.UpgACC+1)*100 + (ScreenChooser.Player1.UpgACC*250) && ScreenChooser.Player1.UpgACC < 3)
							{
								ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgACC+1)*100 + (ScreenChooser.Player1.UpgACC*250);
								ScreenChooser.Player1.ACCdefault -= 2;
								ScreenChooser.Player1.UpgACC += 1;
							}						
			        	}
			        	else if(selected[8])
			        	{
			        		if(ScreenChooser.Player1.money > itens.get(6).price)
							{
								int random = rdm.nextInt(8);
								ScreenChooser.Player1.buffsList.get(random).activeBuff = true;
								ScreenChooser.Player1.money -= itens.get(6).price;
							}	
			        	}    	
		        	}
	        	}
	        });
		
		final TextButton buttonEquip = new TextButton("Equip", Textures.btnBlue);
		buttonEquip.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonEquip.getWidth() && x >0 && y<buttonEquip.getHeight() && y > 0)
	        		if(ScreenChooser.Player1.hasGun[0] && selected[0]) 
	        			ScreenChooser.Player1.setCurGun(0);
	        		else if(ScreenChooser.Player1.hasGun[2] && selected[1])
	        			ScreenChooser.Player1.setCurGun(2);
	        		else if(ScreenChooser.Player1.hasGun[1] && selected[2])
	        			ScreenChooser.Player1.setCurGun(1);
	        	}
	        });
		buttonEquip.setBounds((float)btnExitPos.x+260, (float)btnExitPos.y, 110, 65);
		
		//Minigun
		TextButton imgMinigun = new TextButton("", Textures.btnMinigun);
		imgMinigun.setBounds(210, 350 , 140, 140);
		imgMinigun.setRotation(140);
		
		final TextButton buttonMinigun = new TextButton("", Textures.imgMinigun);
		buttonMinigun.setBounds(210, 350 , 140, 140);
		buttonMinigun.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonMinigun.getWidth() && x >0 && y<buttonMinigun.getHeight() && y > 0)
	        		selected[2] = true;
	        	}
	        });
		
		//Shotgun
		TextButton imgShotgun = new TextButton("", Textures.btnShotgun);
		imgShotgun.setBounds(135, 350 , 70, 120);
		imgShotgun.setRotation(140);
		
		final TextButton buttonShotgun = new TextButton("", Textures.imgShotgun);
		buttonShotgun.setBounds(135, 350 ,70, 120);
		buttonShotgun.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonShotgun.getWidth() && x >0 && y<buttonShotgun.getHeight() && y > 0)
	        		selected[1] = true;
	        	}
	        });
		
		TextButton imgPistol = new TextButton("", Textures.btnPistol);
		imgPistol.setBounds(70, 375 , 50, 90);
		imgPistol.setRotation(140);
		
		final TextButton buttonPistol = new TextButton("", Textures.imgPistol);
		buttonPistol.setBounds(70, 375 ,50, 90);
		buttonPistol.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonPistol.getWidth() && x >0 && y<buttonPistol.getHeight() && y > 0)
	        		selected[0] = true;
	        	}
	        });
		
		final TextButton buttonHealth = new TextButton("", Textures.btnHealth);
		buttonHealth.setBounds(375, 295 ,50, 50);
		buttonHealth.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonHealth.getWidth() && x >0 && y<buttonHealth.getHeight() && y > 0)
	        		selected[3] = true;
	        	}
	        });
		
		final TextButton buttonArmor = new TextButton("", Textures.btnArmor);
		buttonArmor.setBounds(575, 295 ,50, 50);
		buttonArmor.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonArmor.getWidth() && x >0 && y<buttonArmor.getHeight() && y > 0)
	        		selected[4] = true;
	        	}
	        });
		
		final TextButton buttonAmmo = new TextButton("", Textures.btnAmmo);
		buttonAmmo.setBounds(575, 245 ,50, 50);
		buttonAmmo.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonAmmo.getWidth() && x >0 && y<buttonAmmo.getHeight() && y > 0)
	        		selected[5] = true;
	        	}
	        });
		
		final TextButton buttonRes = new TextButton("", Textures.btnRes);
		buttonRes.setBounds(375, 245 ,50, 50);
		buttonRes.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonRes.getWidth() && x >0 && y<buttonRes.getHeight() && y > 0)
	        		selected[6] = true;
	        	}
	        });
		
		final TextButton buttonACC = new TextButton("", Textures.btnACC);
		buttonACC.setBounds(475, 245 ,50, 50);
		buttonACC.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonACC.getWidth() && x >0 && y<buttonACC.getHeight() && y > 0)
	        		selected[7] = true;
	        	}
	        });
		
		
		table.debug();
		stage.addActor(table);
		
		LabelStyle a = new LabelStyle();
		a.font = this.shopfont;
		
		table.addActor(buttonEquip);
		table.addActor(buttonBuy);
		table.addActor(buttonExit);
		table.addActor(imgMinigun);
		table.addActor(buttonMinigun);
		table.addActor(imgShotgun);
		table.addActor(buttonShotgun);
		table.addActor(imgPistol);
		table.addActor(buttonPistol);
		table.addActor(buttonHealth);
		table.addActor(buttonArmor);
		table.addActor(buttonAmmo);
		table.addActor(buttonRes);
		table.addActor(buttonACC);
		
		populateItens();
	}
	
	public void populateItens()
	{
		for(int i = 0; i < 9; i++)
			itens.add(new Item(i, ScreenChooser.Player1));
	}
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Conceito.batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
   
        
        Conceito.batch.end();
        Conceito.batch.begin();
        this.update();
		Conceito.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		Gdx.input.setInputProcessor(stage);
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
		shopfont.dispose();
		stage.dispose();
	}
	
	public void update(){
		mpos.x= Gdx.input.getX();
		mpos.y= Gdx.input.getY();
		
		System.out.println("mpos: "+ Gdx.input.getX() + " " + Gdx.input.getY() + "selected 3 = " + selected[3]);
		
		
		shopfont.draw(Conceito.batch,"Money = " + ScreenChooser.Player1.money + "$" ,650, 580); 
		shopfont.draw(Conceito.batch,"XP = " + ScreenChooser.Player1.XP ,650, 560); 
		shopfont.draw(Conceito.batch,"Health = " + ScreenChooser.Player1.Health + "/" + ScreenChooser.Player1.MaxHp ,650, 540); 
		shopfont.draw(Conceito.batch,"Armor = " + ScreenChooser.Player1.armor + "/" + ScreenChooser.Player1.MaxArmor ,650, 520); 
		
//		Textures.minigun.setPosition(70, 250);
//		Textures.minigun.setSize(140, 140);
//		Textures.minigun.setRotation(160);
//		Textures.minigun.draw(Conceito.batch);
		
//		Textures.shotgun.setPosition(135, 365);
//		Textures.shotgun.setSize(70, 120);
//		Textures.shotgun.draw(Conceito.batch);
		
//		Textures.pistol.setPosition(70, 375);
//		Textures.pistol.setSize(40, 80);
//		Textures.pistol.draw(Conceito.batch);
		
//		Textures.Medkit.setPosition(375, 297); //380 - 420, 255 - 300
//		Textures.Medkit.setSize(50, 50);
//		Textures.Medkit.draw(Conceito.batch);
		
//		Textures.Armor.setPosition(575, 297); // 575 - 620, 255 - 300
//		Textures.Armor.setSize(50, 50);
//		Textures.Armor.draw(Conceito.batch);
		
//		Textures.Ammo.setPosition(575, 242); //575- 620, 310-350
//		Textures.Ammo.setSize(50, 50);
//		Textures.Ammo.draw(Conceito.batch);
		
//		Textures.Resources.setPosition(375, 242); //380-420, 310-350
//		Textures.Resources.setSize(50, 50);
//		Textures.Resources.draw(Conceito.batch);
		
//		Textures.Accuracy.setPosition(475, 242); //475- 525, 310-350
//		Textures.Accuracy.setSize(50, 50);
//		Textures.Accuracy.draw(Conceito.batch);
		
		Textures.rdmBuff.setPosition(475, 193); //475-525, 360-400
		Textures.rdmBuff.setSize(50, 50);
		Textures.rdmBuff.draw(Conceito.batch);
		
		if(Gdx.input.justTouched())
		{
			if( (stage.hit((float)mpos.x, (float)mpos.y, true) == null))
			for(int i = 0; i <9 ; i++)
				selected[i] = false;
			
		}
		
		
		if(selected[0]== false && selected[1] == false && selected[2] == false && selected[3] == false  && selected[4] == false && selected[5] == false && selected[6] == false && selected[7] == false)
		{
			shopfont.draw(Conceito.batch,"Welcome Adventurer... Looking for any goods?" ,450, 135);
			shopfont.draw(Conceito.batch,"Just check on the item you are interested in!" ,450, 110);
//rdmbuff
//			if (mpos.x> 475 && mpos.x <525 && mpos.y >360 && mpos.y < 400)//475-525, 360-400
//			{
//				if (Gdx.input.justTouched()) {
//					selected[8] = true;
//				}
//			}
			
		}		
		else
		{
			shopfont.draw(Conceito.batch,"Press Esc to unselect the item" ,450, 45);
			if(selected[0] == true)
			{
				shopfont.draw(Conceito.batch,"Pistol" ,450, 135); 
				shopfont.draw(Conceito.batch,"Power: " + ScreenChooser.Player1.UpgPwrPistol +"/" + "3" ,450, 110);
				shopfont.draw(Conceito.batch,"Ammo: " + (ScreenChooser.Player1.InvListWeapons.get(0).ammoTotal + ScreenChooser.Player1.InvListWeapons.get(0).ammo) + "/" + ScreenChooser.Player1.InvListWeapons.get(0).Maxammo ,450, 65);
				if(ScreenChooser.Player1.UpgPwrPistol < 3)
					shopfont.draw(Conceito.batch,"Upgrade price: " + ((ScreenChooser.Player1.UpgPwrPistol+1*100) + (ScreenChooser.Player1.UpgPwrPistol*100)) + "XP" ,450, 95);
				else
					shopfont.draw(Conceito.batch,"Maxed out",450, 95);					
			}
			else if(selected[1] == true)
			{
				shopfont.draw(Conceito.batch,"Shotgun" ,450, 135); 
				if(ScreenChooser.Player1.hasGun[2])
				{
					shopfont.draw(Conceito.batch,"Power: " + ScreenChooser.Player1.UpgPwrShotgun +"/" + "3" ,450, 110);
					shopfont.draw(Conceito.batch,"Ammo: " + (ScreenChooser.Player1.InvListWeapons.get(2).ammoTotal + ScreenChooser.Player1.InvListWeapons.get(2).ammo) + "/" + ScreenChooser.Player1.InvListWeapons.get(2).Maxammo ,450, 65);
					if(ScreenChooser.Player1.UpgPwrShotgun < 3)
						shopfont.draw(Conceito.batch,"Upgrade price: " + ((ScreenChooser.Player1.UpgPwrShotgun+1*250) + (ScreenChooser.Player1.UpgPwrShotgun*100)) + "XP" ,450, 95);
					else
						shopfont.draw(Conceito.batch,"Maxed out",450, 95);	
				}
				else
					shopfont.draw(Conceito.batch,"Price: " + itens.get(7).price + "$" ,450, 110);

			}

			else if(selected[2] == true)
			{
				shopfont.draw(Conceito.batch,"Minigun" ,450, 135);
				if(ScreenChooser.Player1.hasGun[1])
				{
					shopfont.draw(Conceito.batch,"Power: " + ScreenChooser.Player1.UpgPwrMinigun +"/" + "3" ,450, 110);
					shopfont.draw(Conceito.batch,"Ammo: " + (ScreenChooser.Player1.InvListWeapons.get(1).ammoTotal + ScreenChooser.Player1.InvListWeapons.get(1).ammo) + "/" + ScreenChooser.Player1.InvListWeapons.get(1).Maxammo ,450, 65);
					if(ScreenChooser.Player1.UpgPwrMinigun < 3)
						shopfont.draw(Conceito.batch,"Upgrade price: " + ((ScreenChooser.Player1.UpgPwrMinigun+1*250) + (ScreenChooser.Player1.UpgPwrMinigun*100)) + "XP" ,450, 95);
					else
						shopfont.draw(Conceito.batch,"Maxed out",450, 95);
				}
				else
				{
					shopfont.draw(Conceito.batch,"Price: " + itens.get(8).price + "$"  ,450, 110);			
				}
			}
			else if(selected[3] == true)
			{
				shopfont.draw(Conceito.batch,"Medic Kit" ,450, 135);
				shopfont.draw(Conceito.batch,"Price: " + itens.get(0).price +"$" ,450, 95);
				shopfont.draw(Conceito.batch,"Description: +20 hp to you current." ,450, 65);	
			}
			else if(selected[4] == true)
			{
				shopfont.draw(Conceito.batch,"Armor" ,450, 135);
				shopfont.draw(Conceito.batch,"Price: " + itens.get(3).price + "$",450, 95);
				shopfont.draw(Conceito.batch,"Description: +10 Armor to you current." ,450, 65);
			}
			else if(selected[5] == true)
			{
				shopfont.draw(Conceito.batch,"Ammo" ,450, 135);
				shopfont.draw(Conceito.batch,"Price: " + itens.get(1).price ,450, 95);
				shopfont.draw(Conceito.batch,"Description: Temos de ver como fazer para as varias armas" ,450, 65);
			}
			else if(selected[6] == true)
			{
				shopfont.draw(Conceito.batch,"Resources" ,450, 135);
				shopfont.draw(Conceito.batch,"Price: " + itens.get(2).price + "$" ,450, 95);
				shopfont.draw(Conceito.batch,"Description: Adds 1 Resource for building purposes" ,450, 65);						
			}
			else if(selected[7] == true)
			{
				shopfont.draw(Conceito.batch,"Accuracy " + ScreenChooser.Player1.UpgACC + "/3" ,450, 135);
				if(ScreenChooser.Player1.UpgACC < 3){
					
					shopfont.draw(Conceito.batch,"Price: " + ((ScreenChooser.Player1.UpgACC+1)*100 + (ScreenChooser.Player1.UpgACC*250)) + "XP" ,450, 95);
					shopfont.draw(Conceito.batch,"Description: Adds 1 to your current accuracy" ,450, 65);	
				}
				else
					shopfont.draw(Conceito.batch,"Maxed out" ,450, 65);
			}
			else if(selected[8] == true)
			{
				shopfont.draw(Conceito.batch,"Random" ,450, 135);
				shopfont.draw(Conceito.batch,"Price: " + itens.get(6).price ,450, 95);
				shopfont.draw(Conceito.batch,"Description: Adds a random boon. It can be power, acc, extra money, etc." ,450, 65);
			}
		}
		
	
	}
		
	
	

	
	public void animateWaveIncoming(int WaveNr, int timer, SpriteBatch batch)
	{
		Quest.font.draw(batch,"Wave incomming! Number: " + Integer.toString(WaveNr),425, 508);
	}	

}
