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
     */
    public void newMusic(String ruta, boolean looping, float volume, String nombre){
        assetManager.load(ruta, Music.class);
        assetManager.finishLoading();
        Music newMusic = assetManager.get(ruta, Music.class);
        newMusic.setLooping(looping);
        newMusic.setVolume(volume);
        musicas.add(new MusicObj(nombre, newMusic));
    }
    
    /**
     * Metodo que recoje la musica
     * 
     * @param nombre nombre que se va a buscar
     * @return retorna la Excepcion
     * @throws Exception si no se encuentra el objeto
     */
    public Music getMusic(String nombre) throws Exception{
        if(musicas != null){
            for(MusicObj musica : musicas){
                if(musica.search(nombre) != null){
                    return musica.search(nombre);
                }
            }
        }
        
        throw new Exception("No se ha encontrado");
    }
    
    
    private class MusicObj{
        String Nombre;
        Music musica;
        
        private MusicObj(String Nombre, Music music){
            this.Nombre = Nombre;
            this.musica = music;
        }
        
        private Music search(String Nombre){
            if(this.Nombre.equals(Nombre)){
                return this.musica;
            }
            return null;
        }
    }
}
