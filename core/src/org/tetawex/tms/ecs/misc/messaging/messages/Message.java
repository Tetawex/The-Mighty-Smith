package org.tetawex.tms.ecs.misc.messaging.messages;


import com.badlogic.ashley.core.Entity;

/**
 * Created by tetawex on 31.01.17.
 */
public abstract class Message {
    private Entity sender;
    private Entity receiver;

    public Message(Entity sender,Entity receiver){
        this.sender=sender;
        this.receiver=receiver;
    }
    public Entity getSender() {
        return sender;
    }
    public Entity getReceiver() {
        return receiver;
    }
}
