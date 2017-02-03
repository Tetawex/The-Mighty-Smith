package org.tetawex.tms.ecs.misc.messaging.listeners;

import org.tetawex.tms.ecs.misc.messaging.messages.Message;

/**
 * Created by tetawex on 31.01.17.
 */
public abstract class MessageListener {
    public abstract void process(Message message);
    public void tick(Message message,float deltaTime){
        message.tick(deltaTime);
        if(message.getDelay()<=0){
            process(message);
            message.setProcessed(true);
        }
    }
}