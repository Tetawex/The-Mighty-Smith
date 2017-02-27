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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.AllyTagComponent;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.misc.stats.RawStats;
import org.tetawex.tms.ecs.misc.stats.Stats;
import org.tetawex.tms.ecs.misc.stats.WeaponStats;
import org.tetawex.tms.ecs.systems.*;
import org.tetawex.tms.util.Bundle;
import org.tetawex.tms.util.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tetawex on 26.01.2017.
 */
public class GameWorld extends Widget {
    public interface WeaponPlacedListener{
        void weaponPlaced();
    }
    private List<WeaponPlacedListener> weaponPlacedListeners=new ArrayList<WeaponPlacedListener>();

    private Engine engine;
    private TMSGame game;
    private RenderSystem renderSystem;

    private Stats queuedWeapon;

    //for testing purposes
    //BitmapFont font = new BitmapFont();
    FPSLogger fpsLogger=new FPSLogger();

    public GameWorld(TMSGame game){
        this.game=game;
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                placeWeapon(new Vector2(x,y));
                return true;
            }
        });
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
        engine.addSystem(new EnemySpawnSystem(game,0.01,0.01f));

        //TODO:remove testing code

        Random r = new Random();

        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j <20; j++) {
                Bundle bundle=new Bundle();
                bundle.putItem("position",new Vector2(300+j*8,12+i*24));
                bundle.putItem("stats",new Stats(10,new RawStats(1+r.nextInt(2),
                        3+r.nextInt(2),1+r.nextInt(2),0.001f)));
                engine.addEntity(game.getTemplateManager().getTemplate("skeleton_basic").createEntity(engine,bundle));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3; j++) {
                Bundle bundle=new Bundle();
                bundle.putItem("position",new Vector2(68+j*24,12+i*24));
                bundle.putItem("stats",new Stats(320,new RawStats(1,10,1 ,10)));
                engine.addEntity(game.getTemplateManager().getTemplate("weapon_sword").createEntity(engine,bundle));
            }
        }*/
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
        //fpsLogger.log();
    }
    @Override
    public void act(float deltaTime){
    }
    public void onWeaponForged(RawStats rawStats, float powerLevel){
        queuedWeapon=new Stats(powerLevel,rawStats);
        Gdx.app.log("strength",""+rawStats.getStrength());
    }
    private void placeWeapon(Vector2 position){
        if(queuedWeapon!=null&&position.x<264&&position.x>72&&position.y>0&&position.y<72){
            Bundle bundle=new Bundle();

            Vector2 roundedPosition=new Vector2(
                    (-(int)position.x%24+position.x-4),
                    (-(int)position.y%24+position.y+12));
            if(checkIfCellOccupied(roundedPosition))
                return;

            bundle.putItem("position", roundedPosition);
            bundle.putItem("stats",queuedWeapon);
            engine.addEntity(game.getTemplateManager()
                    .getTemplate("weapon_sword")
                    .createEntity(engine,bundle));

            notifyListenersWeaponPlaced();
            queuedWeapon=null;
        }
    }
    private boolean checkIfCellOccupied(Vector2 position){
        ImmutableArray<Entity> entities=engine
                .getEntitiesFor(Family.all(AllyTagComponent.class).get());
        for (Entity e:entities) {
            TransformComponent tc=Mappers.transform.get(e);
            if(tc.getPosition().epsilonEquals(position,1f))
            return true;
        }
        return false;
    }
    private void notifyListenersWeaponPlaced(){
        for (WeaponPlacedListener wl:weaponPlacedListeners) {
            wl.weaponPlaced();
        }
    }
    public void addWeaponPlacedListener(WeaponPlacedListener wl){
        weaponPlacedListeners.add(wl);
    }
}
