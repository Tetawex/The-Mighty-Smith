package org.tetawex.tms.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.misc.stats.RawStats;
import org.tetawex.tms.ecs.misc.stats.Stats;
import org.tetawex.tms.util.Bundle;

import java.util.Random;

/**
 * Created by tetawex on 27.01.17.
 */
public class EnemySpawnSystem extends EntitySystem {
    private TMSGame game;

    private double elapsedTime;
    private double baseSpawnFrequency=10;
    private double spawnFrequency;
    private double elapsedTimeSinceLastSpawn;
    private double spawnFrequencyGrowthRate;

    private float basePowerLevel=4;
    private float currentPowerLevel;
    private float powerLevelGrowthRate;

    private int previousSpawnLane=1;

    private Random random;

    public EnemySpawnSystem(TMSGame game,double spawnFrequencyGrowthRate, float powerLevelGrowthRate) {
        this.game=game;
        this.spawnFrequencyGrowthRate=spawnFrequencyGrowthRate;
        spawnFrequency=baseSpawnFrequency;
        random=new Random();
    }

    @Override
    public void update(float deltaTime){
        elapsedTime+=deltaTime;
        elapsedTimeSinceLastSpawn+=deltaTime;
        spawnFrequency+=deltaTime*spawnFrequencyGrowthRate;
        currentPowerLevel+=deltaTime*powerLevelGrowthRate;
        if(elapsedTimeSinceLastSpawn>spawnFrequency) {
            elapsedTimeSinceLastSpawn=0;
            int lane=0;
            while(lane==previousSpawnLane)
                lane=random.nextInt(3);
            previousSpawnLane=lane;
            spawnEnemy("skeleton_basic",currentPowerLevel,lane);
        }

    }
    private void spawnEnemy(String name,float powerLevel,int lane){
        Engine engine=getEngine();

        Bundle bundle=new Bundle();
        bundle.putItem("position",new Vector2(random.nextInt(20)+340,12+lane*24));
        bundle.putItem("stats",new Stats(10, generateRandomRawStats()));
        engine.addEntity(game.getTemplateManager().getTemplate(name).createEntity(engine,bundle));

    }
    private RawStats generateRandomRawStats(){
        return new RawStats(random.nextInt(5)+5,random.nextInt(5)+5,random.nextInt(5)+5,0.001f);
    }
}
