package engine.sprites.transformations;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Aedan Smith.
 *
 * Transformation for translating a matrix.
 */

public class TranslationTransformation implements Transformation<Matrix4f> {
    private final float x,  y;

    @SuppressWarnings("unused")
    public TranslationTransformation(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Matrix4f transform(Matrix4f matrix4f) {
        Matrix4f.translate(new Vector2f(x, y), matrix4f, matrix4f);
        return matrix4f;
    }
}
