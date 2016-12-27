import engine.renderer.DisplayManager;
import engine.renderer.Renderer;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        DisplayManager.createDisplay(1080, 720, false, "Test Display");
        while (!DisplayManager.isCloseRequested()){
            Renderer.beginRender();
            Renderer.bColorG+=.002f;
            Renderer.endRender();
            DisplayManager.updateDisplay();
        }
        DisplayManager.closeDisplay();
    }
}
