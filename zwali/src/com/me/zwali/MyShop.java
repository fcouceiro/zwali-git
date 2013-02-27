package com.me.zwali;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MyShop implements Screen{
	
	Conceito maingame;
	List <Button> btns = new ArrayList<Button>(5);
	BitmapFont shopfont;
	Sprite bgImage;
	Button btnSelected;
	
	public MyShop(Conceito main)
	{
		maingame = main;
		this.bgImage = Textures.shopIM;
		this.bgImage.setSize(800, 600);
		this.bgImage.setPosition(0, 0);
		shopfont = new BitmapFont(Gdx.files.internal("res/fonts/arial.fnt"),
		         Gdx.files.internal("res/fonts/arial.png"), false);
		
		Button minig = new Button(Textures.minigun,"minigun",new Vector2(70,250),new Vector2(140,140),new Vector2(0,0),null);
		minig.thumbnail.setRotation(160);
		Button shotgun = new Button(Textures.shotgun,"shotgun",new Vector2(135,365),new Vector2(70,120),new Vector2(0,0),null);
		Button pistol = new Button(Textures.pistol,"pistol",new Vector2(70,375),new Vector2(40,80),new Vector2(0,0),null);
		Button medkit = new Button(Textures.Medkit,"medkit",new Vector2(375,297),new Vector2(50,50),new Vector2(0,0),null);
		Button armor = new Button(Textures.Armor,"armor",new Vector2(575,297),new Vector2(50,50),new Vector2(0,0),null);
		Button ammo = new Button(Textures.Ammo,"ammo",new Vector2(575,242),new Vector2(50,50),new Vector2(0,0),null);
		Button resources = new Button(Textures.Resources,"resources",new Vector2(375,242),new Vector2(50,50),new Vector2(0,0),null);
		Button accuracy = new Button(Textures.Accuracy,"accuracy",new Vector2(475,242),new Vector2(50,50),new Vector2(0,0),null);
		Button rdmbuff = new Button(Textures.rdmBuff,"rdmBuff",new Vector2(475,193),new Vector2(50,50),new Vector2(0,0),null);
		
		btns.add(minig);
		btns.add(shotgun);
		btns.add(pistol);
		btns.add(medkit);
		btns.add(armor);
		btns.add(ammo);
		btns.add(resources);
		btns.add(accuracy);
		btns.add(rdmbuff);
		
		btnSelected = btns.get(0);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) maingame.setScreen(maingame.questsScreen);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Conceito.batch.begin();
		this.bgImage.draw(Conceito.batch);
		for(Button b:btns)
		{
			b.thumbnail.setPosition(b.Pos.x, b.Pos.y);
			b.thumbnail.setSize(b.size.x, b.size.y);
			b.thumbnail.draw(Conceito.batch);
			this.shopfont.draw(Conceito.batch, b.text, b.Pos.x + b.txtPosRel.x, b.Pos.y + b.txtPosRel.y);
			
			if(b.hit(Gdx.input.getX(), 600-Gdx.input.getY()))
			{
				if(b.description != null)
				this.shopfont.draw(Conceito.batch, b.description, 450, 135);
				
				if(Gdx.input.justTouched()){
					this.btnSelected = b;
				}
				
			}
			
		}
		Conceito.batch.end();
		
		
		System.out.println(this.btnSelected.text.toString());
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
