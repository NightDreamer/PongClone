package de.me.pong.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class InputListener {

    private boolean keys[];
    private Vector2 cursorPos;

    public InputListener() {
        keys = new boolean[256];
        cursorPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
    }

    // KEYBOARD

    public boolean isKeyPressed(int key) {
        if(isKeyDown(key)) {
            if(keys[key]) {
                return false;
            }else {
                keys[key] = true;
                return true;
            }
        }else {
            keys[key] = false;
            return false;
        }
    }

    public boolean isKeyDown(int key) {
        return Gdx.input.isKeyPressed(key);
    }

    public float getDeltaY() {
        float delta = cursorPos.y - (Gdx.graphics.getHeight() - Gdx.input.getY());
        cursorPos.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        return delta;
    }

}
