package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.StatsComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.misc.messaging.messages.DeathMessage;
import org.tetawex.tms.ecs.misc.messaging.messages.Message;
import org.tetawex.tms.util.Bundle;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 31.01.17.
 */
public class StatsSystem extends IteratingSystem {
    private TMSGame game;
    public StatsSystem(TMSGame game) {
        super(Family.all(StatsComponent.class).get());
        this.game=game;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StatsComponent statsComponent=Mappers.stats.get(entity);
        TransformComponent transformComponent=Mappers.transform.get(entity);
        statsComponent.getStats().tick(deltaTime);
        if(statsComponent.getStats().getCurrentHealth()<=0){
            getEngine().removeEntity(entity);
            Bundle bundle=new Bundle();
            bundle.putItem("name",statsComponent.getStats().getName());
            bundle.putItem("position",transformComponent.getPosition());
            getEngine().addEntity(game.getTemplateManager()
                    .getTemplate("death").createEntity(getEngine(),bundle));
        }
    }
}
