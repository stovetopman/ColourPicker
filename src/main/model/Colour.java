package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a colour in terms of red, green, and blue
// each are in the range [0, 255]
public class Colour implements Writable {
    private int red;
    private int green;
    private int blue;

    // EFFECTS: constructs a colour (r, g, b)
    public Colour(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }



    // EFFECTS: returns the hexadecimal representation of this colour
    String toHex() {
        return Integer.toHexString((red * 256 + green) * 256 + blue);
    }

    // EFFECTS: returns the value if it is within bounds, returns bounds if it crosses them
    public static int withinLimits(int val) {
        return (Math.max(Math.min(val, 255), 0));
    }

    // EFFECTS: gets a brighter colour
    Colour getBrighter() {
        return new Colour(withinLimits(red * 13 / 10),
                withinLimits(green * 13 / 10), withinLimits(blue * 13 / 10));
    }

    // EFFECTS: gets the next brighter colour
    Colour getBrightest() {
        return new Colour(withinLimits(red * 8 / 5),
                withinLimits(green * 8 / 5), withinLimits(blue * 8 / 5));
    }

    // EFFECTS: return current colour
    Colour getColour() {
        return new Colour(withinLimits(red),
                withinLimits(green), withinLimits(blue));
    }

    // EFFECTS: return darker colour
    Colour getDarker() {
        return new Colour(withinLimits(red * 7 / 10),
                withinLimits(green * 7 / 10), withinLimits(blue * 7 / 10));
    }

    // EFFECTS: return next darker colour
    Colour getDarkest() {
        return new Colour(withinLimits(red * 2 / 5),
                withinLimits(green * 2 / 5), withinLimits(blue * 2 / 5));
    }

    //EFFECTS: establishes red value within limits
    public void setRed(int red) {
        this.red = withinLimits(red);
    }

    //EFFECTS: establishes green value within limits
    public void setGreen(int green) {
        this.green = withinLimits(green);
    }

    //EFFECTS: establishes blue value within limits
    public void setBlue(int blue) {
        this.blue = withinLimits(blue);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    //EFFECTS: returns complementary colour
    public Colour getComplementary() {
        int red = 255 - this.red;
        int green = 255 - this.green;
        int blue = 255 - this.blue;
        EventLog.getInstance().logEvent(new Event("Complementary Colour Added to Palette:"));
        return new Colour(red, green, blue);
    }

    //EFFECTS: returns a list of monochromatic colours with the original colour in the center
    public List<Colour> getMonochromaticColours() {
        List<Colour> monochromaticColours = new ArrayList<>();
        monochromaticColours.add(getDarkest());
        monochromaticColours.add(getDarker());
        monochromaticColours.add(getColour());
        monochromaticColours.add(getBrighter());
        monochromaticColours.add(getBrightest());
        EventLog.getInstance().logEvent(new Event("Monochromatic Colours Added to Palette:"));
        return monochromaticColours;
    }

    //EFFECTS: returns the colour as a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("red",red);
        json.put("green",green);
        json.put("blue",blue);
        return json;
    }
}


