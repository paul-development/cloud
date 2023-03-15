package de.paul.database;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
@FunctionalInterface
public interface ISQLFunction<I, O> {

    O apply(I i);

}
