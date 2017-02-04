package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by tetawex on 04.02.17.
 */
public class DelayedRemovalComponent implements Component {
    public DelayedRemovalComponent(float delay) {
        this.delay = delay;
    }

    public float getDelay() {
        return delay;
    }
    public void tick(float deltaTime) {
        delay-=deltaTime;
    }

    private float delay;
}
