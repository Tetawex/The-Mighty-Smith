package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.ecs.misc.renderers.Renderer;
import org.tetawex.tms.ecs.misc.renderers.SpriteRenderer;

/**
 * Created by tetawex on 27.01.17.
 */
public class RenderComponent implements Component {
    private float elevation;

    public Vector2 getOffset() {
        return offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }


    private Vector2 offset;

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    private Renderer renderer;


    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }
    public RenderComponent(Vector2 offset,float elevation,Renderer renderer) {
        setOffset(offset);
        setElevation(elevation);
        setRenderer(renderer);
    }
    public RenderComponent(TextureRegion textureRegion) {
        this(Vector2.Zero,0f,new SpriteRenderer(textureRegion,false));
    }
    public RenderComponent(TextureRegion textureRegion,float elevation) {
        this(Vector2.Zero,elevation,new SpriteRenderer(textureRegion, false));
    }



}
