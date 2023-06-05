package persistence;

import model.Colour;
import model.Palette;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Palette pa = new Palette();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPalette() {
        try {
            Palette pa = new Palette();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPalette.json");
            writer.open();
            writer.write(pa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPalette.json");
            pa = reader.read();
            assertEquals(0, pa.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPalette() {
        try {
            Palette pa = new Palette();
            pa.addColour(new Colour(0,0,0));
            pa.addColour(new Colour(100,150,200));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPalette.json");
            writer.open();
            writer.write(pa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPalette.json");
            pa = reader.read();
            List<Colour> colours = pa.getColours();
            assertEquals(2, pa.size());
            checkColour(0,0,0, colours.get(0));
            checkColour(100,150,200, colours.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}