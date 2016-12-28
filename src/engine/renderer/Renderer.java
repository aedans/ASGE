package engine.renderer;

import engine.utils.Logger;
import engine.renderer.resources.TexturedModel;
import engine.renderer.shaders.CompositeShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Created by Aedan Smith.
 *
 * The main renderer class.
 */

@SuppressWarnings("WeakerAccess")
public class Renderer {
    /**
     * The Composite Shader for the renderer to use.
     */
    public static final CompositeShader compositeShader = new CompositeShader();

    /**
     * The RGB values for the OpenGL background color.
     */
    public static float bColorR = 0.0f, bColorG = 0.0f, bColorB = 0.0f;

    /**
     * The current Model and Texture bound to the renderer.
     */
    private static int currentModelID, currentTextureID;

    static {
        // Enables Transparency.
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
    }

    /**
     * Prepares the renderer to render a frame.
     */
    public static void beginRender() {
        // Clears the Display.
        GL11.glClearColor(bColorR, bColorG, bColorB, 1);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClear(GL11.GL_ACCUM_BUFFER_BIT);

        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        compositeShader.start();
    }

    /**
     * Ends the rendering of a frame.
     */
    public static void endRender(){
        compositeShader.stop();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
    }

    /**
     * Binds a TexturedModel into OpenGL's vertex and texture buffers.
     *
     * @param texturedModel The TexturedModel to load.
     */
    public static void bindTexturedModel(TexturedModel texturedModel){
        // If the model is not already bound
        if (texturedModel.getModelID() != currentModelID) {
            GL30.glBindVertexArray(texturedModel.getModelID());
            currentModelID = texturedModel.getModelID();
        }
        // If the texture is not already bound
        if (texturedModel.getTextureID() != currentTextureID) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTextureID());
            currentTextureID = texturedModel.getTextureID();
        }
    }

    /**
     * Draws the currently bound elements.
     */
    public static void drawElements() {
        GL11.glDrawElements(GL11.GL_TRIANGLES, 8, GL11.GL_UNSIGNED_INT, 0);
    }

    /**
     * Cleans up the Renderer.
     */
    public static void cleanUp(){
        compositeShader.cleanUp();
        Logger.log("Cleaned up renderer.");
    }
}
