package com.me.zwali;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	
	Conceito maingame;
	
	public Sounds(Conceito main) throws FileNotFoundException
	{
		this.maingame = main;
		this.loadSounds();
	}
	
	static Audio sm = Gdx.audio;
	static List<Sound> disposable = new ArrayList<Sound>();
	
	// counter attack
	static Sound ca_yah;
	static Sound ca_uhh;
	static Sound ca_woa;
	
	//Getting hurt
	static Sound gh_uhh;
	static Sound gh_aii;
	static Sound gh_huh;
	static Sound gh_hah;
	static Sound gh_eeaah;
	
	//Zombie sounds
	static Sound zo_brainz;
	static Sound zo_ahaha;
	static Sound zo_hurt1;
	static Sound zo_hurt2;
	static Sound zo_bone_crush;
	static Sound zo_neck;
	
	//misc sounds
	static Music main_s;
	static Music mhover_s;
	
	
	void loadSounds()
	{
		// counter attack
		ca_yah = getSound("MC_Attack/Attack1");
		ca_uhh = getSound("MC_Attack/Attack2");
		ca_woa = getSound("MC_Attack/Attack3");
		
		//Getting hurt
		gh_uhh = getSound("MC_GettingHurt/Hurt1");
		gh_aii = getSound("MC_GettingHurt/Hurt2");
		gh_huh = getSound("MC_GettingHurt/Hurt3");
		gh_hah = getSound("MC_GettingHurt/Hurt4");
		gh_eeaah = getSound("MC_GettingHurt/Hurt5");
		
		//Zombie sounds
		zo_brainz = getSound("ZO_Sounds/Brainz");
		zo_ahaha = getSound("ZO_Sounds/Hahaha");
		zo_hurt1 = getSound("ZO_Sounds/Hurt1");
		zo_hurt2 = getSound("ZO_Sounds/Hurt2");
		zo_bone_crush = getSound("ZO_Sounds/bone_crush");
		zo_neck = getSound("ZO_Sounds/neck_snap");
		
		//misc sounds
		main_s = getMusic("misc/lost_village.ogg");
		mhover_s = getMusic("misc/mouse.mp3");
	}
	
	void play(Sound sound)
	{
		if(maingame.sound) sound.play();
	}
	
	void play(Sound sound,float volume)
	{
		if(maingame.sound) sound.play(volume);
	}
	
	void play(Music sound)
	{
		if(maingame.sound) sound.play();
	}
	
	void play(Music sound,float volume)
	{
		if(maingame.sound){
			sound.setVolume(volume);
			sound.play();
		}
	}
	
	
	Sound getSound(String key)
	{
		Sound temp = sm.newSound(Gdx.files.internal("assets/sfx/" + key +".mp3"));
		disposable.add(temp);
		return temp;
	}
	
	Music getMusic(String key)
	{
		Music temp = sm.newMusic(Gdx.files.internal("assets/sfx/" + key));
		return temp;
	}
	
	public void dispose()
	{
		for(Sound s:disposable) s.dispose();
	}
}
