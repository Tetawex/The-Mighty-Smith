package org.tetawex.tms.ecs.misc.ai;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

/**
 * Created by tetawex on 30.01.17.
 */
public interface Behavior {
    void act(float deltaTime,Engine engine, Entity entity);
}
