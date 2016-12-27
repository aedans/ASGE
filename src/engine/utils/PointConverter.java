package engine.utils;

import engine.renderer.DisplayManager;

/**
 * Created by Aedan Smith.
 *
 * Class for converting points on the OpenGL coordinate system to points on the pixel coordinate system, and visa versa.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public final class PointConverter {
    /**
     * The factor to translate between OpenGL and Pixel Coordinate Systems.
     */
    public static final float ppX = 1f / (float) DisplayManager.xRes, ppY = 1f / (float) DisplayManager.yRes;

    /**
     * Converts an OpenGL x coordinate to a pixel x coordinate.
     */
    public static int convertX(float x){
        return (int) ((x+1) / ppX / 2);
    }

    /**
     * Converts an OpenGL y coordinate to a pixel x coordinate.
     */
    public static int convertY(float y){
        return (int) ((y+1) / ppY / 2);
    }

    /**
     * Converts a pixel x coordinate to an OpenGL x coordinate.
     */
    public static float convertX(int x){
        return (x * ppX * 2) - 1;
    }

    /**
     * Converts a pixel x coordinate to an OpenGL y coordinate.
     */
    public static float convertY(int y){
        return (y * ppY * 2) - 1;
    }

    /**
     * Class representing a point on the OpenGL coordinate system.
     */
    @SuppressWarnings("unused")
    public static class OpenGLPoint {
        /**
         * The x and y of the OpenGLPoint.
         */
        public final float x, y;

        public OpenGLPoint(float x, float y){
            this.x = x;
            this.y = y;
        }

        /**
         * @return A PixelPoint with position equal to this point's position.
         */
        public PixelPoint toPixelPoint(){
            return new PixelPoint(
                    (int) ((x+1) / ppX / 2),
                    (int) ((y+1) / ppY / 2)
            );
        }

        @Override
        public String toString() {
            return "OpenGLPoint(" + x + ", " + y + ")";
        }
    }

    /**
     * Class representing a point on the OpenGL pixel system.
     */
    @SuppressWarnings("unused")
    public static class PixelPoint {
        /**
         * The x and y of the PixelPoint
         */
        public final int x, y;

        public PixelPoint(int x, int y){
            this.x = x;
            this.y = y;
        }

        /**
         * @return An OpenGLPoint with position equal to this point's position.
         */
        public OpenGLPoint toOpenGLPoint(){
            return new OpenGLPoint(
                    (x * ppX * 2) - 1,
                    (y * ppY * 2) - 1
            );
        }

        @Override
        public String toString() {
            return "PixelPoint(" + x + ", " + y + ")";
        }
    }
}
