package MyGame;

import Screen.MainScreen;
import com.badlogic.gdx.Game;

public class MyGame extends Game {

    public void create() {
        this.setScreen(new MainScreen());
    }
}
