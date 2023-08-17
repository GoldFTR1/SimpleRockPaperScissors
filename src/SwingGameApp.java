import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SwingGameApp {
    private JFrame frame;
    private JComboBox<String> gameTypeComboBox;
    private JTextArea resultsTextArea;

    public SwingGameApp() {
        frame = new JFrame("SimpleRockPaperScissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        String[] gameTypeOptions = {"Single Game", "Best of Three"};
        gameTypeComboBox = new JComboBox<>(gameTypeOptions);

        JButton playButton = new JButton("Play Game");
        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setEditable(false);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game ThisGame = new Game();
                ThisGame.setGameType(Game.Begin());

                if (ThisGame.getGameType() == Game.GameType.SINGLEGAME) {
                    Game.PlaySingleGame(ThisGame);
                } else {
                    ThisGame.setId(Game.GenerateId("games.ser"));
                    Game.BestOfThree(ThisGame);
                    System.out.println("Game id is" + ThisGame.getId());
                    Game.WriteGameToFile("games.ser", ThisGame);
                }

                // Display game results in the text area
                resultsTextArea.setText(ThisGame.toString());
            }
        });

        frame.add(new JLabel("Select Game Type:"));
        frame.add(gameTypeComboBox);
        frame.add(playButton);
        frame.add(resultsTextArea);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingGameApp());
    }
}