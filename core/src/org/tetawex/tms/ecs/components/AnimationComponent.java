package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tetawex on 28.01.17.
 */
public class AnimationComponent implements Component {
    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    private float elapsedTime;
    private float interval;
    private float duration;

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    private boolean looping;

    public Animation<TextureRegion> getCurrentAnimation() {
        return currentAnimation;
    }
    public void rewind(){
        elapsedTime=0;
    }
    private Animation<TextureRegion> currentAnimation;
    private Map<String,Animation<TextureRegion>> animations;

    public AnimationComponent(boolean looping,float interval,String initAnimation) {
        this(new HashMap<String, Animation<TextureRegion>>(),looping, interval,initAnimation);
    }
    public AnimationComponent
            (HashMap<String, Animation<TextureRegion>> animations,
             boolean looping, float interval,String initAnimation) {
        this.animations=animations;
        this.interval=interval;
        setCurrentAnimation(initAnimation);
        setLooping(looping);
    }
    public void setCurrentAnimation(String name){
        currentAnimation=animations.get(name);
        Object[] f = currentAnimation.getKeyFrames();
        duration=interval*f.length;
        rewind();
    }
    public TextureRegion render(){
        currentAnimation.setFrameDuration(interval);
        TextureRegion frame=currentAnimation.getKeyFrame(elapsedTime,isLooping());
        return frame;
    }
    public void tick(float deltaTime) {
        if(elapsedTime<=duration)
            elapsedTime+=deltaTime;
        else
            elapsedTime=elapsedTime-duration+deltaTime;
    }
    public void addAnimation(String name, Animation<TextureRegion> animation){
        animations.put(name,animation);
    }

}
