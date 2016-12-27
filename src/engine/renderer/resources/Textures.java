package engine.renderer.resources;

import engine.utils.Logger;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 *
 * Class for accessing the textures that OpenGL is using.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Textures {
    /**
     * The list of names of the loaded textures.
     */
    private static final ArrayList<String> textureNames = new ArrayList<>();

    /**
     * List of loaded textures.
     */
    private static final ArrayList<Integer> textures = new ArrayList<>();

    /**
     * Returns the id of the texture with the given name.
     *
     * @param textureName The name of the texture.
     * @return The texture ID.
     */
    public static int getTexture(String textureName){
        if (textureName.contains(textureName)) {
            return textureNames.indexOf(textureName)+1;
        } else {
            return -1;
        }
    }

    /**
     * Loads a BufferedImage into OpenGL.
     *
     * @param name The name by which to retrieve the texture.
     * @param bufferedImage The BufferedImage to load.
     * @return The OpenGL index of the texture.
     * @throws Exception If there was an error loading the BufferedImage.
     */
    @SuppressWarnings("SameParameterValue")
    public static int loadTexture(String name, BufferedImage bufferedImage) throws Exception {
        if (!textureNames.contains(name)) {
            bufferedImage = scaleToSquare(bufferedImage);
            Texture texture = BufferedImageUtil.getTexture(name, bufferedImage);
            textures.add(texture.getTextureID());
            textureNames.add(name);
            Logger.log(String.format("Loaded texture \"%s\" to TextureBuffer %d", name, texture.getTextureID()));
            return textureNames.size();
        } else {
            throw new Exception("Texture \"" + name + "\" is already loaded.");
        }
    }

    private static BufferedImage scaleToSquare(BufferedImage bufferedImage){
        int w = bufferedImage.getWidth(), h = bufferedImage.getHeight(), i = 1;
        while (i < w || i < h){
            i *= 2;
        }
        BufferedImage scaledImage = new BufferedImage(i, i, bufferedImage.getType());
        scaledImage.getGraphics().drawImage(createFlipped(bufferedImage), 0, 0, i, i, null);
        return scaledImage;
    }

    private static BufferedImage createFlipped(BufferedImage image) {
        AffineTransform at = new AffineTransform();
        at.scale(1, -1);
        at.translate(0, -image.getHeight());
        return createTransformed(image, at);
    }

    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public static void cleanUp(){
        textures.forEach(GL11::glDeleteTextures);
        Logger.log("Cleaned up textures.");
    }

    public static int getNumTextures(){
        return textureNames.size();
    }
}
