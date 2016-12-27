package engine.game;

import engine.Logger;
import engine.renderer.DisplayManager;
import engine.renderer.resources.Loader;
import engine.renderer.Renderer;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 *
 * Abstract class for creating StateBasedGames.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class StateBasedGame implements Runnable {
    /**
     * The List of GameStates for the StateBasedGame to use.
     */
    private final ArrayList<GameState> gameStates = new ArrayList<>();

    /**
     * The ID of the active GameState.
     */
    private int activeGameState = 0;

    /**
     * The default StateBasedGame constructor. The game begins on the fist GameState passed in.
     *
     * @param xRes The x resolution of the game.
     * @param yRes The y resolution of the game.
     * @param fullscreen True if the game is fullscreen.
     * @param name The name of the game.
     * @param gameStates The list of GameStates for the StateBasedGame to use.
     * @throws LWJGLException If there was an error creating the game Display.
     */
    protected StateBasedGame(int xRes, int yRes, boolean fullscreen, String name, GameState... gameStates)
            throws Exception {
        if (gameStates.length <= 0) {
            throw new RuntimeException("Cannot create StateBasedGame with 0 states.");
        }
        DisplayManager.createDisplay(xRes, yRes, fullscreen, name);
        init();
        for (int i = 0; i < gameStates.length; i++) {
            gameStates[i].container = this;
            gameStates[i].index = i;
            gameStates[i].init();
            this.gameStates.add(gameStates[i]);
        }
        Logger.log("Created game " + this);
    }

    /**
     * Runs the StateBasedGame until the display is requested to close.
     */
    public void run() {
        try {
            Logger.log("Running game " + this);
            while (!DisplayManager.isCloseRequested()) {
                update();
                beginRender();
                render();
                endRender();
            }
            cleanUp();
        } catch (Exception e) {
            Logger.log(e);
        }
        Logger.log("Finished game " + this);
    }

    /**
     * Method called on game update.
     */
    protected void update() throws Exception {
        gameStates.get(activeGameState).update();
    }

    /**
     * Prepares the renderer to render a frame.
     */
    protected void beginRender(){
        DisplayManager.updateDisplay();
        Renderer.beginRender();
    }

    /**
     * Method called on game render.
     */
    protected void render() throws Exception {
        gameStates.get(activeGameState).render();
    }

    /**
     * Ends the rendering of a frame.
     */
    protected void endRender(){
        Renderer.endRender();
    }

    /**
     * Method called on game creation.
     */
    protected void init() throws LWJGLException {
        Mouse.create();
        Keyboard.create();
    }

    /**
     * Method called on game end.
     */
    protected void cleanUp(){
        Renderer.cleanUp();
        Loader.cleanUp();
        Mouse.destroy();
        Keyboard.destroy();
        DisplayManager.closeDisplay();
    }

    /**
     * Sets the active GameState to the GameState with the given index.
     *
     * @param index The index of the GameState to activate.
     * @return The new active GameState.
     */
    public GameState setActiveGameState(int index){
        return gameStates.get(activeGameState = index);
    }
}
