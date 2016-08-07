package de.me.pong.ingame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import de.me.pong.screens.ScreenIngame;
import de.me.pong.utils.ResManager;

public class Hud {

    private Game game;
    private ResManager resManager;
    private Arena arena;
    public Vector2 score;

    private long lastTime;
    private int startSecs = 3;

    public Hud(Game game, ResManager resManager, Arena arena) {
        this.resManager = resManager;
        this.arena = arena;
        this.game = game;

        score = new Vector2(0, 0);
        lastTime = System.currentTimeMillis();
    }

    public void render() {
        // LOGIC
        if (arena.mode != Arena.TRAINING) {
            if (score.x == 8 || score.y == 8) {
                if (resManager.input.isKeyPressed(Input.Keys.ENTER)) {
                    if (arena.mode == Arena.TRAINING) {
                        game.setScreen(new ScreenIngame(game, resManager, Arena.TRAINING));
                    } else if (arena.mode == Arena.NORMAL) {
                        game.setScreen(new ScreenIngame(game, resManager, Arena.NORMAL));
                    } else if (arena.mode == Arena.MULTIPLAYER) {
                        game.setScreen(new ScreenIngame(game, resManager, Arena.MULTIPLAYER));
                    }
                }
                if(!arena.finish) {
                    arena.finish = true;
                }
            }
        }

        // ACTUAL RENDERING
        resManager.spriteBatch.begin();
        if (arena.mode != Arena.TRAINING) {
            if (score.x == 8) {
                resManager.font64.draw(resManager.spriteBatch, "WIN", Gdx.graphics.getWidth() / 4 - resManager.font64.getBounds("WIN").width / 2, arena.bounds.height / 2 + resManager.font64.getBounds("WIN").height / 2);
                resManager.font16.draw(resManager.spriteBatch, "Press Enter to reset", (Gdx.graphics.getWidth() / 4) * 3 - resManager.font16.getBounds("Press Enter to reset").width / 2, arena.bounds.height / 2 + resManager.font16.getBounds("Press Enter to reset").height);
            } else if (score.y == 8) {
                resManager.font64.draw(resManager.spriteBatch, "WIN", (Gdx.graphics.getWidth() / 4) * 3 - resManager.font64.getBounds("WIN").width / 2, arena.bounds.height / 2 + resManager.font64.getBounds("WIN").height / 2);
                resManager.font16.draw(resManager.spriteBatch, "Press Enter to reset", (Gdx.graphics.getWidth() / 4) - resManager.font16.getBounds("Press Enter to reset").width / 2, Gdx.graphics.getHeight() / 2 + resManager.font16.getBounds("Press Enter to reset").height / 2);
            }
        }
        if (arena.start) {
            if ((System.currentTimeMillis() - lastTime) >= 1000) {
                lastTime = System.currentTimeMillis();
                startSecs--;
            }
            if (startSecs < 1) {
                arena.start = false;
            } else {
                resManager.font128.draw(resManager.spriteBatch, "" + startSecs, Gdx.graphics.getWidth() / 2 - resManager.font128.getBounds("" + startSecs).width / 2, arena.bounds.y + arena.bounds.height / 2 + resManager.font128.getBounds("" + startSecs).height / 2);
            }
        }
        if (arena.mode == Arena.NORMAL || arena.mode == Arena.TRAINING) {
            resManager.font16.draw(resManager.spriteBatch, "QUIT - [ESC]", 15f, Gdx.graphics.getHeight() - 15f);
            resManager.font16.draw(resManager.spriteBatch, "MOVE - [MOUSE]", 15f, arena.bounds.y + arena.bounds.height + 15f + resManager.font16.getBounds("MOVE - [MOUSE]").height);
        } else if (arena.mode == Arena.MULTIPLAYER) {
            resManager.font16.draw(resManager.spriteBatch, "QUIT - [ESC]", 15f, Gdx.graphics.getHeight() - 15f);
            resManager.font16.draw(resManager.spriteBatch, "MOVE - [W] & [S]", 15f, arena.bounds.y + arena.bounds.height + 15f + resManager.font16.getBounds("MOVE - [W] & [S]").height);
            resManager.font16.draw(resManager.spriteBatch, "MOVE - [UP] & [DOWN]", Gdx.graphics.getWidth() - 15f - resManager.font16.getBounds("MOVE - [UP] & [DOWN]").width, arena.bounds.y + arena.bounds.height + 15f + resManager.font16.getBounds("MOVE - [UP] & [DOWN]").height);
        }
        resManager.font32.draw(resManager.spriteBatch, "Score", Gdx.graphics.getWidth() / 2 - resManager.font32.getBounds("Score").width / 2, Gdx.graphics.getHeight() - 12f);
        resManager.font64.draw(resManager.spriteBatch, " : ", Gdx.graphics.getWidth()/2 - resManager.font64.getBounds(" : ").width/2, Gdx.graphics.getHeight() - 54f);
        resManager.font64.draw(resManager.spriteBatch, ""+Math.round(score.x), Gdx.graphics.getWidth()/2 - resManager.font64.getBounds(" : ").width/2 - resManager.font64.getBounds(""+Math.round(score.x)).width, Gdx.graphics.getHeight() - 54f);
        resManager.font64.draw(resManager.spriteBatch, ""+Math.round(score.y), Gdx.graphics.getWidth()/2 + resManager.font64.getBounds(" : ").width/2, Gdx.graphics.getHeight() - 54f);
        resManager.spriteBatch.end();
    }
}
