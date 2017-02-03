package org.tetawex.tms.ecs.misc.messaging.listeners;

import org.tetawex.tms.ecs.components.StatsComponent;
import org.tetawex.tms.ecs.misc.messaging.messages.Message;
import org.tetawex.tms.util.Mappers;

/**
 * Created by tetawex on 31.01.17.
 */
public class AttackMessageListener extends MessageListener {
    @Override
    public void process(Message message) {
        StatsComponent attackerStats= Mappers.stats.get(message.getSender());
        StatsComponent defenderStats=Mappers.stats.get(message.getReceiver());

        defenderStats.getStats().setMaxHealth(defenderStats.getStats()
                .getMaxHealth()-attackerStats.getStats().getAttackDamage());
    }
}
