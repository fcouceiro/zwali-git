package com.me.zwali;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor implements InputProcessor {

	Player Player1;
	public MyInputProcessor(Player pl)
	{
		Player1 = pl;
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
			if(!Conceito.buildMode) Conceito.buildMode = true;
			else if(Conceito.buildMode)
			{
				Conceito.buildMode = false;
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
			Conceito.armaActual = 0;
			if( Player1.setCurGun(0))
				Player1.image = Textures.playerPistolIM;
			
		}
		if(keycode == Keys.NUM_2)
		{
			Conceito.armaActual = 2;
			if( Player1.setCurGun(2) )
				Player1.image = Textures.playerShotGunIM;
			
		}
		if(keycode == Keys.NUM_3)
		
		{	
			Conceito.armaActual = 1; 
			if(Player1.setCurGun(1) )
				Player1.image = Textures.playerMachineGunIM;
			
		}
		if(keycode == Keys.P)
		{
				if(Conceito.STATE == 0){ Conceito.STATE = 4; Conceito.pausecoco = true;}
				else if(Conceito.STATE == 4) Conceito.STATE = 0;
		}
		if(keycode == Keys.DPAD_RIGHT)
		{
			if(Player1.pos.x >= 1550 && Player1.pos.x <= 1730 && Player1.pos.y >= 250 &&  Player1.pos.y <= 380) //Wiz area
			{
			Wizard.counter++;
			if(Wizard.counter == 9)
				Wizard.counter = 0;
	
			}
		}
		if(keycode == Keys.DPAD_LEFT)
		{
			if(Player1.pos.x >= 1550 && Player1.pos.x <= 1730 && Player1.pos.y >= 250 &&  Player1.pos.y <= 380)
			{
			
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
		return false;
	}

}
