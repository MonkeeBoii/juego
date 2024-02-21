package Object;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureUtils {

   public static Texture createTextureFromRegion(TextureRegion region) {
        int width = region.getRegionWidth();
        int height = region.getRegionHeight();

        Texture texture = region.getTexture();
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap originalPixmap = texture.getTextureData().consumePixmap();

        Pixmap pixmap = new Pixmap(width, height, originalPixmap.getFormat());

        int x = region.getRegionX();
        int y = region.getRegionY();

        pixmap.drawPixmap(originalPixmap, x, y, width, height, 0, 0, width, height);

        Texture individualTexture = new Texture(pixmap);

        pixmap.dispose();
        originalPixmap.dispose();

        return individualTexture;
    }
}