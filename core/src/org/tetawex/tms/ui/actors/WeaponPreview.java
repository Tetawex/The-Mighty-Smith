package org.tetawex.tms.ui.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/**
 * Created by tetawex on 21.02.17.
 */
public class WeaponPreview extends Widget {
    private TextureRegion frameRegion;
    private BitmapFont font;
    public WeaponPreview(TextureRegion frameRegion, BitmapFont font){
        this.frameRegion=frameRegion;
        this.font=font;


    }

}
