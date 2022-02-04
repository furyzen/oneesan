package fr.furyzen.oneesan.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public double hypot(final double x, final double y) {
        return Math.sqrt((x * x) + (y * y));
    }
}
