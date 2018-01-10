package com.jcerbito.battleofhogwarts;

import com.badlogic.gdx.Game;
import com.jcerbito.battleofhogwarts.screens.GameScreen;

public class BattleOfHogwarts extends Game {

	public Resources res;
	
	@Override
	public void create () {
		res = new Resources();
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		res.dispose();

	}
}
