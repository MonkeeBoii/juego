package Object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bala extends Image {

    private float VELOCIDAD = 0;
    private static final int COOLDOWN_TIME = 20;
    private static int cooldownCounter = 0;

    public Bala(Texture texture, float origenX, float origenY, float velocidad) {
        super(texture);
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
        float movimientoY = VELOCIDAD * delta;
        setPosition(getX() + movimientoY, getY());

        if (getY() + getHeight() > getStage().getHeight()) {
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
}
