package pl.edu.uj.tcs.matematycy2013;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Scrabble {

    public static void startGame(final Config config) throws FileNotFoundException, IOException {
        Game game = new Game(config);
        final GUI gui = new GUI( game);
        game.setGUI(gui);
        final Cell[][] c = gui.game.getBoard().getBoard();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int size = c.length;
                if (!config.getBoardTorus()) {
                    size -= 2;
                }
                gui.createGUI(size, c, config.getPlayer1(), config.getPlayer2());
            }
        });
    }

    public static void main(String[] args) throws IOException {
        final Config config = new Config();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lobby(config).setVisible(true);
            }
        });

    }
}
