package persistence;

import model.Colour;
import model.Event;
import model.EventLog;
import model.Palette;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads palette from JSON data stored in file
public class JsonReader {
    private static String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        JsonReader.source = source;
    }

    // EFFECTS: reads palette from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static Palette read() throws IOException {
        EventLog.getInstance().logEvent(new Event("Loaded Palette to File."));
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePalette(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses palette from JSON object and returns it
    private static Palette parsePalette(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        Palette pa = new Palette();
        addColours(pa, jsonObject);
        return pa;
    }

    // MODIFIES: pa
    // EFFECTS: parses colours from JSON object and adds them to palette
    private static void addColours(Palette pa, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("colours");
        for (Object json : jsonArray) {
            JSONObject nextColour = (JSONObject) json;
            addColour(pa, nextColour);
        }
    }

    // MODIFIES: pa
    // EFFECTS: parses colour from JSON object and adds it to palette
    private static void addColour(Palette pa, JSONObject jsonObject) {
        int red = jsonObject.getInt("red");
        int green = jsonObject.getInt("green");
        int blue = jsonObject.getInt("blue");

        Colour colour = new Colour(red,green,blue);
        pa.addColour(colour);
    }
}
