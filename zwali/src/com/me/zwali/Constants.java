package com.me.zwali;

public class Constants {
	
	static enum achiev_types {Killstreak1,HealthMaster,Thekiller};
	
	int HEIGHT = 600;
	int WIDTH = 800;
	
	int XMARGIN = 150;
	int YMARGIN = 150;
	
	int XMIN = 50;
	int YMIN = 50;
	
	int FRAME_RATE = 60;
	
	int SPAWNRATE = 60;
	int FIRERATE = 15;


	int HEROI =	1;
	
	Cenario Home()
	{
		Cenario temp = new Cenario(5,Textures.qHome, new Vector(600,950), new Vector(950,750));
		temp.name = "Home";
		temp.Objects.add(new Vector(150,570));
		temp.Objects.add(new Vector(210,450));
		temp.Objects.add(new Vector(410,350));
		temp.Objects.add(new Vector(510,340));
		temp.Objects.add(new Vector(610,330));
		temp.Objects.add(new Vector(60,800));
		temp.Objects.add(new Vector(150,800));
		temp.Objects.add(new Vector(970,250));
		return temp;
	}
	
	Cenario Survival(Cenario bg)
	{
		Cenario temp = bg;
		temp.maxWaves = 0;
		temp.name = "Survival - " + bg.name;
		return temp;
	}
}
