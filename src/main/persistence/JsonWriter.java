package persistence;

import model.Event;
import model.EventLog;
import model.Palette;
import org.json.JSONObject;


import java.io.*;

public class JsonWriter {
    private final int tab = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of palette to file
    public void write(Palette palette) {
        JSONObject json = palette.toJson();
        saveToFile(json.toString(tab));
        EventLog.getInstance().logEvent(new Event("Saved Palette to File."));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
