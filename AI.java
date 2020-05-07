/**
 * the game AI uses Minimax to find the best move.
 * @author Parker Qi
 * @author Parker.qi@hotmail.com
 * @version 1.2.0
 * 
 */
public class AI extends Player{
    private int call = 0;
    private int depthReached = 999;
    private char mySymbol;
    private char enemySymbol;
    private int winCond;

    /**
     * Constructor for the AI
     * @param type if AI is playing X, type = true; if playing O, type = false
     */
    public AI(boolean type, int winCond) {
        super(type);
        if (type) {
            mySymbol = 'X';
            enemySymbol = 'O';
        } else {
            mySymbol = 'O';
            enemySymbol = 'X';
        }
        this.winCond = winCond;
    }
    
    /**
     * AI place piece on the board
     * @param board the charactor 2d array representing the board
     */
    public void placePiece(char[][] board) {
        int[] a = bestMove(board);
        System.out.println("minimax called: " + call);
        System.out.println("depth remaining: " + depthReached);
        call = 0;
        depthReached = 999;
        try {
            Board.placePiece(a[1],a[0], super.type);
        } catch (Exception e) {
            System.out.println("Unexpected Error!");
        }
    }

    /**
     * this method is a helper method for placePiece(Board)
     * It finds all possible locations for AI to place a piece
     * and uses minimax algorithm to evaluate the all locations 
     * @param board the charactor 2d array representing the board
     * @return the location for the best move
     */
    private int[] bestMove(char[][] board) {
        int bestScore = -999;
        //if board is empty, place piece in the middle 
        int[] bestMove = new int[]{(board.length)/2, (board[0].length)/2};
        if (ifEmptyAndComplex(board)) {
            return bestMove;
        }
        //AI look through all available positions on board and ask miniMax's evaluation for each position
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    //if a location can result an immidiate win, place it there
                    board[i][j] = mySymbol;
                    if (Board.checkWin() == mySymbol) {
                        board[i][j] = 0;
                        bestMove = new int[]{i, j};
                        System.out.println("immidiate win found");
                        return bestMove;
                    }
                    int score = miniMax(board, 10, -999, 999, false);
                    board[i][j] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     * This is the minimax algorithm
     * @param position the situation to evaluate
     * @param depth how many turns minimax should emulate
     * @param alpha the max value of a local branch
     * @param beta the min value of a local branch
     * @param maximizingPlayer if the current position is to find best move or worst move
     * @return the evaluation for current position
     */
    private int miniMax(char[][] position, int depth, int alpha, int beta, boolean maximizingPlayer) {
        call++;
        if (depth < depthReached) {
            depthReached = depth;
        }

        //if game over at position or depth is 0 return current position evaluation
        if (Board.ifTie() || depth == 0) {
            return evaluateBoard();
        }

        //evaluating maximizing player
        if (maximizingPlayer) {
            int maxEvaluation = -999;
            //for all children of node
            outer: 
            for (int i = 0; i < position.length; i++) {
                for (int j = 0; j < position[0].length; j++) {
                    if (position[i][j] == 0) {
                        position[i][j] = mySymbol;
                        int evaluation = miniMax(position, depth - 1, alpha, beta, false);
                        position[i][j] = 0;
                        maxEvaluation = Math.max(evaluation, maxEvaluation);
                        alpha = Math.max(alpha, maxEvaluation);
                        if (beta <= alpha) {
                            break outer;
                        }
                    }
                }
            }
            return maxEvaluation;
        } else {
            //evaluating minimizing player
            int minEvaluation = 999;
            //for all possiable moves
            outer: 
            for (int i = 0; i < position.length; i++) {
                for (int j = 0; j < position[0].length; j++) {
                    if (position[i][j] == 0) {
                        position[i][j] = enemySymbol;
                        int evaluation = miniMax(position, depth - 1, alpha, beta, true);
                        position[i][j] = 0;
                        minEvaluation = Math.min(minEvaluation, evaluation);
                        beta = Math.min(beta, minEvaluation);
                        if (beta <= alpha) {
                            break outer;
                        }
                    }
                }
            }
            return minEvaluation;
        }
    }

    private boolean ifEmptyAndComplex(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    return false;
                }
            }
        }
        if (board.length > 4) {
            return true;
        }
        return false;
    }

    private int evaluateBoard() {
        //evaluate according to winner
        char c = Board.checkWin();
        if (c == mySymbol) {
            return 100;
        } if (c == enemySymbol) {
            return -100;
        }
        return 0;
        /*
        // if game not ended, evaluate according to open end connects
        int eval = evaluationBoardH(board) + evaluationBoardV(board) + evaluationBoardD(board);
        return eval;
        */
    }
/*
    private int evaluationBoardH(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    return false;
                }
            }
        }
    }

    private int evaluationBoardV(char[][] board) {
        
    }

    private int evaluationBoardD(char[][] board) {
        
    }
    */
}