import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class StartScreen extends JFrame implements ActionListener {
    private JButton humanoVsHumanoButton;
    private JButton humanoVsIAButton;

    public StartScreen() {
        setTitle("Jogo Da Velha");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BackgroundPanel background = new BackgroundPanel("/JogoDaVelha10.jpg");
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background, BorderLayout.CENTER);

        humanoVsHumanoButton = new JButton("Humano vs Humano");
        humanoVsIAButton = new JButton("Humano vs IA");

        humanoVsHumanoButton.addActionListener(this);
        humanoVsIAButton.addActionListener(this);

        background.add(Box.createVerticalGlue());
        background.add(humanoVsHumanoButton);
        background.add(humanoVsIAButton);
        background.add(Box.createVerticalGlue());

        humanoVsHumanoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        humanoVsIAButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == humanoVsHumanoButton) {
            new TicTacToeGUI(new HumanPlayer("Player 1", 'X'), new HumanPlayer("Player 2", 'O'));
        } else if (e.getSource() == humanoVsIAButton) {
            new TicTacToeGUI(new HumanPlayer("Player 1", 'X'), new AIPlayer("AI", 'O'));
        }
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartScreen());
    }
}
