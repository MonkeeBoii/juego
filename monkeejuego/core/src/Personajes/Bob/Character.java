package Personajes.Bob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {

    public int x, y;
    public int xMouse, yMouse;
    private Animation<TextureRegion> animationWALKL;
    private Animation<TextureRegion> animationNOMOVEL;
    private Animation<TextureRegion> animationWALKR;
    private Animation<TextureRegion> animationNOMOVER;
    private float tiempo;
    private TextureRegion frameActual;
    private float escala;
    
    public enum Vista{
        DERECHA,
        IZQUIERDA
    }
    Vista vista = Vista.DERECHA;
    public Character(int x, int y, float escala) {
        this.x = x;
        this.y = y;
        this.escala = escala; // Guardar el factor de escala
        animationNOMOVEL = AnimationLoader.loadAnimation("Sprites.png", 0, 7, 32, 32, 1, 0.2f, true);
        animationWALKL = AnimationLoader.loadAnimation("Sprites.png", 0, 14, 32, 32, 4, 0.2f, true);
        animationWALKR = AnimationLoader.loadAnimation("Sprites.png", 0, 14, 32, 32, 4, 0.2f, false);
        animationNOMOVER = AnimationLoader.loadAnimation("Sprites.png", 0, 7, 32, 32, 1, 0.2f, false);
        tiempo = 0f;
    }

    public void render(final SpriteBatch batch, boolean ismovingR) {
        tiempo += Gdx.graphics.getDeltaTime();
        if (ismovingR) {
            if(vista == Vista.DERECHA){
                frameActual = animationWALKR.getKeyFrame(tiempo, true);
            }else{
                frameActual = animationWALKL.getKeyFrame(tiempo, true);
            }
        } else {
            if(vista == Vista.DERECHA){
                frameActual = animationNOMOVER.getKeyFrame(tiempo, true);
            }else{
                frameActual = animationNOMOVEL.getKeyFrame(tiempo, true);
            }
           
        }
        batch.draw(frameActual, x, y, frameActual.getRegionWidth() * escala, frameActual.getRegionHeight() * escala);
    }
}
