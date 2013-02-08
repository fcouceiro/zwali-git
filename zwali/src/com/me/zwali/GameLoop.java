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

public class GameLoop implements Screen{

	public static BitmapFont font;
	
	Player Player1;
	List <CharSequence> Log = new ArrayList<CharSequence>();
	
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
	static boolean pausecoco = true;
	
	Collision col;
	
	List <Wave> waves = new ArrayList<Wave>(10);
	
	
	static boolean buildMode;
	boolean WarmUp = true;
	boolean WarmUpBegins = false;
	boolean justended = false;
	boolean survival = false;
	boolean debug = true;
	
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
	static int armaActual = 1;
	int radioactivetime = 0;
	int radioactivetimer = 60;
	int time_wiz = 0;
	int timer_wiz = 15;
	int nWavesCur = 2;
	int nWavesMAX = 2;
	int nWave =2;
	int Wavenr = 1;
	
	boolean waveincoming = false;
	RealCross Cross;
	Crosshair Barril;
	
	
	Vector MPOS;
	List <Bullet> bul = new ArrayList<Bullet>(10);
	List <Enemy> enem = new ArrayList<Enemy>(10);
	List <sangue> sangues = new ArrayList<sangue>(5);
	List<Enemy> dead_enemies = new ArrayList<Enemy>(5);
	
	Conceito MainGame;
	
	public GameLoop(Conceito main)
	{
		this.MainGame = main;
		Log.add("Bem-vindo ao Zwali! ");
		Log.add("Teste ");
		Log.add("Teste");
		
		
		font = new BitmapFont(Gdx.files.internal("res/fonts/arial.fnt"),
		         Gdx.files.internal("res/fonts/arial.png"), false);
		
		Player1 = new Player( new Vector(1024, 1024), 60);
		wizard = new Wizard(new Vector(1575, 1675), new Vector(90,90), Player1);
		Cross = new RealCross( new Vector(0,0), new Vector(2,20), Textures.CrossSide);

		Barril = new Crosshair( new Vector(0,0), new Vector(80,80), Textures.BarrelUnIM);
		
		col = new Collision();
		drop = new ItemDrop(t);
		
		
		waves.add(new Wave(new Vector( 200, 1800), 1,150));
		waves.add(new Wave(new Vector( 2048 -200, 1800), 1,150));
		
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
		backG.addOBJ( new StaticObj( new Vector( 1890, 340), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1928, 250), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 2008, 200), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1810, 380), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1810-80, 380), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1688, 300), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1608, 260), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1540, 260), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1500, 180), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1480, 100), new Vector( 80, 80), Textures.BarrelIM ));
		backG.addOBJ( new StaticObj( new Vector( 1480, 40), new Vector( 80, 80), Textures.BarrelIM ));
		
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
		
		invmenu = new InvMenu(Conceito.batch);
		
		for(int i = 0; i < 15; i++)
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
		}
				
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
		if(WarmUp)
		{
			if(!Wizard.wizardmode)
			{
				Log.add("Warmup - "+ (timerWarmup - timeWarmup)/60 + " secs left");
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
				while(((x >= 800 && x <= 1250) && (y >= 760 && y <= 1250)) || ((x >= 1440 && x <= 2050) && ( y >= 0 && y <= 453)) || x == Player1.pos.x && y == Player1.pos.y)
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
						A = Textures.zombie_type3;
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
		
		if(Player1.pos.x <= 180 || Player1.pos.x >= (2048-180) || Player1.pos.y <= 180 || Player1.pos.y >= (2048-180) || ((Player1.pos.x >= 180 && Player1.pos.x <= 680) && (Player1.pos.y >= 180 && Player1.pos.y <= 600)))
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
			
			if(enimio.pos.x < 0) enimio.kill();
			else if(enimio.pos.y < 0) enimio.kill();
			else if(enimio.pos.x > 2000) enimio.kill();
			else if(enimio.pos.y > backG.size.y) enimio.kill();
			
			if(enimio.getAlive())
			{
				enimio.draw(Disp,Conceito.batch);
				enimio.isRadioActive();			
			}
			else
			{
				dead_enemies.add(enimio);
			
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
		
			MainGame.setScreen(MainGame.mainmenu);
			this.hide();
		}
		
		
		
		

		Player1.draw(Disp,Conceito.batch);
		wizard.showup(Disp, Player1.pos, Player1,Conceito.batch);
		
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
			wizard.animateWaveIncoming(Wavenr, timeWarmup,Conceito.batch);
		}
		
		if(debug)
		{
			font.setColor(Color.RED);
			font.draw(Conceito.batch, "PPos.x: "+ Player1.pos.x, 15, 90);
			font.draw(Conceito.batch, "PPos.y: " + Player1.pos.y, 15, 75);
			font.setColor(Color.WHITE);
		}
		Conceito.batch.end();

		
		if(debug)
		{
		
		for(Enemy enimio:enem)
		{
			Conceito.shapeRenderer.begin(ShapeType.Line);
			Conceito.shapeRenderer.setColor(Color.RED);
			Vector2 enemy = new Vector2((float)(enimio.pos.x - backG.Display.x), (float)(enimio.pos.y-backG.Display.y) );
			Vector2 player = new Vector2((float)(Player1.pos.x-backG.Display.x), (float)(Player1.pos.y-backG.Display.y));
			
			Vector2 radar = new Vector2((float)(enemy.x - player.x),(float) (enemy.y - player.y ));
			radar.nor();
			radar = radar.mul(70);
			
			
			
			Conceito.batch.begin();
			//Conceito.batch.draw(Textures.Red, (float)radar.x, (float)radar.y);
			Textures.rdmBuff.setPosition((float)(radar.x + (player.x) - 10),(float) (radar.y + (player.y) - 17));
			Textures.rdmBuff.setSize(25, 25);
			
			Textures.rdmBuff.draw(Conceito.batch);
			
			Conceito.batch.end();
			
			
			
			
			
			//Conceito.shapeRenderer.line(player.x,player.y,enemy.x,enemy.y);
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
		
		if(wizard.getWizMode())
		{
				Conceito.shapeRenderer.begin(ShapeType.Box);
		Conceito.shapeRenderer.setColor(Color.WHITE);
		Conceito.shapeRenderer.box((float)(1585 - backG.Display.x), (float)(141 - backG.Display.y), 0, 155, 40, 0);
		Conceito.shapeRenderer.end();
		}
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
		Weapon wp1 = new Weapon(0, difficulty);
		Weapon wp2 = new Weapon(1, difficulty);
		Weapon wp3 = new Weapon(2, difficulty);
		Player1.addGun(wp1);
		Player1.addGun(wp2);
		Player1.addGun(wp3);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
		wizard = null;
		enem.clear();
		bul.clear();
		stats = null;
		Wavenr = 1;
		nWavesCur = 2;
		nWavesMAX = 2;
		nWave =2;
		WarmUp = true;
		font.dispose();
	}

}

class sangue
{
	Sprite blood;
	Vector pos;
	int time = 300;
	int timer = 0;
	int time_anim = 400;
	float alpha = 1.0f;
	
	public sangue(Sprite a, Vector pos)
	{
		this.blood = a;
		this.pos = pos;
	}
	
	void update(Background backG)
	{
		if(timer >= time)
		{
			if(timer >= time_anim);
			else{
				timer++;
				blood.setPosition((float)(pos.x - backG.Display.x), (float)(pos.y - backG.Display.y));
				blood.draw(Conceito.batch,alpha);
				alpha -= 0.01f;
			}
		}
		else
		{
			timer++;
			blood.setPosition((float)(pos.x - backG.Display.x), (float)(pos.y - backG.Display.y));
			blood.draw(Conceito.batch);
			
		}
	}
}