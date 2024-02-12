package fr.epita.assistants.tetris.interfaces;

import fr.epita.assistants.tetris.game.Block;

import java.awt.*;
import java.util.List;

public interface GameInterface {

    Block[][] getGridToPrint();

    Integer getSizeX();
    Integer getSizeY();

    Boolean moveDownFalling();

    void moveLeftFalling();
    void moveRightFalling();

    void rotateFalling(int rotation);

    boolean generateFallingPiece(Character type, int initialPosX, int initialPosY);

    boolean collides(List<Point> newPos);
}
