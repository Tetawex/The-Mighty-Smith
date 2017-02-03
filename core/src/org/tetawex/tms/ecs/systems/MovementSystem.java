package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.components.VelocityComponent;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 27.01.17.
 */
public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.transform.get(entity);
        VelocityComponent velocityComponent = Mappers.velocity.get(entity);

        transformComponent.getPosition().mulAdd(velocityComponent.getVelocity(),deltaTime);
    }
}
