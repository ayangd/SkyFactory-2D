package com.ayangd.skyfactory.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ayangd.skyfactory.SkyFactory;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SkyFactory 2D";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new SkyFactory(), config);
	}
}
