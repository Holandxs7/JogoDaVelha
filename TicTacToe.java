import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private Board board;
    private JButton[][] buttons;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToeGUI() {
        board = new Board();
        buttons = new JButton[3][3];
        initializePlayers();
        initializeUI();
    }

    private void initializePlayers() {
        player1 = new HumanPlayer("Player 1", 'X');
        player2 = new AIPlayer("IA", 'O');
        currentPlayer = player1;
    }

    private void initializeUI() {
        setTitle("Tic Tac Toe");
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

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == buttonClicked) {
                    if (board.makeMove(i, j, currentPlayer.getSymbol())) {
                        buttonClicked.setText(String.valueOf(currentPlayer.getSymbol()));
                        if (board.checkWin(currentPlayer.getSymbol())) {
                            JOptionPane.showMessageDialog(this, currentPlayer.getName() + " Ganhou!");
                            resetBoard();
                        } else if (board.isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "Jogo Empatou!");
                            resetBoard();
                        } else {
                            switchPlayer();
                            if (currentPlayer instanceof AIPlayer) {
                                currentPlayer.makeMove(board);
                                updateBoard();
                                if (board.checkWin(currentPlayer.getSymbol())) {
                                    JOptionPane.showMessageDialog(this, currentPlayer.getName() + " Ganhou!");
                                    resetBoard();
                                } else if (board.isBoardFull()) {
                                    JOptionPane.showMessageDialog(this, "Jogo Empatou!");
                                    resetBoard();
                                } else {
                                    switchPlayer();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateBoard() {
        char[][] boardArray = board.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(boardArray[i][j]));
            }
        }
    }

    private void resetBoard() {
        board.initializeBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}