package org.tetawex.tms.util;

import com.badlogic.ashley.core.ComponentMapper;
import org.tetawex.tms.ecs.components.*;

/**
 * Created by tetawex on 30.01.17.
 */
public class Mappers {
    public static ComponentMapper<AIComponent> ai = ComponentMapper.getFor(AIComponent.class);
    public static ComponentMapper<RenderComponent> render = ComponentMapper.getFor(RenderComponent.class);
    public static ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static ComponentMapper<StatsComponent> stats= ComponentMapper.getFor(StatsComponent.class);
    public static ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static ComponentMapper<MessageComponent> message = ComponentMapper.getFor(MessageComponent.class);
}
