package engine.renderer.resources;

import java.awt.geom.Point2D;

/**
 * Created by Aedan Smith.
 *
 * A model and texture to be used by the renderer.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class TexturedModel {
    /**
     * The ID of the model.
     */
    private final int modelID;

    /**
     * The ID of the texture.
     */
    private final int textureID;

    /**
     * The width and height of the model.
     */
    private final float width;
    private final float height;

    /**
     * Default TexturedModel constructor.
     *
     * @param width The width of the model.
     * @param height The height of the model.
     * @param modelID The ID of the model.
     * @param textureID The ID of the texture.
     */
    public TexturedModel(float width, float height, int modelID, int textureID) {
        this.width = width;
        this.height = height;
        this.modelID = modelID;
        this.textureID = textureID;
    }

    /**
     * Creates a rectangular TexturedModel with the given width, height, and texture ID.
     *
     * @param width The width of the model.
     * @param height The height of the model.
     * @param textureID The texture ID.
     * @return The created TexturedModel.
     */
    @SuppressWarnings("SameParameterValue")
    public static TexturedModel getTexturedModel(float width, float height, int textureID) {
        width/=2;
        height/=2;
        return getTexturedModel(
                new float[]{
                        -width, -height,
                        -width, height,
                        width, height,
                        width, -height
                },
                textureID
        );
    }

    /**
     * Creates a TexturedModel with the given vertices and texture ID.
     *
     * @param vps The vertices of the model.
     * @param textureID The texture ID.
     * @return The created TexturedModel.
     */
    public static TexturedModel getTexturedModel(float[] vps, int textureID) {
        // width and height detection possibly bugged for non-square models, needs to be checked.
        Point2D.Float bottomLeft = new Point2D.Float(
                (vps[0] < vps[2]) ? vps[0] : vps[2],
                (vps[1] < vps[7]) ? vps[1] : vps[7]
        );
        Point2D.Float topRight = new Point2D.Float(
                (vps[4] > vps[6]) ? vps[4] : vps[6],
                (vps[3] > vps[5]) ? vps[3] : vps[5]
        );
        return new TexturedModel(
                (topRight.x - bottomLeft.x),
                (topRight.y - bottomLeft.y),
                Models.loadToVAO(
                        new float[]{
                                vps[0], vps[1], 0,
                                vps[2], vps[3], 0,
                                vps[4], vps[5], 0,
                                vps[6], vps[7], 0
                        },
                        new int[]{
                                0, 1, 3,
                                3, 1, 2
                        }
                ), textureID
        );
    }

    public int getTextureID() {
        return textureID;
    }

    public int getModelID() {
        return modelID;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "TexturedModel(" +
                "Width: " + width +
                ", Height: " + height +
                ", ModelID: " + modelID +
                ", TextureID: " + textureID +
                ")";
    }
}
