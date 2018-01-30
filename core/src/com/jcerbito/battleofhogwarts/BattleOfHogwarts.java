package com.jcerbito.battleofhogwarts;

import com.badlogic.gdx.Game;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgrade;
import com.jcerbito.battleofhogwarts.screens.GameScreen;
import com.jcerbito.battleofhogwarts.screens.StartScreen;

public class BattleOfHogwarts extends Game {

	public Resources res;
	
	@Override
	public void create () {
		res = new Resources();
		GameUpgrade.Load();
		//setScreen(new GameScreen(this));
		setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		GameUpgrade.Save();
		res.dispose();

	}
}
