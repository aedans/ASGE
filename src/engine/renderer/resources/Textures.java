package engine.renderer.resources;

import engine.utils.Logger;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 *
 * Class for accessing the textures that OpenGL is using.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Textures {
    /**
     * The directory of the images.
     */
    public static final String imageDir = "assets/imgs/";

    /**
     * The list of names of the textures.
     */
    private static final ArrayList<String> textureNames = new ArrayList<>();

    /**
     * List of loaded textures.
     */
    private static final ArrayList<Integer> textures = new ArrayList<>();

    public static void loadAllTextures() throws Exception {
        //noinspection ConstantConditions
        for (File file : new File(imageDir).listFiles()){
            loadTexture(file.getName());
        }
    }

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
     * Returns the id of the texture with the given file name.
     *
     * @param fileName The name of the texture's file.
     * @return The texture id.
     */
    @SuppressWarnings("UnusedReturnValue")
    public static int loadTexture(String fileName) throws Exception {
        if (!textureNames.contains(fileName)) {
            loadTexture(
                    fileName.substring(fileName.indexOf('.')),
                    fileName = fileName.substring(0, fileName.indexOf('.'))
            );
            textureNames.add(fileName);
            return textureNames.size();
        } else {
            throw new Exception("Texture " + fileName + " is already loaded.");
        }
    }

    /**
     * Loads a texture.
     *
     * @param extension The extension for the file to load.
     * @param fileName The name of the image to load.
     * @return The location at which the image is stored input OpenGL.
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

    public static void cleanUp(){
        textures.forEach(GL11::glDeleteTextures);
        Logger.log("Cleaned up Textures.");
    }

    public static int getNumTextures(){
        return textureNames.size();
    }
}
