package de.me.pong.ingame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.me.pong.utils.ResManager;

public class Ball {

    public static float STEP = 576f;

    private ResManager resManager;

    public Vector2 pos;
    public float radius, angle;

    public Ball(ResManager resManager, Arena arena) {
        this.resManager = resManager;

        reset(arena);
    }

    public void reset(Arena arena) {
        radius = 7.5f;
        if(MathUtils.randomBoolean()) {
            angle = MathUtils.random(180f - 22.5f, 180f + 22.5f);
        }else {
            float tmp = MathUtils.random(-22.5f, 22.5f);
            if(tmp < 0.0f) {
                angle = 359.9f - tmp;
            }else {
                angle = tmp;
            }
        }
        pos = new Vector2(arena.bounds.x + arena.bounds.width/2, arena.bounds.y + arena.bounds.height/2);
    }

    public void update(float delta) {
        pos.x += MathUtils.cosDeg(angle) * STEP * delta;
        pos.y += MathUtils.sinDeg(angle) * STEP * delta;
    }

    public void render() {
        resManager.shapeRenderer.setColor(Color.WHITE);

        resManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        resManager.shapeRenderer.circle(pos.x, pos.y, radius);
        resManager.shapeRenderer.end();
    }

}
