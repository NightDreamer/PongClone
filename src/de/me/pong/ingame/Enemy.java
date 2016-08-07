package de.me.pong.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.me.pong.utils.ResManager;

public class Enemy {
    public static final float MAX_SPEED = 352f;

    private ResManager resManager;
    private Arena arena;
    private Ball ball;
    public Rectangle bounds;

    public boolean ai;

    public Enemy(ResManager resManager, Arena arena, Ball ball, boolean ai) {
        this.resManager = resManager;
        this.arena = arena;
        this.ball = ball;
        this.ai = ai;

        bounds = new Rectangle(arena.bounds.x + arena.bounds.width - 15f, arena.bounds.getHeight() / 2 - 25f, 10f, 50f);
    }

    public void update(float delta) {
        // LOGIC
        if (ai) {
            Vector2 ballPos = new Vector2(ball.pos);

            if (bounds.y > ballPos.y) {
                bounds.y -= MAX_SPEED * delta;
            } else if ((bounds.y + bounds.height) < ballPos.y) {
                bounds.y += MAX_SPEED * delta;
            }
        }else {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                bounds.y += MAX_SPEED * 1.5f * delta;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                bounds.y -= MAX_SPEED * 1.5f * delta;
            }
        }
    }

    public void render() {
        // ACTUAL RENDERING
        resManager.shapeRenderer.setColor(Color.WHITE);

        resManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        resManager.shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        resManager.shapeRenderer.end();
    }

}
