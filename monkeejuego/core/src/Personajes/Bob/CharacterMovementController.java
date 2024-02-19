package Personajes.Bob;

public class CharacterMovementController {

    private Character character;
    private float velocidad; // Velocidad de movimiento del personaje en píxeles por segundo
    private boolean derechaPresionado;
    private boolean izquierdaPresionado;
    private boolean arribaPresionado;
    private boolean abajoPresionado;
    private boolean ismoving;

    public CharacterMovementController(Character character, float velocidad) {
        this.character = character;
        this.velocidad = velocidad;
    }

    public void moverDerecha(float delta) {
        character.x += velocidad * delta; // Mover el personaje hacia la derecha
    }

    public void moverIzquierda(float delta) {
        character.x -= velocidad * delta; // Mover el personaje hacia la izquierda
    }

    public void moverArriba(float delta) {
        character.y += velocidad * delta; // Mover el personaje hacia arriba
    }

    public void moverAbajo(float delta) {
        character.y -= velocidad * delta; // Mover el personaje hacia abajo
    }

    // Métodos para establecer el estado de los botones de dirección
    public void setDerechaPresionado(boolean derechaPresionado) {
        this.derechaPresionado = derechaPresionado;
    }

    public void setIzquierdaPresionado(boolean izquierdaPresionado) {
        this.izquierdaPresionado = izquierdaPresionado;
    }

    public void setArribaPresionado(boolean arribaPresionado) {
        this.arribaPresionado = arribaPresionado;
    }

    public void setAbajoPresionado(boolean abajoPresionado) {
        this.abajoPresionado = abajoPresionado;
    }

    public boolean isDerechaPresionado() {
        return derechaPresionado;
    }

    public boolean isIzquierdaPresionado() {
        return izquierdaPresionado;
    }

    public boolean isArribaPresionado() {
        return arribaPresionado;
    }

    public boolean isAbajoPresionado() {
        return abajoPresionado;
    }

    public boolean comprobarBotones(float delta) {

        if (!derechaPresionado && !izquierdaPresionado && !arribaPresionado && !abajoPresionado) {
            ismoving = false;
        } else {
            ismoving = true;
        }

        if (derechaPresionado) {
            moverDerecha(delta);
        } else if (izquierdaPresionado) {
            moverIzquierda(delta);
        }

        if (arribaPresionado) {
            moverArriba(delta);
        } else if (abajoPresionado) {
            moverAbajo(delta);
        }

        return ismoving;
    }

}
