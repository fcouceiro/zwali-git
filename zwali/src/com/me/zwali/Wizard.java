package com.me.zwali;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



public class Wizard {

	Sprite t, tbox, itembox;
	
	Vector pos;
	Vector size;
	CharSequence menu, instrucao, instrucao2, instrucao3, instrucao4, nome_item, power, accuracy;
	CharSequence Wavenr;
	static int counter = 0;
	static boolean wizardmode = false;
	static Random rdm = new Random();
	ShapeRenderer shapeRenderer;
	
	static List <Item> itens = new ArrayList<Item>(10);
	
	public Wizard( Vector posk, Vector sizek, Player pl)
	{
		shapeRenderer = new ShapeRenderer();
		this.t = new Sprite(Textures.wizard);
		this.tbox = new Sprite(Textures.box);
		t.setOrigin(0, 0);
		t.setSize((float)sizek.x,(float)sizek.y);
		t.setPosition((float)posk.x, (float)posk.y);
		tbox.setOrigin(0, 0);
		tbox.setSize((float)sizek.x,(float)sizek.y);
		this.pos = posk;
		this.size = sizek;

		itens.add(new Item(0, pl));
		itens.add(new Item(1, pl));
		itens.add(new Item(2, pl));
		itens.add(new Item(3, pl));
		itens.add(new Item(4, pl));
		itens.add(new Item(5, pl));
		itens.add(new Item(6, pl));
		itens.add(new Item(7, pl));
		itens.add(new Item(8, pl));
	}
	
	private void draw(Vector Disp, Vector playerpos, Player pl, SpriteBatch batch)
	{
		
		
		//Page
		t.setPosition((float)(pos.x - Disp.x),(float)((-1400 + pos.y - Disp.y)));
		t.draw(batch);
		
		if(playerpos.x >= 1550 && playerpos.x <= 1730 && playerpos.y >= 250 && playerpos.y <= 420) //�rea verde
		{
			wizardmode = true;
			GameLoop.font.draw(batch,"> Welcome Adventurer" , (float)(1540 - Disp.x), (float)(300 - Disp.y));
			GameLoop.font.draw(batch,"> Hurry! Pick some of those items" , (float)(1540 - Disp.x), (float)(285 - Disp.y));
			// 
			GameLoop.font.draw(batch,"  and enhance your survival skills!" , (float)(1540 - Disp.x), (float)(270 - Disp.y));
			GameLoop.font.draw(batch,"[ENTER] - buy" , (float)(1790- Disp.x),(float)(-1585 + 1760 - Disp.y));
			GameLoop.font.draw(batch,"[<- ->] - Navigate" , (float)(1790 - Disp.x), (float)(1770- 1610 - Disp.y));
			
			
			//Box
			tbox.setPosition((float)(1795 - Disp.x), (float)(1760 - 1577 - Disp.y));
			tbox.draw(batch);
			
			
			
			//item na box
			itens.get(counter).img.setSize(73,74);
			itens.get(counter).img.setPosition((float)(1804 - Disp.x),(float)(1760 - 1569 - Disp.y));
			(itens.get(counter)).img.draw(batch);
			
			
			switch(counter){
			case 4: //Se for power -> Buscar a arma equipada de momento e mostrar quantos upgrades tem
				switch(pl.CurGun){
				case 0:
					if(pl.UpgPwrPistol < 3)
					{
						
						GameLoop.font.draw(batch, "Price: "+ (Integer.toString((pl.UpgPwrPistol+1)*100 + pl.UpgPwrPistol*100)) + "XP", (float)(1590 - Disp.x), (float)(175 - Disp.y));
		
					}
					else
						GameLoop.font.draw(batch, "Price: ---", (float)(1590 - Disp.x), (float)(175 - Disp.y));
					
					GameLoop.font.draw(batch, "Power: " + pl.UpgPwrPistol + "/3", (float)(1590 - Disp.x), (float)(160 - Disp.y));
					
					
					break;
				case 1:
					if(pl.UpgPwrMinigun < 3)
					{
						GameLoop.font.draw(batch, "Price: "+ ((pl.UpgPwrMinigun+1)*500  + pl.UpgPwrMinigun*200) + "XP", (float)(1590 - Disp.x), (float)( 175 - Disp.y));
					}
					else
						GameLoop.font.draw(batch, "Price: ---", (float)(1590 - Disp.x), (float)(1750 - 1455 - Disp.y));
					GameLoop.font.draw(batch, "Power: " + pl.UpgPwrMinigun + "/3", (float)(1590 - Disp.x), (float)(160 - Disp.y));		
					break;
				case 2:
					if(pl.UpgPwrShotgun < 3)
					{
						GameLoop.font.draw(batch, "Price: "+ ((pl.UpgPwrShotgun+1)*250  + pl.UpgPwrShotgun*100) + "XP", (float)(1590 - Disp.x), (float)(175 - Disp.y));
					}
					else
						GameLoop.font.draw(batch, "Price: ---", (float)(1590 - Disp.x), (float)(1750 - 1455 - Disp.y));
					GameLoop.font.draw(batch, "Power: " + pl.UpgPwrShotgun + "/3",(float)(1590 - Disp.x), (float)(160 - Disp.y));					
					break;
				}
				break;
			case 5:
				GameLoop.font.draw(batch, "Accuracy: " + pl.UpgACC + "/3", (float)(1590 - Disp.x), (float)(160 - Disp.y));
				if(pl.UpgACC < 3)
					GameLoop.font.draw(batch, "Price: " +  (((pl.UpgACC+1)*100) + (pl.UpgACC*250)) + "XP", (float)(1590 - Disp.x),(float)( 175 - Disp.y));
				else
					GameLoop.font.draw(batch, "Price: ---", (float)(1590 - Disp.x), (float)(175 - Disp.y));
				break;
			}
			
			//Conceito.font.draw(batch, "Preview ",(float)(1790 - Disp.x), (float)(1750 - 1442 - Disp.y));
			
			if( counter!= 4 && counter!= 5 && counter != 1)
			{
			//Instru��es da box
				
				
				GameLoop.font.draw(batch, "Name:  " + itens.get(counter).Name,(float)(1590 - Disp.x), (float)(160 - Disp.y)); //Adicionar o item
				
			
				GameLoop.font.draw(batch, "Price: " + itens.get(counter).price + "$", (float)(1590 - Disp.x), (float)(175 - Disp.y));
			}
			else if(counter == 1)
			{
				//Conceito.font.draw(batch, "Preview ",(float)(1765 - Disp.x), (float)(1600 - Disp.y));
				GameLoop.font.draw(batch,"Name: " + itens.get(counter).Name,(float)(1590 - Disp.x), (float)(160 - Disp.y)); //Adicionar o item
				
				
				if(pl.CurGun == 0)
					GameLoop.font.draw(batch,"Price: 20$", (float)(1590 - Disp.x), (float)(175 - Disp.y));
				else if(pl.CurGun == 1)
					GameLoop.font.draw(batch, "Price: 250$", (float)(1590 - Disp.x), (float)(175 - Disp.y));
				else if(pl.CurGun == 1)
					GameLoop.font.draw(batch, "Price: 90$", (float)(1590 - Disp.x), (float)(175 - Disp.y));
				
			}
		}
		else wizardmode = false;
		

	}
	
	public static void buy(Player pl)
	{
		switch(counter)
		{

		case 0:
			if( pl.money >= 30 && pl.Health < 100)
			{
				pl.money-= itens.get(counter).price;
				pl.Health += 20;
				if(pl.Health > 100)
					pl.Health = 100;
			}
			break;
		case 1:
			
			if(pl.CurGun == 0 && pl.money >= 25)
			{
				pl.money -= 20;
				pl.InvListWeapons.get(pl.CurGun).ammoTotal += pl.InvListWeapons.get(pl.CurGun).MAXCAR;
			}
			else if(pl.CurGun == 1 && pl.money >= 250)
			{
				pl.money -= 250;
				pl.InvListWeapons.get(pl.CurGun).ammoTotal += pl.InvListWeapons.get(pl.CurGun).MAXCAR;
			}
			else if(pl.CurGun == 2 && pl.money >= 90)
			{
				pl.money -= 90;
				pl.InvListWeapons.get(pl.CurGun).ammoTotal += pl.InvListWeapons.get(pl.CurGun).MAXCAR;
			}
			break;
		case 2:
			if( pl.money >= 40)
			{
				pl.money-= itens.get(counter).price;
				pl.buildQuant += 1;
			}
			break;
		case 3:
			if( pl.money >=30 && pl.armor < 100)
			{
				pl.money -= itens.get(counter).price;
				pl.armor += 20;
				if(pl.armor > 100)
				{
					pl.armor = 100;
				}
			}
			break;
		case 4:
			
			switch(pl.CurGun){
			case 0:
				if(pl.UpgPwrPistol < 3)
				{
					if( pl.XP >= (pl.UpgPwrPistol+1)*100 + pl.UpgPwrPistol*100)
					{
						pl.XP -= (pl.UpgPwrPistol+1)*100 + pl.UpgPwrPistol*100 ;
						pl.UpgPwrPistol += 1;	
						pl.InvListWeapons.get(pl.CurGun).power += 5;
						pl.InvListWeapons.get(pl.CurGun).power2 += 5;
					}
				}
				break;
			case 1:
				if(pl.UpgPwrMinigun < 3)
				{
					if( pl.XP >= (pl.UpgPwrMinigun+1)*500  + pl.UpgPwrMinigun*200)
					{
						{
						pl.XP-= (pl.UpgPwrMinigun+1)*500  + pl.UpgPwrMinigun*200;
						pl.UpgPwrMinigun += 1;
						pl.InvListWeapons.get(pl.CurGun).power += 1;
						pl.InvListWeapons.get(pl.CurGun).power2 += 1;
						}
					}
				}
				
				break;
			case 2:
				if(pl.UpgPwrShotgun < 3)
				{
					if( pl.XP >= (pl.UpgPwrShotgun+1)*250  + pl.UpgPwrShotgun*100 )
					{
						{
						pl.XP -= ((pl.UpgPwrShotgun+1)*250  + pl.UpgPwrShotgun*100);
						pl.UpgPwrShotgun += 1;
						pl.InvListWeapons.get(pl.CurGun).power += 3;
						pl.InvListWeapons.get(pl.CurGun).power2 += 3;
						}
					}
				}
				break;
			}
			
			break;
		case 5:
			if(pl.UpgACC < 3 && pl.XP >= (pl.UpgACC+1)*100 + pl.UpgACC*250)
			{
			pl.XP-= ((pl.UpgACC+1)*100 + pl.UpgACC*250);
			pl.ACCdefault -= 2;
			pl.UpgACC += 1;
			}
			break;
		case 6:
			if( pl.money >= 60)
			{
				pl.money-= itens.get(counter).price;
				int tasd = rdm.nextInt(8);
				pl.buffsList.get(tasd).activeBuff = true;
			}
			break;
		case 7:
			if(pl.money >= 1500)
			{
				pl.money -= itens.get(counter).price;
				pl.hasGun[2] = true;
			}
			break;
		case 8:
			if(pl.money >= 7500)
			{
				pl.money -= itens.get(counter).price;
				pl.hasGun[1] = true;
			}
		}
	}
	
	public void animateWaveIncoming(int WaveNr, int timer, SpriteBatch batch)
	{
		
		

		//Page
		Sprite a = null;
		if((timer/30) % 2 == 0)
			a = Textures.wv1;
		else
			a = Textures.wv2;
		
		

		a.setSize(248,114);
		a.setPosition((float)(272),(float)(570 - a.getHeight()));
		a.draw(batch);
		
		GameLoop.font.draw(batch,Integer.toString(WaveNr),425, 508);
	}
	
	public void showup(Vector Disp, Vector playerpos, Player pl, SpriteBatch batch)	
	{
		this.draw(Disp, playerpos, pl, batch);
	}
	
	public boolean getWizMode()
	{
		return wizardmode;
	}
}
