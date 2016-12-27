package engine.renderer.shaders;

import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by Aedan Smith.
 *
 * The Composite Shader for the renderer to use.
 */

public class CompositeShader extends Shader {
    private static final String SHADER_PATH = "assets/shaders/composite/";
    private static final String VERTEX_FILE = SHADER_PATH + "composite.vsh";
    private static final String FRAGMENT_FILE = SHADER_PATH + "composite.fsh";

    private int transformationMatrixLocation;

    public CompositeShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrixLocation = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix4f) {
        super.loadMatrix(transformationMatrixLocation, matrix4f);
    }
}
