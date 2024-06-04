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
        
        System.out.print("Coloque o Nome do Player 1: ");
        String player1Name = scanner.nextLine();
        player1 = new HumanPlayer(player1Name, 'X');

        System.out.print("Coloque o Nome do Player 2 (Deixe Em Branco Se For Para Jogar Com a IA): ");
        String player2Name = scanner.nextLine();
        if (player2Name.isEmpty()) {
            player2 = new AIPlayer("IA", 'O');
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
            System.out.println(currentPlayer.getName() + " Ganhou!");
        } else {
            System.out.println("Jogo Empatado!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
