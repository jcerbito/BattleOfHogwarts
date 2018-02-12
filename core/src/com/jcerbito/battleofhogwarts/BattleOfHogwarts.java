package com.jcerbito.battleofhogwarts;

import com.badlogic.gdx.Game;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgrade;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeDiff;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;
import com.jcerbito.battleofhogwarts.screens.GameScreen;
import com.jcerbito.battleofhogwarts.screens.StartScreen;

public class BattleOfHogwarts extends Game {

	public Resources res;
	
	@Override
	public void create () {
		res = new Resources();
		GameUpgradeEasy.Load();
		GameUpgradeAverage.Load();
		GameUpgradeDiff.Load();
		setScreen(new StartScreen(this));
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
