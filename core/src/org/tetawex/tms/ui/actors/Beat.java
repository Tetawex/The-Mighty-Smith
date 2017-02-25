package org.tetawex.tms.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import org.tetawex.tms.util.StatType;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tetawex on 13.02.17.
 */
public class Beat extends Widget {
    public interface BeatListener{
        void onBeatHit(StatType statType);
    }
    private StatType statType;

    private Queue<BeatListener> listeners;

    private float beatInterval;
    private float elapsedTime=0.1f;

    private boolean charging;

    private Animation<TextureRegion> currentAnimation;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> chargeAnimation;
    private Animation<TextureRegion> clickAnimation;

    public Beat(Animation<TextureRegion> idleAnimation,
                Animation<TextureRegion> chargeAnimation,
                Animation<TextureRegion> clickAnimation){
        listeners=new LinkedList<BeatListener>();

        this.idleAnimation=idleAnimation;
        this.chargeAnimation=chargeAnimation;
        this.clickAnimation=clickAnimation;

        currentAnimation=idleAnimation;
        //great costil
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                click();
                return true;
            }
        });
    }
    public void click(){
        notifyListenersSuccess();
        currentAnimation=clickAnimation;
    }
    public void charge(float beatInterval, StatType statType){
        this.charging=true;
        elapsedTime=0;
        this.beatInterval=beatInterval;
        this.statType=statType;
        currentAnimation=chargeAnimation;
        chargeAnimation.setPlayMode(Animation.PlayMode.LOOP);
        Object[] cock=chargeAnimation.getKeyFrames();
        chargeAnimation.setFrameDuration(0.6f*beatInterval/cock.length);
    }
    public void discharge(){
        elapsedTime=0f;
        charging=false;
        currentAnimation=idleAnimation;
    }
    @Override
    public void act(float deltaTime){
        elapsedTime+=deltaTime;
    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(currentAnimation.getKeyFrame(elapsedTime),getX(),getY());
    }
    public float getPrefWidth() {
        return 48;
    }
    @Override
    public float getPrefHeight() {
        return 32;
    }
    public void addBeatListener(BeatListener beatListener){
        listeners.add(beatListener);
    }

    private void notifyListenersSuccess(){
        for (BeatListener bl:listeners) {
            bl.onBeatHit(statType);
        }
    }
}
