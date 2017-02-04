package org.tetawex.tms.ecs.misc.messaging.messages;

import com.badlogic.ashley.core.Entity;

/**
 * Created by tetawex on 04.02.17.
 */
public class DeathMessage extends Message {
    public DeathMessage(Entity sender, Entity receiver, float delay) {
        super(sender, receiver, delay);
    }
}
