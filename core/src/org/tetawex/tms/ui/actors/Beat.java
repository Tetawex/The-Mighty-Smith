package org.tetawex.tms.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import org.tetawex.tms.util.StatToIntConverter;
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
    private int statNum;

    private Queue<BeatListener> listeners;

    private float beatInterval;
    private float elapsedTime=0.1f;

    public boolean isCharging() {
        return charging;
    }

    private boolean charging;

    private Sound clickSound;

    private Animation<TextureRegion> currentAnimation;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion>[] chargeAnimations;
    private Animation<TextureRegion>[] clickAnimations;
    private Animation<TextureRegion> failAnimation;

    public Beat(Sound clickSound, Animation<TextureRegion> idleAnimation,
                Animation<TextureRegion>[] chargeAnimations,
                Animation<TextureRegion>[] clickAnimations,
                Animation<TextureRegion> failAnimation){
        listeners=new LinkedList<BeatListener>();

        this.clickSound=clickSound;

        this.idleAnimation=idleAnimation;
        this.chargeAnimations=chargeAnimations;
        this.clickAnimations=clickAnimations;
        this.failAnimation=failAnimation;
        for (int i = 0; i < this.clickAnimations.length; i++) {
            this.clickAnimations[i].setFrameDuration(0.05f);
            this.clickAnimations[i].setPlayMode(Animation.PlayMode.NORMAL);
            this.chargeAnimations[i].setPlayMode(Animation.PlayMode.NORMAL);
        }
        idleAnimation.setFrameDuration(10f);
        failAnimation.setFrameDuration(3f);
        idleAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        failAnimation.setPlayMode(Animation.PlayMode.NORMAL);

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
    public void respondOnClick(boolean success){
        if(success)
            currentAnimation=clickAnimations[statNum];
        else
            currentAnimation=failAnimation;
    }
    public void click(){

        //clickSound.play();

        notifyListenersSuccess();
    }
    public void charge(float beatInterval, int statNum){
        this.charging=true;
        elapsedTime=0;
        this.beatInterval=beatInterval;
        this.statNum=statNum;
        currentAnimation=chargeAnimations[statNum];
        //Object[] anim=chargeAnimation[statNum].getKeyFrames();
        chargeAnimations[statNum].setFrameDuration( beatInterval/13f);
    }
    public void discharge(){
        elapsedTime=0f;
        currentAnimation=idleAnimation;
        charging=false;
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
        return 80;
    }
    @Override
    public float getPrefHeight() {
        return 50;
    }
    public void addBeatListener(BeatListener beatListener){
        listeners.add(beatListener);
    }

    public int getStatNum() {
        return statNum;
    }

    private void notifyListenersSuccess(){
        for (BeatListener bl:listeners) {
            bl.onBeatHit(statType);
        }
    }
}
