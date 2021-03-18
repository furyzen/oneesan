package fr.furyzen.oneesan.util.theme;

public class ThemeHelper {

    public static String format(String originalString) {
        return originalString
                .replace("<prefix>", ThemeLoader.INSTANCE.get("<theme>.prefix"))
                .replace("<name>", ThemeLoader.INSTANCE.get("<theme>.name"));
    }
}
