package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.util.ElevationComparator;

/**
 * Created by tetawex on 27.01.17.
 */
public class RenderSystem extends SortedIteratingSystem {
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);


    public Batch getBatch() {
        return batch;
    }

    public Vector2 getViewportDimension() {
        return viewportDimension;
    }

    public void setViewportDimension(Vector2 viewportDimension) {
        this.viewportDimension = viewportDimension;
    }

    private Vector2 viewportDimension;

    private Vector2 origin=Vector2.Zero;
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    private Batch batch;

    public RenderSystem() {
        super(Family.all(TransformComponent.class, RenderComponent.class).get(),
                new ElevationComparator(ComponentMapper.getFor(RenderComponent.class),
                        ComponentMapper.getFor(TransformComponent.class)));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent renderComponent=rm.get(entity);
        TransformComponent transformComponent=tm.get(entity);
        renderComponent.getRenderer().render(batch,origin,
                transformComponent.getPosition(), transformComponent.getDimension());
    }

    public void setOrigin(Vector2 vector2) {
        origin=vector2;
    }
}
