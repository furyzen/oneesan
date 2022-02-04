package fr.furyzen.oneesan.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ICommand {

    String permission();
    String[] names();
    String description() default "default description, if you see this please report to FurYzen#0001.";
}