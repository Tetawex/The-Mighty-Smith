package org.tetawex.tms.ecs.misc.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tetawex on 30.01.17.
 */
public class TileMapRenderer implements Renderer {
    private Vector2 tileDimension;
    private int[][] tileMap;
    private TextureRegion[] mapRegions;
    public TileMapRenderer(Vector2 tileDimension, int[][] tileMap,TextureRegion[] mapRegions){
        this.tileMap=tileMap;
        this.tileDimension=tileDimension;
        this.mapRegions=mapRegions;
    }
    @Override
    public void render(Batch batch, Vector2 origin, Vector2 position, Vector2 dimension) {
        for (int i=0;i<dimension.x/tileDimension.x;i++)
            for (int j=0;j<dimension.y/tileDimension.y;j++) {
                batch.draw(mapRegions[tileMap[i][j]],position.x+i*tileDimension.x+origin.x,
                        position.y+j*tileDimension.y+origin.y,tileDimension.x,tileDimension.y);
            }
    }

    @Override
    public void setTextureRegion(TextureRegion region) {

    }
}
