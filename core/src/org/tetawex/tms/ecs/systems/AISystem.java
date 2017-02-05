package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import org.tetawex.tms.ecs.components.AIComponent;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 30.01.17.
 */
public class AISystem extends IteratingSystem {

    public AISystem() {
        super(Family.all(AIComponent.class).get(),-1);
    }

    @Override
    protected void processEntity(Entity entity,float deltaTime) {
        Mappers.ai.get(entity).getBehavior().act(deltaTime, getEngine(), entity);
    }
}
