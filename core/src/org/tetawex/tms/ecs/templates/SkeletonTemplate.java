package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.*;
import org.tetawex.tms.ecs.misc.ai.SkeletonBehavior;
import org.tetawex.tms.ecs.misc.stats.Stats;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by tetawex on 30.01.17.
 */
public class SkeletonTemplate extends Template {
    private Random random;
    public SkeletonTemplate(TMSGame game) {
        super(game);
        random=new Random();
    }

    @Override
    public Entity createEntity(Engine engine, org.tetawex.tms.util.Bundle bundle) {
        Entity entity=new Entity();
        entity.add(new TransformComponent(bundle.getItem("position",Vector2.class),new Vector2(32,32)));
        entity.add(new VelocityComponent(Vector2.Zero));

        StatsComponent statsComponent=new StatsComponent(bundle.getItem("stats", Stats.class));
        statsComponent.getStats().setName("skeleton_basic");

        HashMap<String,Animation<TextureRegion>> animationHashMap=new HashMap<String, Animation<TextureRegion>>();
        animationHashMap.put("walk",getGame().getAnimationManager().getAnimation("skeleton_basic_walk"));
        animationHashMap.put("attack1",getGame().getAnimationManager().getAnimation("skeleton_basic_attack1"));
        animationHashMap.put("attack2",getGame().getAnimationManager().getAnimation("skeleton_basic_attack2"));

        AnimationComponent ac=new AnimationComponent(animationHashMap,
                true,statsComponent.getStats().getAttackInterval()/6,"walk");
        entity.add(ac);
        entity.add(new RenderComponent(ac.render()));

        entity.add(statsComponent);
        entity.add(new AIComponent(new SkeletonBehavior()));
        entity.add(new EnemyTagComponent());
        entity.add(new MessageComponent());



        return entity;
    }
}
