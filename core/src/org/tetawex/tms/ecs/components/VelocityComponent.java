package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tetawex on 27.01.17.
 */
public class VelocityComponent implements Component {
    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    private Vector2 velocity;

    public VelocityComponent(Vector2 velocity){
        setVelocity(velocity);
    }
}
