package ui;

import model.Colour;
import model.ColourIndexException;
import model.Event;
import model.EventLog;
import model.Palette;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.Scanner;


public class PaletteGeneratorApp {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final String jsonStore = "./data/palette.json";
//    private final Scanner scanner;
    private static Palette palette;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private JPanel colourDisplay;
    private final JPanel displayColours;
    private JFrame window;

    public PaletteGeneratorApp(Palette palette) {

        colourDisplay = new JPanel();
        displayColours = new JPanel();
//        scanner = new Scanner(System.in);
        PaletteGeneratorApp.palette = palette;
        jsonWriter = new JsonWriter(jsonStore);
        jsonReader = new JsonReader(jsonStore);





    }

    //EFFECTS: calls the graphics initializer method
    public  void start() {
        initializeGraphics();

    }

    //EFFECTS: Creates and positions all relevant JPanels to create interface
    private void initializeGraphics() {
        window = new JFrame("Palette Generator App");
        window.setSize(WIDTH,HEIGHT);
        window.setLayout(new BorderLayout());
        window.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                palette.printActions();
                        System.exit(0);
            }
        });
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.repaint();
        window.setBackground(new Color(26,63,50));
        window.getContentPane();
        colourOrDisplaySet();
        window.add(colourDisplay);
        addButtons(colourDisplay);
        window.add(displayColours);
        window.validate();

    }

    public void colourOrDisplaySet() {
        colourDisplay = (JPanel) window.getGlassPane();
        colourDisplay.setVisible(true);
        colourDisplay.setBounds(10,10,220,310);
        colourDisplay.setBackground(new Color(116,198,157));
        displayColours.setBounds(400,0,300,600);
        displayColours.setBackground(new Color(116,198,157,20));
        displayColours.setLayout(new BoxLayout(displayColours, BoxLayout.PAGE_AXIS));
        colourDisplay.setLayout(new BoxLayout(colourDisplay, BoxLayout.PAGE_AXIS));
        colourDisplay.add(Box.createRigidArea(new Dimension(0,5)));
    }


    //EFFECTS: Adds buttons to the glasspane container.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addButtons(Container container) {
        JButton viewPalette = new JButton(new ViewPaletteAction());
        JButton addColour = new JButton(new AddColourAction());
        JButton editColour = new JButton(new EditColourAction());
        JButton getComplementary = new JButton(new ComplementaryColourAction());
        JButton getMonochromatic = new JButton(new MonochromaticColourAction());
        JButton removeColour = new JButton(new RemoveColourAction());
        JButton savePalette = new JButton(new SavePaletteAction());
        JButton loadPalette = new JButton(new LoadPaletteAction());
        container.add(viewPalette);
        container.add(addColour);
        container.add(editColour);
        container.add(getComplementary);
        container.add(getMonochromatic);
        container.add(removeColour);
        container.add(savePalette);
        container.add(loadPalette);
    }




    //EFFECTS: Displays all the colours in the current palette
    public class ViewPaletteAction extends AbstractAction {
        ViewPaletteAction() {
            super("View Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            viewColours();
            displayColours.removeAll();
            window.add(displayColours);
            System.out.println("Viewing Palette");
            for (int i = 0; i < palette.size(); i++) {
                JPanel colourBlock = new JPanel();
                try {
                    System.out.println(i);
                    displayColours.add(colourBlock);
                    colourBlock.setBackground(new Color(palette.getColour(i).getRed(),
                            palette.getColour(i).getGreen(),
                            palette.getColour(i).getBlue()));
                    colourBlock.setBounds(300, 50 * i, 300, 50);
                    colourBlock.add(generateLabel(i));
                } catch (ColourIndexException e) {
                    System.out.println("Error");
                }
            }
            window.repaint();
            displayColours.revalidate();
        }
    }

    //EFFECTS: Generates a short description of the colour
    public JLabel generateLabel(int i) throws ColourIndexException {
        JLabel label = new JLabel("Colour " + i + ": " + "[R: " + palette.getColour(i).getRed()
                + ", G: " + palette.getColour(i).getGreen()
                + ", B: " + palette.getColour(i).getBlue() + "]");
        if (palette.getColour(i).getRed() < 100
                || palette.getColour(i).getGreen() < 100
                || palette.getColour(i).getBlue() < 100) {
            label.setForeground(Color.white);
        }
        return label;

    }

    //EFFECTS: Adds a colour to the palette
    public class AddColourAction extends AbstractAction {
        AddColourAction() {
            super("Add Colour");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            String redColourToAdd = JOptionPane.showInputDialog(null,
                    "Red Value?,"
                            + "Red Value",
                    JOptionPane.QUESTION_MESSAGE);
            int red = Integer.parseInt(redColourToAdd);
            String greenColourToAdd = JOptionPane.showInputDialog(null,
                    "Green Value?,"
                            + "Green Value",
                    JOptionPane.QUESTION_MESSAGE);
            int green = Integer.parseInt(greenColourToAdd);
            String blueColourToAdd = JOptionPane.showInputDialog(null,
                    "Blue Value?,"
                            + "Blue Value",
                    JOptionPane.QUESTION_MESSAGE);
            int blue = Integer.parseInt(blueColourToAdd);
            addColour(red, green, blue);
            System.out.println("Adding colour");
        }
    }

//    public class GetColourAction extends AbstractAction {
//        GetColourAction() {
//            super("Get Colour");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            String colourIndex = JOptionPane.showInputDialog(null,
//                    "What is the index of the colour you want to get??,"
//                            + "Colour Index",
//                    JOptionPane.QUESTION_MESSAGE);
//            int index = Integer.parseInt(colourIndex);
//            try {
//                getColour(index);
//            } catch (ColourIndexException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    //EFFECTS: Edits a specific colour in the palette
    public class EditColourAction extends AbstractAction {
        EditColourAction() {
            super("Edit Colour");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String colourIndex = JOptionPane.showInputDialog(null,
                    "What is the index of the colour you want to edit?,"
                            + "Colour Index",
                    JOptionPane.QUESTION_MESSAGE);
            int index = Integer.parseInt(colourIndex);
            try {
                editColour(index);
            } catch (ColourIndexException e) {
                JOptionPane.showMessageDialog(null, "There is no colour here", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //EFFECTS: Adds the complementary colour of a colour in the palette to the palette
    public static class ComplementaryColourAction extends AbstractAction {
        ComplementaryColourAction() {
            super("Add Complementary Colour");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String colourIndex = JOptionPane.showInputDialog(null,
                    "What is the index of the colour you want to edit?,"
                            + "Colour Index",
                    JOptionPane.QUESTION_MESSAGE);
            int index = Integer.parseInt(colourIndex);
            try {
                Colour complement = palette.getColour(index).getComplementary();
                palette.addColour(complement);
            } catch (ColourIndexException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }

        }
    }

    //EFFECTS: Adds the monochromatic colours of a colour in the palette to the palette
    public static class MonochromaticColourAction extends AbstractAction {
        MonochromaticColourAction() {
            super("Get Monochromatic Colours");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String colourIndex = JOptionPane.showInputDialog(null,
                    "What is the index of the colour you want to edit?,"
                            + "Colour Index",
                    JOptionPane.QUESTION_MESSAGE);
            int index = Integer.parseInt(colourIndex);
            try {
                List<Colour> mono = palette.getColour(index).getMonochromaticColours();
                for (Colour colour : mono) {
                    palette.addColour(colour);
                }
            } catch (ColourIndexException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }
        }
    }

    //EFFECTS: Removes a colour from the palette
    public class RemoveColourAction extends AbstractAction {
        RemoveColourAction() {
            super("Remove Colour");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String colourIndex = JOptionPane.showInputDialog(null,
                    "What is the index of the colour you want to remove?,"
                            + "Removal Index",
                    JOptionPane.QUESTION_MESSAGE);
            int index = Integer.parseInt(colourIndex);
            try {
                removeColour(index);
            } catch (ColourIndexException e) {
                JOptionPane.showMessageDialog(null, "There is no colour here", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //EFFECTS: Saves the palette to a JSON file
    public class SavePaletteAction extends AbstractAction {
        SavePaletteAction() {
            super("Save Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            savePalette();
        }
    }

    //EFFECTS: Loads a palette from a JSON file
    public class LoadPaletteAction extends AbstractAction {
        LoadPaletteAction() {
            super("Load Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadPalette();
        }
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
//    private  void handleOptions() {
//        int option = scanner.nextInt();
//        switch (option) {
//            case 1:
//                viewColours();
//                break;
//            case 2:
//                addColour();
//                break;
//            case 3:
//                try {
//                    getColour();
//                } catch (ColourIndexException e) {
//                    System.out.println("No colour here");
//                }
//                break;
//            case 4:
//                try {
//                    editColour();
//                } catch (ColourIndexException e) {
//                    System.out.println("No colour here");
//                }
//                break;
//            case 5:
//                try {
//                    getComplementaryPalette();
//                } catch (ColourIndexException e) {
//                    System.out.println("No colour here");
//                }
//                break;
//            case 6:
//                try {
//                    getMonochromaticPalette();
//                } catch (ColourIndexException e) {
//                    System.out.println("No colour here");
//                }
//                break;
//            case 7:
//                try {
//                    removeColour();
//                } catch (ColourIndexException e) {
//                    System.out.println("No colour here");
//                }
//                break;
//            case 8:
//                savePalette();
//                break;
//            case 9:
//                loadPalette();
//                break;
//            case 10:
//                running = false;
//                break;
//            default:
//                System.out.println("Invalid option");
//                break;
//        }
//    }

//    private  void printOptions() {
//        System.out.println("--- Colour Management System ---");
//        System.out.println("1. View palette");
//        System.out.println("2. Add a colour");
//        System.out.println("3. Get a colour");
//        System.out.println("4. Edit a colour");
//        System.out.println("5. Get complementary colour");
//        System.out.println("6. Get monochromatic palette");
//        System.out.println("7. Remove a colour");
//        System.out.println("8. Save palette to file");
//        System.out.println("9. Load palette from file");
//        System.out.println("10. Exit");
//        System.out.print("Enter an option: ");
//    }

    // EFFECTS: saves the palette to file
    public void savePalette() {
        try {
            jsonWriter.open();
            jsonWriter.write(palette);
            jsonWriter.close();
            System.out.println("Saved palette to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads palette from file
    public void loadPalette() {
        try {
            palette = jsonReader.read();
            System.out.println("Loaded palette from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStore);
        }
    }

    //EFFECTS: Prints out all the colors currently added in palette
    public void viewColours() {

        for (int i = 0; i < palette.size(); i++) {
            System.out.println("Colour " + (i + 1) + ": " + palette.colours.get(i).getRed() + ","
                    + palette.colours.get(i).getGreen() + ","
                    + palette.colours.get(i).getBlue());
        }
    }


    //EFFECTS: Adds a colour to palette
    public void addColour(int r, int g, int b) {

        Colour color = new Colour(r, g, b);
        palette.addColour(color);

        System.out.println("Colour added successfully");
    }


    //EFFECTS: Edits a specific colour in palette at an index
    public void editColour(int index) throws ColourIndexException {

        Colour colour = palette.getColour(index);
        if (colour != null) {
            String redColourToAdd = JOptionPane.showInputDialog(null,
                    "Red Value?,"
                            + "Red Value",
                    JOptionPane.QUESTION_MESSAGE);
            int red = Integer.parseInt(redColourToAdd);
            String greenColourToAdd = JOptionPane.showInputDialog(null,
                    "Green Value?,"
                            + "Green Value",
                    JOptionPane.QUESTION_MESSAGE);
            int green = Integer.parseInt(greenColourToAdd);
            String blueColourToAdd = JOptionPane.showInputDialog(null,
                    "Blue Value?,"
                            + "Blue Value",
                    JOptionPane.QUESTION_MESSAGE);
            int blue = Integer.parseInt(blueColourToAdd);

            palette.editColour(index, red, green, blue);

            System.out.println("Colour edited successfully");
        } else {
            System.out.println("Colour not found");
        }
    }

    //EFFECTS: Gets a complementary colour of a colour in palette at an index
//    public void getComplementaryPalette() throws ColourIndexException {
//        System.out.print("Enter index: ");
//        int index = scanner.nextInt();
//
//
//        Colour colour = palette.getColour(index);
//        if (colour != null) {
//            Colour complementary = colour.getComplementary();
//            System.out.println("Complementary Colour: " + complementary.getRed() + ", " + complementary.getGreen()
//                    + ", " + complementary.getBlue());
//
//        } else {
//            System.out.println("Colour not found");
//        }


//    }



    //EFFECTS: Gets monochromatic colours of a colour in palette at an index
//    public void getMonochromaticPalette() throws ColourIndexException {
//        System.out.print("Enter index: ");
//        int index = scanner.nextInt();
//
//        Colour colour = palette.getColour(index);
//        if (colour != null) {
//            List<Colour> monochromaticColours = colour.getMonochromaticColours();
//            for (Colour colour1 : monochromaticColours) {
//                System.out.println("Colour: " + colour1.getRed() + "," + colour1.getGreen() + "," + colour1.getRed());
//            }
//            System.out.println("Monochromatic Palette: " + monochromaticColours);
//        } else {
//            System.out.println("Colour not found");
//        }
//
//    }

    //EFFECTS: Removes a specific colour in palette at an index
    public void removeColour(int index) throws ColourIndexException {

        Colour color = palette.getColour(index);
        palette.removeColour(color);

        System.out.println("Colour removed successfully");
    }
}
