package persistence;

import model.Colour;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkColour(int red, int green, int blue, Colour colour) {
        assertEquals(red, colour.getRed());
        assertEquals(blue, colour.getBlue());
        assertEquals(green, colour.getGreen());
    }
}
