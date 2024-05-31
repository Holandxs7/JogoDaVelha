import javax.swing.JButton;
import java.util.Random;

public class AIPlayer extends Player {
    private Random random;

    public AIPlayer(char symbol) {
        super(symbol);
        random = new Random();
    }

    public void makeMove(JButton[][]buttons) {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!buttons[row][col].getText().equals(""));
        buttons[row][col].setText(String.valueOf(symbol));
    }
}
