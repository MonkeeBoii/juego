package MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Personajes.Bob.Character;
import Personajes.Bob.CharacterMovementController;
import Personajes.Bob.MyInputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyGame extends ApplicationAdapter {

    SpriteBatch batch;
    private OrthographicCamera camera;
    private Character character;
    private CharacterMovementController movementController;
    private MyInputProcessor inputProcessor;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.update();

        character = new Character(100, 100, 2);
        movementController = new CharacterMovementController(character, 200); // Aumenté la velocidad a 200 píxeles por segundo
        inputProcessor = new MyInputProcessor(movementController); // Asigna el controlador de movimiento al procesador de entrada
        Gdx.input.setInputProcessor(inputProcessor); // Configura el procesador de entrada
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime(); // Obtener el tiempo transcurrido desde el último fotograma

        // Limpia la pantalla
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Vector2 mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector3 mousePosition3D = new Vector3(mousePosition.x, mousePosition.y, 0);

        
        batch.begin();
        character.render(batch, movementController.comprobarBotones(delta));
        character.mousePosition();
        batch.end();
        camera.unproject(mousePosition3D);

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
