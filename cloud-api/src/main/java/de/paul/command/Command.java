package de.paul.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Command {
    String name();

    String description();

    String[] aliases() default "";
}
