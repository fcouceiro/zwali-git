package com.me.zwali;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Button
{
	Sprite thumbnail;
	CharSequence text;
	Vector2 Pos;
	Vector2 size;
	Vector2 txtPosRel;
	Screen screen;
	
	public Button(Sprite t,CharSequence text, Vector2 Pos, Vector2 Size,Vector2 txtPosRel, Screen screen)
	{
		this.size = Size;
		this.thumbnail = t;
		this.text = text;
		this.Pos = Pos;
		this.txtPosRel = txtPosRel;
		this.screen = screen;
	}
	
	boolean hit(int x, int y)
	{
		if(x >= Pos.x && x <= Pos.x + (size.x) && y >= Pos.y && y <= Pos.y + (size.y) ){
			return true;
		}
		return false;
	}
}
