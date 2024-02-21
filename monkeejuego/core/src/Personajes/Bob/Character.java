package Personajes.Bob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Character extends Image {

    TextureRegion stand, jump, quietoDisparo;
    Animation walk, disparo, quieto, quieto1;
    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    public boolean isFacingRight = true;
    public TiledMapTileLayer layer;
    float inactivityTime = 0;
    float incativityTimeAnimation = 0;
    final float QUIET_TIME_THRESHOLD = 3;
    final float QUIET_TIME_THRESHOLD_ANIMATION = 5;

    final float GRAVITY = -1.5f;
    final float MAX_VELOCITY = 9f;
    final float DAMPING = 0.5f;

    public Character(float escala) {
        final float width = 32;
        final float height = 32;
        this.setSize(1, (height / width) * escala);

        Texture koalaTexture = new Texture("Sprites.png");
        TextureRegion[][] grid = TextureRegion.split(koalaTexture, (int) width, (int) height);
        stand = grid[7][0];
        jump = grid[8][1];
        quietoDisparo = grid[7][1];
        quieto = new Animation(0.5f, grid[10][0], grid[10][1], grid[10][2], grid[11][0]);
        quieto1 = new Animation(0.2f, grid[11][1], grid[11][2], grid[11][3]);
        disparo = new Animation(0.20f, grid[26][0], grid[26][1], grid[26][2], grid[26][3]);
        walk = new Animation(0.15f, grid[14][0], grid[14][1], grid[14][2], grid[14][3]);
        walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void act(float delta) {
        time = time + delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (canJump) {
                yVelocity = yVelocity + MAX_VELOCITY * 3;
            }
            canJump = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVelocity = -1 * MAX_VELOCITY;
            isFacingRight = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVelocity = MAX_VELOCITY;
            isFacingRight = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D) || (Gdx.input.isKeyPressed(Input.Keys.SPACE) && canJump) || (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.E))) {
            inactivityTime = 0;
            incativityTimeAnimation = 0;
        } else {
            inactivityTime += delta;
            incativityTimeAnimation += delta;
        }

        yVelocity = yVelocity + GRAVITY;

        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;

        if (canMoveTo(x + xChange, y) == false) {
            xVelocity = xChange = 0;
        }

        if (canMoveTo(x, y + yChange) == false) {
            canJump = yVelocity < 0;
            yVelocity = yChange = 0;
        }

        this.setPosition(x + xChange, y + yChange);

        xVelocity = xVelocity * DAMPING;
        if (Math.abs(xVelocity) < 0.5f) {
            xVelocity = 0;
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;

        if (yVelocity != 0) {
            frame = jump;
        } else if (xVelocity != 0) {
            frame = (TextureRegion) walk.getKeyFrame(time);
        } else if (inactivityTime >= QUIET_TIME_THRESHOLD) {
            frame = (TextureRegion) quieto.getKeyFrame(time, true);
            if (incativityTimeAnimation >= QUIET_TIME_THRESHOLD_ANIMATION) {
                frame = (TextureRegion) quieto1.getKeyFrame(time, true);
            }
            if (incativityTimeAnimation >= 5.9) {
                incativityTimeAnimation = 0;
                inactivityTime = 0;
            }
        } else {
            frame = stand;
            if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.E)) {
                frame = quietoDisparo;
            }
        }

        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }

    }

    private boolean canMoveTo(float startX, float startY) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();

        for (float x = startX; x < endX; x += 0.1f) {
            for (float y = startY; y < endY; y += 0.1f) {
                if (layer.getCell((int) x, (int) y) != null) {
                    return false;
                }
            }
        }

        return true;
    }

}
