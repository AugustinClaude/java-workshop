package fr.epita.assistants.tetris.game;

import fr.epita.assistants.tetris.interfaces.BlockInterface;

import java.awt.*;

public class Block implements BlockInterface {
    private Color color;
    private Point position;

    public Block(Point position) {
        this.position = position;
    }

    public Block(Block block) {
        this.color = block.color;
        this.position = block.position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void performTranslation(int dx, int dy) {
        position.x += dx;
        position.y += dy;
    }

    @Override
    public Point computeTranslation(Point translation) {
        return new Point(position.x + translation.x, position.y + translation.y);
    }
}
