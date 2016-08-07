package de.me.pong.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import de.me.pong.utils.ResManager;

public class Player {
    public static final float MAX_SPEED = 352f;
    public static final float MOUSE_SPEED = 17.5f;

    public ResManager resManager;
    public Rectangle bounds;

    private boolean multiplayer;

    public Player(ResManager resManager, Arena arena, boolean multiplayer) {
        this.resManager = resManager;
        this.multiplayer = multiplayer;

        bounds = new Rectangle(arena.bounds.x + 5f, arena.bounds.getHeight()/2 - 25f, 10f, 50f);
    }

    public void update(float delta) {
        // LOGIC
        if(multiplayer) {
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                bounds.y += MAX_SPEED * 1.5f * delta;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                bounds.y -= MAX_SPEED * 1.5f * delta;
            }
        }else {
            bounds.y -= resManager.input.getDeltaY() * delta * MOUSE_SPEED;
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
