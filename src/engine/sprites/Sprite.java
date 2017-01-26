package engine.sprites;

import engine.renderer.Renderer;
import engine.renderer.resources.TexturedModel;
import engine.utils.MatrixMath;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

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
     * The scale of the sprite.
     */
    private float xScale = 1, yScale = 1;

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
    @SuppressWarnings("SameParameterValue")
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
     */
    @SuppressWarnings("unused")
    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
        this.recalculateMatrix();
    }

    /**
     * Sets the position of the Sprite.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.recalculateMatrix();
    }

    /**
     * Scales the sprite by the given amount.
     */
    @SuppressWarnings("unused")
    public void scale(float xScale, float yScale){
        this.xScale *= xScale;
        this.yScale *= yScale;
        this.recalculateMatrix();
    }

    /**
     * Sets the scale of the sprite to the given amount.
     */
    @SuppressWarnings("unused")
    public void setScale(float xScale, float yScale){
        this.xScale = xScale;
        this.yScale = yScale;
        this.recalculateMatrix();
    }

    public void recalculateMatrix(){
        this.transformationMatrix = MatrixMath.createTransformationMatrix(
                this.x,
                this.y,
                new Vector3f(xScale, yScale, 1)
        );
    }

    @SuppressWarnings("unused")
    public float getX() {
        return x;
    }

    @SuppressWarnings("unused")
    public float getY() {
        return y;
    }

    @SuppressWarnings("unused")
    public float getWidth(){
        return texturedModel.getWidth();
    }

    @SuppressWarnings("unused")
    public float getHeight(){
        return texturedModel.getHeight();
    }

    @SuppressWarnings("unused")
    public float getxScale() {
        return xScale;
    }

    @SuppressWarnings("unused")
    public float getyScale() {
        return yScale;
    }

    public Matrix4f getTransformationMatrix(){
        return transformationMatrix;
    }

    public void setTransformationMatrix(Matrix4f transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
    }

    @SuppressWarnings("unused")
    public TexturedModel getTexturedModel() {
        return texturedModel;
    }
}
