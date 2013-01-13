package com.me.zwali;

import com.badlogic.gdx.ApplicationListener;
import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Conceito implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	public static boolean hasKeyboard;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(800, 600);
		batch = new SpriteBatch();
		hasKeyboard = false;
		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {	
		input();
		hasKeyboard = false;
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
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
						bul.add ( new Bullet( dir, c, t.bulletIM, Player1.ShootSpeed(), Player1.ShootPower() , t));
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
							Vector PerpDir = new Vector ( c.y, -c.x);
							c.rotate(((float)(rdm.nextInt(201)-100))/100*Player1.accuracy);
							Vector k1 = new Vector( c );
							k1.rotate(10);
							Vector k2 = c;
							Vector k3 = new Vector( c);
							k3.rotate(-10);
							
							bul.add ( new Bullet( new Vector(dir), k1, t.bulletIM, Player1.ShootSpeed(), Player1.ShootPower() ,  t) );
							
							bul.add ( new Bullet( new Vector(dir), k2, t.bulletIM, Player1.ShootSpeed(), Player1.ShootPower() ,  t) );
		
							bul.add ( new Bullet( new Vector(dir), k3 , t.bulletIM, Player1.ShootSpeed(), Player1.ShootPower() ,  t) );
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

		
		
			
			if(hasKeyboard){
				if(Gdx.input.isKeyPressed(Keys.ESCAPE))//Keyboard.KEY_ESCAPE)
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
								Player1.image = t.playerPistolIM;
								break;
							case 1:
								Player1.image = t.playerMachineGunIM;
								break;
							case 2:
								Player1.image = t.playerShotGunIM;
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
						Player1.image = t.playerPistolIM;
					
				}
				if(Gdx.input.isKeyPressed(Keys.NUM_2))
				{
					armaActual = 2;
					if( Player1.setCurGun(2) )
						Player1.image = t.playerShotGunIM;
					
				}
				if(Gdx.input.isKeyPressed(Keys.NUM_3))
				
				{	
					armaActual = 1; 
					if(Player1.setCurGun(1) )
						Player1.image = t.playerMachineGunIM;
					
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
			}else{
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
		
		
	}
	
	
	@Override
	public void pause() {
	}
	

	@Override
	public void resume() {
	}
}
