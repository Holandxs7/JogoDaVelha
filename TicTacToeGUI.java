import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private Game game;
    private JButton[][] buttons;

    public TicTacToeGUI() {
        Player player1 = new HumanPlayer("Player 1", 'X');
        Player player2 = new AIPlayer("IA", 'O');
        game = new Game(player1, player2);
        buttons = new JButton[3][3];
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Jogo Da Velha");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == buttonClicked) {
                    if (game.makeMove(i, j)) {
                        buttonClicked.setText(String.valueOf(game.getCurrentPlayer().getSymbol()));
                        if (game.checkWin()) {
                            JOptionPane.showMessageDialog(this, game.getCurrentPlayer().getName() + " Ganhou!");
                            resetBoard();
                        } else if (game.isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "O Jogo Empatou!");
                            resetBoard();
                        } else {
                            game.switchPlayer();
                            if (game.getCurrentPlayer() instanceof AIPlayer) {
                                game.getCurrentPlayer().makeMove(game.getBoard());
                                updateBoard();
                                if (game.checkWin()) {
                                    JOptionPane.showMessageDialog(this, game.getCurrentPlayer().getName() + " Ganhou!");
                                    resetBoard();
                                } else if (game.isBoardFull()) {
                                    JOptionPane.showMessageDialog(this, "O jogo Empatou!");
                                    resetBoard();
                                } else {
                                    game.switchPlayer();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateBoard() {
        char[][] boardArray = game.getBoard().getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(boardArray[i][j]));
            }
        }
    }

    private void resetBoard() {
        game.resetGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
