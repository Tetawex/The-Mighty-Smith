package org.tetawex.tms.ecs.misc.messaging.messages;


import com.badlogic.ashley.core.Entity;

/**
 * Created by tetawex on 31.01.17.
 */
public class AttackMessage extends Message {
    public AttackMessage(Entity sender, Entity receiver) {
        super(sender, receiver);
    }
}
