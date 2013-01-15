
package com.me.zwali;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Conceito extends Game {
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static boolean hasKeyboard;
	
	Mainmenu mainmenu;
	Howtoplay howtoplaymenu;
	GameLoop gameloop;
	


	
	@Override
	public void create() {		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		try {
			Textures.loadTextures();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		batch.getTransformMatrix().setToTranslation(-1*(w/2), -1*(h/2), 0);
		shapeRenderer = new ShapeRenderer();
		
		
		mainmenu = new Mainmenu(this);
		howtoplaymenu = new Howtoplay(this);

		setScreen(mainmenu);

	}
	
//	public void generateNewGameLoop()
//	{
//		this.gameloop = null;
//		gameloop = new GameLoop(this);
//	}

	@Override
	public void dispose() {
		batch.dispose();
	
	}

}
