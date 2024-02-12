package fr.epita.assistants.tetris.game;

import fr.epita.assistants.tetris.interfaces.GameInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements GameInterface {
    private final Block[][] grid;
    private final Integer sizeX;
    private final Integer sizeY;
    private Piece fallingPiece;

    public Game(final Integer sizeX, final Integer sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grid = new Block[this.sizeY][this.sizeX];
        fallingPiece = null;
    }

    @Override
    public Block[][] getGridToPrint() {
        Block[][] gridCopy = new Block[sizeY][sizeX];
        for (int i = 0; i < grid.length; i++) {
            Block[] blocks = grid[i];
            System.arraycopy(blocks, 0, gridCopy[i], 0, blocks.length);
        }

        return gridCopy;
    }

    @Override
    public Integer getSizeX() {
        return sizeX;
    }

    @Override
    public Integer getSizeY() {
        return sizeY;
    }

    private void movePiece(Point translation) {
        List<Point> newPoints = new ArrayList<>();
        for (Block block : fallingPiece.getBlocks()) {
            Point translated = block.computeTranslation(translation);
            newPoints.add(translated);
        }

        for (Block block : fallingPiece.getBlocks()) {
            Point blockPoint = block.getPosition();
            grid[blockPoint.y][blockPoint.x] = null;
        }
        if (collides(newPoints)) {
            for (Block block : fallingPiece.getBlocks()) {
                Point blockPoint = block.getPosition();
                grid[blockPoint.y][blockPoint.x] = block;
            }
            return;
        }

        fallingPiece.performTranslation(translation.x, translation.y);
        for (Block block : fallingPiece.getBlocks()) {
            Point blockPoint = block.getPosition();
            grid[blockPoint.y][blockPoint.x] = block;
        }
    }

    private boolean movePieceDown(Point translation) {
        List<Point> newPoints = new ArrayList<>();
        for (Block block : fallingPiece.getBlocks()) {
            Point translated = block.computeTranslation(translation);
            newPoints.add(translated);
        }

        for (Block block : fallingPiece.getBlocks()) {
            Point blockPoint = block.getPosition();
            grid[blockPoint.y][blockPoint.x] = null;
        }
        if (collides(newPoints)) {
            for (Block block : fallingPiece.getBlocks()) {
                Point blockPoint = block.getPosition();
                grid[blockPoint.y][blockPoint.x] = block;
            }
            return true;
        }

        fallingPiece.performTranslation(translation.x, translation.y);
        for (Block block : fallingPiece.getBlocks()) {
            Point blockPoint = block.getPosition();
            grid[blockPoint.y][blockPoint.x] = block;
        }

        return false;
    }

    @Override
    public Boolean moveDownFalling() {
        if (fallingPiece == null)
            return null;

        Point translation = new Point(0, 1);
        if (movePieceDown(translation))
            return true;

        for (int i = 0; i < grid.length; i++) {
            Block[] blocks = grid[i];
            if (!Arrays.stream(blocks).toList().contains(null)) {
                for (int j = 1; j <= i; j++)
                    grid[j] = grid[j - 1];
                grid[0] = new Block[sizeY];
            }
        }

        return false;
    }

    @Override
    public void moveLeftFalling() {
        if (fallingPiece == null)
            return;

        Point translation = new Point(-1, 0);
        movePiece(translation);
    }

    @Override
    public void moveRightFalling() {
        if (fallingPiece == null)
            return;

        Point translation = new Point(1, 0);
        movePiece(translation);
    }

    @Override
    public void rotateFalling(int rotation) {
        if (fallingPiece == null)
            return;

        System.out.println("ah!");
    }

    @Override
    public boolean collides(List<Point> newPos) {
        for (Point point : newPos) {
            if (point.x < 0 || point.x >= sizeX || point.y < 0 || point.y >= sizeY)
                return true;

            if (grid[point.y][point.x] != null)
                return true;
        }

        return false;
    }

    @Override
    public boolean generateFallingPiece(Character type, int initialPosX, int initialPosY) {
        List<Character> characters = new ArrayList<>(List.of('I', 'J', 'L', 'O', 'S', 'T', 'Z'));
        if (!characters.contains(type))
            return false;

        Piece newFallingPiece = new Piece(type, initialPosX, initialPosY);
        List<Point> blockPoints = new ArrayList<>();
        for (Block block : newFallingPiece.getBlocks())
            blockPoints.add(block.getPosition());

        if (collides(blockPoints))
            return false;

        fallingPiece = newFallingPiece;
        for (Block block : fallingPiece.getBlocks()) {
            Point blockPoint = block.getPosition();
            grid[blockPoint.y][blockPoint.x] = block;
        }
        return true;
    }
}
