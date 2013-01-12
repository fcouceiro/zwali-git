package com.me.zwali;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;



public class Wizard {

	Texture t, tbox;
	Textures textures;
	Vector pos;
	Vector size;
	Text menu, instrucao, instrucao2, instrucao3, instrucao4, nome_item, power, accuracy;
	Text Wavenr;
	int counter = 0;
	boolean wizardmode = false;
	Random rdm = new Random();
	
	List <Item> itens = new ArrayList<Item>(10);
	
	public Wizard( Vector posk, Vector sizek, Textures T, Player pl)
	{
		this.textures = T;
		this.t = T.wizard;
		this.tbox = T.box;
		this.pos = posk;
		this.size = sizek;
		this.menu = new Text(T);
		this.instrucao = new Text(T);
		this.instrucao2 = new Text(T);
		this.instrucao3 = new Text(T);
		this.instrucao4 = new Text(T);
		this.nome_item = new Text(T);
		this.power = new Text(T);
		this.accuracy = new Text(T);
		this.Wavenr = new Text(T);
		
		itens.add(new Item(0, T, pl));
		itens.add(new Item(1, T, pl));
		itens.add(new Item(2, T, pl));
		itens.add(new Item(3, T, pl));
		itens.add(new Item(4, T, pl));
		itens.add(new Item(5, T, pl));
		itens.add(new Item(6, T, pl));
		itens.add(new Item(7, T, pl));
		itens.add(new Item(8, T, pl));
	}
	
	private void draw(Vector Disp, Vector playerpos, Player pl)
	{
		glLoadIdentity();
		glPushMatrix();

		//Page
		t.bind();
		glTranslatef((float)(pos.x - Disp.x),(float)(pos.y - Disp.y), 0.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(0, 0); // Upper-left
		glTexCoord2f(1, 0);
		glVertex2i((int)size.x, 0); // Upper-right
		glTexCoord2f(1, 1);
		glVertex2i((int)size.x,(int)size.y); // Bottom-right
		glTexCoord2f(0, 1);
		glVertex2i(0, (int)size.y);

		glEnd();
		glPopMatrix();
		
		if(playerpos.x >= 1550 && playerpos.x <= 1730 && playerpos.y >= 1550 && playerpos.y <= 1680) //�rea verde
		{
			wizardmode = true;
			this.menu.drawString("Welcome Adventurer" , new Vector(1490,1750), 10);
			this.menu.setPos(new Vector(1490 - Disp.x, 1750 - Disp.y));
			this.menu.draw();
			this.instrucao.drawString("ENTER - buy" , new Vector(1500,1760), 10);
			this.instrucao.setPos(new Vector(1500 - Disp.x, 1760 - Disp.y));
			this.instrucao.draw();
			this.instrucao2.drawString("<- -> - Navigate" , new Vector(1500,1770), 10);
			this.instrucao2.setPos(new Vector(1500 - Disp.x, 1770 - Disp.y));
			this.instrucao2.draw();
			
			//Box
			glLoadIdentity();
			glPushMatrix();
			tbox.bind();
			glTranslatef((float)(1765 - Disp.x),(float)(1620 - Disp.y), 0.0f);
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(0, 0); // Upper-left
			glTexCoord2f(1, 0);
			glVertex2i((int)size.x, 0); // Upper-right
			glTexCoord2f(1, 1);
			glVertex2i((int)size.x,(int)size.y); // Bottom-right
			glTexCoord2f(0, 1);
			glVertex2i(0, (int)size.y);
			glEnd();
			glPopMatrix();
			
			
			
			
			//item na box
			glLoadIdentity();
			glPushMatrix();
			(itens.get(counter)).img.bind();
			glTranslatef((float)(1774 - Disp.x),(float)(1628 - Disp.y), 0.0f);
			glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(0, 0); // Upper-left
			glTexCoord2f(1, 0);
			glVertex2i(73, 0); // Upper-right
			glTexCoord2f(1, 1);
			glVertex2i(73,74); // Bottom-right
			glTexCoord2f(0, 1);
			glVertex2i(0, 74);
			glEnd();
			glPopMatrix();
			
			switch(counter){
			case 4: //Se for power -> Buscar a arma equipada de momento e mostrar quantos upgrades tem
				switch(pl.CurGun){
				case 0:
					if(pl.UpgPwrPistol < 3)
					{
					this.power.drawString("Price: "+ ((pl.UpgPwrPistol+1)*100 + pl.UpgPwrPistol*100) + "XP", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
					this.power.draw();
					}
					else
						this.power.drawString("Price: ---", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
					this.power.draw();
					this.power.drawString("Power: " + pl.UpgPwrPistol + "/3", new Vector(1765 - Disp.x, 1720 - Disp.y), 10);
					this.power.setPos(new Vector(1765 - Disp.x, 1720 - Disp.y));
					this.power.draw();
					
					break;
				case 1:
					if(pl.UpgPwrMinigun < 3)
					{
					this.power.drawString("Price: "+ ((pl.UpgPwrMinigun+1)*500  + pl.UpgPwrMinigun*200) + "XP", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
					this.power.draw();
					}
					else
						this.power.drawString("Price: ---", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
					this.power.drawString("Power: " + pl.UpgPwrMinigun + "/3", new Vector(1765 - Disp.x, 1720 - Disp.y), 10);
					this.power.setPos(new Vector(1765 - Disp.x, 1720 - Disp.y));
					this.power.draw();
					
					break;
				case 2:
					if(pl.UpgPwrShotgun < 3)
					{
					this.power.drawString("Price: "+ ((pl.UpgPwrShotgun+1)*250  + pl.UpgPwrShotgun*100) + "XP", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
					this.power.draw();
					}
					else
						this.power.drawString("Price: ---", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
					this.power.drawString("Power: " + pl.UpgPwrShotgun + "/3", new Vector(1765 - Disp.x, 1720 - Disp.y), 10);
					this.power.setPos(new Vector(1765 - Disp.x, 1720 - Disp.y));
					this.power.draw();
					
					break;
				}
				break;
			case 5:
				this.accuracy.drawString("Accuracy: " + pl.UpgACC + "/3", new Vector(1765 - Disp.x, 1720 - Disp.y), 10);
				this.accuracy.setPos(new Vector(1765 - Disp.x, 1720 - Disp.y));
				this.accuracy.draw();
				if(pl.UpgACC < 3)
					this.accuracy.drawString("Price: " +  (((pl.UpgACC+1)*100) + (pl.UpgACC*250)) + "XP", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
				else
					this.power.drawString("Price: ---", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
				this.accuracy.draw();
				break;
			}
			
			this.instrucao3.drawString("Preview ",new Vector(1765 - Disp.x, 1600 - Disp.y), 10);
			this.instrucao3.setPos(new Vector(1765 - Disp.x, 1600 - Disp.y));
			this.instrucao3.draw();
			
			if( counter!= 4 && counter!= 5 && counter != 1)
			{
			//Instru��es da box
				
				
				this.instrucao4.drawString("Name - " + itens.get(counter).Name,new Vector(1765 - Disp.x, 1740 - Disp.y), 10); //Adicionar o item
				this.instrucao4.setPos(new Vector(1765 - Disp.x, 1720 - Disp.y));
				this.instrucao4.draw();
			
				this.instrucao4.drawString("Price: " + itens.get(counter).price + "$", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
				this.instrucao4.draw();
			}
			else if(counter == 1)
			{
				this.instrucao3.drawString("Preview ",new Vector(1765 - Disp.x, 1600 - Disp.y), 10);
				this.instrucao3.setPos(new Vector(1765 - Disp.x, 1600 - Disp.y));
				this.instrucao3.draw();
				this.instrucao4.drawString("Nome - " + itens.get(counter).Name,new Vector(1765 - Disp.x, 1740 - Disp.y), 10); //Adicionar o item
				this.instrucao4.setPos(new Vector(1765 - Disp.x, 1720 - Disp.y));
				this.instrucao4.draw();
				
				if(pl.CurGun == 0)
					this.instrucao4.drawString("Price: 20$", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
				else if(pl.CurGun == 1)
					this.instrucao4.drawString("Price: 250$", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
				else if(pl.CurGun == 1)
					this.instrucao4.drawString("Price: 90$", new Vector(1765 - Disp.x, 1740 - Disp.y), 10);
				this.instrucao4.draw();
			}
		}
		else wizardmode = false;
		
	}
	
	public void buy(Player pl)
	{
		switch(this.counter)
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
				int tasd = this.rdm.nextInt(8);
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
	
	public void animateWaveIncoming(int WaveNr, int timer)
	{
		
		glLoadIdentity();
		glPushMatrix();

		//Page
		if((timer/30) % 2 == 0)
			textures.wv1.bind();
		else
			textures.wv2.bind();
		
		int h = 228 / 2;
		int w = textures.wv1.getImageWidth() / 2 + 10;
		glTranslatef((float)(272),(float)(30), 0.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2i(0, 0); // Upper-left
		glTexCoord2f(1, 0);
		glVertex2i((int)w, 0); // Upper-right
		glTexCoord2f(1, 1);
		glVertex2i((int)w,(int)h); // Bottom-right
		glTexCoord2f(0, 1);
		glVertex2i(0, (int)h);

		glEnd();
		glPopMatrix();
		
		this.Wavenr.drawString(Integer.toString(WaveNr),new Vector(435, 100), 10);
		this.Wavenr.setPos(new Vector(432, 95));
		this.Wavenr.draw();
	}
	
	public void showup(Vector Disp, Vector playerpos, Player pl)	
	{
		this.draw(Disp, playerpos, pl);
	}
	
}