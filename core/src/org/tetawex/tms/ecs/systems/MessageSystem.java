package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.MessageComponent;
import org.tetawex.tms.ecs.misc.messaging.listeners.AttackMessageListener;
import org.tetawex.tms.ecs.misc.messaging.listeners.DeathMessageListener;
import org.tetawex.tms.ecs.misc.messaging.listeners.MessageListener;
import org.tetawex.tms.ecs.misc.messaging.messages.AttackMessage;
import org.tetawex.tms.ecs.misc.messaging.messages.DeathMessage;
import org.tetawex.tms.ecs.misc.messaging.messages.Message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by tetawex on 31.01.17.
 */
public class MessageSystem extends EntitySystem {
    private Map<Class,MessageListener> listenerMap;
    private Queue<Message> messages;
    private TMSGame game;
    public MessageSystem(TMSGame game) {
        messages=new LinkedList<Message>();
        listenerMap=new HashMap<Class, MessageListener>();
        this.game=game;
        initListenerMap();
    }
    private void initListenerMap(){
        listenerMap.put(AttackMessage.class, new AttackMessageListener(game,getEngine()));
        listenerMap.put(DeathMessage.class, new DeathMessageListener(game,getEngine()));
    }
    public void putMessage(Message message){
        messages.add(message);
    }

    @Override
    public void update(float deltaTime){
        Queue<Message> tickedMessages=new LinkedList<Message>();
        while(!messages.isEmpty()){
            Message message=messages.remove();
            listenerMap.get(message.getClass()).tick(message,deltaTime);
            if(!message.isProcessed())
                tickedMessages.add(message);
        }
        messages.addAll(tickedMessages);
    }
}
