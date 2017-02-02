package org.tetawex.tms.ecs.misc.messaging.listeners;

import org.tetawex.tms.ecs.misc.messaging.messages.Message;

/**
 * Created by tetawex on 31.01.17.
 */
public interface MessageListener {
    void process(Message message);
}
