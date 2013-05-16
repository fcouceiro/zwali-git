package com.me.zwali;

import java.util.Random;



public class Wave {

	Vector pos;
	int Spawn[];
	int index;
	int MAX;
	int timer;
	int time;
	int waveNR;
	Random rdm = new Random();
	
	
	public Wave( Vector pos, int waveNr, int timer)
	{
		this.pos = pos;
		this.timer = timer-waveNr;
		this.time = 0;
		this.index = 0;
		this.waveNR = waveNr;
		
		int temp[] = new int [101];
		int size = waveNR ;
		if( size>100) size = 100;
		
		for(int i = 0; i < size;i++)
		{
			temp[i] = rdm.nextInt(3)+1;
		}
		temp[size] = -1;
		this.MAX = size;
		this.Spawn = temp;
		System.out.println("WAVE "+waveNr+" added");
		
	}
	
	public boolean Update()
	{
		time++;
		if( time >= timer && index<MAX)
		{
			time=0;
			return true;
		}
		return false;
	}
	
	public int addEnemy()
	{
		
			index++;
			return Spawn[index-1];
		
	}
	
	public boolean empty()
	{
		return (index >= MAX);
	}
	
	
	
}