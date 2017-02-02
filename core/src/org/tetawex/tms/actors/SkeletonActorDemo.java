package org.tetawex.tms.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.tetawex.tms.ecs.misc.stats.Stats;


/**
 * Created by Tetawex on 21.01.2017.
 */
public class SkeletonActorDemo extends Actor {
    public enum SkeletonType{SKELETON_BASIC,SKELETON_LIGHT,SKELETON_HEAVY}

    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion>[] attackAnimations;
    private Animation<TextureRegion> currentAnimation;

    private float elapsedTime;
    private float speed;
    private float mSpeed;
    private Stats stats;

    public SkeletonActorDemo(Vector2 position, Stats stats, float delay, float speed, Animation walkAnimation, Animation... attackAnimations)
    {
        setX(position.x);
        setY(position.y);
        elapsedTime=delay;
        this.walkAnimation=walkAnimation;
        this.walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
        this.attackAnimations=attackAnimations;
        currentAnimation=walkAnimation;
        this.speed=0.15f;
        this.mSpeed=0.6f;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(currentAnimation.isAnimationFinished(elapsedTime))
        {
            //elapsedTime=0;
            currentAnimation=walkAnimation;
        }
        currentAnimation.setFrameDuration(speed);
        batch.draw(currentAnimation.getKeyFrame(elapsedTime,true),getX(),getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        setX(getX()-mSpeed);
        elapsedTime+=delta;
    }

}
