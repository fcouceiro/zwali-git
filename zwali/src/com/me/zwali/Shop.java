package com.me.zwali;


import java.util.ArrayList;
import java.util.List;
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

	boolean highlight = false;
	static boolean wizardmode = false;
	static Random rdm = new Random();
	
	Sprite btnH,btn;
	
	
	public Shop(Conceito Main)
	{
		MainGame = Main;
		shopfont = new BitmapFont(Gdx.files.internal("res/fonts/arial.fnt"),
		         Gdx.files.internal("res/fonts/arial.png"), false);
		
		TextureRegion btnHr = new TextureRegion(Textures.shopBtnExit,0,0,128,128);
		TextureRegion btnr = new TextureRegion(Textures.shopBtnExit,128,0,128,128);
		btnH = new Sprite(btnr);
		btn = new Sprite(btnHr);
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
	}
	
	public void update(){
		mpos.x= Gdx.input.getX();
		mpos.y= Gdx.input.getY();
		
		//System.out.println(mpos.x + "    " + mpos.y);
		
		this.draw();
		
		shopfont.draw(Conceito.batch,"Money = " + ScreenChooser.Player1.money + "$" ,650, 580); 
		shopfont.draw(Conceito.batch,"XP = " + ScreenChooser.Player1.XP ,650, 560); 
		
		if(mpos.x > 55 && mpos.x < 130 && mpos.y < 260 && mpos.y > 100)
		{
			shopfont.draw(Conceito.batch,"Pistol" ,450, 135); 
			shopfont.draw(Conceito.batch,"Power" + "/" + "3" ,450, 110);
			shopfont.draw(Conceito.batch,"Upgrade price:" + "XP" ,450, 95);
		}
		else if(mpos.x > 130 && mpos.x < 210 && mpos.y < 260 && mpos.y > 100) //2nd cabide
		{
			shopfont.draw(Conceito.batch,"Shotgun" ,450, 135); 
			if(ScreenChooser.Player1.hasGun[2])
			{
			shopfont.draw(Conceito.batch,"Power" + "/" + "3" ,450, 110);
			shopfont.draw(Conceito.batch,"Upgrade price:" + "XP" ,450, 95);
			}
			else
				shopfont.draw(Conceito.batch,"Price: 1500$"  ,450, 110);
		}
		else if(mpos.x > 210 && mpos.x < 280 && mpos.y < 260 && mpos.y > 100)
		{
			shopfont.draw(Conceito.batch,"Minigun" ,450, 135); 
			if(ScreenChooser.Player1.hasGun[1])
			{
			shopfont.draw(Conceito.batch,"Power" + "/" + "3" ,450, 110);
			shopfont.draw(Conceito.batch,"Upgrade price:" + "XP" ,450, 95);
			}
			else
				shopfont.draw(Conceito.batch,"Price: 7500$"  ,450, 110);
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
		else if(mpos.x > btnExitPos.x && mpos.x < btnExitPos.x + 110 && 600 - mpos.y < btnExitPos.y + 65 && 600 -mpos.y > btnExitPos.y) 
		{
			shopfont.draw(Conceito.batch,"Go back." ,450, 135);
			highlight = true;
			if(Gdx.input.justTouched()) this.MainGame.setScreen(this.MainGame.questsScreen);
		}
		else
		{
			shopfont.draw(Conceito.batch,"Welcome Adventurer... Looking for any goods?" ,450, 135);
			shopfont.draw(Conceito.batch,"Just check on the item you are interested in!" ,450, 110);
			highlight = false;
		}
		
	}
	
	private void draw()
	{
		//Mudar a textura para textura da shop
		Textures.shopIM.setPosition(0,0);
		Textures.shopIM.setSize(800,600);
		Textures.shopIM.draw(Conceito.batch);
		
		if(highlight)
		{
			btnH.setPosition((float)btnExitPos.x,(float)btnExitPos.y);
			btnH.setSize(128,128);
			btnH.draw(Conceito.batch);
		}
		else{
			btn.setPosition((float)btnExitPos.x,(float)btnExitPos.y);
			btn.setSize(128,128);
			btn.draw(Conceito.batch);
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
