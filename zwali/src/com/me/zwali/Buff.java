package com.me.zwali;

public class Buff 
{

	int type, timebuff;
	int timerbuff = 3600;
	boolean activeBuff;
	
	public Buff(int type)
	{
		this.activeBuff = false;
	
		this.timebuff = 0;
		timebuff=0;
		this.type = type;
	}
	


	public boolean active()
	{
		if(timebuff >= timerbuff)
		{
			timebuff = 0;
			return false;
		}
		else
		{
			timebuff++;
			return true;
		}
	}
}
