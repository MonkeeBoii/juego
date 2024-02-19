/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personajes.Bob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationLoader {

    public static Animation<TextureRegion> loadAnimation(String spritesheetPath, int startX, int startY, int frameWidth, int frameHeight, int frameCount, float frameDuration) {
    Texture spritesheet = new Texture(Gdx.files.internal(spritesheetPath));
    TextureRegion[][] tmp = TextureRegion.split(spritesheet, frameWidth, frameHeight);
    TextureRegion[] frames = new TextureRegion[frameCount];

    int index = 0;
    for (int j = startX; j < startX + frameCount; j++) {
        frames[index++] = tmp[startY][j];
    }

    return new Animation<>(frameDuration, frames);
}

}