package Personajes.Bob;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

    private CharacterMovementController movementController;
    private float delta;

    public MyInputProcessor(CharacterMovementController movementController) {
        this.movementController = movementController;
    }

    @Override
    public boolean keyDown(int keycode) {
        // Manejar eventos de teclado
        switch (keycode) {
            case Input.Keys.D:
                movementController.setDerechaPresionado(true);
                System.out.println("Derecha presionada");
                break;
            case Input.Keys.A:
                movementController.setIzquierdaPresionado(true);
                System.out.println("Izquierda presionada");
                break;
            case Input.Keys.W:
                movementController.setArribaPresionado(true);
                System.out.println("Arriba presionada");
                break;
            case Input.Keys.S:
                movementController.setAbajoPresionado(true);
                System.out.println("Abajo presionada");
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.D:
                movementController.setDerechaPresionado(false);
                System.out.println("Derecha liberada");
                break;
            case Input.Keys.A:
                movementController.setIzquierdaPresionado(false);
                System.out.println("Izquierda liberada");
                break;
            case Input.Keys.W:
                movementController.setArribaPresionado(false);
                System.out.println("Arriba liberada");
                break;
            case Input.Keys.S:
                movementController.setAbajoPresionado(false);
                System.out.println("Abajo liberada");
                break;
        }
        return true;
    }

    // Otros métodos de la interfaz InputProcessor que debes implementar, pero pueden dejarse vacíos
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    // Otros métodos de la interfaz InputProcessor que debes implementar, pero pueden dejarse vacíos
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
