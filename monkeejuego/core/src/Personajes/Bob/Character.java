package Personajes.Bob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Character {
    
    private boolean flip;
    public int x, y;
    public int xMouse, yMouse;
    private Animation<TextureRegion> animationWALK;
    private Animation<TextureRegion> animationNOMOVE;
    private float tiempo;
    private TextureRegion frameActual;
    private float escala;

    public Character(int x, int y, float escala) {
        this.x = x;
        this.y = y;
        this.escala = escala; // Guardar el factor de escala
        animationNOMOVE = AnimationLoader.loadAnimation("Sprites.png", 0, 7, 32, 32, 1, 0.2f);
        animationWALK = AnimationLoader.loadAnimation("Sprites.png", 0, 14, 32, 32, 4, 0.2f); // Ajustar el tamaño de los frames
        tiempo = 0f;
    }

    public void render(final SpriteBatch batch, boolean ismoving) {
        tiempo += Gdx.graphics.getDeltaTime();
        if (ismoving) {
            frameActual = animationWALK.getKeyFrame(tiempo, true);
        } else {
            frameActual = animationNOMOVE.getKeyFrame(tiempo, true);
        }
        batch.draw(frameActual, x, y, frameActual.getRegionWidth() * escala, frameActual.getRegionHeight() * escala); // Ajustar la posición y el tamaño del personaje
    }
}
