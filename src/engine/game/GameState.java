package engine.game;

/**
 * Created by Aedan Smith.
 *
 * Abstract class to be used in a StateBasedGame.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class GameState {
    /**
     * The StateBasedGame containing this GameState.
     */
    public StateBasedGame container = null;

    /**
     * The index of the GameState input the container.
     */
    public int index = -1;

    /**
     * The initialization function for the GameState.
     *
     * All initialization functionality should be input the init function, not the constructor. The init function is called
     * once when the StateBasedGame loads the GameState, not when the object is constructed, and should not be called by
     * the GameState constructor implementation.
     */
    @SuppressWarnings("RedundantThrows")
    public abstract void init() throws Exception;

    /**
     * The update function for the GameState.
     *
     * This function is called once each frame that the GameState is active. If another GameState is active, this
     * function is ignored.
     */
    @SuppressWarnings("RedundantThrows")
    public abstract void update() throws Exception;

    /**
     * The render function for the GameState.
     *
     * This function is called once each frame that the GameState is active. If another GameState is active, this
     * function is ignored.
     *
     * This function is called between the StateBasedGame beginRender and endRender functions. This function should
     * not modify the renderer's state input any way, or unexpected errors will occur.
     */
    @SuppressWarnings("RedundantThrows")
    public abstract void render() throws Exception;
}
