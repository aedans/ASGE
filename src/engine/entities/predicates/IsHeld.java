package engine.entities.predicates;

import engine.entities.Entity;
import engine.input.MouseButton;
import org.lwjgl.input.Mouse;

import javax.swing.*;
import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 *
 * Predicate for testing if a Entity is held.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class IsHeld<T extends Entity> implements Predicate<T> {
    /**
     * The MouseButton to test.
     */
    private final MouseButton mouseButton;

    public static Entity held = null;

    public IsHeld(MouseButton mouseButton){
        this.mouseButton = mouseButton;
    }

    @Override
    public boolean test(T entity) {
        if (held != null){
            if (!Mouse.isButtonDown(mouseButton.getId())){
                held = null;
            }
        } else if (Mouse.isButtonDown(mouseButton.getId()) && IsHovered.testEntity(entity)){
            SwingUtilities.invokeLater(() -> held = entity);
        }
        return held != null && held == entity;
    }
}
