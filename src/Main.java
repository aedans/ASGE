import engine.game.GameState;
import engine.game.StateBasedGame;
import engine.renderer.Renderer;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        new StateBasedGame(
                1080, 720, false, "Test Display",
                new GameState(){
                    private Sprite sprite;

                    @Override
                    public void init() throws Exception {
                        this.sprite = new Sprite(
                                0,
                                0,
                                TexturedModel.getTexturedModel(
                                        1f,
                                        1f,
                                        Textures.loadTexture("test.png")
                                )
                        ){
                            @Override
                            public void update() {

                            }
                        };
                    }

                    @Override
                    public void update() throws Exception {
                        Renderer.bColorR += 0.005f;
                        Renderer.bColorG += 0.005f;
                        Renderer.bColorB += 0.005f;
                        sprite.update();
                    }

                    @Override
                    public void render() throws Exception {
                        Renderer.beginRender();
                        sprite.render();
                        Renderer.endRender();
                    }
                }
        ).run();
    }
}
