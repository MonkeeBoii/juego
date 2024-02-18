package MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Personajes.Character;
import com.badlogic.gdx.graphics.Texture;

public class MyGame extends ApplicationAdapter {

    SpriteBatch batch;
    private OrthographicCamera camera;
    private Character i, j, k;

    @Override
    public void create() {
        batch = new SpriteBatch(); //agrupacion de sprites, u objetos que se van a dibujar
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480); // Y apunta hacia arriba,
        camera.update();
        i = new Character(100, 100);
        j = new Character(400, 150);
        k = new Character(200, 200);
    }

    @Override
    public void render() {
        //limpiar el buffer de dibujo
        Gdx.gl.glClearColor(1, 1, 1, 1); //1,1,1,1 pone fondo blanco
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        j.render(batch);
        k.render(batch);
        i.render(batch);
        //dibujar mas objetos en nuestro mundo
        batch.end(); //finalizar
    }
}
