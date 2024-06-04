public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public boolean makeMove(int row, int col) {
        return board.makeMove(row, col, currentPlayer.getSymbol());
    }

    public boolean checkWin() {
        return board.checkWin(currentPlayer.getSymbol());
    }

    public boolean isBoardFull() {
        return board.isBoardFull();
    }

    public void resetGame() {
        board.initializeBoard();
        currentPlayer = player1;
    }
}
