package engine.sprites.predicates;

import engine.in.MouseButton;
import engine.sprites.Sprite;
import org.lwjgl.input.Mouse;

import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 *
 * Predicate for testing if a Sprite is held.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class IsHeld implements Predicate<Sprite> {
    /**
     * The MouseButton to test.
     */
    private final MouseButton mouseButton;

    private boolean held = false;

    public IsHeld(MouseButton mouseButton){
        this.mouseButton = mouseButton;
    }

    @Override
    public boolean test(Sprite sprite) {
        if (held){
            held = Mouse.isButtonDown(mouseButton.getId());
        } else if (Mouse.isButtonDown(mouseButton.getId()) && IsHovered.testSprite(sprite)){
            held = true;
        }
        return held;
    }
}
