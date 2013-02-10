package com.me.zwali;


import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;




public class Shop implements Screen{

	public static BitmapFont shopfont;
	Conceito MainGame;
	Vector mpos = new Vector(0,0);
	Vector exit = new Vector(0,0);
	Vector btnExitPos = new Vector(50, 50);

	boolean highlight[] = new boolean[3];
	boolean selected[] = new boolean[3];
	static boolean wizardmode = false;
	static Random rdm = new Random();
	
	Sprite btnH,btn, buyH, buy, equip, equipH;
	
	
	public Shop(Conceito Main)
	{
		MainGame = Main;
		shopfont = new BitmapFont(Gdx.files.internal("res/fonts/arial.fnt"),
		         Gdx.files.internal("res/fonts/arial.png"), false);
		
		TextureRegion btnHr = new TextureRegion(Textures.shopBtns,0,0,128,128);
		TextureRegion btnr = new TextureRegion(Textures.shopBtns,128,0,128,128);
		TextureRegion buyr = new TextureRegion(Textures.shopBtns,256,0,128,128);
		TextureRegion buyHr = new TextureRegion(Textures.shopBtns,384,0,128,128);
		TextureRegion equipr = new TextureRegion(Textures.shopBtns,512,0,128,128);
		TextureRegion equipHr = new TextureRegion(Textures.shopBtns,640,0,128,128);
		
		btnH = new Sprite(btnr);
		btn = new Sprite(btnHr);
		buyH = new Sprite(buyHr);
		buy = new Sprite(buyr);
		equipH = new Sprite(equipHr);
		equip = new Sprite(equipr);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) MainGame.setScreen(MainGame.questsScreen);
		Conceito.batch.begin();
		this.update();
		Conceito.batch.end();
		
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
		shopfont.dispose();
		btn.getTexture().dispose();
		btnH.getTexture().dispose();
		buy.getTexture().dispose();
		equip.getTexture().dispose();
	}
	
	public void update(){
		mpos.x= Gdx.input.getX();
		mpos.y= Gdx.input.getY();
		
		//System.out.println(mpos.x + "    " + mpos.y);
		
		this.draw();
		
		shopfont.draw(Conceito.batch,"Money = " + ScreenChooser.Player1.money + "$" ,650, 580); 
		shopfont.draw(Conceito.batch,"XP = " + ScreenChooser.Player1.XP ,650, 560); 
		Textures.minigun.setPosition(210, 370);
		Textures.minigun.setSize(70, 120);
		
		Textures.minigun.draw(Conceito.batch);
		
		if(mpos.x > btnExitPos.x && mpos.x < btnExitPos.x + 110 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y) 
		{
			highlight[0] = true;
			if(Gdx.input.justTouched()) this.MainGame.setScreen(this.MainGame.questsScreen);
		}
		else
			highlight[0] = false;
		
		if(selected[0]== false && selected[1] == false && selected[2] == false)
		{
			shopfont.draw(Conceito.batch,"Welcome Adventurer... Looking for any goods?" ,450, 135);
			shopfont.draw(Conceito.batch,"Just check on the item you are interested in!" ,450, 110);
		
		
			if(mpos.x > 55 && mpos.x < 130 && mpos.y < 260 && mpos.y > 100 )
			{
				if( Gdx.input.justTouched())
					{
						selected[0] = true;	
						selected[1] = false;
						selected[2] = false;
					}
			}
			else if(mpos.x > 130 && mpos.x < 210 && mpos.y < 260 && mpos.y > 100) //2nd cabide
			{
				if( Gdx.input.justTouched())
				{
					selected[0] = false;	
					selected[1] = true;
					selected[2] = false;
				}	
			}
			else if(mpos.x > 210 && mpos.x < 280 && mpos.y < 260 && mpos.y > 100)
			{
				if( Gdx.input.justTouched())
				{
					selected[0] = false;	
					selected[1] = false;
					selected[2] = true;
				}				
			}
			else if(mpos.x > 280 && mpos.x < 350 && mpos.y < 260 && mpos.y > 100)
			{
				shopfont.draw(Conceito.batch,"???" ,450, 135); 
				shopfont.draw(Conceito.batch,"No one really knows mutch about this item." ,450, 110);
				
			}
			else if(mpos.x > 375 && mpos.x < 405 && mpos.y < 320 && mpos.y > 350) //Primeiro slot segunda prateleira
			{
				shopfont.draw(Conceito.batch,"Health Potions" ,450, 135); 
				shopfont.draw(Conceito.batch,"+20 HP." ,450, 110);
				
			}			
		}		
		else
		{
			if(mpos.x > btnExitPos.x +140 && mpos.x < btnExitPos.x + 250 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y) 
			{
				highlight[1] = true;
			}
			else
				highlight[1] = false;
			if(mpos.x > btnExitPos.x +280 && mpos.x < btnExitPos.x + 400 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y) 
			{
				highlight[2] = true;
			}
			else
				highlight[2] = false;
			
			if(selected[0] == true)
			{
					shopfont.draw(Conceito.batch,"Pistol" ,450, 135); 
					shopfont.draw(Conceito.batch,"Power: " + ScreenChooser.Player1.UpgPwrPistol +"/" + "3" ,450, 110);
					shopfont.draw(Conceito.batch,"Upgrade price: " + ((ScreenChooser.Player1.UpgPwrPistol+1*100) + (ScreenChooser.Player1.UpgPwrPistol*100)) + "XP" ,450, 95);
						//Add butoes;
					if(ScreenChooser.Player1.UpgPwrPistol < 3 && ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrPistol+1)*100+ (ScreenChooser.Player1.UpgPwrPistol*100)))
					{
						if(mpos.x > btnExitPos.x +140 && mpos.x < btnExitPos.x + 250 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y && Gdx.input.justTouched())
							{
							ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgPwrPistol+1*100) + (ScreenChooser.Player1.UpgPwrPistol*100) ;
							ScreenChooser.Player1.UpgPwrPistol += 1;	
							ScreenChooser.Player1.InvListWeapons.get(0).power += 5;
							ScreenChooser.Player1.InvListWeapons.get(0).power2 += 5;
							}
					}
					else if(ScreenChooser.Player1.hasGun[0] && mpos.x > btnExitPos.x +280 && mpos.x < btnExitPos.x + 400 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y)
						ScreenChooser.Player1.setCurGun(0);
					if(!(mpos.x > 55 && mpos.x < 130 && mpos.y < 260 && mpos.y > 100) && Gdx.input.justTouched())
						{
						selected[0] = false;
						highlight[1] = false;
						highlight[2] = false;
						}
			}
			else if(selected[1] == true)
			{
				shopfont.draw(Conceito.batch,"Shotgun" ,450, 135); 
				if(ScreenChooser.Player1.hasGun[2])
				{
					shopfont.draw(Conceito.batch,"Power: " + ScreenChooser.Player1.UpgPwrShotgun +"/" + "3" ,450, 110);
					shopfont.draw(Conceito.batch,"Upgrade price: " + ((ScreenChooser.Player1.UpgPwrShotgun+1*250) + (ScreenChooser.Player1.UpgPwrShotgun*100)) + "XP" ,450, 95);
					if( ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrShotgun+1)*250 + ScreenChooser.Player1.UpgPwrShotgun*100))
						{
							if(ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrPistol+1)*100*2) && mpos.x > btnExitPos.x +140 && mpos.x < btnExitPos.x + 250 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y && Gdx.input.justTouched())
							{
								ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgPwrShotgun+1)*250 + ScreenChooser.Player1.UpgPwrShotgun*100 ;
								ScreenChooser.Player1.UpgPwrShotgun += 1;	
								ScreenChooser.Player1.InvListWeapons.get(2).power += 3;
								ScreenChooser.Player1.InvListWeapons.get(2).power2 += 3;
							}
						}
					else if(ScreenChooser.Player1.hasGun[2] && mpos.x > btnExitPos.x +280 && mpos.x < btnExitPos.x + 400 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y)
							ScreenChooser.Player1.setCurGun(2);
					
						
					if(!(mpos.x > 130 && mpos.x < 210 && mpos.y < 260 && mpos.y > 100) && Gdx.input.justTouched())
						{
						selected[1] = false;	
						highlight[1] = false;
						highlight[2] = false;
						}		
				}
				else
				{
					shopfont.draw(Conceito.batch,"Price: 1500$"  ,450, 110);
						if(ScreenChooser.Player1.money >= 1500 ) 
						{
								if(mpos.x > btnExitPos.x +140 && mpos.x < btnExitPos.x + 250 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y && Gdx.input.justTouched())
								{
									ScreenChooser.Player1.money -= 1500;
									ScreenChooser.Player1.CurGun = 2;
									ScreenChooser.Player1.hasGun[2] = true;
								}
								
							
						}
						if(!(mpos.x > 130 && mpos.x < 210 && mpos.y < 260 && mpos.y > 100) && Gdx.input.justTouched())
							{
							selected[1] = false;
							highlight[1] = false;
							highlight[2] = false;
						
							}
				}
			}
					
					else if(selected[2] == true)
					{
						shopfont.draw(Conceito.batch,"Minigun" ,450, 135);
						if(ScreenChooser.Player1.hasGun[1])
						{
							shopfont.draw(Conceito.batch,"Power: " + ScreenChooser.Player1.UpgPwrMinigun +"/" + "3" ,450, 110);
							shopfont.draw(Conceito.batch,"Upgrade price: " + ((ScreenChooser.Player1.UpgPwrMinigun+1*250) + (ScreenChooser.Player1.UpgPwrMinigun*100)) + "XP" ,450, 95);
							if( ScreenChooser.Player1.XP >= ((ScreenChooser.Player1.UpgPwrMinigun+1)*500 + ScreenChooser.Player1.UpgPwrMinigun*200))
							{
								if(mpos.x > btnExitPos.x +140 && mpos.x < btnExitPos.x + 250 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y && Gdx.input.justTouched())
								{
									ScreenChooser.Player1.XP -= (ScreenChooser.Player1.UpgPwrMinigun+1)*500 + ScreenChooser.Player1.UpgPwrMinigun*200 ;
									ScreenChooser.Player1.UpgPwrMinigun += 1;	
									ScreenChooser.Player1.InvListWeapons.get(2).power += 1;
									ScreenChooser.Player1.InvListWeapons.get(2).power2 += 1;
								}
							}
							else if(ScreenChooser.Player1.hasGun[1] && mpos.x > btnExitPos.x +280 && mpos.x < btnExitPos.x + 400 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y)
								ScreenChooser.Player1.setCurGun(1);
						}
						else
						{
							shopfont.draw(Conceito.batch,"Price: 7500$"  ,450, 110);
							if(ScreenChooser.Player1.money >= 7500) //add botoes
							{
								if(Gdx.input.justTouched() && mpos.x < btnExitPos.x + 250 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y)
								{
									ScreenChooser.Player1.money -= 7500;
									ScreenChooser.Player1.hasGun[1] = true;
								}
							}
						}
						if(!(mpos.x > 210 && mpos.x < 280 && mpos.y < 260 && mpos.y > 100) && Gdx.input.justTouched())
						{
							selected[2] = false;
							highlight[1] = false;
							highlight[2] = false;
						}
						
					}
				
					
				}
				
				
			}
		
	
	
	private void draw()
	{
		//Mudar a textura para textura da shop
		Textures.shopIM.setPosition(0,0);
		Textures.shopIM.setSize(800,600);
		Textures.shopIM.draw(Conceito.batch);
		
		if(highlight[0])
		{
			btnH.setPosition((float)btnExitPos.x,(float)btnExitPos.y);
			btnH.setSize(128,128);
			btnH.draw(Conceito.batch);
		}
		else
		{
			btn.setPosition((float)btnExitPos.x,(float)btnExitPos.y);
			btn.setSize(128,128);
			btn.draw(Conceito.batch);
		}
		if(highlight[1])
		{
			buyH.setPosition((float)btnExitPos.x +140, (float)btnExitPos.y + 65);
			buyH.setSize(128,128);
			buyH.draw(Conceito.batch);
		}
		else
		{
			buy.setPosition((float)btnExitPos.x +140, (float)btnExitPos.y + 65);
			buy.setSize(128,128);
			buy.draw(Conceito.batch);
		}
		if(highlight[2])
		{
			equipH.setPosition((float)btnExitPos.x +280, (float)btnExitPos.y + 65);
			equipH.setSize(128,128);
			equipH.draw(Conceito.batch);
		}
		else{
			equip.setPosition((float)btnExitPos.x +280, (float)btnExitPos.y + 65);
			equip.setSize(128,128);
			equip.draw(Conceito.batch);
			
			
		}
	}
	
//	public static void buy(Player pl)
//	{
//		switch(counter)
//		{
//
//		case 0:
//			if( pl.money >= 30 && pl.Health < 100)
//			{
//				pl.money-= itens.get(counter).price;
//				pl.Health += 20;
//				if(pl.Health > 100)
//					pl.Health = 100;
//			}
//			break;
//		case 1:
//			
//			if(pl.CurGun == 0 && pl.money >= 25)
//			{
//				pl.money -= 20;
//				pl.InvListWeapons.get(pl.CurGun).ammoTotal += pl.InvListWeapons.get(pl.CurGun).MAXCAR;
//			}
//			else if(pl.CurGun == 1 && pl.money >= 250)
//			{
//				pl.money -= 250;
//				pl.InvListWeapons.get(pl.CurGun).ammoTotal += pl.InvListWeapons.get(pl.CurGun).MAXCAR;
//			}
//			else if(pl.CurGun == 2 && pl.money >= 90)
//			{
//				pl.money -= 90;
//				pl.InvListWeapons.get(pl.CurGun).ammoTotal += pl.InvListWeapons.get(pl.CurGun).MAXCAR;
//			}
//			break;
//		case 2:
//			if( pl.money >= 40)
//			{
//				pl.money-= itens.get(counter).price;
//				pl.buildQuant += 1;
//			}
//			break;
//		case 3:
//			if( pl.money >=30 && pl.armor < 100)
//			{
//				pl.money -= itens.get(counter).price;
//				pl.armor += 20;
//				if(pl.armor > 100)
//				{
//					pl.armor = 100;
//				}
//			}
//			break;
//		case 4:
//			
//			switch(pl.CurGun){
//			case 0:
//				if(pl.UpgPwrPistol < 3)
//				{
//					if( pl.XP >= (pl.UpgPwrPistol+1)*100 + pl.UpgPwrPistol*100)
//					{
//						pl.XP -= (pl.UpgPwrPistol+1)*100 + pl.UpgPwrPistol*100 ;
//						pl.UpgPwrPistol += 1;	
//						pl.InvListWeapons.get(pl.CurGun).power += 5;
//						pl.InvListWeapons.get(pl.CurGun).power2 += 5;
//					}
//				}
//				break;
//			case 1:
//				if(pl.UpgPwrMinigun < 3)
//				{
//					if( pl.XP >= (pl.UpgPwrMinigun+1)*500  + pl.UpgPwrMinigun*200)
//					{
//						{
//						pl.XP-= (pl.UpgPwrMinigun+1)*500  + pl.UpgPwrMinigun*200;
//						pl.UpgPwrMinigun += 1;
//						pl.InvListWeapons.get(pl.CurGun).power += 1;
//						pl.InvListWeapons.get(pl.CurGun).power2 += 1;
//						}
//					}
//				}
//				
//				break;
//			case 2:
//				if(pl.UpgPwrShotgun < 3)
//				{
//					if( pl.XP >= (pl.UpgPwrShotgun+1)*250  + pl.UpgPwrShotgun*100 )
//					{
//						{
//						pl.XP -= ((pl.UpgPwrShotgun+1)*250  + pl.UpgPwrShotgun*100);
//						pl.UpgPwrShotgun += 1;
//						pl.InvListWeapons.get(pl.CurGun).power += 3;
//						pl.InvListWeapons.get(pl.CurGun).power2 += 3;
//						}
//					}
//				}
//				break;
//			}
//			
//			break;
//		case 5:
//			if(pl.UpgACC < 3 && pl.XP >= (pl.UpgACC+1)*100 + pl.UpgACC*250)
//			{
//			pl.XP-= ((pl.UpgACC+1)*100 + pl.UpgACC*250);
//			pl.ACCdefault -= 2;
//			pl.UpgACC += 1;
//			}
//			break;
//		case 6:
//			if( pl.money >= 60)
//			{
//				pl.money-= itens.get(counter).price;
//				int tasd = rdm.nextInt(8);
//				pl.buffsList.get(tasd).activeBuff = true;
//			}
//			break;
//		case 7:
//			if(pl.money >= 1500)
//			{
//				pl.money -= itens.get(counter).price;
//				pl.hasGun[2] = true;
//			}
//			break;
//		case 8:
//			if(pl.money >= 7500)
//			{
//				pl.money -= itens.get(counter).price;
//				pl.hasGun[1] = true;
//			}
//		}
//	}
	
	public void animateWaveIncoming(int WaveNr, int timer, SpriteBatch batch)
	{
		Quest.font.draw(batch,"Wave incomming! Number: " + Integer.toString(WaveNr),425, 508);
	}	

}
