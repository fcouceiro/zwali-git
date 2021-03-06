package com.me.zwali;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MyInputProcessor implements InputProcessor {

	Quest GMl;
	Player Player1;
	
	public MyInputProcessor(Quest gml)
	{
		GMl = gml;
		Player1 = ScreenChooser.Player1;
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
		if(keycode == Keys.SPACE)
		{
			Player.setKick(true);
		}
		if(keycode == Keys.B)
		{
			if(!Quest.buildMode) Quest.buildMode = true;
			else if(Quest.buildMode)
			{
				Quest.buildMode = false;
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
		if(keycode == Keys.ESCAPE)
		{
			if(!GMl.paused){
			GMl.pause_menu.setVisible(true);
			GMl.paused = true;
			GMl.pause_menu.getColor().a = 0;
			GMl.pause_menu.addAction(Actions.fadeIn(0.5f));
			}
			else{
				GMl.paused = false;
				GMl.pause_menu.addAction(Actions.sequence(Actions.fadeOut(0.5f),Actions.hide()));
			}

		}

		if(keycode == Keys.R)
		{
			Player1.ReloadWeapon();
		}
		if(keycode == Keys.NUM_1)
		{
			Quest.armaActual = 0;
			if( Player1.setCurGun(0))
				Player1.image = Textures.playerPistolIM;
			
		}
		if(keycode == Keys.NUM_2)
		{
			Quest.armaActual = 2;
			if( Player1.setCurGun(2) )
				Player1.image = Textures.playerShotGunIM;
			
		}
		if(keycode == Keys.NUM_3)
		
		{	
			Quest.armaActual = 1; 
			if(Player1.setCurGun(1) )
				Player1.image = Textures.playerMachineGunIM;
			
		}
		if(keycode == Keys.P)
		{
			//Pause screen
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
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		
		if(amount == 1)
		{
			if(Quest.armaActual == 2) Quest.armaActual = 0;
			else Quest.armaActual++;
		}
		if(amount == -1)
		{
			if(Quest.armaActual == 0) Quest.armaActual = 2;
			else Quest.armaActual--;
		}
			
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		
		return false;
	}

}
