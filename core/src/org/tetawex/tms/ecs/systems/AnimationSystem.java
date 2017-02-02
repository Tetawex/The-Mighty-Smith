package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.tetawex.tms.ecs.components.AnimationComponent;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 01.02.17.
 */
public class AnimationSystem extends IteratingSystem {
    public AnimationSystem() {
        super(Family.all(RenderComponent.class,AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent rc= Mappers.render.get(entity);
        AnimationComponent ac=Mappers.animation.get(entity);
        ac.tick(deltaTime);
        rc.getRenderer().setTextureRegion(ac.render());
    }
}
