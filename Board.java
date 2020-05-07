/**
 * This is the board for playing connecting games.
 * The size of board and the wining condition can be anything player wants.
 * @author Parker Qi
 * @version 2.0.0
 * 
 */
public class Board {
    private static int size;
    public static char[][] board;
    private static int winCondition;
    private static int ignoreL;
    private static int ignoreR;
    public static int piecePlaced = 0;
    public static int totalPlaces;

    /**
     * The constructor for the board
     * @param size the size of board, the board will always be a square
     * @param winCondition the number of pieces need to be connected to win. (diaginal connect counts)
     */
    public Board(int size, int winCondition) {
        Board.size = size;
        Board.winCondition = winCondition;
        Board.board = new char[size][size];
        Board.ignoreL = winCondition - 1;
        Board.ignoreR = board[0].length- ignoreL;
        Board.totalPlaces = size * size;
    }

    /**
     * Construcor of Board
     * @param b piece 2D array
     * @param winCondition the number of pieces need to be connected to win. (diaginal connect counts) 
     */
    public Board(char[][] b, int winCondition) {
        Board.size = b[0].length;
        Board.winCondition = winCondition;
        Board.board = b;
        Board.ignoreL = winCondition - 1;
        Board.ignoreR = board[0].length- ignoreL;
        Board.totalPlaces = Board.size * Board.size;
    }

    /**
     * default constructor 
     * it will be created as a Gomoku board
     */
    public Board() {
        Board.size = 15;
        Board.winCondition = 5;
        Board.board = new char[Board.size][Board.size];
        Board.totalPlaces = Board.size * Board.size;
    }

    /**
     * To place a piece on the board (should only be used by player object)
     * @param x the x axis location for placing the piece
     * @param y the y axis location for placing the piece
     * @param player the player placing the piece, X is true, O is false
     * @throws OverridingLocationException thrown when the location has been occupied already
     */
    public static void placePiece(int x, int y, Boolean player) throws OverridingLocationException, IndexOutOfBoundsException{
        if (board[y][x] != 0) {
            throw new OverridingLocationException("");
        } else if (player){
            board[y][x] = 'X';
        } else {
            board[y][x] = 'O';
        }
        piecePlaced++;
    }

    /**
     * check vertical winner
     * @return X if player1 won, O if player2 won, E if no player won, T if tie
     */
    public static char checkWin() {
        char h = checkH();
        char v = checkV();
        char c = checkCross();

        if (h == 'X'||v == 'X'||c == 'X') {
            return 'X';
        } if (h == 'O'||v == 'O'||c == 'O') {
            return 'O';
        } else if (ifTie()) {
            return 'T';
        } else {
            return 'E';
        }
    }

    private static char checkV() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                try {
                    if (board[j][i] == 'X') {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (board[j][i] == 'O'){
                        oStrikes++;
                        if (oStrikes == winCondition) {
                            return 'O';
                        }
                        xStrikes = 0;
                    }
                } catch (NullPointerException e) {
                    xStrikes = oStrikes = 0;
                }
            }
            xStrikes = oStrikes = 0;
        }
        return 'E';
    }

    private static char checkH() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                       if (board[i][j] == 'X') {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (board[i][j] == 'O'){
                        oStrikes++;
                        if (oStrikes == winCondition) {
                            return 'O';
                        }
                        xStrikes = 0;
                    }
                } catch (NullPointerException e){
                    xStrikes = oStrikes = 0;
                }
            }
            xStrikes = oStrikes = 0;
        }
        return 'E';
    }

    private static char checkCross() {
        char a = checkUpperNDirection();
        char b = checkLowerNDirection();
        char c = checkUpperPDirection();
        char d = checkLowerPDirection();
        if (a == 'X' || b == 'X'|| c == 'X'|| d == 'X') {
            return 'X';
        } else if (a == 'O' || b == 'O'|| c == 'O'|| d == 'O') {
            return 'O';
        } else {
            return 'E';
        }
    }

    //check upper half \ direction
    private static char checkUpperNDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = ignoreR; i >= 0; i--) {
            for (int j = i, k = 0; j < size; j++, k++) {
                try {
                    if (board[k][j] == 'X') {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (board[k][j] == 'O') {
                        oStrikes++;
                        if (oStrikes == winCondition) {
                            return 'O';
                        }
                        xStrikes = 0;
                    }
                } catch (NullPointerException e) {
                    xStrikes = oStrikes = 0;
                }
            }
            xStrikes = oStrikes = 0;
        }
        return 'E';
    }

    //check lower half \ direction
    private static char checkLowerNDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 1; i < ignoreR; i++) {
            for (int j = i, k = 0; j < size; j++, k++) {
                try {
                    if (board[j][k] == 'X') {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (board[j][k] == 'O') {
                        oStrikes++;
                        if (oStrikes == winCondition) {
                            return 'O';
                        }
                        xStrikes = 0;
                    }
                } catch (NullPointerException e) {
                    xStrikes = oStrikes = 0;
                }
            }
            xStrikes = oStrikes = 0;
        }
        return 'E';
    }
    
    //check upper half / direction
    private static char checkUpperPDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = ignoreL; i < size; i++) {
            for (int j = i, k = 0; j >= 0; j--, k++) {
                try {
                    if (board[k][j] == 'X') {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (board[k][j] == 'O') {
                        oStrikes++;
                        if (oStrikes == winCondition) {
                            return 'O';
                        }
                        xStrikes = 0;
                    }
                } catch (NullPointerException e) {
                    xStrikes = oStrikes = 0;
                }
            }
            xStrikes = oStrikes = 0;
        }
        return 'E';
    }

    //check lower half / direction
    private static char checkLowerPDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 1; i < ignoreR; i++) {
            for (int j = i, k = size - 1; j < size; j++, k--) {
                try {
                    if (board[j][k] == 'X') {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (board[j][k] == 'O') {
                        oStrikes++;
                        if (oStrikes == winCondition) {
                            return 'O';
                        }
                        xStrikes = 0;
                    }
                } catch (NullPointerException e) {
                    xStrikes = oStrikes = 0;
                }
            }
            xStrikes = oStrikes = 0;
        }
        return 'E';
    }

    /**
     * check if the board is filled or not
     * @return if all filled return true, else return false
     */
    public static boolean ifTie() {
        for (int i = 0; i < Board.size; i++) {
            for (int j = 0; j < Board.size; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Print out the board position in the terminal
     */
    public static void printBoard() {
        for (int i = 0; i < size; i++) {
            printBoundry();
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    System.out.print("|   ");
                } else {
                    System.out.print("| " + board[i][j] + " ");
                }

            }
            System.out.print("|\n");
        }
        printBoundry();
    }
    
    private static void printBoundry() {
        for (int i = 0; i < size; i++) {
            System.out.print(" ---");
        }
        System.out.println();
    }

    /**
     * It return the current situation of the board in terms of a 2D array of Piece
     * @return the board position in charactor 2d array
     */
    public static char[][] getStatus() {
        return board;
    }  

    /**
     * return the win condition of the board
     * @return winCondition
     */
    public int getWinCondition() {
        return winCondition;
    }
}