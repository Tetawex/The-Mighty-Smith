package org.tetawex.tms.ecs.misc.ai;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.ecs.components.*;
import org.tetawex.tms.ecs.components.AnimationComponent;
import org.tetawex.tms.ecs.misc.messaging.messages.AttackMessage;
import org.tetawex.tms.ecs.systems.MessageSystem;
import org.tetawex.tms.util.Mappers;

import java.util.Random;

/**
 * Created by tetawex on 30.01.17.
 */
public class SkeletonBehavior implements Behavior {
    public enum SkeletonState{STATE_ATTACK,STATE_WALK}
    private SkeletonState state=SkeletonState.STATE_WALK;
    private Entity targetEntity;
    private Random random;

    public SkeletonBehavior(){
        random=new Random();
    }
    @Override
    public void act(float deltaTime,Engine engine, Entity entity) {
        StatsComponent sc=Mappers.stats.get(entity);
        AnimationComponent ac=Mappers.animation.get(entity);
        VelocityComponent vc=Mappers.velocity.get(entity);
        TransformComponent tc=Mappers.transform.get(entity);
        MessageComponent mc=Mappers.message.get(entity);


        if (state!=SkeletonState.STATE_WALK&&targetEntity!=null){
            StatsComponent statsTargetEntity=Mappers.stats.get(targetEntity);

            if (statsTargetEntity.getStats().getCurrentHealth()<=0)
                targetEntity=null;

            state=SkeletonState.STATE_WALK;
            vc.setVelocity(new Vector2(-0.5f,0));
        }
        if(state==SkeletonState.STATE_ATTACK&&sc.getStats().canAttack()) {
            ac.setCurrentAnimation("attack" + random.nextInt(2));
            engine.getSystem(MessageSystem.class)
                    .putMessage(new AttackMessage(entity,targetEntity,sc.getStats().getAttackInterval()/2f));
        }
        if(state==SkeletonState.STATE_WALK){
            ImmutableArray<Entity> entities=engine
                    .getEntitiesFor(Family.all(TransformComponent.class,
                            StatsComponent.class, AllyTagComponent.class).get());
            for(Entity e : entities){
                TransformComponent entityTransform=Mappers.transform.get(e);
                if(Math.abs(entityTransform.getCenterPosition().x-tc.getCenterPosition().x)
                        <sc.getStats().getAttackRange()) {
                        state=SkeletonState.STATE_ATTACK;
                        ac.setCurrentAnimation("attack"+random.nextInt(2));
                        ac.setInterval(sc.getStats().getAttackSpeed()/5);
                        vc.setVelocity(new Vector2(0f,0));
                        return;
                }
            }
        }
        if(state==SkeletonState.STATE_WALK)
            vc.setVelocity(new Vector2(-0.5f,0));
    }
}
