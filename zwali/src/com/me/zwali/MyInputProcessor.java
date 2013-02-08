package com.me.zwali;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor implements InputProcessor {

	GameLoop GMl;
	Player Player1;
	
	public MyInputProcessor(GameLoop gml)
	{
		GMl = gml;
		Player1 = gml.Player1;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.A)
		{
			Player.setVelX( -1 );
		}
		if(keycode == Keys.D)
		{
			Player.setVelX( 1 );
		}
		if(keycode == Keys.W)
		{
			Player.setVelY( 1 );
		}
		if(keycode == Keys.S)
		{
			Player.setVelY (-1);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.A)
		{
			Player.setVelX( 0 );
		}
		if(keycode == Keys.D)
		{
			Player.setVelX( 0 );
		}
		if(keycode == Keys.W)
		{
			Player.setVelY( 0 );
		}
		if(keycode == Keys.S)
		{
			Player.setVelY( 0 );
		}
		if(keycode == Keys.B)
		{
			if(!GameLoop.buildMode) GameLoop.buildMode = true;
			else if(GameLoop.buildMode)
			{
				GameLoop.buildMode = false;
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

		if(keycode == Keys.R)
		{
			Player1.ReloadWeapon();
		}
		if(keycode == Keys.NUM_1)
		{
			GameLoop.armaActual = 0;
			if( Player1.setCurGun(0))
				Player1.image = Textures.playerPistolIM;
			
		}
		if(keycode == Keys.NUM_2)
		{
			GameLoop.armaActual = 2;
			if( Player1.setCurGun(2) )
				Player1.image = Textures.playerShotGunIM;
			
		}
		if(keycode == Keys.NUM_3)
		
		{	
			GameLoop.armaActual = 1; 
			if(Player1.setCurGun(1) )
				Player1.image = Textures.playerMachineGunIM;
			
		}
		if(keycode == Keys.P)
		{
			//Pause screen
		}
		if(keycode == Keys.DPAD_RIGHT)
		{
			if(Player1.pos.x >= 1550 +45 && Player1.pos.x <= 1730+45 && Player1.pos.y >= 250+45 &&  Player1.pos.y <= 380+45) //Wiz area
			{
			Wizard.counter++;
			if(Wizard.counter == 9)
				Wizard.counter = 0;
	
			}
		}
		if(keycode == Keys.DPAD_LEFT)
		{
			if(Player1.pos.x >= 1550+45 && Player1.pos.x <= 1730+45 && Player1.pos.y >= 250+45 &&  Player1.pos.y <= 380+45)
			{
				Wizard.counter--;
			if(Wizard.counter < 0)
				Wizard.counter = 8;
	
			}
		}	
		if(Wizard.wizardmode && keycode == Keys.ENTER)
		{
			Wizard.buy(Player1);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		
		if(amount == 1)
		{
			if(GameLoop.armaActual == 2) GameLoop.armaActual = 0;
			else GameLoop.armaActual++;
		}
		if(amount == -1)
		{
			if(GameLoop.armaActual == 0) GameLoop.armaActual = 2;
			else GameLoop.armaActual--;
		}
			
		return false;
	}

}
