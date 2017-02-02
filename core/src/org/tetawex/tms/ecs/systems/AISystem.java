package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.tetawex.tms.ecs.components.AIComponent;

/**
 * Created by tetawex on 30.01.17.
 */
public class AISystem extends IteratingSystem {
    private ComponentMapper<AIComponent> am = ComponentMapper.getFor(AIComponent.class);

    public AISystem() {
        super(Family.all(AIComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        am.get(entity).getBehavior().act(deltaTime, getEngine(), entity);
    }
}
