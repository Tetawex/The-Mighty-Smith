package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.misc.renderers.TileMapRenderer;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by tetawex on 01.02.17.
 */
public class WallTemplate extends Template {
    private TextureRegion[] textureRegions;
    private Random random;
    public WallTemplate(TMSGame game) {
        super(game);
        //int i=game.getAssetManager()
          //      .get("atlas.atlas", TextureAtlas.class)
            //    .findRegions("tile_bricks").toArray(TextureRegion.class).length;
        com.badlogic.gdx.utils.Array<TextureAtlas.AtlasRegion> a=game.getAssetManager()
                .get("atlas.atlas", TextureAtlas.class)
                .findRegions("tile_bricks");
        textureRegions=new TextureRegion[a.size];
        for (int i = 0; i < a.size; i++) {
            textureRegions[i]=a.get(i);
        }
        random=new Random();
    }

    @Override
    public Entity createEntity(Engine engine, Bundle bundle) {
        Entity entity=new Entity();

        entity.add(new TransformComponent(
                bundle.getItem("position", Vector2.class),
                new Vector2(400,400)));

        int[][] tileMap=new int[50][50];
        for (int i=0;i<tileMap.length;i++){
            for (int j=0;j<tileMap[i].length;j++){
                tileMap[i][j]=random.nextInt(25);
                if(tileMap[i][j]>=textureRegions.length)
                    tileMap[i][j]=0;
            }
        }

        entity.add(new RenderComponent(Vector2.Zero,-1f,
                new TileMapRenderer(new Vector2(24,24),tileMap,textureRegions)));

        return entity;
    }
}
