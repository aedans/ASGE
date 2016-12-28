import engine.entities.Entity;
import engine.game.GameState;
import engine.game.StateBasedGame;
import engine.renderer.Renderer;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import engine.utils.Updateable;
import org.lwjgl.util.Renderable;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        new StateBasedGame(
                1080, 720, false, "Test Display",
                new GameState(){
                    TestObject testObject;

                    @Override
                    public void init() throws Exception {
                        testObject = new TestObject();
                    }

                    @Override
                    public void update() throws Exception {
                        testObject.update();
                    }

                    @Override
                    public void render() throws Exception {
                        testObject.render();
                    }
                }
        ).run();
    }

    private static class TestObject implements Renderable, Updateable {
        private Sprite sprite;

        TestObject() throws Exception {
            this.sprite = new Sprite(
                    0, 0,
                    TexturedModel.getTexturedModel(
                            1f,
                            1f,
                            Textures.loadTexture(
                                    "test",
                                    ImageIO.read(new File("assets/imgs/test.png"))
                            )
                    )
            );
        }

        @Override
        public void update() {
            Renderer.bColorR += 0.005f;
            Renderer.bColorG += 0.005f;
            Renderer.bColorB += 0.005f;
        }

        @Override
        public void render() {
            sprite.render();
        }
    }
}
