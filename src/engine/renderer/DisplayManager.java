package engine.renderer;

import engine.renderer.resources.Loader;
import engine.renderer.resources.Textures;
import engine.utils.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

/**
 * Created by Aedan Smith.
 *
 * The display manager for the client.
 */

@SuppressWarnings("WeakerAccess")
public class DisplayManager {
    /**
     * The x and y resolution of the display.
     */
    public static int xRes, yRes;

    /**
     * The FPS to cap the display at.
     */
    public static int fpscap;

    /**
     * Creates the display.
     *
     * @param xRes       The x resolution of the display.
     * @param yRes       The y resolution of the display.
     * @param fullscreen True if the display should initialize input fullscreen.
     * @param title      The title of the display.
     * @throws LWJGLException If LWJGL could not initialize the display.
     */
    public static void createDisplay(int xRes, int yRes, boolean fullscreen, String title) throws LWJGLException {
        DisplayManager.xRes = xRes;
        DisplayManager.yRes = yRes;

        ContextAttribs attributes = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        Display.setDisplayMode(new DisplayMode(xRes, yRes));
        Logger.log("Width = " + xRes + ", Height = " + yRes);
        Logger.log("FPSCap = " + (fpscap = Display.getDesktopDisplayMode().getFrequency()));
        Display.setResizable(false);
        Display.setFullscreen(fullscreen);
        Logger.log("Fullscreen = " + fullscreen);
        Display.setTitle(title);
        Logger.log("Title = \"" + title + "\"");
        Display.setVSyncEnabled(true);
        Display.create(new PixelFormat(), attributes);
        Logger.log("Created display");
        Logger.log("OpenGL version " +  GL11.glGetString(GL11.GL_VERSION));
        GL11.glViewport(0, 0, xRes, yRes);

        Mouse.create();
        Keyboard.create();
    }

    /**
     * @return True if the user has requested to close the display.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    /**
     * Synchronizes and updates the display.
     */
    public static void updateDisplay() {
        Display.sync(fpscap);
        Display.update();
    }

    /**
     * Destroys the display.
     */
    public static void closeDisplay() {
        Renderer.cleanUp();
        Loader.cleanUp();
        Textures.cleanUp();
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
        Logger.log("Destroyed display");
    }
}