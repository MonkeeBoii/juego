package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author monkeeboi
 */
public class PantallaInicio implements Screen {

    Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Drawable botnPulsado = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/boton.png")));
        Drawable drawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/botonPulsado.png")));
        Drawable drawTextInicio = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/start.png")));

        Drawable option = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/options.png")));
        Drawable exit = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/exit.png")));

        //start
        ImageButton.ImageButtonStyle estiloBoton = new ImageButton.ImageButtonStyle();
        estiloBoton.up = drawable;
        estiloBoton.down = botnPulsado;
        estiloBoton.imageUp = drawTextInicio;

        //option
        ImageButton.ImageButtonStyle estiloBotonoption = new ImageButton.ImageButtonStyle();
        estiloBotonoption.up = drawable;
        estiloBotonoption.down = botnPulsado;
        estiloBotonoption.imageUp = option;

        //exit
        ImageButton.ImageButtonStyle estiloBotonexit = new ImageButton.ImageButtonStyle();
        estiloBotonexit.up = drawable;
        estiloBotonexit.down = botnPulsado;
        estiloBotonexit.imageUp = exit;

        ImageButton inicio = new ImageButton(estiloBoton);
        ImageButton optionImage = new ImageButton(estiloBotonoption);
        ImageButton exitImage = new ImageButton(estiloBotonexit);

        inicio.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
            }
        });
        optionImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionScreen());
            }
        });
        exitImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        inicio.setPosition((stage.getWidth() - inicio.getWidth()) / 2, 260);
        optionImage.setPosition((stage.getWidth() - inicio.getWidth()) / 2, 200);
        exitImage.setPosition((stage.getWidth() - inicio.getWidth()) / 2, 140);

        Texture fondoTexture = new Texture(Gdx.files.internal("gui/fondo.png"));
        Image fondo = new Image(fondoTexture);
        fondo.setSize(stage.getWidth(), stage.getHeight());

        stage.addActor(fondo);
        stage.addActor(inicio);
        stage.addActor(optionImage);
        stage.addActor(exitImage);
    }

    @Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {
        stage.dispose();
    }

}
