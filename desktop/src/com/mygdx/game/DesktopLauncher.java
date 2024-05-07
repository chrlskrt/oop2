package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import imissyou.DodgeBallGame;

public class DesktopLauncher {
	public static Lwjgl3Application imissyou;
	public DesktopLauncher() {
	}

	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(720, 480);
		config.setForegroundFPS(60);
		config.setForegroundFPS(60);
//		new Lwjgl3Application(new MyGdxGame(), config);

		imissyou = new Lwjgl3Application(new DodgeBallGame(), config);
	}
}
