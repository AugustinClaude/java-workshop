package fr.epita.assistants.staticpen;

public class Pen {
    public enum Color {
        RED,
        BLUE,
    }

    private Color color;
    private static int counter = 0;
    private static int redCounter = 0;
    private static int blueCounter = 0;

    public Pen(final Color color) {
        this.color = color;
        counter++;

        if (color == Color.RED)
            redCounter++;
        else if (color == Color.BLUE)
            blueCounter++;
    }

    public static int getRedPenCounter() {
        return redCounter;
    }

    public static int getPenCounter() {
        return counter;
    }

    public static int getBluePenCounter() {
        return blueCounter;
    }

    public void changeColor(final Color color) {
        if (color == Color.BLUE) {
            blueCounter++;
            if (this.color == Color.RED)
                redCounter--;
        }
        else if (color == Color.RED) {
            redCounter++;
            if (this.color == Color.BLUE)
                blueCounter--;
        }

        this.color = color;
    }

    public static void resetCounts() {
        counter = 0;
        redCounter = 0;
        blueCounter = 0;
    }

    public void print() {
        System.out.println("I'm a " + color.toString() + " pen.");
    }
}
