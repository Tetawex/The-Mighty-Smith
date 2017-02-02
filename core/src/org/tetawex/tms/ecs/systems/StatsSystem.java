package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.tetawex.tms.ecs.components.StatsComponent;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 31.01.17.
 */
public class StatsSystem extends IteratingSystem {
    public StatsSystem() {
        super(Family.all(StatsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Mappers.stats.get(entity).getStats().tick(deltaTime);
    }
}
