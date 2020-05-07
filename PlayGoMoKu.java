import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the runner class for playing GoMoKu between two players
 * @author Parker Qi
 * @author parker.qi@hotmail.com
 * @version 1.1.0
 * 
 */
public class PlayGoMoKu {
    private static Scanner sc = new Scanner(System.in);
    /**
     * The runner method for the game
     * @param arg accepts two integers, the size of board and the connects to be made to win the game
     */
    public static void main(String[] arg) {
        //Board board = new Board(4, 3);
        Board board = new Board(Integer.parseInt(arg[0]) , Integer.parseInt(arg[1]));
        System.out.println("Select mode: PvP => p, PvC => c");
        boolean gameOver = false;
        while (!gameOver) {
            try {
                String c = sc.next();
                if (c.equals("p")) {
                    playPvP(board);
                    gameOver = true;
                } else if (c.equals("c")) {
                    playPvC(board);
                    gameOver = true;
                } else {
                    System.out.println("Select mode again");
                }
            } catch(InputMismatchException e) {
                System.out.println("Select mode again");
            }
        }
        sc.close();
    }

    private static void playPvP(Board board) {
        HumanPlayer player1 = new HumanPlayer(true);
        HumanPlayer player2 = new HumanPlayer(false);
        char gameStatus = 'E';
        Board.printBoard();
        while (gameStatus == 'E') {
            int x = -1;
            int y = -1;
            boolean ifNextTurn = false;
            do {
                try {
                    //player 1's turn
                    System.out.println("Player 1's turn.");
                    System.out.println("Type your piece's x axis.");
                    x = sc.nextInt();
                    System.out.println("Type your piece's y axis.");
                    y = sc.nextInt();
                    if (!player1.place(x, y)) {
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
            Board.printBoard();

            //check game status
            gameStatus = Board.checkWin();
            if (gameStatus == 'X') {
                System.out.println("Player 1 wins.");
            } else if (gameStatus == 'T') {
                System.out.println("Tie.");
            } else {
                do {
                    try {
                        //player 2's turn
                        System.out.println("Player 2's turn.");
                        System.out.println("Type your piece's x axis.");
                        x = sc.nextInt();
                        System.out.println("Type your piece's y axis.");
                        y = sc.nextInt();
                        if (!player2.place(x, y)) {
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
                Board.printBoard();

                //check game status
                gameStatus = Board.checkWin();
                if (gameStatus == 'O') {
                    System.out.println("Player 2 wins.");
                } else if (gameStatus == 'T') {
                    System.out.println("TIE!");
                }
            }
        }
    }

    /**
     * play PvC with this method, the human always goes first
     * @param board the board to place PvC on
     */
    private static void playPvC(Board board) {
        AI ai = new AI(false, board.getWinCondition());
        HumanPlayer player = new HumanPlayer(true);
        char gameStatus = 'E';
        Board.printBoard();
        while (gameStatus == 'E') {
            int x = -1;
            int y = -1;
            boolean ifNextTurn = false;
            do {
                try {
                    //player's turn
                    System.out.println("Your turn.");
                    System.out.println("Type your piece's x axis.");
                    x = sc.nextInt();
                    System.out.println("Type your piece's y axis.");
                    y = sc.nextInt();
                    if (!player.place(x, y)) {
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
            Board.printBoard();

            //check game status
            gameStatus = Board.checkWin();
            if (gameStatus == 'X') {
                System.out.println("You win.");
            } else if (gameStatus == 'T') {
                System.out.println("TIE!");
            } else {

                //AI's turn
                ai.placePiece(Board.getStatus());
                System.out.println("AI's turn.");
                Board.printBoard();

                //check game status
                gameStatus = Board.checkWin();
                if (gameStatus == 'O') {
                    System.out.println("AI wins.");
                } else if (gameStatus == 'T') {
                    System.out.println("TIE!");
                }
            }
        }
    }
}