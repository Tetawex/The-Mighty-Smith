package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.TransformComponent;

/**
 * Created by tetawex on 02.02.17.
 */
public class FloorTemplate extends Template {
    public FloorTemplate(TMSGame game) {
        super(game);
    }

    @Override
    public Entity createEntity(Engine engine, Bundle bundle) {
        Entity entity=new Entity();
        entity.add(new TransformComponent(bundle.getItem("position", Vector2.class),new Vector2(400,400)));

        return entity;
    }
}
