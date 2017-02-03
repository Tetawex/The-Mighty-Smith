package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.tetawex.tms.ecs.components.MessageComponent;
import org.tetawex.tms.ecs.misc.messaging.listeners.AttackMessageListener;
import org.tetawex.tms.ecs.misc.messaging.listeners.MessageListener;
import org.tetawex.tms.ecs.misc.messaging.messages.AttackMessage;
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
    public MessageSystem() {
        messages=new LinkedList<Message>();
        listenerMap=new HashMap<Class, MessageListener>();
    }
    private void initListenerMap(){
        listenerMap.put(AttackMessage.class, new AttackMessageListener());
    }
    public void putMessage(Message message){
        messages.add(message);
    }

    @Override
    public void update(float deltaTime){
        if(!messages.isEmpty()){
            Message message=messages.remove();
            listenerMap.get(message.getClass()).tick(message,deltaTime);
        }
    }
}
