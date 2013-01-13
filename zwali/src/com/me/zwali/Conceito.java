
package com.me.zwali;
import com.badlogic.gdx.ApplicationListener;

import java.io.FileNotFoundException;
import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Conceito implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	public static boolean hasKeyboard;
	static BitmapFont font;
	
	private Player Player1;
	Stack <CharSequence> Log = new Stack<CharSequence>();
	
	Wizard wizard;
	Textures t = new Textures();
	Constants consts = new Constants();
	Background backG;
	Stats stats = new Stats();
	Stats stats2;
	Random rdm;
	InvMenu invmenu;
	ItemDrop drop;
	Builder obra;
	Mainmenu mainmenu;
	Howtoplay howtoplaymenu;
	boolean pausecoco = true;
	
	Collision col;
	
	List <Wave> waves = new ArrayList<Wave>(10);
	
	
	
	boolean quit = false;
	boolean restart=false;
	boolean decision=false;
	boolean buildTrigger;
	boolean buildMode;
	boolean WarmUp = true;
	boolean WarmUpBegins = false;
	boolean justended = false;
	boolean survival = false;
	
	int difficulty;
	
	int MouseX, MouseY;	
	int HealthReg = 0;
	int STATE;
	int timerGun = 0;
	int timerEnem = 0;
	int timerRegeneration = 0;
	int timebuild = 0;
	int timerbuilt = 120;
	int timeWarmup = 0;
	int timerWarmup = 600;
	int armaActual = 1;
	int radioactivetime = 0;
	int radioactivetimer = 60;
	int time_wiz = 0;
	int timer_wiz = 15;
	int nWavesCur = 2;
	int nWavesMAX = 2;
	int nWave =2;
	int Wavenr = 1;

	
	Vector MPOS;
		
	List <Bullet> bul = new ArrayList<Bullet>(10);
	List <Enemy> enem = new ArrayList<Enemy>(10);
	
	int logLength = 5;
	
	float a = 0;
	long lastFrame;
	int fps;
	int fpstodisplay;
	long lastFPS;
	boolean waveincoming = false;
	RealCross Cross;
	Crosshair Barril;
	Achievements achieves = new Achievements();
	
	@Override
	public void create() {		
		Log.add("Bem-vindo ao Zwali! ");
		Log.add(" ");
		Log.add("");
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		
		batch = new SpriteBatch();
		batch.getTransformMatrix().setToTranslation(-1*(w/2), -1*(h/2), 0);
		
		hasKeyboard = false;
		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		try {
			Textures.loadTextures();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		font = new BitmapFont();
		
		STATE = 2;
		this.achieves.populate();
		Player1 = new Player( new Vector(1024, 1024), 60);
		wizard = new Wizard(new Vector(1575, 1675), new Vector(90,90), Player1);
		Cross = new RealCross( new Vector(0,0), new Vector(2,20), Textures.CrossSide);

		Barril = new Crosshair( new Vector(0,0), new Vector(80,80), Textures.BarrelUnIM);
		
		
		drop = new ItemDrop(t);
		
		
		waves.add(new Wave(new Vector( 200, 200), 1,150));
		waves.add(new Wave(new Vector( 2048 -200, 200), 1,150));
		
		stats.PlayerAtingido = 0;
		stats.PlayerDisparos = 0;
		stats.PlayerScore = 0;
		stats.PlayerTirosNoEnemy = 0;
		
		//Generating the background
		backG = new Background( Textures.backgroundIM, new Vector( 2048, 2048) );
		
		/*barreiras laterais/superiores/inferiores*/
		backG.addOBJ( new StaticObj( new Vector( -2, backG.size.y/2), new Vector( 4, backG.size.y), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( backG.size.x +2, backG.size.y/2), new Vector( 4, backG.size.y), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( backG.size.x/2, -2), new Vector( backG.size.x, 4), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( backG.size.x/2, backG.size.y + 2), new Vector(  backG.size.x, 4), Textures.BarrelIM ));

		
		
		//Barris de guardiï¿½â€¹o ao wizard
		backG.addOBJ( new StaticObj( new Vector( 1848, 1600), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1928, 1600), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 2008, 1600), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1768, 1620), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1768, 1700), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1688, 1740), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1628, 1740), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1540, 1720), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1500, 1800), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1480, 1880), new Vector( 80, 80), Textures.Red ));
		backG.addOBJ( new StaticObj( new Vector( 1480, 1960), new Vector( 80, 80), Textures.Red ));
		
		//SafeHouse. 4cantos: (834,834) (1214,834) (834,1214) (1214,1214)
		backG.addOBJ( new StaticObj( new Vector( 834, 834), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 914, 834), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 834, 914), new Vector( 80, 80), Textures.BarrelIM ));
		
		backG.addOBJ( new StaticObj( new Vector( 1214, 834), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1134, 834), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1214, 914), new Vector( 80, 80), Textures.BarrelIM ));
		
		backG.addOBJ( new StaticObj( new Vector( 834, 1214), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 834, 1134), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 914, 1214), new Vector( 80, 80), Textures.BarrelIM ));
		
		backG.addOBJ( new StaticObj( new Vector( 1214, 1214), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1134, 1214), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1214, 1134), new Vector( 80, 80), Textures.BarrelIM ));
		
		rdm = new Random();
		
		invmenu = new InvMenu(batch);
		//Ã�rvores
				for(int i = 0; i < 15; i++)
				{
					int size;
					size = rdm.nextInt(50) +50;
					
					int x =  rdm.nextInt(1800);
					int y =  rdm.nextInt(1800);
					while(((x >= 800 && x <= 1250) && (y >= 800 && y <= 1250)) || ((x >= 1440 && x <= 2050) && ( y >= 1600 && y <= 1960)))
					{
						x = rdm.nextInt(1800);
						y = rdm.nextInt(1800);
					}
					backG.addUnOBJ( new UnStaticObj(new Vector(x, y), new Vector( size, size), Textures.environment_Tree ,  (size+size) * 2) );
				}
				
				rdm = new Random();
				
		
				
				obra = new Builder();
			

				mainmenu = new Mainmenu(batch);
				howtoplaymenu = new Howtoplay(batch);
				
				col = new Collision();

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {	
		MouseX = Gdx.input.getX();
		MouseY = Gdx.input.getY();
		input();
		//hasKeyboard = false;
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	
		
		System.out.println(MouseX);
		System.out.println(MouseY);
		MPOS = new Vector( MouseX, MouseY);
		Cross.setPos( MPOS);

		if( STATE == 0)
		{
			this.gameLoop();
		}
		else if( STATE == 1)
		{

			font.draw(batch,"Wanna try again? Y - Yes N/esc - Exit", 100,300);
			if(decision)
			{
				if(restart)
				{
					restart = false;
					enem.clear();
					waves.clear();
					Wavenr = 1;
					nWavesCur = 2;
					nWavesMAX = 2;
					nWave =2;
					WarmUp = true;
				}
				else
				{
					quit = true;
				}
			}
		}
		else if(STATE == 2)
		{
			//Main menu
			 if(mainmenu.update(batch) == 0)
			 {

			 }
			 else if(mainmenu.update(batch) == 1) 
			 {
				 STATE = 0;
				 difficulty = 1;
				 
				 Weapon wp1 = new Weapon(0, difficulty);
					Weapon wp2 = new Weapon(1, difficulty);
					Weapon wp3 = new Weapon(2, difficulty);
					Player1.addGun(wp1);
					Player1.addGun(wp2);
					Player1.addGun(wp3);
			 }
			 else if(mainmenu.update(batch) == 2) 
			 {
				 STATE = 0; 
				 difficulty = 2;
				 
				 Weapon wp1 = new Weapon(0, difficulty);
					Weapon wp2 = new Weapon(1, difficulty);
					Weapon wp3 = new Weapon(2, difficulty);
					Player1.addGun(wp1);
					Player1.addGun(wp2);
					Player1.addGun(wp3);
			 }
			 else if(mainmenu.update(batch) == 3) 
			 {
				 STATE = 0;
				 difficulty = 3;
				 
				 Weapon wp1 = new Weapon(0, difficulty);
					Weapon wp2 = new Weapon(1, difficulty);
					Weapon wp3 = new Weapon(2, difficulty);
					Player1.addGun(wp1);
					Player1.addGun(wp2);
					Player1.addGun(wp3);
			 }
			 else if(mainmenu.update(batch) == 4) 
			 {
				 STATE = 0; 
				 
				 difficulty = 2;//Se for survival tem a dificuldade de regular. (Não esquecer de rondas infinitas)
				 survival = true;
				 	
				 Weapon wp1 = new Weapon(0, difficulty);
				 Weapon wp2 = new Weapon(1, difficulty);
				 Weapon wp3 = new Weapon(2, difficulty);
				 Player1.addGun(wp1);
				 Player1.addGun(wp2);
				 Player1.addGun(wp3);
			 }
			 
			 else if(mainmenu.update(batch) == 5) 
			 {
				 STATE = 3; 
			 }
			 
			 
				 
				 
		}
		else if(STATE == 3)
		{
			if(howtoplaymenu.update())
			{}
			else {STATE = 2;}
		}
		
		else if(STATE == 4)
		{
			//Pause menu
			
		}

		batch.end();
	}

	public void gameLoop()
	{	
			timerGun++;
			timerEnem++;		
			time_wiz++;
			if(WarmUp)
			{
				if(!wizard.wizardmode)
				{
					Log.add("Warmup - "+ (timerWarmup - timeWarmup)/60 + " secs left");
					timeWarmup++;
					System.out.println("Ta em warm up" + timeWarmup);
					if (timeWarmup >= 300 && timeWarmup < 600) {
						waveincoming = true;
						
					}
					else 
						{
						waveincoming = false;
						
						}
				}
				else
				{
					//System.out.println("Wiz area. Warm up freezed");
					
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
				Log.add("Bonus gold + 150");
				for(int i = 0; i < 7; i++)
				{
					int size;
					size = rdm.nextInt(50) +50;
					
					int x =  rdm.nextInt(1800);
					int y =  rdm.nextInt(1800);
					while(((x >= 800 && x <= 1250) && (y >= 800 && y <= 1250)) || ((x >= 1440 && x <= 2050) && ( y >= 1600 && y <= 1960)) || x == Player1.pos.x && y == Player1.pos.y)
					{
						x = rdm.nextInt(1800);
						y = rdm.nextInt(1800);
					}
					backG.addUnOBJ( new UnStaticObj(new Vector(x, y), new Vector( size, size), Textures.environment_Tree ,  (size+size) * 2) );
				}
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
							A = Textures.Red;
							break;
						}
								enem.add(new Enemy(new Vector(waves
										.get(i).pos), A, next, difficulty));
					}
					
					if (waves.get(i).empty()) 
					{
						nWavesCur--;
						nWave++;
						System.out.println("Wave "+waves.get(i).waveNR+"ended and there are "+nWavesCur );
						waves.add(new Wave( waves.get(i).pos, Wavenr, 150));
						waves.remove(waves.get(i));	
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
			
			
		
			
			for(int i = 0; i< enem.size(); i++ )
			{
				if(enem.get(i).getAlive())
				{
					enem.get(i).Update(Player1.getPos(), enem, i, backG);
				}
				
				
			}
			
			Vector Disp = backG.getDisp();
			
			MouseX = Gdx.input.getX();
			MouseY = consts.HEIGHT - Gdx.input.getY();
			
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
								
							}
							else
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
							stats.PlayerTirosNoEnemy++;
							bilio.kill(false);
							break;
						}
						
					}
				}
			}
			
			
			boolean alive  = Player1.Update(Disp, backG);
			
			backG.Update( Player1.pos, MPOS );
			

			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			backG.draw(batch);
			
			if(Player1.pos.x <= 180 || Player1.pos.x >= (2048-180) || Player1.pos.y <= 180 || Player1.pos.y >= (2048-180) || ((Player1.pos.x >= 180 && Player1.pos.x <= 680) && (Player1.pos.y >= 1450 && Player1.pos.y <= (2048-180))))
			{
				if(radioactivetime == radioactivetimer)
				{
					Player1.Health -= 5;
					radioactivetime = 0;
					this.Log.add("Danger - Nuclear Area");
				}
				else
					radioactivetime++;
			}
			
			
			//Build Mode
			
			if(buildMode)
			{
				Barril.draw(Disp,batch);
				
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
											obra.drawbar(MPOS, timebuild, timerbuilt,batch);
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
				Cross.draw(batch);
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
						bilio.draw(Disp,batch);
				}
			}
			
			drop.update(Player1, Disp,batch);
			
			for(Enemy enimio: enem)
			{
				
				if(enimio.pos.x < 0) enimio.kill();
				else if(enimio.pos.y < 0) enimio.kill();
				else if(enimio.pos.x > 2000) enimio.kill();
				else if(enimio.pos.y > backG.size.y) enimio.kill();
				
				if(enimio.getAlive())
				{
					enimio.draw(Disp,batch);
					enimio.isRadioActive();			
				}

				if(enimio.getAlive() && enimio.Collide(Player1))
				{
					Vector dir = new Vector( -(enimio.pos.x - Player1.pos.x), -(enimio.pos.y- Player1.pos.y));
					dir.normalize();
					Player1.recoil(dir, 30);
					Player1.subHealth(enimio.power);	
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
				System.out.println("Estatisticas: \nDisparos - "+stats.PlayerDisparos+"\nDisparos acertados - "+stats.PlayerTirosNoEnemy+"\nScore - "+stats.PlayerScore+"\nAtingido "+ stats.PlayerAtingido +" vezes");
				System.out.println("GAME OVER! O MUNDO TAMBEM FICA MELHOR SEM TI!!");
				System.out.println("CARREGA Y PARA RECOMECAR, N PARA TE ACORBARDARES E FUGIRES COM O RABINHO ENTRE AS PERNAS!");
				decision = false;
				STATE = 1;
			}
			
			
			
			

			Player1.draw(Disp,batch);
			wizard.showup(Disp, Player1.pos, Player1,batch);
			
			Player1.InvListWeapons.get(Player1.CurGun).Update();
			
		
			
			//String draw area
			updateLog();
			invmenu.update(this.buildMode);
			
			font.draw(batch,Integer.toString(Player1.Health) + " / " + Integer.toString(Player1.armor), 265,20);
			font.draw(batch,Integer.toString(Player1.buildQuant), 460,53);
			font.draw(batch,Integer.toString(Player1.money), 460,37);	
			font.draw(batch,Integer.toString(Player1.XP), 460,20);
			font.draw(batch,(Integer.toString(Player1.InvListWeapons.get(Player1.CurGun).ammo) + "/" + Player1.InvListWeapons.get(Player1.CurGun).ammoTotal), 20,20);
			//End
			
			
			
			//weapons on bar
			switch(Player1.CurGun)
			{
			case 0:
				Textures.draw(Textures.pistol,batch);
				break;
			case 1:
				Textures.draw(Textures.minigun, batch);
				break;
			case 2:
				Textures.draw(Textures.shotgun, batch);
				break;
			}
			
			if(waveincoming)
			{
				wizard.animateWaveIncoming(Wavenr, timeWarmup,batch);
			}
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	private void updateLog()
	{
		CharSequence tres = Log.get(2);
		CharSequence dois = Log.get(1);
		CharSequence um = Log.get(0);
		//Vector(583, 553)
		font.draw(batch, um, 583, 433);
		font.draw(batch, dois, 583, 448);
		font.draw(batch, tres, 583, 463);
	}
	
	private void input()
	{
		if( STATE == 0)
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
						Vector c = new Vector(MouseX - p.x + d.x , MouseY- p.y + d.y);
						c.normalize();
						Vector dir = new Vector(p.x + c.x*30, p.y + c.y*30 );
										
						c.rotate(((float)(rdm.nextInt(201)-100))/100*Player1.accuracy);
						
						if(!Player1.InvListWeapons.get(Player1.CurGun).lethalarea)
						{
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
						}
						else
						{
							
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

		
		
				if(Gdx.input.getInputProcessor().keyDown(Keys.ESCAPE))//Keyboard.KEY_ESCAPE)
				{
					quit = true;
				}
				if(Gdx.input.isKeyPressed(Keys.B))
				{
					if(!buildMode) buildMode = true;
					else if(buildMode)
					{
						buildMode = false;
						switch(Player1.CurGun)
						{
							case 0:
								Player1.image = Textures.playerPistolIM;
								break;
							case 1:
								Player1.image = Textures.playerMachineGunIM;
								break;
							case 2:
								Player1.image = Textures.playerShotGunIM;
								break;
						}
					}
				}
				if(Gdx.input.isKeyPressed(Keys.A))
				{
					Player1.setVelX( -1 );
				}
				if(Gdx.input.isKeyPressed(Keys.D))
				{
					Player1.setVelX( 1 );
				}
				if(Gdx.input.isKeyPressed(Keys.W))
				{
					Player1.setVelY( -1 );
				}
				if(Gdx.input.isKeyPressed(Keys.S))
				{
					Player1.setVelY (1);
				}
				if(Gdx.input.isKeyPressed(Keys.R))
				{
					if(Player1.InvListWeapons.get(armaActual).ammo != Player1.InvListWeapons.get(armaActual).MAXCAR)
					{
						Player1.InvListWeapons.get(armaActual).Reload();
					}
				}
				if(Gdx.input.isKeyPressed(Keys.NUM_1))
				{
					armaActual = 0;
					if( Player1.setCurGun(0))
						Player1.image = Textures.playerPistolIM;
					
				}
				if(Gdx.input.isKeyPressed(Keys.NUM_2))
				{
					armaActual = 2;
					if( Player1.setCurGun(2) )
						Player1.image = Textures.playerShotGunIM;
					
				}
				if(Gdx.input.isKeyPressed(Keys.NUM_3))
				
				{	
					armaActual = 1; 
					if(Player1.setCurGun(1) )
						Player1.image = Textures.playerMachineGunIM;
					
				}
			
				if(Gdx.input.isKeyPressed(Keys.P))
				{
					
						
						if(STATE == 0){ STATE = 4; pausecoco = true;}
						else if(STATE == 4) STATE = 0;
				}
				if(STATE == 1 && Gdx.input.isKeyPressed(Keys.Y))
				{
					decision = true;
					restart = true;
				}

				if(STATE == 1 && Gdx.input.isKeyPressed(Keys.N))
				{
					decision = true;
					restart = false;
					
				}
					
				if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && time_wiz >= timer_wiz)
				{
					if(Player1.pos.x >= 1550 && Player1.pos.x <= 1730 && Player1.pos.y >= 1550 && Player1.pos.y <= 1680) //Wiz area
					{
					wizard.counter++;
					if(wizard.counter == 9)
						wizard.counter = 0;
					time_wiz = 0;
					}
				}
				if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)  && time_wiz >= timer_wiz)
				{
					if(Player1.pos.x >= 1550 && Player1.pos.x <= 1730 && Player1.pos.y >= 1550 && Player1.pos.y <= 1680)
					{
					wizard.counter--;
					if(wizard.counter < 0)
						wizard.counter = 8;
					time_wiz = 0;
					}
				}	
				if(wizard.wizardmode && Gdx.input.isKeyPressed(Keys.ENTER))
				{
					wizard.buy(Player1);
				}
		
				if(Gdx.input.isKeyPressed(Keys.A))
				{
					Player1.setVelX( 0 );
				}
				if(Gdx.input.isKeyPressed(Keys.D))
				{
					Player1.setVelX( 0 );
				}
				if(Gdx.input.isKeyPressed(Keys.W))
				{
					Player1.setVelY( 0 );
				}
				if(Gdx.input.isKeyPressed(Keys.S))
				{
					Player1.setVelY( 0 );
				}
				if(Gdx.input.isKeyPressed(Keys.M))
				{
					buildTrigger = false;
				}
				if(Gdx.input.isKeyPressed(Keys.R))
				{
					Player1.ReloadWeapon();
				}
				
		
		
		
	}
	
	
	@Override
	public void pause() {
	}
	

	@Override
	public void resume() {
	}
}
