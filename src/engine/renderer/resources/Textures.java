package engine.renderer.resources;

import java.io.File;
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
            Loader.loadTexture(
                    fileName.substring(fileName.indexOf('.')),
                    fileName = fileName.substring(0, fileName.indexOf('.'))
            );
            textureNames.add(fileName);
            return textureNames.size();
        } else {
            throw new Exception("Texture " + fileName + " is already loaded.");
        }
    }

    public static int getNumTextures(){
        return textureNames.size();
    }
}
