package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Menu implements Screen{

	private BitmapFont font = new BitmapFont(Gdx.files.internal("res/fonts/arial.fnt"),
	         Gdx.files.internal("res/fonts/arial.png"), false);
	
	List <Button> btns = new ArrayList<Button>(5);
	int mx=0,my=0;
	Conceito maingame;
	
	public Menu(Conceito maingame)
	{
		this.maingame = maingame;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		mx = Gdx.input.getX();
		my = 600 - Gdx.input.getY();
		
		Conceito.shapeRenderer.begin(ShapeType.Rectangle);
		for(Button b:btns)
		{
			Conceito.shapeRenderer.rect(b.Pos.x, b.Pos.y, b.size.x, b.size.y);
			if(Gdx.input.justTouched())
			{
				if(b.hit(mx, my))
				{
					if(b.screen != null) maingame.setScreen(b.screen);
				}
			}
		}
		Conceito.shapeRenderer.end();
		
		Conceito.batch.begin();
		for(Button b:btns)
		{
			font.draw(Conceito.batch, b.text, b.Pos.x + b.txtPosRel.x, b.Pos.y + b.txtPosRel.y);
		}
		Conceito.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
		
	}

}

