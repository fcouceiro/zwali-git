package com.me.zwali;
import org.lwjgl.input.Mouse;

public class Button
{
	String Name;
	Vector UL;
	Vector BR;

	
	public Button(Vector UL, Vector BR, String name)
	{
		this.UL = UL;
		this.BR = BR;
		this.Name = name;
	}
	
	public boolean Quit(Vector mousepos)
	{
			if(Mouse.isButtonDown(0) && mousepos.x > UL.x && mousepos.y > UL.y && mousepos.x < BR.x && mousepos.y < BR.y)
			{			
				return true;
			}		
			return false;
	}
	
	public boolean mousehover(Vector mpos)
	{
		if(mpos.x > UL.x && mpos.x < BR.x && mpos.y > UL.y && mpos.y < BR.y)
		{
			System.out.println("Mousehover: " + this.Name);
			return true;
		}
		return false;
	}

}
