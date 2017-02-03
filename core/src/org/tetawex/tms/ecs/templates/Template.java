package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import org.tetawex.tms.core.TMSGame;

/**
 * Created by tetawex on 28.01.17.
 */
public abstract class Template {
    public TMSGame getGame() {
        return game;
    }

    public void setGame(TMSGame game) {
        this.game = game;
    }

    private TMSGame game;
    public Template(TMSGame game){
        this.game=game;
    }
    public abstract Entity createEntity(Engine engine, org.tetawex.tms.util.Bundle bundle);
}
