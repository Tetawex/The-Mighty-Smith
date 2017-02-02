package org.tetawex.tms.ecs.misc.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tetawex on 27.01.17.
 */
public class TileRenderer implements Renderer {
    public Vector2 getTileDimension() {
        return tileDimension;
    }

    public void setTileDimension(Vector2 tileDimension) {
        this.tileDimension = tileDimension;
    }

    private Vector2 tileDimension;
    private TextureRegion textureRegion;

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TileRenderer(TextureRegion textureRegion, Vector2 tileDimension) {
        this.tileDimension=tileDimension;
        this.textureRegion=textureRegion;
    }
    @Override
    public void render(Batch batch, Vector2 origin, Vector2 position, Vector2 dimension) {
        for (int i=0;i<dimension.x/tileDimension.x;i++)
            for (int j=0;j<dimension.y/tileDimension.y;j++) {
                batch.draw(textureRegion,position.x+i*tileDimension.x+origin.x,
                        position.y+j*tileDimension.y+origin.y,tileDimension.x,tileDimension.y);
            }
    }
}
