package ui;

import model.Palette;

public class Main {
    public static void main(String[] args) {
        Palette palette = new Palette();
        PaletteGeneratorApp paletteGeneratorApp = new PaletteGeneratorApp(palette);
        paletteGeneratorApp.start();
    }
}
