import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        board = new Board();
        initializePlayers();
    }

    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter name for Player 1: ");
        String player1Name = scanner.nextLine();
        player1 = new HumanPlayer(player1Name, 'X');

        System.out.print("Enter name for Player 2 (leave blank for AI): ");
        String player2Name = scanner.nextLine();
        if (player2Name.isEmpty()) {
            player2 = new AIPlayer("AI", 'O');
        } else {
            player2 = new HumanPlayer(player2Name, 'O');
        }

        currentPlayer = player1;
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public void play() {
        boolean gameWon = false;
        while (!board.isBoardFull()) {
            board.printBoard();
            currentPlayer.makeMove(board);
            if (board.checkWin(currentPlayer.getSymbol())) {
                gameWon = true;
                break;
            }
            switchPlayer();
        }

        board.printBoard();

        if (gameWon) {
            System.out.println(currentPlayer.getName() + " wins!");
        } else {
            System.out.println("The game is a tie!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
