package org.tetawex.tms.ecs.misc.messaging.listeners;

import com.badlogic.ashley.core.Engine;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.misc.messaging.messages.Message;

/**
 * Created by tetawex on 31.01.17.
 */
public abstract class MessageListener {
    protected TMSGame game;
    protected Engine engine;
    public MessageListener(TMSGame game,Engine engine){
        this.game=game;
        this.engine=engine;
    }
    public abstract void process(Message message);
    public void tick(Message message,float deltaTime){
        message.tick(deltaTime);
        if(message.getDelay()<=0){
            process(message);
            message.setProcessed(true);
        }
    }
}