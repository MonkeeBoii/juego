package MyGame;

import Object.Musica;
import Screen.PantallaInicio;
import com.badlogic.gdx.Game;

public class MyGame extends Game {

    Musica music = new Musica();

    public void create() {
        music.newMusic("Music/menu.ogg", true, 0.5f, "fondo", false);
        music.newMusic("Music/level1.ogg", true, 0.5f, "level1", false);
        music.newMusic("Music/jefe.ogg", true, 0.5f, "jefe", false);
        this.setScreen(new PantallaInicio(music));
    }
}
