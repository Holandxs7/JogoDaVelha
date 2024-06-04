import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
    private JButton humanoVsHumanoButton;
    private JButton humanoVsIAButton;

    public StartScreen() {
        setTitle("Jogo da Velha");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        humanoVsHumanoButton = new JButton("Humano vs Humano");
        humanoVsIAButton = new JButton("Humano vs IA");

        humanoVsHumanoButton.addActionListener(this);
        humanoVsIAButton.addActionListener(this);

        add(humanoVsHumanoButton);
        add(humanoVsIAButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == humanoVsHumanoButton) {
            new TicTacToeGUI(new HumanPlayer("Player 1", 'X'), new HumanPlayer("Player 2", 'O'));
        } else if (e.getSource() == humanoVsIAButton) {
            new TicTacToeGUI(new HumanPlayer("Player 1", 'X'), new AIPlayer("IA", 'O'));
        }
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartScreen());
    }
}
