package fr.epita.assistants.drawing;

public class Rectangle extends Sharp {
    private int width;

    public Rectangle(int width, int length) {
        super(length);
        this.width = width;
    }

    @Override
    public void draw() {
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == length - 1) {
                for (int j = 0; j < width - 1; j++) {
                    System.out.print("# ");
                }
                System.out.print("#");
            }
            else {
                System.out.print("# ");
                for (int j = 1; j < width - 1; j++) {
                    System.out.print("  ");
                }
                System.out.print("#");
            }

            System.out.print("\n");
        }
    }
}
