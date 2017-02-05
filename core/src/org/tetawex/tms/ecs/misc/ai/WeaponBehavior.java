package org.tetawex.tms.ecs.misc.ai;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.ecs.components.*;
import org.tetawex.tms.ecs.misc.messaging.messages.AttackMessage;
import org.tetawex.tms.ecs.systems.MessageSystem;
import org.tetawex.tms.util.Mappers;

import java.util.Random;

/**
 * Created by tetawex on 30.01.17.
 */
public class WeaponBehavior implements Behavior {
    private AIState state= AIState.STATE_ATTACK;
    private Entity targetEntity;
    private Random random;

    public WeaponBehavior(){
        random=new Random();
    }
    @Override
    public void act(float deltaTime,Engine engine, Entity entity) {
        StatsComponent sc=Mappers.stats.get(entity);
        AnimationComponent ac=Mappers.animation.get(entity);
        TransformComponent tc=Mappers.transform.get(entity);

        if(state==AIState.STATE_ATTACK){
            if(targetEntity!=null) {
                StatsComponent targetStats = Mappers.stats.get(targetEntity);
                if (targetStats.getStats().getCurrentHealth() <= 0)
                    targetEntity = null;
            }
            if(targetEntity==null) {
                setState(AIState.STATE_WALK);
                ac.setInterval(sc.getStats().getAttackInterval()/4);
                ac.setCurrentAnimation("idle");
                return;
            }
            else{
                if(sc.getStats().canAttack()) {
                    ac.setCurrentAnimation("attack");
                    ac.setInterval(sc.getStats().getAttackInterval()/7);
                    sc.getStats().attack();
                    engine.getSystem(MessageSystem.class)
                            .putMessage(new AttackMessage(entity, targetEntity, sc.getStats().getAttackInterval()));
                }
            }
        }

        else{
            ImmutableArray<Entity> entities=engine
                    .getEntitiesFor(Family.all(EnemyTagComponent.class).get());
            for(Entity checkedEntity : entities){
                TransformComponent entityTransform=Mappers.transform.get(checkedEntity);
                if(Math.abs(entityTransform.getCenterPosition().y-tc.getCenterPosition().y)
                        <=8&&Math.abs(entityTransform.getCenterPosition().x-tc.getCenterPosition().x)
                        <=sc.getStats().getAttackRange()){
                    setState(AIState.STATE_ATTACK);
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
