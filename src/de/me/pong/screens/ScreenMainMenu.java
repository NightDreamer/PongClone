package de.me.pong.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.me.pong.ingame.Arena;
import de.me.pong.utils.ResManager;
import de.me.pong.utils.TextButton;

public class ScreenMainMenu implements Screen {

    Game game;
    ResManager resManager;
    TextButton[] buttons;

    Vector2 ball;
    float angle, radius;

    public ScreenMainMenu(Game game, ResManager resManager) {
        Gdx.input.setCursorCatched(false);

        this.game = game;
        this.resManager = resManager;

        buttons = new TextButton[4];
        buttons[0] = new TextButton(resManager, "Play", 424f);
        buttons[1] = new TextButton(resManager, "PvP", 424f - 96f);
        buttons[2] = new TextButton(resManager, "Training", 424f - (96f * 2));
        buttons[3] = new TextButton(resManager, "Exit", 424f - (96f * 3));

        ball = new Vector2(MathUtils.random(10f, Gdx.graphics.getWidth() - 10f), MathUtils.random(10f, Gdx.graphics.getHeight() - 10f));
        if(MathUtils.randomBoolean()) {
            if(MathUtils.randomBoolean()) {
                angle = 45f;
            }else {
                angle = 135f;
            }
        }else {
            if(MathUtils.randomBoolean()) {
                angle = 360f - 45f;
            }else {
                angle = 360f - 135f;
            }
        }
        radius = 10f;
    }

    @Override
    public void render(float delta) {
        // LOGIC
        resManager.clear();

        if(resManager.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            return;
        }

        ball.x += MathUtils.cosDeg(angle) * delta * 1024f;
        ball.y += MathUtils.sinDeg(angle) * delta * 1024f;

        if((ball.x + radius) > Gdx.graphics.getWidth()) {
            ball.x = Gdx.graphics.getWidth() - radius;
            angle = 180f - angle;
        }else if((ball.x - radius) < 0f) {
            ball.x = 0f + radius;
            angle = 180f - angle;
        }

        if((ball.y + radius) > Gdx.graphics.getHeight()) {
            ball.y = Gdx.graphics.getHeight() - radius;
            angle = 360f - angle;
        }else if((ball.y - radius) < 0f) {
            ball.y = 0f + radius;
            angle = 360 - angle;
        }

        for(TextButton tmp : buttons) {
            tmp.update(delta);

            if(tmp.active) {
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    if(tmp.lable == "Training") {
                        game.setScreen(new ScreenIngame(game, resManager, Arena.TRAINING));
                        return;
                    }else if(tmp.lable == "Play") {
                        game.setScreen(new ScreenIngame(game, resManager, Arena.NORMAL));
                        return;
                    }else if(tmp.lable == "PvP") {
                        game.setScreen(new ScreenIngame(game, resManager, Arena.MULTIPLAYER));
                        return;
                    }else if(tmp.lable == "Exit") {
                        Gdx.app.exit();
                        return;
                    }
                }
            }
        }

        // ACTUAL RENDERING
        resManager.shapeRenderer.setColor(Color.LIGHT_GRAY);
        resManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        resManager.shapeRenderer.circle(ball.x, ball.y, radius);
        resManager.shapeRenderer.end();

        resManager.spriteBatch.begin();
        for(TextButton tmp : buttons) {
            tmp.render();
        }
        resManager.spriteBatch.end();
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
