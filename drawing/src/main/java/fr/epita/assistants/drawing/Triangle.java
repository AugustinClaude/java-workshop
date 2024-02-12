package fr.epita.assistants.drawing;

public class Triangle extends Sharp {
    public Triangle(int length) {
        super(length);
    }

    @Override
    public void draw() {
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == length - 1) {
                for (int j = 0; j < i; j++) {
                    System.out.print("# ");
                }
            }
            else {
                System.out.print("# ");
                for (int j = 1; j < i; j++) {
                    System.out.print("  ");
                }
            }

            System.out.print("#\n");
        }
    }
}
