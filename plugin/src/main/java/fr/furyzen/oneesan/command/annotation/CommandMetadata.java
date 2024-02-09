package fr.furyzen.oneesan.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandMetadata {

    String name();

    String description() default "default description, if you see this please report to furyzen on Discord.";

    String[] aliases();
}
