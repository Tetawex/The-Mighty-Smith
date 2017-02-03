package org.tetawex.tms.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tetawex on 27.01.17.
 */
public class TransformComponent implements Component {

    private Vector2 position;
    private Vector2 dimension;

    public TransformComponent()
    {
        this(Vector2.Zero,Vector2.Zero);
    }
    public TransformComponent(Vector2 position,Vector2 dimension)
    {
        setPosition(position);
        setDimension(dimension);
    }
    public TransformComponent(Vector2 position)
    {
        setPosition(position);
        setDimension(Vector2.Zero);
    }
    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getCenterPosition() {
        return new Vector2(position).mulAdd(dimension,0.5f);
    }
    public void setCenterPosition(Vector2 centerPosition) {
        setPosition(new Vector2(centerPosition).mulAdd(dimension,-0.5f));
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public void setDimension(Vector2 dimension) {
        this.dimension = dimension;
    }
}
