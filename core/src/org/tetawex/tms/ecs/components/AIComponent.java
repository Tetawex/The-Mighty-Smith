package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import org.tetawex.tms.ecs.misc.ai.Behavior;

/**
 * Created by tetawex on 30.01.17.
 */
public class AIComponent implements Component {
    public Behavior getBehavior() {
        return behavior;
    }
    public AIComponent(Behavior behavior){
        this.behavior=behavior;
    }
    public AIComponent(){
        this(new Behavior() {
            @Override
            public void act(float deltaTime, Engine engine, Entity entity) {

            }
        });
    }
    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    private Behavior behavior;
}
