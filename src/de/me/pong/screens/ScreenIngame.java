package de.me.pong.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import de.me.pong.ingame.Arena;
import de.me.pong.utils.ResManager;

public class ScreenIngame implements Screen {

    Game game;
    ResManager resManager;
    Arena arena;

    public ScreenIngame(Game game, ResManager resManager, int mode) {
        Gdx.input.setCursorCatched(true);

        this.game = game;
        this.resManager = resManager;

        arena = new Arena(game, resManager, mode);
    }

    @Override
    public void render(float delta) {
        // LOGIC
        resManager.clear();

        if(resManager.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new ScreenMainMenu(game, resManager));
            return;
        }

        if(resManager.input.isKeyPressed(Input.Keys.F)) {
            arena.hud.score.x = 8;
        }

        if(!arena.finish) {
            arena.update(delta);
        }

        // ACTUAL RENDERING
        arena.render();
    }

    @Override
    public void resize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void show() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
