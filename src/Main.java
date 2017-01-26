import engine.game.GameState;
import engine.game.StateBasedGame;
import engine.renderer.Renderer;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
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
                    TestObject testObject1;
                    TestObject testObject2;

                    @Override
                    public void init() throws Exception {
                        Textures.loadTexture(
                                "test",
                                ImageIO.read(new File("assets/imgs/test.png"))
                        );
                        testObject1 = new TestObject(.3f, .3f);
                        testObject2 = new TestObject(.1f, .1f);
                    }

                    @Override
                    public void update() throws Exception {
                        Renderer.bColorR += 0.005f;
                        Renderer.bColorG += 0.005f;
                        Renderer.bColorB += 0.005f;
                    }

                    @Override
                    public void render() throws Exception {
                        testObject1.render();
                        testObject2.render();
                    }
                }
        ).run();
    }

    private static class TestObject implements Renderable {
        private Sprite sprite;

        TestObject(float w, float h) throws Exception {
            this.sprite = new Sprite(
                    0, 0,
                    TexturedModel.getTexturedModel(
                            w,
                            h,
                            Textures.getTexture("test")
                    )
            );
        }

        @Override
        public void render() {
            sprite.render();
        }
    }
}
