package engine.entities.predicates;

import engine.entities.Entity;
import engine.input.MouseButton;
import org.lwjgl.input.Mouse;

import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 *
 * Predicate for testing if a Sprite is clicked.
 */

@SuppressWarnings("unused")
public class IsClicked<T extends Entity> implements Predicate<T> {
    private boolean hasBeenPressed = false;

    /**
     * The MouseButton to test.
     */
    private final MouseButton mouseButton;

    public IsClicked(MouseButton mouseButton){
        this.mouseButton = mouseButton;
    }

    @Override
    public boolean test(T entity) {
        if (Mouse.isButtonDown(mouseButton.getId()) && IsHovered.testEntity(entity)){
            hasBeenPressed = true;
        } else if (hasBeenPressed && !Mouse.isButtonDown(mouseButton.getId())){
            hasBeenPressed = false;
            return true;
        }
        return false;
    }
}
