package de.me.pong.starter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
import de.me.pong.screens.ScreenMainMenu;
import de.me.pong.utils.ResManager;

public class Main extends Game{

    @Override
    public void create() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glEnable(GL10.GL_BLEND);

        ResManager resManager = new ResManager(this);
        this.setScreen(new ScreenMainMenu(this, resManager));
    }

    public static void main(String[] args) {

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.vSyncEnabled        = true;
        cfg.fullscreen          = false;
        cfg.resizable           = false;
        cfg.useGL20             = false;
        cfg.title               = "Pong Clone";
        cfg.samples             = 8;
        cfg.width               = 800;
        cfg.height              = 600;

        new LwjglApplication(new Main(), cfg);

    }


}
