package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PaletteTest {
    Palette p1;
    Palette p2;
    Colour c1;
    Colour c2;
    Colour c3;
    Colour c4;

    @BeforeEach
    void runBefore() {
        c1 = new Colour(0, 0, 0);
        c2 = new Colour(255, 255, 255);
        c3 = new Colour(100, 100, 100);
        c4 = new Colour( 300, 400, -344);
        p1 = new Palette();
        p1.addColour(c1);
        p1.addColour(c2);
        p1.addColour(c3);
        p2 = new Palette();
    }

    @Test
    void testAddColour() {
        p2.addColour(c1);
        assertEquals(1, p2.size());
    }

    @Test
    void testAddColourInvalid() throws ColourIndexException {
        p2.addColour(c4);
        assertEquals(255, p2.getColour(0).getRed());
        assertEquals(255, p2.getColour(0).getGreen());
        assertEquals(0, p2.getColour(0).getBlue());
    }

    @Test
    void testAddColourMultiple() {
        p2.addColour(c1);
        assertEquals(1, p2.size());
        p2.addColour(c2);
        assertEquals(2, p2.size());
    }

    @Test
    void testRemoveColour(){
        p1.removeColour(c1);
        assertEquals(2, p1.size());
    }

    @Test
    void testRemoveColourMultiple(){
        p1.removeColour(c1);
        assertEquals(2, p1.size());
        p1.removeColour(c2);
        assertEquals(1, p1.size());
    }

    @Test
    void testRemoveColourToZero(){
        p1.removeColour(c1);
        assertEquals(2, p1.size());
        p1.removeColour(c2);
        assertEquals(1, p1.size());
        p1.removeColour(c3);
        assertEquals(0, p1.size());
    }

    @Test
    void testException() {
        try {
            p1.getColour(5);
            fail();
        } catch (ColourIndexException e) {
            //pass
        }
    }

    @Test
    void testGetColour() throws ColourIndexException {
        assertEquals(c1, p1.getColour(0));
        assertEquals(c3, p1.getColour(2));
    }

    @Test
    void testEditColour() throws ColourIndexException {
        p1.editColour(0, 10, 10, 10);
        assertEquals(10, p1.getColour(0).getRed());
        assertEquals(10, p1.getColour(0).getBlue());
        assertEquals(10, p1.getColour(0).getGreen());
    }

    @Test
    void testEditColourInvalid() throws ColourIndexException {
        p1.editColour(0, 1000, -100, 1000);
        assertEquals(255, p1.getColour(0).getRed());
        assertEquals(0, p1.getColour(0).getGreen());
        assertEquals(255, p1.getColour(0).getBlue());
    }

    @Test
    void testPrintActions() {
        
    }


}