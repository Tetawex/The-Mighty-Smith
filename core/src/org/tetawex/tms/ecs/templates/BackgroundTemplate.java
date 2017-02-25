package org.tetawex.tms.ecs.templates;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.components.RenderComponent;
import org.tetawex.tms.ecs.components.TransformComponent;
import org.tetawex.tms.ecs.misc.renderers.TileMapRenderer;

import java.util.Random;

/**
 * Created by tetawex on 01.02.17.
 */
public class BackgroundTemplate extends Template {
    private TextureRegion[] textureRegions;
    private int lastBrickNum;
    private int lastRockNum;
    private int lastPadNum;
    private Random random;
    public BackgroundTemplate(TMSGame game) {
        super(game);
        Array<TextureAtlas.AtlasRegion> a2=game.getAssetManager()
                .get("atlas.atlas", TextureAtlas.class)
                .findRegions("tile_bricks");
        Array<TextureAtlas.AtlasRegion> a1=game.getAssetManager()
                .get("atlas.atlas", TextureAtlas.class)
                .findRegions("tile_rocks");
        Array<TextureAtlas.AtlasRegion> a3=game.getAssetManager()
                .get("atlas.atlas", TextureAtlas.class)
                .findRegions("tile_pads");
        textureRegions=new TextureRegion[a1.size+a2.size+a3.size];
        lastRockNum=a1.size;
        lastBrickNum=a2.size+a1.size;
        lastPadNum=a2.size+a1.size+a3.size;
        for (int i = 0; i < a1.size; i++) {
            textureRegions[i]=a1.get(i);
        }
        for (int i = lastRockNum; i < lastBrickNum; i++) {
            textureRegions[i]=a2.get(i-lastRockNum);
        }
        for (int i = lastBrickNum; i < lastPadNum; i++) {
            textureRegions[i]=a3.get(i-lastBrickNum);
        }
        random=new Random();
    }

    @Override
    public Entity createEntity(Engine engine, org.tetawex.tms.util.Bundle bundle) {
        Entity entity=new Entity();

        entity.add(new TransformComponent(
                bundle.getItem("position", Vector2.class),
                new Vector2(15*24,12*24)));

        int[][] tileMap=new int[15][12];


        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 7; j++) {
                tileMap[i][j]=tileMap[i][j]=random.nextInt(lastRockNum);
            }
        }
        for (int i = 0; i < 15; i++){
            for (int j = 7; j < tileMap[i].length; j++) {
                tileMap[i][j]=tileMap[i][j]=
                        random.nextInt(lastBrickNum-lastRockNum)+lastRockNum;
            }
        }
        for (int i = 4; i < 12; i++){
            for (int j = 4; j < 7; j++) {
                tileMap[i][j]=tileMap[i][j]=
                        random.nextInt(lastPadNum-lastBrickNum)+lastBrickNum;
            }
        }

        entity.add(new RenderComponent(Vector2.Zero,-1f,
                new TileMapRenderer(new Vector2(24,24),tileMap,textureRegions)));

        return entity;
    }
}
