package fr.epita.assistants.tetris;

import fr.epita.assistants.tetris.displayer.Displayer;
import fr.epita.assistants.tetris.game.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(16, 30);
        Displayer displayer = new Displayer(game);
        displayer.create();
    }
}
