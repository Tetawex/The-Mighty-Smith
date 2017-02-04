package org.tetawex.tms.ecs.misc.messaging.listeners;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.misc.messaging.messages.Message;
import org.tetawex.tms.util.Bundle;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 34.01.17.
 */
public class DeathMessageListener extends MessageListener {
    public DeathMessageListener(TMSGame game, Engine engine) {
        super(game, engine);
    }

    @Override
    public void process(Message message) {
        Entity entity=new Entity();
        Bundle bundle=new Bundle();
        bundle.putItem("name", Mappers.stats.get(entity).getStats().getName());
        engine.addEntity(game.getTemplateManager()
                .getTemplate("death")
                .createEntity(engine,bundle));
    }
}
