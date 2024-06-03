import java.util.Scanner;

public class HumanPlayer implements Player {
    private String name;
    private char symbol;

    public HumanPlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println(name + "'s turn. Enter row and column (0, 1, or 2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (board.makeMove(row, col, symbol)) {
                break;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }
}
