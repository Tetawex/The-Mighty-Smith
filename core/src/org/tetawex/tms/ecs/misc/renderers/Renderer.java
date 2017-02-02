package org.tetawex.tms.ecs.misc.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tetawex on 27.01.17.
 */
public interface Renderer {
    void render(Batch batch, Vector2 origin, Vector2 position, Vector2 dimension);
    void setTextureRegion(TextureRegion region);
}
