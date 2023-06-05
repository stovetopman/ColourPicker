package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Represents a palette in terms of a list of valid colours
public class Palette implements Writable {
    public final List<Colour> colours;



    //EFFECTS: constructs a palette
    public Palette() {
        colours = new ArrayList<>();

    }

    //EFFECTS: Adds colour to this list of colours while making sure its values are valid
    public void addColour(Colour colour) {
        colour.setRed(Colour.withinLimits(colour.getRed()));
        colour.setGreen(Colour.withinLimits(colour.getGreen()));
        colour.setBlue(Colour.withinLimits(colour.getBlue()));
        colours.add(colour);
        EventLog.getInstance().logEvent(new Event("Colour ["
                + colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + "] Added to Palette."));
    }

    //EFFECTS: Removes colour from this
    public void removeColour(Colour colour) {
        colours.remove(colour);
        EventLog.getInstance().logEvent(new Event("Colour ["
                + colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + "] Removed from Palette."));
    }

    //EFFECTS: Returns the RGB stats of a colour at a specific index
    public Colour getColour(int index) throws ColourIndexException {
        if (index > colours.size()) {
            throw new ColourIndexException();
        } else {
            return colours.get(index);
        }
    }

    //EFFECTS: Edits the RGB composition of a colour at a specified index
    public void editColour(int index, int red, int green, int blue) {
        Colour colour = colours.get(index);
        EventLog.getInstance().logEvent(new Event("Colour " + index + " ["
                                + colour.getRed() + ", " + colour.getGreen() + ", " + colour.getBlue() + "] edited to "
                + "Colour ["
                + red + "," + green + "," + blue + "]"
                ));
        colour.setRed(red);
        colour.setGreen(green);
        colour.setBlue(blue);

    }

    //EFFECTS: returns the number of colours in colours
    public int size() {
        return colours.size();
    }


    //EFFECTS: returns colours as a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("colours", coloursToJson());
        return json;
    }


    //EFFECT: returns colours in this palette as a JSON array
    private JSONArray coloursToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Colour c : colours) {
            jsonArray.put((c.toJson()));
        }

        return jsonArray;
    }

    //EFFECTS: returns colours as a unmodifiable list
    public List<Colour> getColours() {
        return Collections.unmodifiableList(colours);
    }

    //Prints the list of actions performed since the program start
    public void printActions() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }
}

