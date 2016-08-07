package de.me.pong.ingame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import de.me.pong.utils.ResManager;

public class Arena {
    public static final int NORMAL = 0;
    public static final int TRAINING = 1;
    public static final int MULTIPLAYER = 2;

    private ResManager resManager;
    public Hud hud;
    private Player player;
    private Enemy enemy;
    private Ball ball;

    public Rectangle bounds;
    public int mode;
    public boolean start = true, finish = false;

    public Arena(Game game, ResManager resManager, int mode) {
        this.resManager = resManager;
        this.mode = mode;

        bounds = new Rectangle(10f, 10f, Gdx.graphics.getWidth() - 20f, Gdx.graphics.getHeight() - 128f);
        hud = new Hud(game, resManager, this);
        ball = new Ball(resManager, this);

        if(mode == NORMAL) {
            player = new Player(resManager, this, false);
            enemy = new Enemy(resManager, this, ball, true);
        }else if(mode == MULTIPLAYER) {
            player = new Player(resManager, this, true);
            enemy = new Enemy(resManager, this, ball, false);
        }else if(mode == TRAINING) {
            player = new Player(resManager, this, false);
        }
    }

    public void render() {
        resManager.shapeRenderer.setColor(Color.WHITE);

        resManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        resManager.shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        resManager.shapeRenderer.line(bounds.x + bounds.width / 2, bounds.y, bounds.x + bounds.width / 2, bounds.height + 10f);
        resManager.shapeRenderer.end();

        player.render();
        if(!finish && !start ) {
            ball.render();
        }
        if (mode != TRAINING) {
            enemy.render();
        }
        hud.render();
    }

    public void update(float delta) {
        // PLAYER LOGIC
        player.update(delta);

        if ((player.bounds.y + player.bounds.height) > (bounds.y + bounds.height - 5f)) {
            player.bounds.y = bounds.y + bounds.height - 5f - player.bounds.height;
        }

        if (player.bounds.y < (bounds.y + 5f)) {
            player.bounds.y = bounds.y + 5f;
        }

        // BALL LOGIC
        if(!start) {
            ball.update(delta);
        }

        if (mode == TRAINING) {
            if ((ball.pos.x + ball.radius) > (bounds.x + bounds.width)) {
                ball.pos.x = bounds.x + bounds.width - ball.radius;
                ball.angle = 180f - ball.angle;
                resManager.boing.play();
            } else if ((ball.pos.x - ball.radius) < bounds.x) {
                ball.reset(this);
                if(ball.angle < 90f) {
                    ball.angle += 180f;
                }else if(ball.angle > 270f) {
                    ball.angle -= 180f;
                }
                hud.score.y += 1;
            }
            if(ball.STEP < 576f*2) {
                ball.STEP += 8f * delta;
            }
        } else {
            if ((ball.pos.x + ball.radius) > (bounds.x + bounds.width)) {
                ball.reset(this);
                hud.score.x += 1;
            } else if ((ball.pos.x - ball.radius) < bounds.x) {
                ball.reset(this);
                hud.score.y += 1;
            }
        }

        if((ball.pos.x - ball.radius) < (player.bounds.x + player.bounds.width) && ball.pos.y < (player.bounds.y + player.bounds.height) && ball.pos.y > player.bounds.y) {
            ball.pos.x = player.bounds.x + player.bounds.width + ball.radius;
            float dst = ball.pos.y - (player.bounds.y + (player.bounds.height / 2));
            ball.angle = Math.round(dst * 3f);
            if(mode == TRAINING) {
                hud.score.x++;
            }
            resManager.boing.play();
        }

        if((ball.pos.y + ball.radius) > (bounds.y + bounds.height)) {
            ball.pos.y = bounds.y + bounds.height - ball.radius;
            ball.angle = 360f - ball.angle;
            resManager.boing.play();
        } else if ((ball.pos.y - ball.radius) < bounds.y) {
            ball.pos.y = bounds.y + ball.radius;
            ball.angle = 360f - ball.angle;
            resManager.boing.play();
        }

        // ENEMY LOGIC
        if (mode != TRAINING) {
            enemy.update(delta);

            if ((enemy.bounds.y + enemy.bounds.height) > (bounds.y + bounds.height - 5f)) {
                enemy.bounds.y = bounds.y + bounds.height - 5f - enemy.bounds.height;
            }

            if (enemy.bounds.y < (bounds.y + 5f)) {
                enemy.bounds.y = bounds.y + 5f;
            }

            if ((ball.pos.x + ball.radius) > enemy.bounds.x && ball.pos.y < (enemy.bounds.y + enemy.bounds.height) && ball.pos.y > enemy.bounds.y) {
                ball.pos.x = enemy.bounds.x - ball.radius;
                float dst = ball.pos.y - (enemy.bounds.y + (enemy.bounds.height / 2));
                ball.angle = 180f - Math.round(dst * 3f);
                resManager.boing.play();
            }
        }
    }

}
