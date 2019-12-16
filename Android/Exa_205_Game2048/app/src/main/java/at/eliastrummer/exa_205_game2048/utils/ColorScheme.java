package at.eliastrummer.exa_205_game2048.utils;

public enum ColorScheme {
    VALUE_2(2, 0xE7C69C, 0x333333);

    ColorScheme(int value, int background, int foreground) {
        this.value = value;
        this.background = background;
        this.foreground = foreground;
    }

    private int value;
    private int background;
    private int foreground;

}
