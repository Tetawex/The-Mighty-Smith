package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.*;
import org.tetawex.tms.ecs.misc.ai.SkeletonBehavior;
import org.tetawex.tms.ecs.misc.ai.WeaponBehavior;
import org.tetawex.tms.ecs.misc.stats.Stats;

import java.util.HashMap;

/**
 * Created by tetawex on 30.01.17.
 */
public class WeaponTemplate extends Template {
    public WeaponTemplate(TMSGame game) {
        super(game);
    }

    @Override
    public Entity createEntity(Engine engine, org.tetawex.tms.util.Bundle bundle) {
        Entity entity=new Entity();
        entity.add(new TransformComponent(bundle.getItem("position",Vector2.class),new Vector2(32,32)));
        entity.add(new VelocityComponent(Vector2.Zero));

        StatsComponent statsComponent=new StatsComponent(bundle.getItem("stats", Stats.class));

        HashMap<String,Animation<TextureRegion>> animationHashMap=new HashMap<String, Animation<TextureRegion>>();
        animationHashMap.put("attack",getGame().getAnimationManager().getAnimation("sword_attack"));
        animationHashMap.put("idle",getGame().getAnimationManager().getAnimation("sword_idle"));

        AnimationComponent ac=new AnimationComponent(animationHashMap,
                true,statsComponent.getStats().getAttackInterval()/4,"idle");
        entity.add(ac);
        entity.add(new RenderComponent(ac.render()));

        entity.add(new StatsComponent(bundle.getItem("stats", Stats.class)));
        entity.add(new AIComponent(new WeaponBehavior()));
        entity.add(new AllyTagComponent());
        entity.add(new MessageComponent());

        return entity;
    }
}
