package persistence;

import model.Colour;
import model.Palette;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Palette pa = JsonReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyPalette() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPalette.json");
        try {
            Palette pa = JsonReader.read();
            assertEquals(0, pa.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void restReaderGeneralPalette() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPalette.json");
        try {
            Palette pa = JsonReader.read();
            List<Colour> colours = pa.getColours();
            assertEquals(3,colours.size());
            checkColour(0, 0, 0, colours.get(0));
            checkColour(100, 150, 200, colours.get(1));
            checkColour(255, 255, 255, colours.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
