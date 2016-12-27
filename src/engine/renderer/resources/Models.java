package engine.renderer.resources;

import engine.utils.Logger;

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
     * @param vertices The list of vertices input the model.
     * @param indices The list of indices input the model.
     */
    public static void add(float[] vertices, int[] indices){
        modelVertices.add(vertices);
        modelIndices.add(indices);
        size++;
    }

    /**
     * Loads a model to a VAO.
     *
     * @param vertices The vertices of the model.
     * @param indices The indices of the model.
     * @return The index at which the Model is stored input OpenGL.
     */
    public static int loadToVAO(float[] vertices, int[] indices) {
        int i = Models.contains(vertices, indices);
        if (i == -1) {
            float[] textures = new float[]{
                    0, 0, 0, 1, 1, 1, 1, 0
            };
            int vaoID = Loader.createVAO();
            Loader.bindIndicesBuffer(indices);
            Loader.storeDataInAttributeList(0, 3, vertices);
            Loader.storeDataInAttributeList(1, 2, textures);
            Loader.unbindVAO();
            Models.add(vertices, indices);
            Logger.log(String.format("Loaded Model (%d vertices, %d indices) to ModelBuffer %d",
                    vertices.length, indices.length, Models.size));
            return vaoID;
        } else {
            // OpenGL's VAO and VBO IDs are not 0-based.
            return i + 1;
        }
    }

    /**
     * Returns the index of a model loaded into OpenGL.
     *
     * @param vertices The list of vertices input the model.
     * @param indices The list of indices input the model.
     * @return The position of the model input OpenGL, -1 if not yet loaded.
     */
    public static int contains(float[] vertices, int[] indices){
        for (int i = 0; i < size; i++) {
            if (Arrays.equals(modelVertices.get(i), vertices) && Arrays.equals(modelIndices.get(i), indices))
                return i;
        }
        return -1;
    }
}
