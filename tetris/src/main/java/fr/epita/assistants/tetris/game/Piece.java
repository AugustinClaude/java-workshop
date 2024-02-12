package fr.epita.assistants.tetris.game;

import fr.epita.assistants.tetris.interfaces.PieceInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece implements PieceInterface {
    private final Character pieceType;
    private final List<Block> blocks;

    public Piece(Character pieceType, int posX, int posY) {
        this.pieceType = pieceType;
        blocks = new ArrayList<>();

        Block b1 = new Block(new Point(0, 1));
        Block b2 = new Block(new Point(1, 1));
        Block b3 = new Block(new Point(2, 1));
        Block b4 = new Block(new Point(3, 1));
        Block b5 = new Block(new Point(0, 0));
        Block b6 = new Block(new Point(2, 0));
        Block b7 = new Block(new Point(1, 0));
        if (pieceType == 'I') {
            List<Block> newBlocks = List.of(b1, b2, b3, b4);
            for (Block block : newBlocks)
                block.setColor(Color.CYAN);
            blocks.addAll(newBlocks);
        }
        else if (pieceType == 'J') {
            List<Block> newBlocks = List.of(b5, b1, b2, b3);
            for (Block block : newBlocks)
                block.setColor(Color.BLUE);
            blocks.addAll(newBlocks);
        }
        else if (pieceType == 'L') {
            List<Block> newBlocks = List.of(b1, b2, b3, b6);
            for (Block block : newBlocks)
                block.setColor(Color.ORANGE);
            blocks.addAll(newBlocks);
        }
        else if (pieceType == 'O') {
            List<Block> newBlocks = List.of(b7, b6, b2, b3);
            for (Block block : newBlocks)
                block.setColor(Color.YELLOW);
            blocks.addAll(newBlocks);
        }
        else if (pieceType == 'S') {
            List<Block> newBlocks = List.of(b7, b6, b1, b2);
            for (Block block : newBlocks)
                block.setColor(Color.GREEN);
            blocks.addAll(newBlocks);
        }
        else if (pieceType == 'T') {
            List<Block> newBlocks = List.of(b7, b1, b2, b3);
            for (Block block : newBlocks)
                block.setColor(Color.MAGENTA);
            blocks.addAll(newBlocks);
        }
        else if (pieceType == 'Z') {
            List<Block> newBlocks = List.of(b5, b7, b2, b3);
            for (Block block : newBlocks)
                block.setColor(Color.RED);
            blocks.addAll(newBlocks);
        }

        for (Block block : blocks)
            block.performTranslation(posX, posY);
    }

    @Override
    public Character getPieceType() {
        return pieceType;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void performTranslation(int dx, int dy) {
        for (Block block : blocks)
            block.performTranslation(dx, dy);
    }

    public static Character getRandomPiece(Random rand) {
        int rdm = rand.nextInt(7);
        List<Character> characters = new ArrayList<>(List.of('I', 'J', 'L', 'O', 'S', 'T', 'Z'));
        return characters.get(rdm);
    }
}
