package fr.epita.assistants.tetris.interfaces;

import java.awt.*;

public interface BlockInterface {

    Color getColor();
    void setColor(Color color);
    
    Point getPosition();
    void setPosition(Point position);
    
    void performTranslation(int dx, int dy);
    Point computeTranslation(Point translation);
}
