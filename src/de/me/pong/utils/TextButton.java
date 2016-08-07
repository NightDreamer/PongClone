package de.me.pong.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TextButton {

    private ResManager resManager;
    public String lable;
    public Rectangle bounds;

    public boolean active = false;

    public TextButton(ResManager resManager, String lable, float yPos) {
        this.resManager = resManager;
        this.lable = lable;

        bounds = new Rectangle(Gdx.graphics.getWidth()/2 - resManager.font64.getBounds(lable).width/2, yPos, resManager.font64.getBounds(lable).width, resManager.font64.getBounds(lable).height);
    }

    public void update(float delta) {
        Vector2 cursor = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        if(bounds.contains(cursor)) {
            if(!active) {
                active = true;
                resManager.button.play(1f);
            }
        }else {
            active = false;
        }
    }

    public void render() {
        if(active) {
            resManager.font64.setScale(1.1f);
        }
        resManager.font64.draw(resManager.spriteBatch, lable, Gdx.graphics.getWidth()/2 - resManager.font64.getBounds(lable).width/2, bounds.y + bounds.height);
        if(active) {
            resManager.font64.setScale(1.0f);
        }
    }

}
