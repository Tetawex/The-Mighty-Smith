package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.AnimationComponent;
import org.tetawex.tms.ecs.components.DelayedRemovalComponent;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.util.Bundle;

import java.util.HashMap;

/**
 * Created by tetawex on 04.02.17.
 */
public class DeathTemplate extends Template {

    public DeathTemplate(TMSGame game) {
        super(game);
    }

    @Override
    public Entity createEntity(Engine engine, Bundle bundle) {
        Entity entity=new Entity();

        HashMap<String,Animation<TextureRegion>> animationHashMap=
                new HashMap<String, Animation<TextureRegion>>();

        animationHashMap.put("death",
                getGame().getAnimationManager()
                        .getAnimation(bundle
                                .getItem("name",String.class)+"_death"));

        AnimationComponent ac=new AnimationComponent(animationHashMap,
                true,0.15f,"death");
        entity.add(ac);
        entity.add(new TransformComponent(bundle.getItem("position", Vector2.class)));
        entity.add(new RenderComponent(ac.render()));

        Object[] o=ac.getCurrentAnimation().getKeyFrames();
        entity.add(new DelayedRemovalComponent(0.15f*o.length));
        return entity;
    }
}
