package Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bala extends Image {

    private float VELOCIDAD = 0;
    private static final int COOLDOWN_TIME = 20;
    private static int cooldownCounter = 0;
    private TiledMap tiledMap;
    public TiledMapTileLayer layer;

    public Bala(Texture texture, float origenX, float origenY, float velocidad, TiledMap tiledMap) {
        super(texture);
        this.tiledMap = tiledMap;
        if (VELOCIDAD == 0) {
            VELOCIDAD = velocidad;
        }
        setX(origenX);
        setY(origenY);
        setPosition(origenX, origenY);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Calcular la nueva posición de la bala
        float newPosX = getX() + VELOCIDAD * delta;
        float newPosY = getY();

        if (canMoveTo(getX() + newPosX, getY()) == false) {
            this.remove();
        }

        // Verificar si la bala ha salido de la pantalla
        if (newPosY + getHeight() > getStage().getHeight()) {
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
