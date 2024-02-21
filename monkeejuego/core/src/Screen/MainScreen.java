package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Personajes.Bob.Character;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainScreen implements Screen {

    SpriteBatch batch;
    private OrthographicCamera camera;
    private Character character;
    private TiledMapRenderer renderer;
    Stage stage;
    TiledMap map;
    ContactListener listener;
    boolean jefe;

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("level1.tmx");
        final float pixelsPerTile = 32;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);

        character = new Character(1);
        character.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        character.setPosition(10, 5);
        stage.addActor(character);
    }

    @Override
    public void render(float delta) {
        
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(character.getX() >= 10 && jefe == false){
            camera.position.x = character.getX();
        }
        if(character.getX() <= 0){
            character.setPosition(0, character.getY());
        }
        if(character.getY() <= 0){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaInicio());
        }
        if(character.getX() >= 228){
            camera.position.x = 228.7f;
            jefe = true;
        }
        camera.update();

 
        renderer.setView(camera);
        renderer.render();

        stage.act(delta);
        stage.draw();
        
       
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 20, 10);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}
