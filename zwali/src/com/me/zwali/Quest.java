package com.me.zwali;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.ruthlessgames.api.UI;

public class Quest extends UI{
	
	public static BitmapFont font;

	
	Player Player1;
	List <CharSequence> Log = new ArrayList<CharSequence>();
	List <Long> act_sounds = new ArrayList<Long>(10);
	
	Textures t = new Textures();
	Constants consts = new Constants();
	Background backG;
	Stats stats = new Stats();
	Stats stats2;
	Random rdm;
	InvMenu invmenu;
	ItemDrop drop;
	Builder obra;
	Kick KKick;
	static boolean pausecoco = true;
	
	Collision col;
	
	List <Wave> waves = new ArrayList<Wave>(10);
	
	
	static boolean buildMode;
	boolean WarmUp = true;
	boolean WarmUpBegins = false;
	boolean justended = false;
	boolean survival = false;
	boolean debug = false;
	boolean kick = false;
	
	int difficulty;
	
	int MouseX, MouseY;	
	int HealthReg = 0;
	int timerGun = 0;
	int timerEnem = 0;
	int timerRegeneration = 0;
	int timebuild = 0;
	int timerbuilt = 120;
	int timeWarmup = 0;
	int timerWarmup = 600;
	
	int timeKick = 0;
	int timerKick = 60;
	int timenotkick = 0;
	int timernotkick = 20;
	
	static int armaActual = 1;
	int radioactivetime = 0;
	int radioactivetimer = 60;
	int time_wiz = 0;
	int timer_wiz = 15;
	int nWavesCur = 2;
	int nWavesMAX = 2;
	int nWave =2;
	int Wavenr = 1;
	
	//maxWaves for current quest
	int maxWaves;
	
	int maxScore = 7000;
	boolean waveincoming = false;
	RealCross Cross;
	Crosshair Barril;
	
	
	Vector MPOS;
	List <Bullet> bul = new ArrayList<Bullet>(10);
	List <Enemy> enem = new ArrayList<Enemy>(10);
	List <sangue> sangues = new ArrayList<sangue>(5);
	List<Enemy> dead_enemies = new ArrayList<Enemy>(5);
	List<Explosion> explo = new ArrayList<Explosion>(5);
	
	Conceito MainGame;
	
	public Quest(Conceito main, Vector Wave1Pos, Vector Wave2Pos,int maxWaves)
	{
		super(Conceito.batch,font,false);
		this.MainGame = main;
		this.maxWaves = maxWaves;
		
		if(maxWaves == 0){
			this.survival = true;
			Gdx.app.log("Survival", "On");
		}
		
		Log.add("Bem-vindo ao Zwali! ");
		Log.add("Teste ");
		Log.add("Teste");

		font = main.font;
		Cross = new RealCross( new Vector(0,0), new Vector(2,20), Textures.CrossSide);

		Barril = new Crosshair( new Vector(0,0), new Vector(80,80), Textures.BarrelUnIM);
		
		col = new Collision();
		drop = new ItemDrop(t);
		
		KKick = new Kick(new Vector(0,0), new Vector(50, 50), true,Textures.BarrelIM);
		
		waves.add(new Wave(new Vector( Wave1Pos.x, Wave1Pos.y), 1,150));
		waves.add(new Wave(new Vector( Wave2Pos.x, Wave2Pos.y), 1,150));
		
		stats.PlayerAtingido = 0;
		stats.PlayerDisparos = 0;
		stats.PlayerScore = 0;
		stats.PlayerTirosNoEnemy = 0;
		
		//Generating the background
		backG = new Background( Textures.Red, new Vector( 1024, 1024) );
		
		/*barreiras laterais/superiores/inferiores*/
		backG.addOBJ( new StaticObj( new Vector(800, 150), new Vector(  200, 120),40, Textures.Red ) );
		backG.addOBJ( new StaticObj( new Vector( -20, backG.size.y/2), new Vector( 40, backG.size.y), 0,Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( backG.size.x +20, backG.size.y/2), new Vector( 40, backG.size.y),0, Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( backG.size.x/2, -20), new Vector( backG.size.x, 40),0, Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( backG.size.x/2, backG.size.y + 20), new Vector(  backG.size.x, 40),0, Textures.BarrelIM ));
		
		
		rdm = new Random();
		
		invmenu = new InvMenu(Conceito.batch);
		
		/*for(int i = 0; i < 15; i++)
		{
			int size;
			size = rdm.nextInt(50) +50;
			
			int x =  rdm.nextInt(1800);
			int y =  rdm.nextInt(1800);
			while(((x >= 800 && x <= 1250) && (y >= 760 && y <= 1250)) || ((x >= 1414 && x <= 2050) && ( y >= 0 && y <= 453)))
			{
				x = rdm.nextInt(1800);
				y = rdm.nextInt(1800);
			}
			backG.addUnOBJ( new UnStaticObj(new Vector(x, y), new Vector( size, size), Textures.environment_Tree ,  (size+size) * 2) );
		}*/
				
		obra = new Builder();
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		MouseX = Gdx.input.getX();
		MouseY = 600 - Gdx.input.getY();
		input();
		//hasKeyboard = false;
		Conceito.batch.begin();		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		MPOS = new Vector( MouseX, MouseY);
		Cross.setPos( MPOS);
		timerGun++;
		timerEnem++;		
		time_wiz++;
		
		if(!kick)
		{
			timenotkick++;
		}
		
		if(WarmUp)
		{
				if((timerWarmup - timeWarmup)%60 == 0 )
				{
					Log.add("Warmup - "+ (timerWarmup - timeWarmup)/60 + " secs left");
				}
				timeWarmup++;
				//System.out.println("Ta em warm up" + timeWarmup);
				if (timeWarmup >= 300 && timeWarmup < 600) {
					waveincoming = true;
					
				}
				else 
					{
					waveincoming = false;
					
					}
		}
		
		if(timeWarmup >= timerWarmup && WarmUp){
			WarmUp = false;
			justended = false;
			WarmUpBegins = false;
			timeWarmup = 0;
			Log.add("Warm Up finished");
			
			if(Wavenr % 5 == 0)
			{
			Log.add("Chegou a ronda " + Wavenr);
			Log.add("Bonus: +150$");
			this.showToast("Bonus: +150$", 3, new Vector2(200,500),true);
			
			/*for(int i = 0; i < 7; i++)
			{
				int size;
				size = rdm.nextInt(50) +50;
				
				int x =  rdm.nextInt(1800);
				int y =  rdm.nextInt(1800);
				while(((x >= 800 && x <= 1250) && (y >= 760 && y <= 1250)) || ((x >= 1440 && x <= 2050) && ( y >= 0 && y <= 453)) || x == Player1.pos.x && y == Player1.pos.y)
				{
					x = rdm.nextInt(1800);
					y = rdm.nextInt(1800);
				}
				backG.addUnOBJ( new UnStaticObj(new Vector(x, y), new Vector( size, size), Textures.environment_Tree ,  (size+size) * 2) );
			}*/
			}
			Wavenr++;
			
			
		}
		
		else if(!WarmUp && WarmUpBegins)
		{
			WarmUp = true;
			timeWarmup = 0;
			if(Wavenr % 5 == 0)
			{
				Player1.money += 150;
			}
		}
		
		
		
		//Spawn waves.
		if (waves.size()!=0 && !WarmUp && !justended)
		{
			for(int i = 0; i<nWavesCur; i++)
			{
				if (waves.get(i).empty()) 
				{
		
					nWavesCur--;
					nWave++;
					System.out.println("Wave "+waves.get(i).waveNR+"ended and there are "+nWavesCur );
					
					waves.add(new Wave( waves.get(i).pos, Wavenr, 150)); //adiciona uma nova
					
					waves.remove(waves.get(i));	//remove actual (empty)
				
					if(waves.get(i).waveNR == maxWaves +1 && !survival){
						MainGame.questsScreen.setScore(getScore(),this.maxScore);
						MainGame.setScreen(MainGame.questsScreen);
					}
				}
				
				if (waves.get(i).Update()) 
				{
					int next = waves.get(i).addEnemy();
					Sprite A = Textures.zombie_type1;
					switch(next)
					{
					case 1:
						break;
					case 2:
						A = Textures.zombie_type2;
						break;
					case 3:
						A = Textures.zombie_type3;
						break;
					}
							enem.add(new Enemy(new Vector(waves
									.get(i).pos), A, next, difficulty));
				}
				
				
				if( nWavesCur == 0)
				{
					justended = true;

					timeWarmup = 0;
					nWavesCur = nWavesMAX;
			
				}
			}
		}
		
		if(justended)
		{
			boolean A = true;
			for(int i = 0; i< enem.size(); i++ )
			{
				if(enem.get(i).getAlive())
				{
					A = false;
					break;
				}
			}
			
			if(A)
			{
				justended = false;
				WarmUpBegins = true;
			}
		}
		
		if(Player1.kick && timenotkick > timernotkick)
		{
			switch(Conceito.rdm.nextInt(4))
			{
				case 0:
					Sounds.ca_uhh.play();
					break;
				case 1:
					Sounds.ca_woa.play();
					break;
				case 2:
					Sounds.ca_yah.play();
					break;
			}
			kick = true;
			timeKick= 0;
			timenotkick = 0;
		}
		
		if(kick)
		{
			for(int i = 0; i< enem.size(); i++ )
			{
				if(enem.get(i).getAlive())
				{
					Vector Y =KKick.Impact(enem.get(i));
					
					if(Y.Size() > 0)
					{
						enem.get(i).recoil(Y);
						enem.get(i).DecreaseHealth(Player1.kickPower, Y);
					}
				}
			}
		}
		
		for(Explosion ex:explo)
		{
			Vector Y = ex.Impact(Player1);
			
			if(Player1.STATE != 2 && Y.Size() > 0)
			{
				Player1.recoilExp(Y, 4);
				Player1. subHealth((int)ex.power);
			}
			
			for(Enemy en: enem)
			{
				if(en.getAlive())
				{
					Y =ex.Impact(en);
					
					if(Y.Size() > 0)
					{
						en.recoilExp(Y);
						en.DecreaseHealth((int)ex.power, Y);
					}
				}
			}
		}
		
		for(int i = 0; i<explo.size(); i++)
		{
			explo.get(i).UP();
			
			if( explo.get(i).Alive == false)
			{
				explo.remove(i);
				i--;
			}
		}
		
		
		for(int i = 0; i< enem.size(); i++ )
		{
			if(enem.get(i).getAlive())
			{
				enem.get(i).Update(Player1.getPos(), enem, i, backG);
			}
			
		}
		
		Vector Disp = backG.getDisp();
		
		MouseX = Gdx.input.getX();
		MouseY = 600 -Gdx.input.getY();
		
		Barril.setPos( new Vector( MPOS.x + Disp.x, MPOS.y + Disp.y));

		
		for( Bullet bilio:bul)
		{
			if(bilio.getAlive())
			{
				bilio.Update(backG,Player1);
				for( Enemy enimio:enem)
				{
					if(enimio.getAlive() && bilio.Collide(enimio))
					{				

						switch(rdm.nextInt(2))
						{
							case 0:
								Sounds.zo_hurt2.play();
								break;
							case 1:
								Sounds.zo_hurt1.play();
								break;
						}
						
						if(Player1.ragemode)
						{
							enimio.Health-=(bilio.power + 20);
						}
						else
						{
						enimio.Health-=bilio.power;
						}
						
						if(enimio.Health > 0) 
						{
							Sprite a = null;
							if(enimio.Health < 10)
							{
								a = new Sprite(Textures.Sangue_3);
								}
							else if(enimio.Health < 60)
							{
								a = new Sprite(Textures.Sangue_2);
									}
							else if(enimio.Health < enimio.MaxHealth)
							{
								a = new Sprite(Textures.Sangue_1);
							}
							a.setPosition((float)(enimio.pos.x  - a.getWidth()/2 -  backG.Display.x), (float)(enimio.pos.y- a.getHeight()/2 -  backG.Display.y));
							a.setRotation((float)enimio.angle);
							sangues.add(new sangue(a,new Vector((float)(enimio.pos.x -a.getWidth()/2),(float)(enimio.pos.y - a.getHeight()/2))));
						
						}
						else{
							switch(rdm.nextInt(2))
							{
							case 0:
								Sounds.zo_bone_crush.play(0.1f);
								break;
							case 1:
								Sounds.zo_neck.play(0.1f);
								break;
							}
							
							Conceito.achiev_checker.update(Constants.achiev_types.Thekiller, 1);
							
						}
						
						
						stats.PlayerTirosNoEnemy++;
						bilio.kill(false);
						break;
					}
					
				}
			}
		}
		
		for(Enemy enimio: enem)
		{
			
			if(enimio.Health < 0)
			{
				drop.drop(new Vector(enimio.pos.x - Disp.x, enimio.pos.y - Disp.y), Disp, Player1);
				enimio.kill();
				
				switch(enimio.type)
				{
				case 1:
					Player1.XP += Player1.xpenemy1;
					break;
				case 2:
					Player1.XP += Player1.xpenemy2;
					break;
				case 3:
					Explosion A = new Explosion( enimio.pos, new Vector (20*enimio.power,20*enimio.power), Textures.Sangue_3 );
					A.SetExp(enimio.power, 10*enimio.power);
					explo.add(A);
					//System.out.println("ASDASDASDASDA");
					break;
				}
				
				Player1.money += rdm.nextInt(10) + Wavenr + Player1.moneybuff;
				stats.killstreakCont++;
				stats.PlayerScore++;
				if(stats.killstreakCont == 15)
				{
					Player1.ragemode = true;
					stats.killstreakCont = 0;
					this.Log.add("Rage on!");
				}
				if(Player1.ragemode)
				{
					Log.add("Enemy died with rage");
					Player1.ragemode = true;
				}
				else
				{
					Log.add("Enemy died");
				}
				
				Log.add("KillStreak "+ stats.killstreakCont);
			}
		}
		
		boolean alive  = Player1.Update(Disp, backG);
		
		if(kick)
		{
			timeKick++;
			
			KKick.Up(Player1.pos, Player1.angle);
			
			if(timeKick >= timerKick)
			{
				kick = false;
				timeKick =0;
				timenotkick = 0;
			}
			
		}
		
		backG.Update( Player1.pos, MPOS );
		

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		backG.draw(Conceito.batch);
		
		for(sangue b:sangues)
		{
			b.update(backG);
		}
		
		for(Enemy e:dead_enemies)
		{
			if(e != null)
			{
				if(!e.ready)
				e.dead_anim(Disp);
				else e = null;
			}
		}
		
		backG.draw_objs(Conceito.batch);
		
		
		
		//Build Mode
		
		if(buildMode)
		{
			Barril.draw(Disp,Conceito.batch);
			
			if(Gdx.input.isTouched())
			{
				boolean colision = false;
				
				if(  !col.BCool( Barril, Player1) )
				{
					for(Enemy ene: enem)
					{
						if( col.BCool(ene, Barril))
						{
							System.out.println("Inimigo");
							colision = true;
						}
					}
					
					if( colision == false)
					{
						for( StaticObj obj: backG.Objects)
						{
							if( col.BCool(obj, Barril))
							{
								System.out.println("OBJECTS");
								colision = true;
							}
						}
						
						if( colision == false)
						{
							for( UnStaticObj obj: backG.UnObjects)
							{
								if( col.BCool(obj, Barril))
								{
									System.out.println("UNSTATIC");
									colision = true;
								}
							}
							
							if( colision == false)
							{
								if (Player1.buildQuant >= 1) 
								{
									if (timebuild == timerbuilt) 
									{
										obra.addItem(backG, MPOS);
										Player1.buildQuant--;
										timebuild = 0;
										Log.add("Resource built");
									} else 
									{
										obra.drawbar(MPOS, timebuild, timerbuilt,Conceito.batch);
										Log.add("Building...");
										timebuild++;
										
									}
								}
								else
								{
									Log.add("You dont have ");
									Log.add("enough resources");
								}	
							}
						}
					}
				}
			}
		
		}
		else
		{
			Cross.setOpen(Player1, Disp);
			Cross.draw(Conceito.batch);
		}
		
	
		
		for( Bullet bilio:bul)
		{
			if(bilio.getAlive())
			{
				double x = bilio.getPos().x;
				double y = bilio.getPos().y;
				if( x> 2000)bilio.kill(false);
				else if(x<56) bilio.kill(false);
				else if(y<56) bilio.kill(false);
				else if(y>backG.size.y) bilio.kill(false);
				else
					bilio.draw(Disp,Conceito.batch);
			}
		}
		
		drop.update(Player1, Disp,Conceito.batch);
		
		for(Enemy enimio: enem)
		{
			Vector2 enemy = new Vector2((float)(enimio.pos.x - backG.Display.x), (float)(enimio.pos.y-backG.Display.y) );
			Vector2 player = new Vector2((float)(Player1.pos.x-backG.Display.x), (float)(Player1.pos.y-backG.Display.y));
			
			Vector2 radar = new Vector2((float)(enemy.x - player.x),(float) (enemy.y - player.y ));
			radar.nor();
			radar = radar.scl(70);
			
			float anlge = radar.angle();
			
			
		
			//Conceito.batch.draw(Textures.Red, (float)radar.x, (float)radar.y);
			Textures.radar.setPosition((float)(radar.x + (player.x) - 10),(float) (radar.y + (player.y) - 17));
			Textures.radar.setSize(25, 25);
			Textures.radar.setRotation(anlge - 90);
			Textures.radar.draw(Conceito.batch,0.5f);
			
			
			
			
			if(enimio.pos.x < 0) enimio.kill();
			else if(enimio.pos.y < 0) enimio.kill();
			else if(enimio.pos.x > 2000) enimio.kill();
			else if(enimio.pos.y > backG.size.y) enimio.kill();
			
			if(enimio.getAlive())
			{
				enimio.draw(Disp,Conceito.batch);		
			}
			else
			{
				dead_enemies.add(enimio);
				
			}

			if(enimio.getAlive() && enimio.Collide(Player1))
			{
				switch(rdm.nextInt(4))
				{
					case 0:
						act_sounds.add(Sounds.gh_aii.play());
						break;
					case 1:
						act_sounds.add(Sounds.gh_eeaah.play());
						break;
					case 2:
						act_sounds.add(Sounds.gh_hah.play());
						break;
					case 3:
						act_sounds.add(Sounds.gh_huh.play());
						break;
					case 4:
						act_sounds.add(Sounds.gh_uhh.play());
						break;
				}
				
			
				Vector dir = new Vector( -(enimio.pos.x - Player1.pos.x), -(enimio.pos.y- Player1.pos.y));
				dir.normalize();
				Player1.subHealth(enimio.power);
				Player1.recoil(dir, 30);
					
				stats.killstreakCont = 0;
				stats.PlayerAtingido++;
				
				enimio.Attack();
			}
			
			
			for(UnStaticObj objecto: backG.UnObjects)
				if(enimio.getAlive() && enimio.CollideUnstatic(objecto))
				{
					enimio.Attack();
					objecto.Health -= enimio.power;
					if(objecto.Health <= 0)
					{
						backG.UnObjects.remove(objecto);
						break;
					}
				}
		}
		
		for(Explosion ex:explo)
		{
			ex.draw(Disp, Conceito.batch);
		}
		
		for(int i = 0; i<enem.size(); i++)
		{
			if( enem.get(i).getAlive() == false)
			{
				enem.remove(i);
				i--;
			}
		}
		
		
		
		for(int i = 0; i<bul.size(); i++)
		{
			if( bul.get(i).getAlive() == false)
			{
				bul.remove(i);
				i--;
			}
		}


		if( ! alive)
		{
			Player1.timeron = false;
			Player1.speed = Player1.nSpeed;
			
			System.out.println("Estatisticas: \nDisparos - "+stats.PlayerDisparos+"\nDisparos acertados - "+stats.PlayerTirosNoEnemy+"\nScore - "+stats.PlayerScore+"\nAtingido "+ stats.PlayerAtingido +" vezes");
			System.out.println("GAME OVER! O MUNDO TAMBEM FICA MELHOR SEM TI!!");
			MainGame.setScreen(MainGame.gameover);
			this.hide();
		}
		
		
		
		

		Player1.draw(Disp,Conceito.batch);
		if(kick)
		{
			KKick.draw(Disp, Conceito.batch);
		}
		
		Player1.InvListWeapons.get(Player1.CurGun).Update(Player1,backG.Display);
		
	
		
		//String draw area
		
		invmenu.update(buildMode);
		updateLog();
		font.draw(Conceito.batch,Integer.toString(Player1.Health) + " / " + Integer.toString(Player1.armor), 265,20);
		font.draw(Conceito.batch,Integer.toString(Player1.buildQuant), 460,53);
		font.draw(Conceito.batch,Integer.toString(Player1.money), 460,37);	
		font.draw(Conceito.batch,Integer.toString(Player1.XP), 460,20);
		font.draw(Conceito.batch,(Integer.toString(Player1.InvListWeapons.get(Player1.CurGun).ammo) + "/" + Player1.InvListWeapons.get(Player1.CurGun).ammoTotal), 20,20);
		//End
		
		
		
		//weapons on bar
		switch(Player1.CurGun)
		{
		case 0:
			Textures.draw(Textures.pistol,Conceito.batch);
			break;
		case 1:
			Textures.draw(Textures.minigun, Conceito.batch);
			break;
		case 2:
			Textures.draw(Textures.shotgun, Conceito.batch);
			break;
		}
		
		if(waveincoming)
		{
			//font.draw(Conceito.batch,"Wave incomming! Number: " + Integer.toString(Wavenr),425, 508);
			this.showToast("Wave incomming! Number: " + Integer.toString(Wavenr), 5, new Vector2(200,500),false);
		}
		else toastHandler.setVisible(false);
		
		
		
		if(debug)
		{
			font.setColor(Color.RED);
			font.draw(Conceito.batch, "PPos.x: "+ Player1.pos.x, 15, 90);
			font.draw(Conceito.batch, "PPos.y: " + Player1.pos.y, 15, 75);
			font.setColor(Color.WHITE);
		}
		Conceito.batch.end();

		Conceito.achiev_checker.updateAll();
		
		if(debug)
		{
		
		for(Enemy enimio:enem)
		{
			Conceito.shapeRenderer.begin(ShapeType.Line);
			Conceito.shapeRenderer.setColor(Color.RED);
			Vector2 enemy = new Vector2((float)(enimio.pos.x - backG.Display.x), (float)(enimio.pos.y-backG.Display.y) );
			Vector2 player = new Vector2((float)(Player1.pos.x-backG.Display.x), (float)(Player1.pos.y-backG.Display.y));
			
		
			Conceito.shapeRenderer.line(player.x,player.y,enemy.x,enemy.y);
			Conceito.shapeRenderer.end();
		}
		
		Conceito.shapeRenderer.begin(ShapeType.Line);
		Conceito.shapeRenderer.setColor(Color.RED);
		Conceito.shapeRenderer.line(790,0,790,600);
		Conceito.shapeRenderer.line(10,0,10,600);
		Conceito.shapeRenderer.line(0,590,800,590);
		Conceito.shapeRenderer.line(790,10,0,10);
		Conceito.shapeRenderer.end();
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	private int getScore() {
		// TODO Auto-generated method stub
		return 1;
	}

	private void updateLog()
	{
		CharSequence tres = Log.get(Log.size() -1);
		CharSequence dois = Log.get(Log.size() -2);
		CharSequence um = Log.get(Log.size()-3);
		//Vector(583, 553)
		font.draw(Conceito.batch, um, 583, 50);
		font.draw(Conceito.batch, dois, 583, 36);
		font.draw(Conceito.batch, tres, 583, 22);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		if(Sounds.main_s.isPlaying()) Sounds.main_s.stop();
		MyInputProcessor inputProcessor = new MyInputProcessor(this);
		Gdx.input.setInputProcessor(inputProcessor);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		if(!Sounds.main_s.isPlaying() && this.MainGame.sound) Sounds.main_s.play();
		Gdx.app.log("wave", "Ended in wave " + this.Wavenr);
	}

	private void input()
	{
		if(!Player1.InvListWeapons.get(Player1.CurGun).lethalarea)
		{
			if(Gdx.input.isTouched())
			{
				if( !buildMode)
				{
					if(Player1.Shoot())
					{
						
						
						timerGun = 0;
						Vector p = Player1.getPos();
						Vector d = backG.getDisp(); 
						Vector c = new Vector(MouseX - p.x  + d.x , MouseY- p.y + d.y);
						c.normalize();
						Vector dir = new Vector(p.x  + c.x*30, p.y + c.y*30 );
										
						c.rotate(((float)(rdm.nextInt(201)-100))/100*Player1.accuracy);
						
					
						bul.add ( new Bullet( dir, c, Textures.bulletIM, Player1.ShootSpeed(), Player1.ShootPower()));
							switch(Player1.InvListWeapons.get(Player1.CurGun).Type)
							{
							case 0:
								//this.soundmanager.playFX(soundmanager.shot);
								break;
							case 1:
								//this.soundmanager.playFX(soundmanager.shot2);
								break;
							}
						
				
						
						int AddAC = 0;
						switch(Player1.CurGun)
						{
						case 0:
							AddAC = 10;
							break;
						case 1:
							AddAC = 3;
							break;
						case 2:
							AddAC = 7;
							break;
						}
						Player1.accuracy += AddAC;
						if(Player1.accuracy>Player1.ACCMAX)
						{
							Player1.accuracy=Player1.ACCMAX;
						}
						
						stats.PlayerDisparos++;
					}
				}
			}
	
		}
		else
		{
			if(Gdx.input.justTouched())
			{
				if( !buildMode)
				{
					if(Player1.Shoot())
					{
						timerGun = 0;
						Vector p = Player1.getPos();
						Vector d = backG.getDisp(); 
						Vector c = new Vector(MouseX - p.x + d.x , MouseY- p.y + d.y);
						c.normalize();
						Vector dir = new Vector(p.x + c.x*30, p.y + c.y*30 );
										
						c.rotate(((float)(rdm.nextInt(201)-100))/100*Player1.accuracy);
						
					
							c.rotate(((float)(rdm.nextInt(201)-100))/100*Player1.accuracy);
							Vector k1 = new Vector( c );
							k1.rotate(10);
							Vector k2 = c;
							Vector k3 = new Vector( c);
							k3.rotate(-10);
							
							bul.add ( new Bullet( new Vector(dir), k1, Textures.bulletIM, Player1.ShootSpeed(), Player1.ShootPower()) );
							
							bul.add ( new Bullet( new Vector(dir), k2, Textures.bulletIM, Player1.ShootSpeed(), Player1.ShootPower()) );
		
							bul.add ( new Bullet( new Vector(dir), k3 , Textures.bulletIM, Player1.ShootSpeed(), Player1.ShootPower()) );
							//this.soundmanager.playFX(soundmanager.shot);
					
						
						
						int AddAC = 0;
						switch(Player1.CurGun)
						{
						case 0:
							AddAC = 10;
							break;
						case 1:
							AddAC = 3;
							break;
						case 2:
							AddAC = 7;
							break;
						}
						Player1.accuracy += AddAC;
						if(Player1.accuracy>Player1.ACCMAX)
						{
							Player1.accuracy=Player1.ACCMAX;
						}
						
						stats.PlayerDisparos++;
					}
				}
			}
		}
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
		backG = null;
		Player1 = null;
		enem.clear();
		bul.clear();
		stats = null;
		Wavenr = 1;
		nWavesCur = 2;
		nWavesMAX = 2;
		nWave =2;
		WarmUp = true;
		
	}

}

