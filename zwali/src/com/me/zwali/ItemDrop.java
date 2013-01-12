package com.me.zwali;
import java.util.*;



public class ItemDrop
{
	Textures T;
	Random rdm;
	List<Item> itemstodrop = new ArrayList<Item>(10);
	List<Item> itemsdropped = new ArrayList<Item>(10);
	
	
	public ItemDrop(Textures t) 
	{
		this.T = t;
		rdm = new Random();
		//add items
	}

	public void update(Player pl,Vector disp)
	{
		
		for(Item item:itemsdropped)
		{
			item.draw(disp);
			if(item.Collide(pl))
			{
				item.alive = false;
				switch(item.Type)
				{
					case 0: // medkits
						int local= 5 + rdm.nextInt(11);
						pl.Health += local;
						if( pl.Health >100)
							pl.Health = 100;
					break;
					
					case 1: //ammo -> Vai pa arma seleccionada
						switch(pl.CurGun){
						case 0:
							pl.InvListWeapons.get(pl.CurGun).ammoTotal += 20;
							break;
						case 1:
							pl.InvListWeapons.get(pl.CurGun).ammoTotal += 25;
							break;
						case 2:
							pl.InvListWeapons.get(pl.CurGun).ammoTotal += 6;
							break;
							
						}
						
					break;
					
					case 2: // build
					
						pl.buildQuant++;
					break;	
				}
				
			}
			
		}
		
		for(int i = 0; i<itemsdropped.size(); i++)
		{
			if( !itemsdropped.get(i).getAlive())
			{
				itemsdropped.remove(i);
				i--;
			}
		}
		
	}
	
	public void drop(Vector pos, Vector disp, Player pl)
	{
		int rand = rdm.nextInt(9);
			if( rand<3)
			{
				if(rand == 2) //If it is supply
				{
					rand = rdm.nextInt(3); // You have 2/3 less chance of him to drop.
					if(rand == 0)
					{
						rand = 2;
					}
				}
				Item x = new Item (rand, T, pl) ;
				itemsdropped.add(x);
				itemsdropped.get(itemsdropped.indexOf(x)).Pos = pos;
				itemsdropped.get(itemsdropped.indexOf(x)).Pos.x += disp.x;
				itemsdropped.get(itemsdropped.indexOf(x)).Pos.y += disp.y;
				if(itemsdropped.size() > 10)
				{
					itemsdropped.remove(0);
				}
			}
	
		
	}
}
