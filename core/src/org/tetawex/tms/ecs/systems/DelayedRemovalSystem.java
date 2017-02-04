package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.tetawex.tms.ecs.components.DelayedRemovalComponent;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 04.02.17.
 */
public class DelayedRemovalSystem extends IteratingSystem {
    public DelayedRemovalSystem() {
        super(Family.all(DelayedRemovalComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DelayedRemovalComponent delayedRemovalComponent=Mappers.delayedRemoval.get(entity);
        delayedRemovalComponent.tick(deltaTime);
        if(delayedRemovalComponent.getDelay()<=0){
            getEngine().removeEntity(entity);
        }
    }
}
