import java.util.Random;

public class AIPlayer implements Player {
    private String name;
    private char symbol;
    private Random random;

    public AIPlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void makeMove(Board board) {
        int row, col;

        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (board.makeMove(row, col, symbol)) {
                break;
            }
        }
    }
}
