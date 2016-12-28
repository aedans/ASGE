package engine.sprites.transformations;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Aedan Smith.
 *
 * Transformation for scaling a matrix.
 */

public class ScaleTransformation implements Transformation<Matrix4f> {
    private final float x, y, z;

    @SuppressWarnings("unused")
    public ScaleTransformation(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Matrix4f transform(Matrix4f matrix4f) {
        return matrix4f.scale(new Vector3f(x, y, z));
    }
}
