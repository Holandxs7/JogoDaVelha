public class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    public void makeMove(TicTacToe game, int row, int col) throws Exception {
        game.play(row, col);
    }
}
