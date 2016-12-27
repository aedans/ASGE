package engine.components;

import java.util.function.Predicate;

/**
 * Created by Aedan Smith.
 *
 * Abstract class for creating components that apply when a condition is true or false.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class PredicateComponent<T> implements Component<T> {
    /**
     * The predicate to test if the component should be applied.
     */
    private final Predicate<T> predicate;

    /**
     * Default PredicateComponent constructor.
     *
     * @param predicate The predicate to test if the component should be applied.
     */
    public PredicateComponent(Predicate<T> predicate){
        this.predicate = predicate;
    }

    @Override
    public void apply(T t) {
        if (predicate.test(t)){
            whenTrue(t);
        } else {
            whenFalse(t);
        }
    }

    /**
     * Called when the predicate is true.
     */
    protected abstract void whenTrue(T t);

    /**
     * Called when the predicate is false.
     */
    @SuppressWarnings("EmptyMethod")
    protected abstract void whenFalse(T t);
}
