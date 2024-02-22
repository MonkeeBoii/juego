/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;

/**
 * @author MonkeeBoi - 23:08 - 21/02/2024
 */
public class Musica {

    AssetManager assetManager;
    ArrayList<MusicObj> musicas;

    public Musica() {
        musicas = new ArrayList<>();
        assetManager = new AssetManager();
    }

    /**
     * Metodo que crea una cancion
     *
     * @param ruta ruta de la cancion
     * @param looping si se va a repetir
     * @param volume el volumen
     * @param nombre nombre con el que se va a guardar la cancion
     * @param sfx si quieres hacer un sonido y no una cancion
     */
    public void newMusic(String ruta, boolean looping, float volume, String nombre, boolean sfx) {
        assetManager.load(ruta, Music.class);
        assetManager.finishLoading();
        Music newMusic = assetManager.get(ruta, Music.class);
        newMusic.setLooping(looping);
        newMusic.setVolume(volume);
        musicas.add(new MusicObj(nombre, newMusic, sfx));
    }

    /**
     * Metodo que recoje la musica
     *
     * @param nombre nombre que se va a buscar
     * @return retorna la Excepcion
     */
    public Music getMusic(String nombre) {
        if (musicas != null) {
            for (MusicObj musica : musicas) {
                if (musica.search(nombre) != null) {
                    if(musica.sfx == false){
                        musica.setEscuchando(true);
                    }
                    return musica.search(nombre);
                }
            }
        }
        return null;
    }

    /**
     * Metodo que para la cancion
     *
     * @param nombre
     */
    public void stopMusic(String nombre) {
        if (musicas != null) {
            for (MusicObj musica : musicas) {
                if (musica.search(nombre) != null) {
                    if (musica.escuchando == true) {
                        musica.setEscuchando(false);
                        musica.musica.stop();
                    }
                }
            }
        }
    }
    
    /**
     * Metodo que comprueba si se esta escuchando una cancion
     * 
     * @return true -> no se esta escuchando musica false-> si se esta escuchando musica
     */
    public boolean filterMusic(){
        if(musicas!= null){
            for(MusicObj musica : musicas){
                if(musica.escuchando == true){
                    System.out.println(musica.escuchando);
                    return false;
                }
            }
        }
        return true;
    }

    private class MusicObj {

        String Nombre;
        Music musica;
        boolean escuchando = false;
        boolean sfx;

        private MusicObj(String Nombre, Music music, boolean sfx) {
            this.Nombre = Nombre;
            this.musica = music;
            this.sfx = sfx;

        }

        private Music search(String Nombre) {
            if (this.Nombre.equals(Nombre)) {
                return this.musica;
            }
            return null;
        }

        public boolean isEscuchando() {
            return escuchando;
        }

        public void setEscuchando(boolean escuchando) {
            this.escuchando = escuchando;
        }

    }
}
