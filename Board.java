public class Board {
    private int size;
    private Piece[][] board;
    private int winCondition;
    private int ignoreL;
    private int ignoreR;

    public Board(int size, int winCondition) {
        this.size = size;
        this.winCondition = winCondition;
        board = new Piece[size][size];
        ignoreL = winCondition - 1;
        ignoreR = board[0].length- ignoreL;
    }

    public Board(Board b) {
        this.size = b.getBoardSize();
        this.winCondition = b.getWinCondition();
        Piece[][] sta = b.getStatus();
        board = new Piece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (sta[i][j] != null) {
                    this.board[i][j] = sta[i][j];                   
                }

            }
        }
        ignoreL = winCondition - 1;
        ignoreR = board[0].length- ignoreL;
    }

    public Board() {
        this.size = 15;
        this.winCondition = 5;
        board = new Piece[this.size][this.size];
    }

    public void placePiece(Piece p) throws OverridingLocationException{
        int[] a = p.getLocation();
        if (board[a[1]][a[0]] != null) {
            throw new OverridingLocationException("");
        } else {
            board[a[1]][a[0]] = p;
        }
    }
/*
    private void removePiece(Piece p) {
        int[] a = p.getLocation();
        board[a[1]][a[0]] = null;
    }
*/
    /**
     * check vertical winner
     * @return X if player1 won, O if player2 won, E if no player won, T if tie
     */
    public char checkWin() {
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

    private char checkV() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (board[j][i].getType()) {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (!board[j][i].getType()){
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

    private char checkH() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                       if (board[i][j].getType()) {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (!board[i][j].getType()){
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

    private char checkCross() {
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
    private char checkUpperNDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = ignoreR; i >= 0; i--) {
            for (int j = i, k = 0; j < size; j++, k++) {
                try {
                    if (board[k][j].getType()) {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (!board[k][j].getType()) {
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
    private char checkLowerNDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 1; i < ignoreR; i++) {
            for (int j = i, k = 0; j < size; j++, k++) {
                try {
                    if (board[j][k].getType()) {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (!board[j][k].getType()) {
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
    private char checkUpperPDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = ignoreL; i < size; i++) {
            for (int j = i, k = 0; j >= 0; j--, k++) {
                try {
                    if (board[k][j].getType()) {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (!board[k][j].getType()) {
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
    private char checkLowerPDirection() {
        int xStrikes = 0;
        int oStrikes = 0;
        for (int i = 1; i < ignoreR; i++) {
            for (int j = i, k = size - 1; j < size; j++, k--) {
                try {
                    if (board[j][k].getType()) {
                        xStrikes++;
                        if (xStrikes == winCondition) {
                            return 'X';
                        }
                        oStrikes = 0;
                    } else if (!board[j][k].getType()) {
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

    private boolean ifTie() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            printBoundry();
            for (int j = 0; j < size; j++) {
                try {
                    System.out.print("| " + board[i][j].getSymbol() + " ");
                } catch (NullPointerException e) {
                    System.out.print("|   ");
                }
            }
            System.out.print("|\n");
        }
        printBoundry();
    }
    
    private void printBoundry() {
        for (int i = 0; i < size; i++) {
            System.out.print(" ---");
        }
        System.out.println();
    }

    public void clearBoard() {
        board = new Piece[15][15];
    }

    public Piece[][] getStatus() {
        return board;
    }

    public int getBoardSize() {
        return size;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public boolean isAvailable(int x, int y) {
        if (board[y][x] == null) {
            return true;
        }
        return false;
    }
}