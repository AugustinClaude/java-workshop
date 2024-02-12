package fr.epita.assistants.tetris.interfaces;

import fr.epita.assistants.tetris.game.Block;

import java.util.List;

public interface PieceInterface {

    Character getPieceType();

    List<Block> getBlocks();

    void performTranslation(int dx, int dy);
}
