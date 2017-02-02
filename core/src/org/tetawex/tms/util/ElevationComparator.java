package org.tetawex.tms.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;

import java.util.Comparator;

/**
 * Created by tetawex on 27.01.17.
 */
public class ElevationComparator implements Comparator<Entity> {
    private ComponentMapper<RenderComponent> rm;
    private ComponentMapper<TransformComponent> tm;
    public ElevationComparator(ComponentMapper<RenderComponent> rm,ComponentMapper<TransformComponent> tm) {
        this.rm=rm;
        this.tm=tm;
    }
    @Override
    public int compare(Entity o1, Entity o2) {
        float e1=rm.get(o1).getElevation();
        float e2=rm.get(o2).getElevation();
        if(e1>e2)
            return 1;
        if(e1<e2)
            return -1;

        float y1=tm.get(o1).getPosition().y;
        float y2=tm.get(o2).getPosition().y;
        if(y1>y2)
            return 1;
        if(y1<y2)
            return -1;
        return 0;
    }
}
