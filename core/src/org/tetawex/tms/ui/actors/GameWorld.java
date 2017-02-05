package org.tetawex.tms.ui.actors;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.misc.stats.RawStats;
import org.tetawex.tms.ecs.misc.stats.Stats;
import org.tetawex.tms.ecs.systems.*;
import org.tetawex.tms.util.Bundle;
import org.tetawex.tms.util.Mappers;

import java.util.Random;

/**
 * Created by Tetawex on 26.01.2017.
 */
public class GameWorld extends Widget {
    private Engine engine;
    private TMSGame game;
    private RenderSystem renderSystem;

    //for testing purposes
    BitmapFont font = new BitmapFont();
    FPSLogger fpsLogger=new FPSLogger();

    public GameWorld(TMSGame game){
        this.game=game;
        initEngine();
    }
    private void initEngine() {
        engine=new Engine();
        engine.addSystem(new MovementSystem());
        renderSystem=new RenderSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(new AISystem());
        engine.addSystem(new MessageSystem(game));
        engine.addSystem(new StatsSystem(game));
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new DelayedRemovalSystem());

        //TODO:remove testing code

        Random r = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <20; j++) {
                Bundle bundle=new Bundle();
                bundle.putItem("position",new Vector2(300+j*8,12+i*24));
                bundle.putItem("stats",new Stats(10,new RawStats(1+r.nextInt(2),
                        3+r.nextInt(2),1+r.nextInt(2),0.001f)));
                engine.addEntity(game.getTemplateManager().getTemplate("skeleton_basic").createEntity(engine,bundle));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <4; j++) {
                Bundle bundle=new Bundle();
                bundle.putItem("position",new Vector2(68+j*24,12+i*24));
                bundle.putItem("stats",new Stats(300,new RawStats(1,10,1 ,10)));
                engine.addEntity(game.getTemplateManager().getTemplate("weapon_sword").createEntity(engine,bundle));
            }
        }
        Bundle bundle=new Bundle();
        bundle.putItem("position",new Vector2(-24,-96));
        engine.addEntity(game.getTemplateManager().getTemplate("wall").createEntity(engine,bundle));
    }
    @Override
    public float getPrefWidth() {
        return 320;
    }
    @Override
    public float getPrefHeight() {
        return 106;
    }
    @Override
    public void draw(Batch batch,float deltaTime) {
        renderSystem.setBatch(batch);
        renderSystem.setOrigin(new Vector2(getX(),getY()));
        engine.update(Gdx.graphics.getDeltaTime());
        fpsLogger.log();
    }
    @Override
    public void act(float deltaTime){
    }

}
