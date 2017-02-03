package org.tetawex.tms.ecs.misc.messaging.messages;


import com.badlogic.ashley.core.Entity;

/**
 * Created by tetawex on 31.01.17.
 */
public abstract class Message {
    private Entity sender;
    private Entity receiver;

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    private float delay;

    public Message(Entity sender,Entity receiver,float delay){
        this.sender=sender;
        this.receiver=receiver;
        this.delay=delay;
    }
    public Message(Entity sender,Entity receiver){
        this(sender,receiver,0f);
    }
    public Entity getSender() {
        return sender;
    }
    public Entity getReceiver() {
        return receiver;
    }
}
