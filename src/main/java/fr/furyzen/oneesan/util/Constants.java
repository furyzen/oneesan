package fr.furyzen.oneesan.util;

public enum Constants {
    NAME("Oneesan"),
    MAJOR_VERSION("1"),
    MINOR_VERSION("0"),
    FIX_VERSION("0"),
    VERSION(String.format("%s.%s.%s", MAJOR_VERSION, MINOR_VERSION, FIX_VERSION)),
    AUTHOR("FurYzen");

    String name;

    Constants(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
