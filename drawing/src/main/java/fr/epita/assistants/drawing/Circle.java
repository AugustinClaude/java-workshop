package fr.epita.assistants.drawing;

import static java.lang.Math.abs;

public class Circle extends Entity {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                int sqDist = abs(radius * radius - (i * i + j * j));
                if (sqDist < radius)
                    System.out.print("#");
                else
                    System.out.print(" ");

                if (j < radius)
                    System.out.print(" ");
            }

            if (i < radius)
                System.out.print("\n");
        }
    }
}
