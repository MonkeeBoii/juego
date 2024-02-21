package MyGame;

import Screen.PantallaInicio;
import com.badlogic.gdx.Game;

public class MyGame extends Game {

    public void create() {
        this.setScreen(new PantallaInicio());
    }
}
