package Personajes.Bob;

import Object.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Character extends Image {

    TextureRegion stand, jump, bulletObj;
    Animation walk;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    public TiledMapTileLayer layer;
    Texture bulletTexture;
    Bullet bullet;

    final float GRAVITY = -1.5f;
    final float MAX_VELOCITY = 9f;
    final float DAMPING = 0.5f;

    public Character(float escala) {
        final float width = 32;
        final float height = 32;
        this.setSize(1, (height / width) * escala);

        Texture koalaTexture = new Texture("Sprites.png");
        TextureRegion[][] grid = TextureRegion.split(koalaTexture, (int) width, (int) height);
        bulletTexture = new Texture("bolt1_strip.png");
        TextureRegion[][] bulletArray = TextureRegion.split(bulletTexture, (int) 10, (int) 10);
        stand = grid[7][0];
        jump = grid[8][0];
        bulletObj = bulletArray[0][0];
        walk = new Animation(0.15f, grid[14][0], grid[14][1], grid[14][2], grid[14][3]);
        walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void act(float delta) {
        time = time + delta;

        boolean upTouched = Gdx.input.isTouched() && Gdx.input.getY() < Gdx.graphics.getHeight() / 2;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || upTouched) {
            if (canJump) {
                yVelocity = yVelocity + MAX_VELOCITY * 3;
            }
            canJump = false;
        }

        boolean leftTouched = Gdx.input.isTouched() && Gdx.input.getX() < Gdx.graphics.getWidth() / 3;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || leftTouched) {
            xVelocity = -1 * MAX_VELOCITY;
            isFacingRight = false;
        }

        boolean rightTouched = Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() * 2 / 3;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || rightTouched) {
            xVelocity = MAX_VELOCITY;
            isFacingRight = true;
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
        } else {
            frame = stand;
        }

        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                bullet = new Bullet(new TextureRegion(bulletTexture), this.getX(), this.getY());
                bullet.setActive(true);
            }
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                
            }
        }
        if(bullet != null){
            bullet.update(time);
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
