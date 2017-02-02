package org.tetawex.tms.core;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import org.tetawex.tms.ui.GameScreen;
import org.tetawex.tms.ui.MainMenuScreen;


/**
 * Created by Tetawex on 30.12.2016.
 */
public class GameStateManager
{
    public enum GameState{MAIN_MENU,GAME,SETTINGS,GAME_OVER}

    private Screen currentScreen;
    private GameState currentState;
    private TMSGame game;

    public GameStateManager(TMSGame game)
    {
        this.game=game;
        setState(GameState.MAIN_MENU);
    }
    public GameStateManager(TMSGame game, GameState state)
    {
        this(game);
        setState(state);
    }

    public Screen getCurrentScreen()
    {
        return currentScreen;
    }

    public void setState(GameState gameState)
    {

        currentState=gameState;
        if(currentScreen!=null)
            currentScreen.dispose();

        switch (currentState)
        {
            case MAIN_MENU:
                currentScreen=new MainMenuScreen(game);
                break;
            case GAME:
                currentScreen=new GameScreen(game);
                break;
            case GAME_OVER:
                currentScreen=new ScreenAdapter();
                break;
            case SETTINGS:
                currentScreen=new ScreenAdapter();
                break;
        }
    }
}
