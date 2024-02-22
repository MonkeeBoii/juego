package Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bala extends Image {

    private float VELOCIDAD = 0;
    private static final int COOLDOWN_TIME = 15;
    private static int cooldownCounter = 0;
    private TiledMap tiledMap;
    public TiledMapTileLayer layer;
    Rectangle rectangle;

    public Bala(Texture texture, float origenX, float origenY, float velocidad, TiledMap tiledMap) {
        super(texture);
        this.tiledMap = tiledMap;
        if (VELOCIDAD == 0) {
            VELOCIDAD = velocidad;
        }
        setX(origenX);
        setY(origenY);
        setPosition(origenX, origenY);
        
        layer = (TiledMapTileLayer) this.tiledMap.getLayers().get("walls");
        this.cambiarTamaño(1.5f, 1f);
        rectangle = new Rectangle(origenX, origenY, getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        float newPosX = getX() + VELOCIDAD * delta;
        float newPosY = getY();
        setPosition(newPosX, newPosY);
        rectangle.setPosition(newPosX, newPosY);
        if (newPosY + getHeight() > getStage().getHeight()) {
            remove();
        }
        if (newPosX > getStage().getWidth() || newPosX < 0) {
            remove();
        }
        if (!canMoveTo(rectangle)) {
            remove();
        }
    }

    public static boolean puedeDisparar() {
        if (cooldownCounter <= 0) {
            cooldownCounter = COOLDOWN_TIME;
            return true;
        } else {
            return false;
        }
    }

    public static void actualizarCooldown() {
        cooldownCounter = Math.max(0, cooldownCounter - 1);
    }

    public void cambiarTamaño(float nuevoAncho, float nuevoAlto) {
        setSize(nuevoAncho, nuevoAlto);
    }

    private boolean canMoveTo(Rectangle rectangle) {
        int startX = MathUtils.floor(rectangle.x);
        int startY = MathUtils.floor(rectangle.y);
        int endX = MathUtils.ceil(rectangle.x + rectangle.width);
        int endY = MathUtils.ceil(rectangle.y + rectangle.height);

        // Aumentar la separación entre el rectángulo de colisión y el suelo
        startY += 1.0f;
        // Asegurarse de no exceder los límites del mapa
        endX = Math.min(endX, layer.getWidth());
        endY = Math.min(endY, layer.getHeight());

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                if (layer.getCell(x, y) != null) {
                    return false;
                }
            }
        }

        return true;
    }

}
