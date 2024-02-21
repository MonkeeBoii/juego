/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
public class OptionScreen implements Screen {

    Stage stage;
    Preferences prefs = Gdx.app.getPreferences("MyPreferenceFile");

    boolean myBooleanValue = prefs.getBoolean("myBooleanKey", false);
    Integer num = prefs.getInteger("myInteger", 0);

    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Drawable botnAudio = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/botonOption.png")));
        Drawable audio = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/altavoz.png")));
        Drawable audioMute = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/altavozMute.png")));

        ImageButton.ImageButtonStyle botnAudioStyle = new ImageButton.ImageButtonStyle();

        botnAudioStyle.up = botnAudio;
        if (myBooleanValue) {
            botnAudioStyle.imageUp = audio;
        } else {
            botnAudioStyle.imageUp = audioMute;
        }

        ImageButton botnAudioInterfaz = new ImageButton(botnAudioStyle);
        botnAudioInterfaz.setPosition((stage.getWidth() - botnAudioInterfaz.getWidth()) / 2 - 100, 200);
        botnAudioInterfaz.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences prefs = Gdx.app.getPreferences("MyPreferenceFile");
                prefs.putBoolean("myBooleanKey", !prefs.getBoolean("myBooleanKey"));
                prefs.flush();
                myBooleanValue = prefs.getBoolean("myBooleanKey", false);
                if (myBooleanValue) {
                    botnAudioInterfaz.getStyle().imageUp = audio;
                } else {
                    botnAudioInterfaz.getStyle().imageUp = audioMute;
                }
            }
        });

        Drawable facil = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/facil.png")));
        Drawable medio = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/nomal.png")));
        Drawable dificil = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/dificil.png")));
        ImageButton.ImageButtonStyle botnDerStyle = new ImageButton.ImageButtonStyle();
        botnDerStyle.up = botnAudio;
        switch (num) {
            case 0:
                botnDerStyle.imageUp = facil;
                break;

            case 1:
                botnDerStyle.imageUp = medio;
                break;

            case 2:
                botnDerStyle.imageUp = dificil;
                break;

        }

        ImageButton botnDer = new ImageButton(botnDerStyle);
        botnDer.setPosition((stage.getWidth() - botnAudioInterfaz.getWidth()) / 2 + 100, 200);
        botnDer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                num++;
                if (num == 3) {
                    num = 0;
                }
                Preferences prefs = Gdx.app.getPreferences("MyPreferenceFile");
                prefs.putInteger("myInteger", num);
                prefs.flush();
                num = prefs.getInteger("myInteger");
                switch (num) {
                    case 0:
                        botnDerStyle.imageUp = facil;
                        break;

                    case 1:
                        botnDerStyle.imageUp = medio;
                        break;

                    case 2:
                        botnDerStyle.imageUp = dificil;
                        break;

                }
            }
        });

        Texture fondoTexture = new Texture(Gdx.files.internal("gui/fondo.png"));
        Image fondo = new Image(fondoTexture);
        fondo.setSize(stage.getWidth(), stage.getHeight());

        Drawable btonSalir = new TextureRegionDrawable(new Texture(Gdx.files.internal("gui/salirMenu.png")));
        ImageButton.ImageButtonStyle botnSalirStyle = new ImageButton.ImageButtonStyle();
        botnSalirStyle.up = btonSalir;
        ImageButton btonSalirMenu = new ImageButton(botnSalirStyle);
        btonSalirMenu.setPosition(0, stage.getHeight() - btonSalirMenu.getWidth());
        btonSalirMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaInicio());
            }
        });

        stage.addActor(fondo);
        stage.addActor(botnAudioInterfaz);
        stage.addActor(botnDer);
        stage.addActor(btonSalirMenu);

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
    }

}
