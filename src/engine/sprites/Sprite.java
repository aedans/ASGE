package engine.sprites;

import engine.renderer.Renderer;
import engine.renderer.resources.TexturedModel;
import engine.utils.MatrixMath;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("WeakerAccess")
public class Sprite implements Renderable {
    /**
     * The TexturedModel of the Sprite.
     */
    private final TexturedModel texturedModel;

    /**
     * The position of the Sprite.
     */
    private float x, y;

    /**
     * The transformation matrix for the Sprite.
     */
    private Matrix4f transformationMatrix;

    /**
     * Default Sprite constructor.
     *
     * @param x The x position of the Entity.
     * @param y The y position of the Entity.
     * @param texturedModel The TexturedModel of the Entity.
     */
    public Sprite(float x, float y, TexturedModel texturedModel){
        this.setPosition(x, y);
        this.texturedModel = texturedModel;
    }

    /**
     * The default render function for the Sprite.
     */
    @Override
    public void render(){
        Renderer.compositeShader.loadTransformationMatrix(getTransformationMatrix());
        Renderer.bindTexturedModel(texturedModel);
        Renderer.drawElements();
    }

    /**
     * Translates the Sprite.
     *
     * @param x The x amount to translate.
     * @param y The y amount to translate.
     */
    @SuppressWarnings("unused")
    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
        this.transformationMatrix = MatrixMath.createTransformationMatrix(this.x, this.y);
    }

    /**
     * Sets the position of the Sprite.
     *
     * @param x The x position of the Entity.
     * @param y The y position of the Entity.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.transformationMatrix = MatrixMath.createTransformationMatrix(this.x, this.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth(){
        return texturedModel.getWidth();
    }

    public float getHeight(){
        return texturedModel.getHeight();
    }

    public Matrix4f getTransformationMatrix(){
        return transformationMatrix;
    }

    @SuppressWarnings("unused")
    public TexturedModel getTexturedModel() {
        return texturedModel;
    }
}
