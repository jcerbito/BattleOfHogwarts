package com.jcerbito.battleofhogwarts;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgrade;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeDiff;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;
import com.jcerbito.battleofhogwarts.screens.GameScreen;
import com.jcerbito.battleofhogwarts.screens.SplashScreen;
import com.jcerbito.battleofhogwarts.screens.StartScreen;

public class BattleOfHogwarts extends Game {

	public Resources res;

	static Music hptheme;


	
	@Override
	public void create () {
		res = new Resources();
		GameUpgradeEasy.Load();
		GameUpgradeAverage.Load();
		GameUpgradeDiff.Load();
		setScreen(new SplashScreen(this));
	}

	public static void playMusic(){
		hptheme = Gdx.audio.newMusic(Gdx.files.internal("music/harrypottertheme.ogg"));
		hptheme.setLooping(true);
		hptheme.play();
	}

	public static void stopMusic(){

		if (hptheme != null){
			hptheme.stop();
			hptheme = null;
		}
	}




	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		GameUpgradeEasy.Save();
		GameUpgradeAverage.Save();
		GameUpgradeDiff.Save();
		res.dispose();
	}
}
