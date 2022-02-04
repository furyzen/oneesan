package fr.furyzen.oneesan.util.theme;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ThemeHelper {

    public String format(String originalString) {
        return originalString
                .replace("<prefix>", ThemeLoader.INSTANCE.get("<theme>.prefix"))
                .replace("<name>", ThemeLoader.INSTANCE.get("<theme>.name"));
    }
}
