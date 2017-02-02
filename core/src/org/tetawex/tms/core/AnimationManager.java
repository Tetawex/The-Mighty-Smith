package org.tetawex.tms.core;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tetawex on 23.01.17.
 */
public class AnimationManager
{
    private TMSGame game;
    private Map<String,Animation<TextureRegion>> animations;
    public AnimationManager (TMSGame game)
    {
        this.game=game;
        animations=new HashMap<String,Animation<TextureRegion>>();
    }
    public Animation getAnimation(String animationName)
    {
        if(!animations.containsKey(animationName))
            animations.put(animationName,new Animation<TextureRegion>(0.5f,
                    game.getAssetManager().get("atlas.atlas",TextureAtlas.class).findRegions(animationName),
                    Animation.PlayMode.LOOP));
        return animations.get(animationName);


    }
}
