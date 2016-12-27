package engine.renderer.resources;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aedan Smith.
 *
 * Class for accessing the models that have been loaded into OpenGL.
 */

@SuppressWarnings("WeakerAccess")
public class Models {
    /**
     * The number of models.
     */
    public static int size = 0;

    /**
     * The list of model vertices that OpenGL is using.
     */
    private static final ArrayList<float[]> modelVertices = new ArrayList<>();

    /**
     * The list of model indices that OpenGL is using.
     */
    private static final ArrayList<int[]> modelIndices = new ArrayList<>();

    /**
     * Adds the given model to the list of models.
     *
     * @param vertices The list of vertices in the model.
     * @param indices The list of indices in the model.
     */
    public static void add(float[] vertices, int[] indices){
        modelVertices.add(vertices);
        modelIndices.add(indices);
        size++;
    }

    /**
     * Returns the index of a model loaded into OpenGL.
     *
     * @param vertices The list of vertices in the model.
     * @param indices The list of indices in the model.
     * @return The position of the model in OpenGL, -1 if not yet loaded.
     */
    public static int contains(float[] vertices, int[] indices){
        for (int i = 0; i < size; i++) {
            if (Arrays.equals(modelVertices.get(i), vertices) && Arrays.equals(modelIndices.get(i), indices))
                return i;
        }
        return -1;
    }
}
