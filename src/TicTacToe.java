import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToe() {
        player1 = new HumanPlayer('X');
        player2 = new HumanPlayer('O');
        currentPlayer = player1;

        setTitle("Jogo da Velha");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            currentPlayer.makeMove(TicTacToe.this, row, col);
                            buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
                            if (checkWin()) {
                                JOptionPane.showMessageDialog(null, "Jogador " + currentPlayer.getSymbol() + " ganhou!");
                                resetBoard();
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(null, "Empate!");
                                resetBoard();
                            } else {
                                switchPlayer();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }

    public void play(int row, int col) throws Exception {
        if (!buttons[row][col].getText().equals("-")) {
            throw new IllegalArgumentException("Célula já ocupada!");
        }
        buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer.getSymbol()))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer.getSymbol()))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer.getSymbol()))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer.getSymbol())) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer.getSymbol()))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
