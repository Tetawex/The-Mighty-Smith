package org.tetawex.tms.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.tetawex.tms.actors.AnvilWithSword;
import org.tetawex.tms.actors.GameWorld;
import org.tetawex.tms.actors.SkeletonActorDemo;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.ecs.misc.stats.Stats;

import java.util.Random;

/**
 * Created by Tetawex on 08.01.2017.
 */
public class GameScreen implements Screen
{
    private Stage stage;
    private TMSGame game;

    private Group gameGroup;
    private Group uiGroup;

    private GameWorld gameWorldActor;

    private Group pauseGroup;

    private Actor anvilWithSword;

    private boolean gamePaused;

    public GameScreen(TMSGame game)
    {
        this.game=game;

        Camera camera=new OrthographicCamera(320f,180f);
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0f);

        stage=new Stage(new ExtendViewport(320,180,camera));
        Gdx.input.setInputProcessor(stage);

        gameGroup=new Group();
        stage.addActor(gameGroup);

        pauseGroup=new Group();
        stage.addActor(pauseGroup);

        uiGroup=new Group();
        gameGroup.addActor(uiGroup);

        initUi();
    }
    private void initUi()
    {
        Skin skin = new Skin();
        skin.addRegions(game.getAssetManager().get("atlas.atlas",TextureAtlas.class));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getAssetManager().get("font.fnt",BitmapFont.class);
        textButtonStyle.up = skin.getDrawable("ui_button_pause_default");
        textButtonStyle.down = skin.getDrawable("ui_button_pause_pressed");



        Table mainTable=new Table();
        stage.addActor(mainTable);
        Table topRowTable = new Table();
        Table midRowTable = new Table();
        Table bottomRowTable = new Table();


        mainTable.setFillParent(true);
        mainTable.add(topRowTable).expandX().prefHeight(41f).left().row();
        mainTable.add(midRowTable).expandX().expandY().row();
        mainTable.add(bottomRowTable).expandX().prefHeight(56f);

        Table topRowLeftTable=new Table();
        Table topRowRightTable=new Table();

        topRowLeftTable.left().top();
        topRowRightTable.right().top();
        TextButton pauseButton = new TextButton("",textButtonStyle);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gamePaused=true;
            }
        });
        topRowLeftTable.add(pauseButton).size(16,16);

        topRowTable.add(topRowRightTable).expandY();
        topRowTable.add(topRowLeftTable).top();
        topRowTable.top();

        NinePatch patch = skin.getPatch("ui_anvil");
        NinePatchDrawable ninePatchAnvil = new NinePatchDrawable(patch);
        patch = skin.getPatch("ui_sword");
        NinePatchDrawable ninePatchSword = new NinePatchDrawable(patch);

        anvilWithSword=new AnvilWithSword(ninePatchAnvil,ninePatchSword);

        bottomRowTable.add(anvilWithSword);
        gameWorldActor=new GameWorld(game);
        midRowTable.add(gameWorldActor).expand();
        midRowTable.toBack();

        mainTable.setDebug(true);
        topRowTable.setDebug(true);
        topRowLeftTable.setDebug(true);
        topRowRightTable.setDebug(true);
        midRowTable.setDebug(true);
        bottomRowTable.setDebug(true);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
        stage.getViewport().getCamera().update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
