package fr.epita.assistants.tetris.displayer;

import fr.epita.assistants.tetris.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.locks.ReentrantLock;

public class Displayer {

    private GameDisplayer gameDisplayer;
    private Game game;

    private boolean pause = false;

    public Displayer(Game game){
        this.game = game;
    }

    public void create() {
        JFrame frame = new JFrame();
        ReentrantLock lock = new ReentrantLock();
        Timer timer = new Timer(400, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lock.lock();
                try {
                    if (!gameDisplayer.fallDown())
                        timer.stop();
                }
                finally {
                    lock.unlock();
                }
            }
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent){

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        gameDisplayer.rotate(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        gameDisplayer.rotate(-1);
                        break;
                    case KeyEvent.VK_LEFT:
                        gameDisplayer.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameDisplayer.moveRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        gameDisplayer.fallDown();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        pause = !pause;
                        if (pause)
                            timer.stop();
                        else
                            timer.start();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        timer.setRepeats(true);

        frame.setLocationRelativeTo(null);
        frame.setTitle("Tetris");
        this.gameDisplayer = new GameDisplayer(this.game);
        frame.getContentPane().add(this.gameDisplayer);
        frame.setResizable(false);
        frame.setSize(new Dimension(16 * this.game.getSizeX(), 16 * this.game.getSizeY()));
        frame.pack();

        frame.setVisible(true);
        timer.start();
    }
}
