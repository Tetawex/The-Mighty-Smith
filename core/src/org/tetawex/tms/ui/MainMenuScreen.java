package org.tetawex.tms.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.tetawex.tms.core.GameStateManager;
import org.tetawex.tms.core.TMSGame;

/**
 * Created by Tetawex on 28.12.2016.
 */
public class MainMenuScreen implements Screen
{
    private Stage stage;
    private TMSGame game;
    public MainMenuScreen(TMSGame game)
    {
        this.game=game;
        stage=new Stage(new ExtendViewport(320,180,new OrthographicCamera(320f,180f)));
        Gdx.input.setInputProcessor(stage);
        initUi();
    }
    private void initUi()
    {
        Skin skin = new Skin();
        skin.addRegions(game.getAssetManager().get("atlas.atlas",TextureAtlas.class));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        NinePatch patch = skin.getPatch("ui_button_default");
        NinePatchDrawable ninePatch = new NinePatchDrawable(patch);
        textButtonStyle.font = game.getAssetManager().get("font.fnt",BitmapFont.class);
        textButtonStyle.font.getData().setScale(0.5f);
        textButtonStyle.up = new Image(ninePatch).getDrawable();
        patch = skin.getPatch("ui_button_pressed");
        ninePatch = new NinePatchDrawable(patch);
        textButtonStyle.down = new Image(ninePatch).getDrawable();

        Table mainTable=new Table();
        stage.addActor(mainTable);
        Table midColumnTable = new Table();


        TextButton playButton=new TextButton("play", textButtonStyle);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getGameStateManager().setState(GameStateManager.GameState.GAME);
            }
        });
        TextButton quitButton=new TextButton("quit", textButtonStyle);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.dispose();
                Gdx.app.exit();
            }
        });

        mainTable.setFillParent(true);
        mainTable.setFillParent(true);
        mainTable.add(midColumnTable).expandY().center();
        midColumnTable.row();
        midColumnTable.add(playButton).pad(4).prefSize(64,32);
        midColumnTable.row();
        midColumnTable.add(new TextButton("settings", textButtonStyle)).pad(4).prefSize(64,32);
        midColumnTable.row();
        midColumnTable.add(quitButton).pad(4).prefSize(64,32);
        midColumnTable.row().expandY();

        //mainTable.setDebug(true);
        //midColumnTable.setDebug(true);
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
