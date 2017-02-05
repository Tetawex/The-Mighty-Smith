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
    private AIState state=AIState.STATE_ATTACK;
    private Entity targetEntity;
    private Random random;

    public SkeletonBehavior(){
        random=new Random();
    }
    @Override
    public void act(float deltaTime,Engine engine, Entity entity) {
        StatsComponent sc = Mappers.stats.get(entity);
        AnimationComponent ac = Mappers.animation.get(entity);
        VelocityComponent vc = Mappers.velocity.get(entity);
        TransformComponent tc = Mappers.transform.get(entity);

        if(state==AIState.STATE_ATTACK){
            if(targetEntity!=null) {
                StatsComponent targetStats = Mappers.stats.get(targetEntity);
                if (targetStats.getStats().getCurrentHealth() <= 0)
                    targetEntity = null;
            }
            if(targetEntity==null) {
                setState(AIState.STATE_WALK);
                ac.setInterval(sc.getStats().getAttackInterval()/8);
                vc.setVelocity(new Vector2(-sc.getStats().getMovementSpeed(),0f));
                ac.setCurrentAnimation("walk");
                return;
            }
            else{
                if(sc.getStats().canAttack()) {
                    ac.setCurrentAnimation("attack" + (1 + random.nextInt(2)));
                    ac.setInterval(sc.getStats().getAttackInterval()/6);
                    sc.getStats().attack();
                    engine.getSystem(MessageSystem.class)
                            .putMessage(new AttackMessage(entity, targetEntity, sc.getStats().getAttackInterval()));
                }
            }
        }
        else if(tc.getPosition().x<320){

            ImmutableArray<Entity> entities=engine
                    .getEntitiesFor(Family.all(AllyTagComponent.class).get());
            for(Entity checkedEntity : entities){
                TransformComponent entityTransform=Mappers.transform.get(checkedEntity);
                if(Math.abs(entityTransform.getCenterPosition().y-tc.getCenterPosition().y)
                        <=8&&Math.abs(entityTransform.getCenterPosition().x-tc.getCenterPosition().x)
                        <=sc.getStats().getAttackRange()) {
                    setState(AIState.STATE_ATTACK);
                    vc.setVelocity(new Vector2(0f,0f));
                    targetEntity=checkedEntity;
                    return;
                }
            }
        }
    }

    public void setState(AIState state){
        this.state=state;
    }
}
