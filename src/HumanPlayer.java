import java.util.Scanner;

public class HumanPlayer implements Player {
    private String name;
    private char symbol;

    public HumanPlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println(name + " sua vez. Dê enter nos seguintes números para colocar na coluna(0, 1, ou 2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (board.makeMove(row, col, symbol)) {
                break;
            } else {
                System.out.println("Seu movimento é inválido. Tente Novamente.");
            }
        }
    }
}
