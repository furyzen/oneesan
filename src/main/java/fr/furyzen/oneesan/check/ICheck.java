package fr.furyzen.oneesan.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ICheck {

    String name();
    CheckState checkState() default CheckState.EXPERIMENTAL;
    String description() default "default description, if you see this please report to FurYzen#0001.";
}