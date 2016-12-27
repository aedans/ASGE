package engine.renderer.resources;

import engine.utils.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

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
     * Creates a VAO.
     *
     * @return The VAO ID.
     */
    public static int createVAO() {
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
    public static void bindIndicesBuffer(int[] indices) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * Cleans up the VAOs, VBOs, and Textures used by OpenGL.
     */
    public static void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);
        Logger.log("Cleaned up VAOs.");
        vbos.forEach(GL15::glDeleteBuffers);
        Logger.log("Cleaned up VBOs.");
    }

    /**
     * Stores data input a VBO.
     *
     * @param attributeNumber The VBO to store the data input.
     * @param dataSize The size of the data.
     * @param data The data to store.
     */
    public static void storeDataInAttributeList(int attributeNumber, int dataSize, float[] data) {
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
    public static void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    /**
     * Creates an IntBuffer with the given data.
     *
     * @param data The data to store input the IntBuffer.
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
     * @param data The data to store input the FloatBuffer.
     * @return The created FloatBuffer.
     */
    private static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
