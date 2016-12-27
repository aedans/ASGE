package engine.sprites.predicates;

import engine.in.MouseButton;
import engine.sprites.Sprite;
import org.lwjgl.input.Mouse;

import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 *
 * Predicate for testing if a Sprite is clicked.
 */

@SuppressWarnings("unused")
public class IsClicked implements Predicate<Sprite> {
    private boolean hasBeenPressed = false;

    /**
     * The MouseButton to test.
     */
    private final MouseButton mouseButton;

    public IsClicked(MouseButton mouseButton){
        this.mouseButton = mouseButton;
    }

    @Override
    public boolean test(Sprite sprite) {
        if (Mouse.isButtonDown(mouseButton.getId()) && IsHovered.testSprite(sprite)){
            hasBeenPressed = true;
        } else if (hasBeenPressed && !Mouse.isButtonDown(mouseButton.getId())){
            hasBeenPressed = false;
            return true;
        }
        return false;
    }
}
