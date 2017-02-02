package org.tetawex.tms.actors;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.misc.stats.RawStats;
import org.tetawex.tms.ecs.misc.stats.Stats;
import org.tetawex.tms.ecs.systems.*;
import org.tetawex.tms.ecs.templates.Bundle;

/**
 * Created by Tetawex on 26.01.2017.
 */
public class GameWorld extends Widget {
    private Engine engine;
    private TMSGame game;
    private RenderSystem renderSystem;
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
        engine.addSystem(new MessageSystem());
        engine.addSystem(new StatsSystem());
        engine.addSystem(new AnimationSystem());

        //TODO:remove testing code
        Bundle bundle=new Bundle();
        bundle.putItem("position",new Vector2(0,0));
        bundle.putItem("stats",new Stats(20,new RawStats()));
        engine.addEntity(game.getTemplateManager().getTemplate("skeleton_basic").createEntity(engine,bundle));

        bundle=new Bundle();
        bundle.putItem("position",new Vector2(-40,80));
        engine.addEntity(game.getTemplateManager().getTemplate("wall").createEntity(engine,bundle));
        engine.getEntities();
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
        renderSystem.setOrigin(new Vector2(getX(),getY()));
        renderSystem.setBatch(batch);
        engine.update(deltaTime);
    }

}
