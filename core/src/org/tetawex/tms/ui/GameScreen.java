package org.tetawex.tms.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import org.tetawex.tms.ui.actors.AnvilWithSword;
import org.tetawex.tms.ui.actors.Beat;
import org.tetawex.tms.ui.actors.BeatTracker;
import org.tetawex.tms.ui.actors.GameWorld;
import org.tetawex.tms.core.TMSGame;
import org.tetawex.tms.util.BeatFactory;

/**
 * Created by Tetawex on 08.01.2017.
 */
public class GameScreen implements Screen
{
    private Stage stage;
    private Stage pauseStage;
    private TMSGame game;

    private Group gameGroup;
    private Group fileDialog;
    private Group uiGroup;

    private GameWorld gameWorldActor;

    private Group pauseGroup;

    private Actor anvilWithSword;

    private boolean gamePaused;

    private boolean choosingFile;

    private String pathToMusic;

    private BeatTracker tracker;

    //final FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);

    public GameScreen(TMSGame game)
    {
        this.game=game;

        //fileDialog=new Group();

        Camera camera=new OrthographicCamera(320f,180f);
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0f);

        stage=new Stage(new ExtendViewport(320,180,camera));
        pauseStage=new Stage(new ExtendViewport(320,180,camera));
        Gdx.input.setInputProcessor(stage);

        gameGroup=new Group();
        stage.addActor(gameGroup);

        pauseGroup=new Group();
        pauseStage.addActor(pauseGroup);

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
        mainTable.add(bottomRowTable).expandX().prefHeight(50f);

        Table topRowLeftTable=new Table();
        Table topRowRightTable=new Table();

        topRowLeftTable.left().top();
        topRowRightTable.right().top();
        TextButton pauseButton = new TextButton("",textButtonStyle);
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setGamePaused(!gamePaused);
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

        Beat[] beats=new Beat[4];
        for (int i = 0; i <beats.length ; i++) {
            beats[i]= BeatFactory.generateBeat(game);
        }
        //Refactor ASAP!!!
        Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/game_main.ogg"));
        menuMusic.setLooping(true);
        menuMusic.play();
        gameGroup.addActor(new BeatTracker(beats,100));
        anvilWithSword=new AnvilWithSword(ninePatchAnvil,ninePatchSword);
        Stack bottomStack=new Stack();
        Table beatsTable=new Table();
        for (int i = 0; i < beats.length; i++) {
            beatsTable.add(beats[i]).expand();
        }

        bottomStack.addActor(anvilWithSword);
        bottomStack.addActor(beatsTable);

        bottomRowTable.add(bottomStack).expand();
        gameWorldActor=new GameWorld(game);
        midRowTable.add(gameWorldActor).expand();
        midRowTable.toBack();

        pauseGroup.addActor(new TextButton("",textButtonStyle));

        //debug
        /*mainTable.setDebug(true);
        topRowTable.setDebug(true);
        topRowLeftTable.setDebug(true);
        topRowRightTable.setDebug(true);
        midRowTable.setDebug(true);
        bottomRowTable.setDebug(true);*/
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }
    public void setChoosingFile(boolean choosingFile) {
        this.choosingFile = choosingFile;
        if(choosingFile)
            Gdx.input.setInputProcessor(pauseStage);
        else
            Gdx.input.setInputProcessor(stage);
    }
    /*public void loadMusic(){

        setChoosingFile(true);

        fileChooser.setSelectionMode(FileChooser.SelectionMode.DIRECTORIES);
        fileChooser.setListener(new FileChooserAdapter() {
            @Override
            public void selected (Array<FileHandle> file) {
                pathToMusic=(file.toArray())[0].path();
                setChoosingFile(false);
            }
        });
    }*/

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        /*if(!choosingFile) {
            stage.act(delta);
            stage.draw();
        }
        else{
            pauseStage.act(delta);
            pauseStage.draw();
        }*/
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
        stage.getViewport().getCamera().update();
        pauseStage.getViewport().update(width,height,true);
        pauseStage.getViewport().getCamera().update();

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
