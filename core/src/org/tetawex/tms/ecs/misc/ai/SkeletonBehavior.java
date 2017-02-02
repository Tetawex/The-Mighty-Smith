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
    private Random random;

    public SkeletonBehavior(){
        random=new Random();
    }
    @Override
    public void act(float deltaTime,Engine engine, Entity entity) {
        AnimationComponent ac=Mappers.animation.get(entity);
        VelocityComponent vc=Mappers.velocity.get(entity);
        TransformComponent tc=Mappers.transform.get(entity);
        MessageComponent mc=Mappers.message.get(entity);
        StatsComponent sc=Mappers.stats.get(entity);

        ImmutableArray<Entity> entities=engine
                .getEntitiesFor(Family.all(TransformComponent.class,
                        StatsComponent.class, AllyTagComponent.class).get());

        for(Entity e : entities){
            TransformComponent c=Mappers.transform.get(e);
            if(!(Math.abs(c.getPosition().x-tc.getPosition().x)
                    <sc.getStats().getAttackRange()) ) {
                if (state != SkeletonState.STATE_WALK) {
                    state=SkeletonState.STATE_WALK;
                    ac.setCurrentAnimation("walk");
                    vc.setVelocity(new Vector2(sc.getStats().getMovementSpeed(),0));
                }
            }
            else{
                engine.getSystem(MessageSystem.class).putMessage(new AttackMessage(entity,e));
                if(state!=SkeletonState.STATE_ATTACK||sc.getStats().canAttack()){
                    state=SkeletonState.STATE_ATTACK;
                    ac.setCurrentAnimation("attack"+random.nextInt(2));
                }
            }

        }
    }
}
