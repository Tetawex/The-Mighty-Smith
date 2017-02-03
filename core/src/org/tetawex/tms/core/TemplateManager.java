package org.tetawex.tms.core;

import org.tetawex.tms.ecs.templates.SkeletonTemplate;
import org.tetawex.tms.ecs.templates.Template;
import org.tetawex.tms.ecs.templates.BackgroundTemplate;
import org.tetawex.tms.ecs.templates.WeaponTemplate;

import java.util.HashMap;

/**
 * Created by tetawex on 01.02.17.
 */
public class TemplateManager {
    private TMSGame game;
    private HashMap<String,Template> templates;
    public TemplateManager(TMSGame game){
        this.game=game;
        templates=new HashMap<String, Template>();
        initTemplates();
    }
    public Template getTemplate(String name){
        return templates.get(name);
    }
    private void initTemplates(){
        templates.put("skeleton_basic",new SkeletonTemplate(game));
        templates.put("weapon_sword",new WeaponTemplate(game));
        templates.put("wall",new BackgroundTemplate(game));
    }
}
