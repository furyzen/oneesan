package fr.furyzen.oneesan.util.theme;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ThemeHelper {

    public String format(String originalString) {
        return originalString
                .replace("<prefix>", ThemeLoader.getInstance().get("<theme>.prefix"))
                .replace("<name>", ThemeLoader.getInstance().get("<theme>.name"));
    }
}
