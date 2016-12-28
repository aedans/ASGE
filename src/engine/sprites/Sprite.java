package engine.sprites;

import engine.renderer.Renderer;
import engine.renderer.resources.TexturedModel;
import engine.sprites.transformations.Transformation;
import engine.utils.MatrixMath;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Aedan Smith
 */

@SuppressWarnings("WeakerAccess")
public class Sprite implements Renderable {
    /**
     * The TexturedModel of the Entity.
     */
    private final TexturedModel texturedModel;

    /**
     * The position of the Entity.
     */
    private float x, y;

    /**
     * The transformation matrix for the Entity.
     */
    private Matrix4f transformationMatrix;

    /**
     * Default Entity constructor.
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
     * The default render function for the Entity.
     */
    @Override
    public void render(){
        render(getTransformationMatrix());
    }

    /**
     * Renders the sprite with the given transformations.
     */
    @SuppressWarnings("unused")
    @SafeVarargs
    public final void render(Transformation<Matrix4f>... transformations){
        Matrix4f matrix4f = new Matrix4f(getTransformationMatrix());
        for (Transformation<Matrix4f> transformation : transformations){
            matrix4f = transformation.transform(matrix4f);
        }
        render(matrix4f);
    }

    public void render(Matrix4f matrix4f){
        Renderer.compositeShader.loadTransformationMatrix(matrix4f);
        Renderer.bindTexturedModel(texturedModel);
        Renderer.drawElements();
    }

    /**
     * Translates the Entity.
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
     * Sets the position of the Entity.
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
