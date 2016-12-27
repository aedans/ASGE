package engine.renderer.resources;

import engine.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 *
 * Class for loading textures and models into OpenGL.
 */

@SuppressWarnings("WeakerAccess")
public class Loader {
    /**
     * List of vertex attribute objects.
     */
    private static final ArrayList<Integer> vaos = new ArrayList<>();

    /**
     * List of vertex buffer objects.
     */
    private static final ArrayList<Integer> vbos = new ArrayList<>();

    /**
     * List of loaded textures.
     */
    private static final ArrayList<Integer> textures = new ArrayList<>();

    /**
     * Loads a model to a VAO.
     *
     * @param vertices The vertices of the model.
     * @param indices The indices of the model.
     * @return The index at which the Model is stored in OpenGL.
     */
    public static int loadToVAO(float[] vertices, int[] indices) {
        int i = Models.contains(vertices, indices);
        if (i == -1) {
            float[] textures = new float[]{
                    0, 0, 0, 1, 1, 1, 1, 0
            };
            int vaoID = createVAO();
            bindIndicesBuffer(indices);
            storeDataInAttributeList(0, 3, vertices);
            storeDataInAttributeList(1, 2, textures);
            unbindVAO();
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
     * Creates a VAO.
     *
     * @return The VAO ID.
     */
    private static int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /**
     * Binds an integer array to the current VAO.
     *
     * @param indices The indices to store.
     */
    private static void bindIndicesBuffer(int[] indices) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * Loads a texture.
     *
     * @param extension The extension for the file to load.
     * @param fileName The name of the image to load.
     * @return The location at which the image is stored in OpenGL.
     * @throws IOException If the image could not be loaded.
     */
    @SuppressWarnings("UnusedReturnValue")
    public static int loadTexture(String extension, String fileName) throws IOException {
        Texture texture = TextureLoader.getTexture(
                extension,
                ResourceLoader.getResourceAsStream(Textures.imageDir + fileName + extension)
        );
        int textureID = texture.getTextureID();
        textures.add(textureID);
        Logger.log(String.format("Loaded Texture \"%s%s\" to TextureBuffer %d", fileName, extension, textureID));
        return textureID;
    }

    /**
     * Cleans up the VAOs, VBOs, and Textures used by OpenGL.
     */
    public static void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);
        Logger.log("Cleaned up VAOs.");
        vbos.forEach(GL15::glDeleteBuffers);
        Logger.log("Cleaned up VBOs.");
        textures.forEach(GL11::glDeleteTextures);
        Logger.log("Cleaned up Textures.");
    }

    /**
     * Stores data in a VBO.
     *
     * @param attributeNumber The VBO to store the data in.
     * @param dataSize The size of the data.
     * @param data The data to store.
     */
    private static void storeDataInAttributeList(int attributeNumber, int dataSize, float[] data) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, dataSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * Unbinds a VAO.
     */
    private static void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    /**
     * Creates an IntBuffer with the given data.
     *
     * @param data The data to store in the IntBuffer.
     * @return The created IntBuffer.
     */
    private static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * Creates an FloatBuffer with the given data.
     *
     * @param data The data to store in the FloatBuffer.
     * @return The created FloatBuffer.
     */
    private static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
