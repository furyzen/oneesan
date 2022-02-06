package fr.furyzen.oneesan.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public final double EXPANDER = Math.pow(2, 24);


    public double hypot(final double x, final double y) {
        return Math.sqrt((x * x) + (y * y));
    }

    public long getGcd(final long current, final long previous) {
        return (previous <= 16384L) ? current : getGcd(previous, current % previous);
    }

    public static long getAbsoluteGcd(final float current, final float last) {

        final long currentExpanded = (long) (current * EXPANDER);

        final long lastExpanded = (long) (last * EXPANDER);

        return getGcd(currentExpanded, lastExpanded);
    }
}
