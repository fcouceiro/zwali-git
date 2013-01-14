package com.me.zwali;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor implements InputProcessor {

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
