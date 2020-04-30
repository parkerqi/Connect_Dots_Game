import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the runner class for playing GoMoKu with AI
 * @author Parker Qi
 * @author parker.qi@hotmail.com
 * @version 1.0.0
 */
public class AIFirst {
    private static Scanner sc = new Scanner(System.in);
    /**
     * The runner method for the game
     * @param arg accepts two integers, the size of board and the connects to be made to win the game
     */
    public static void main(String[] arg) {
        //Board board = new Board(4, 3);
        Board board = new Board(Integer.parseInt(arg[0]) , Integer.parseInt(arg[1]));
        boolean gameOver = false;
        while (!gameOver) {
            playPvC(board);
            gameOver = true;
        }
        sc.close();
    }

    /**
     * play PvC with this method, the AI always goes first
     * @param board the board to place PvC on
     */
    private static void playPvC(Board board) {
        AI ai = new AI(true);
        Player player = new Player(false);
        char gameStatus = 'E';
        while (gameStatus == 'E') {
            int x = -1;
            int y = -1;
            boolean ifNextTurn = false;
            
            //AI's turn
            ai.placePiece(board);
            System.out.println("AI's turn.");
            board.printBoard();

            //check game status
            gameStatus = board.checkWin();
            if (gameStatus == 'X') {
                System.out.println("AI wins.");
            } else if (gameStatus == 'T') {
                System.out.println("TIE!");
            } else {
                do {
                    try {
                        //player's turn
                        System.out.println("Your turn.");
                        System.out.println("Type your piece's x axis.");
                        x = sc.nextInt() - 1;
                        System.out.println("Type your piece's y axis.");
                        y = sc.nextInt() - 1;
                        if (!player.place(x, y, board)) {
                            System.out.println("Your coordinate is invalid.");
                            ifNextTurn = false;
                        } else {
                            ifNextTurn = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Your coordinate is invalid.");
                        sc.next();
                        ifNextTurn = false;
                    }
                } while (!ifNextTurn);
                board.printBoard();

                //check game status
                gameStatus = board.checkWin();
                if (gameStatus == 'O') {
                    System.out.println("You win.");
                } else if (gameStatus == 'T') {
                    System.out.println("TIE!");
                }
            }
        }
    }
}

