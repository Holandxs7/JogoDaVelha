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
    }
}
