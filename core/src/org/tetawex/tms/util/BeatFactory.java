package org.tetawex.tms.util;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ui.actors.Beat;

/**
 * Created by Tetawex on 26.02.2017.
 */
public class BeatFactory {
    public static Beat generateBeat(TMSGame game){
        Sound anvilSound=game.getAssetManager().get("sounds/anvil.ogg",Sound.class);
        anvilSound.setVolume(0,.5f);

        return new Beat(anvilSound,
                game.getAnimationManager().getAnimation("beat_idle"),
                new Animation[]{game.getAnimationManager().getAnimation("beat_red_charge"),
                        game.getAnimationManager().getAnimation("beat_green_charge"),
                        game.getAnimationManager().getAnimation("beat_yellow_charge"),
                        game.getAnimationManager().getAnimation("beat_blue_charge")},
                new Animation[]{game.getAnimationManager().getAnimation("beat_red_click"),
                        game.getAnimationManager().getAnimation("beat_green_click"),
                        game.getAnimationManager().getAnimation("beat_yellow_click"),
                        game.getAnimationManager().getAnimation("beat_blue_click")},
                game.getAnimationManager().getAnimation("beat_fail"));
    }
}
