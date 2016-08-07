package de.me.pong.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ResManager {

    public Game game;
    public InputListener input;
    public SpriteBatch spriteBatch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font16, font32, font64, font128;
    public Sound button, boing;
    public Music bgm1;

    public ResManager(Game game) {
        this.game = game;

        input = new InputListener();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("res/pixelfont.ttf"));
        font16 = gen.generateFont(16);
        font32 = gen.generateFont(32);
        font64 = gen.generateFont(64);
        font128 = gen.generateFont(128);
        gen.dispose();

        button = Gdx.audio.newSound(Gdx.files.internal("res/button.wav"));
        boing = Gdx.audio.newSound(Gdx.files.internal("res/boing.wav"));
        bgm1 = Gdx.audio.newMusic(Gdx.files.internal("res/bgm1.mp3"));
        bgm1.setLooping(true);
        bgm1.setVolume(0.25f);
        bgm1.play();
    }

    public void clear() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    public void dispose() {
        Gdx.gl.glDisable(GL10.GL_BLEND);

        spriteBatch.dispose();
        shapeRenderer.dispose();
        font16.dispose();
        font32.dispose();
        font64.dispose();
        font128.dispose();

        button.stop();
        button.dispose();

        boing.stop();
        boing.dispose();

        if(bgm1.isPlaying()) {
            bgm1.stop();
        }
        bgm1.dispose();
    }

}
