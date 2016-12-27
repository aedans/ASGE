package engine.input;

/**
 * Created by Aedan Smith.
 *
 * Enum containing mouse buttons and their OpenGL IDs.
 */
@SuppressWarnings("unused")
public enum MouseButton {
    LEFT(0),
    RIGHT(1),
    MIDDLE(2),
    GO_PREVIOUS(3),
    GO_NEXT(4);

    private final int id;

    MouseButton(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
