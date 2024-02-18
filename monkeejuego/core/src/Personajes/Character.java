/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {

    public int x,y;
    private Animation animation;
    private float tiempo;
    private TextureRegion [] regionsMovimiento;
    private Texture imagen;
    private TextureRegion frameActual;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        //cargar la imagen
        imagen = new Texture(Gdx.files.internal("Sprites.png"));
        TextureRegion [][] tmp = TextureRegion.split(imagen,
                imagen.getWidth()/8,imagen.getHeight()/29);

        regionsMovimiento = new TextureRegion[5];
        System.arraycopy(tmp[14], 0, regionsMovimiento, 0, 5);
        animation = new Animation(1/10f,regionsMovimiento);
        tiempo = 0f;

    }


    public void render(final SpriteBatch batch) {
        tiempo += Gdx.graphics.getDeltaTime(); //es el tiempo que paso desde el ultimo render
        frameActual = (TextureRegion) animation.getKeyFrame(tiempo,true);
        batch.draw(frameActual,x,y);
    }
}
