package org.tetawex.tms.ecs.misc.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tetawex on 27.01.17.
 */
public class SpriteRenderer implements Renderer {
    private boolean scaleToDimension;

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    private TextureRegion textureRegion;
    public SpriteRenderer(TextureRegion textureRegion,boolean scaleToDimension) {
        this.scaleToDimension=scaleToDimension;
        this.textureRegion=textureRegion;
    }
    @Override
    public void render(Batch batch, Vector2 origin, Vector2 position, Vector2 dimension) {
        if(!scaleToDimension)
            batch.draw(textureRegion, position.x+origin.x, position.y+origin.y);
        else
            batch.draw(textureRegion, position.x+origin.x, position.y+origin.y,dimension.x,dimension.y);
    }
}
