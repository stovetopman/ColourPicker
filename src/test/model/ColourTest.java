package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColourTest {
    Colour c1;
    Colour c2;
    Colour c3;
    Colour c4;
    Colour c5;
    Colour c6;

    @BeforeEach
    void runBefore() {
        c1 = new Colour(0, 0, 0);        //black
        c2 = new Colour(255, 255, 255);  //white
        c3 = new Colour(100, 100, 100);  //bluish dark grey
        c4 = new Colour(10, 200, 30);      //green
        c5 = new Colour(100, 200, 100);
        c6 = new Colour(155, 55, 155);
    }


    @Test
    void testToHex() {
        assertEquals("0",c1.toHex());
        assertEquals("ffffff",c2.toHex());
        assertEquals("646464",c3.toHex());
        assertEquals("ac81e",c4.toHex());
        assertEquals("64c864",c5.toHex());
        }


@Test
    void testGetBrighter(){
        assertEquals(130, c3.getBrighter().getRed());
        assertEquals(130, c3.getBrighter().getGreen());
        assertEquals(130, c3.getBrighter().getBlue());


}

    @Test
    void testGetBrightest(){
        assertEquals(160, c3.getBrightest().getRed());
        assertEquals(160, c3.getBrightest().getGreen());
        assertEquals(160, c3.getBrightest().getBlue());

    }

    @Test
    void testGetColour(){
        assertEquals(100, c3.getColour().getRed());
        assertEquals(100, c3.getColour().getGreen());
        assertEquals(100, c3.getColour().getBlue());

    }

    @Test
    void testGetDarker(){
        assertEquals(70, c3.getDarker().getRed());
        assertEquals(70, c3.getDarker().getGreen());
        assertEquals(70, c3.getDarker().getBlue());

    }

    @Test
    void testGetDarkest(){
        assertEquals(40, c3.getDarkest().getRed());
        assertEquals(40, c3.getDarkest().getGreen());
        assertEquals(40, c3.getDarkest().getBlue());

    }

    @Test
    void testGetComplementary() {
        assertEquals(c2.getRed(), c1.getComplementary().getRed());
        assertEquals(c2.getGreen(), c1.getComplementary().getGreen());
        assertEquals(c2.getBlue(), c1.getComplementary().getBlue());
        assertEquals(c6.getRed(), c5.getComplementary().getRed());
        assertEquals(c6.getGreen(), c5.getComplementary().getGreen());
        assertEquals(c6.getBlue(), c5.getComplementary().getBlue());
    }

    @Test
    void testGetMonoChromaticColours(){
        assertEquals(c3.getDarkest().getRed(), c3.getMonochromaticColours().get(0).getRed());
        assertEquals(c3.getDarkest().getGreen(), c3.getMonochromaticColours().get(0).getGreen());
        assertEquals(c3.getDarkest().getBlue(), c3.getMonochromaticColours().get(0).getBlue());
        assertEquals(c3.getDarker().getRed(), c3.getMonochromaticColours().get(1).getRed());
        assertEquals(c3.getDarker().getGreen(), c3.getMonochromaticColours().get(1).getGreen());
        assertEquals(c3.getDarker().getBlue(), c3.getMonochromaticColours().get(1).getBlue());
        assertEquals(c3.getColour().getRed(), c3.getMonochromaticColours().get(2).getRed());
        assertEquals(c3.getColour().getGreen(), c3.getMonochromaticColours().get(2).getGreen());
        assertEquals(c3.getColour().getBlue(), c3.getMonochromaticColours().get(2).getBlue());
        assertEquals(c3.getBrighter().getRed(), c3.getMonochromaticColours().get(3).getRed());
        assertEquals(c3.getBrighter().getGreen(), c3.getMonochromaticColours().get(3).getGreen());
        assertEquals(c3.getBrighter().getBlue(), c3.getMonochromaticColours().get(3).getBlue());
        assertEquals(c3.getBrightest().getRed(), c3.getMonochromaticColours().get(4).getRed());
        assertEquals(c3.getBrightest().getGreen(), c3.getMonochromaticColours().get(4).getGreen());
        assertEquals(c3.getBrightest().getBlue(), c3.getMonochromaticColours().get(4).getBlue());


    }
}


