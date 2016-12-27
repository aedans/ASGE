package engine.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Aedan Smith.
 *
 * Utility class for creating matrices.
 */

public final class MatrixMath {
    /**
     * Creates a transformation matrix to translate an image at (0, 0) to (x, y).
     *
     * @param x The x position of the point to translate the image to.
     * @param y The y position of the point to translate the image to.
     * @return The matrix that translates the image at (0, 0) to (x, y).
     */
    public static Matrix4f createTransformationMatrix(float x, float y) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.setIdentity();
        Matrix4f.translate(new Vector2f(x, y), matrix4f, matrix4f);
        return matrix4f;
    }
}
