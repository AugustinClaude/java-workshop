package fr.epita.assistants.forkjoin;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Double> {
    final private double[][] matrix;
    final private int xLowerBound;
    final private int xUpperBound;
    final private int yLowerBound;
    final private int yUpperBound;

    public MyRecursiveTask(double[][] matrix, int xLowerBound, int xUpperBound, int yLowerBound, int yUpperBound) {
        this.matrix = matrix;
        this.xLowerBound = xLowerBound;
        this.xUpperBound = xUpperBound;
        this.yLowerBound = yLowerBound;
        this.yUpperBound = yUpperBound;
    }

    @Override
    protected Double compute() {
        int xLength = xUpperBound - xLowerBound;
        int yLength = yUpperBound - yLowerBound;
        if (xLength == 0 || yLength == 0)
            return 0.0;

        if (xLength <= 5 && yLength <= 5) {
            double avg = 0.0;
            for (int i = xLowerBound; i < xUpperBound; i++) {
                for (int j = yLowerBound; j < yUpperBound; j++) {
                    avg += matrix[j][i];
                }
            }

            return avg / (xLength * yLength);
        }

        double avg = 0.0;

        MyRecursiveTask r1 = new MyRecursiveTask(matrix, xLowerBound, xLowerBound + xLength / 2, yLowerBound, yLowerBound + yLength / 2);
        r1.fork();
        MyRecursiveTask r2 = new MyRecursiveTask(matrix, xLowerBound + xLength / 2, xUpperBound, yLowerBound, yLowerBound + yLength / 2);
        r2.fork();
        MyRecursiveTask r3 = new MyRecursiveTask(matrix, xLowerBound, xLowerBound + xLength / 2, yLowerBound + yLength / 2, yUpperBound);
        r3.fork();
        MyRecursiveTask r4 = new MyRecursiveTask(matrix, xLowerBound + xLength / 2, xUpperBound, yLowerBound + yLength / 2, yUpperBound);
        r4.fork();

        avg += r1.join();
        avg += r2.join();
        avg += r3.join();
        avg += r4.join();
        return avg / 4;
    }
}
