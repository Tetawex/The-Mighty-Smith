package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.components.VelocityComponent;

/**
 * Created by tetawex on 27.01.17.
 */
public class MovementSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = tm.get(entity);
        VelocityComponent velocityComponent = vm.get(entity);

        transformComponent.setPosition(transformComponent
                .getPosition().mulAdd(velocityComponent.getVelocity(),deltaTime));
    }
}
