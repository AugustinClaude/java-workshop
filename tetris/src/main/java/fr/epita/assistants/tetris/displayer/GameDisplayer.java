package fr.epita.assistants.tetris.displayer;

import fr.epita.assistants.tetris.game.Block;
import fr.epita.assistants.tetris.game.Game;
import fr.epita.assistants.tetris.game.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class GameDisplayer extends JPanel {

    private Game game;

    private static Random rand = new Random();

    public GameDisplayer(Game game) {
        this.game = game;
        this.game.generateFallingPiece(Piece.getRandomPiece(rand), (this.game.getSizeX() / 2) - 1, 0);
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(16 * game.getSizeX(), 16 * game.getSizeY()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (game != null) {
            Arrays.stream(game.getGridToPrint())
                  .forEach(blocks -> Arrays.stream(blocks)
                  .filter(block -> block != null)
                  .forEach(block -> drawBlock(g, block)));
        }
    }

    public void drawBlock(Graphics g, Block block) {
        g.setColor(block.getColor());
        g.fillRect(16*(int)block.getPosition().getX(), 16*(int)block.getPosition().getY(), 15, 15);
    }

    public boolean fallDown() {
        Boolean res = this.game.moveDownFalling();

        this.repaint();
        if (res)
            res = !this.game.generateFallingPiece(Piece.getRandomPiece(rand),
                              (this.game.getSizeX() / 2) - 1, 0);

        return !res;
    }

    public void moveLeft() {
        this.game.moveLeftFalling();
        this.repaint();
    }

    public void moveRight() {
        this.game.moveRightFalling();
        this.repaint();
    }

    public void rotate(int rotation) {
        this.game.rotateFalling(rotation);
        this.repaint();
    }
}
