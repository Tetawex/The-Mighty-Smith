package org.tetawex.tms.ecs.misc.messaging.listeners;

import com.badlogic.ashley.core.Engine;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.StatsComponent;
import org.tetawex.tms.ecs.misc.messaging.messages.DeathMessage;
import org.tetawex.tms.ecs.misc.messaging.messages.Message;
import org.tetawex.tms.ecs.systems.MessageSystem;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 31.01.17.
 */
public class AttackMessageListener extends MessageListener {
    public AttackMessageListener(TMSGame game, Engine engine) {
        super(game, engine);
    }

    @Override
    public void process(Message message) {
        StatsComponent attackerStats= Mappers.stats.get(message.getSender());
        StatsComponent defenderStats=Mappers.stats.get(message.getReceiver());

        defenderStats.getStats().receiveDamage(attackerStats.getStats().getAttackDamage());
        //if (defenderStats.getStats().getCurrentHealth()<=0){
           // engine.getSystem(MessageSystem.class)
            //.putMessage(new DeathMessage(message.getSender(),message.getReceiver(),0f));
        //}
    }
}
