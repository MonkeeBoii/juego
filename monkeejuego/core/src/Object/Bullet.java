package Object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bullet extends Image {
    private boolean active;
    private float speed;
    
    public Bullet(TextureRegion textureRegion, float x, float y) {
        super(textureRegion);
        setPosition(x, y);
        active = false;
        speed = 5;
    }

    public void update(float delta) {
        if (active) {
            moveBy(speed * delta, 0);
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
