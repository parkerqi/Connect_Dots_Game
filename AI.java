/**
 * the game AI uses Minimax to find the best move.
 * @author Parker Qi
 * @author Parker.qi@hotmail.com
 * @version 1.0.0
 */
public class AI {
    private final boolean type;

    /**
     * Constructor for the AI
     * @param type if AI is playing X, type = true; if playing O, type = false
     */
    public AI(boolean type) {
        this.type = type;
    }
    
    /**
     * AI place piece on the board
     * @param b the board object to place piece
     */
    public void placePiece(Board b) {
        int[] a = bestMove(b);
        Piece p = new Piece(a[1],a[0], this.type);
        try {
            b.placePiece(p);
        } catch (OverridingLocationException e) {
            System.out.println("Unexpected Error!");
        }
    }

    /**
     * this method is a helper method for placePiece(Board)
     * It finds all possible locations for AI to place a piece
     * and uses minimax algorithm to evaluate the all locations 
     * @param b the board to find the best move
     * @return the location for the best move
     */
    private int[] bestMove(Board b) {
        int bestScore = -999;
        int[] bestMove = new int[2];
        int size = b.getBoardSize();
        //AI look through all available positions on board and ask miniMax's evaluation for each position
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b.isAvailable(j, i)) {
                    Piece p = new Piece(j, i, type);
                    Board childBoard = new Board(b);
                    try {
                        childBoard.placePiece(p);
                    } catch (OverridingLocationException e) {
                        System.out.println("Unexpected Error!");
                    }
                    int score = miniMax(childBoard, 25, false);
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
     * @param maximizingPlayer if the current position is to find best move or worst move
     * @return the evaluation for current position
     */
    private int miniMax(Board position, int depth, boolean maximizingPlayer) {
        //System.out.println(depth);
        char symbol;
        if (type) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }
        //if game over at position or depth is 0 return current position evaluation
        char c = position.checkWin();
        if (depth == 0 || c != 'E') {
            if (c == 'T') {
                return 0;
            } else if (c == symbol) {
                return 2;
            } else {
                return -1;
            }
        }
        //if evaluating maximizing player
        if (maximizingPlayer) {
            int maxEvaluation = -999;
            //for all possiable moves
            for (int i = 0; i < position.getBoardSize(); i++) {
                for (int j = 0; j < position.getBoardSize(); j++) {
                    if (position.isAvailable(j, i)) {
                        Piece p = new Piece(j, i, type);
                        Board childrenPosition = new Board(position);
                        try {    
                            childrenPosition.placePiece(p);
                        } catch (OverridingLocationException e) {
                            System.out.println("Unexpected Error!");
                        }
                        int evaluation = miniMax(childrenPosition, depth - 1, false);
                        maxEvaluation = Math.max(evaluation, maxEvaluation); 
                    }
                }
            }
            return maxEvaluation;
        } else {
            //if evaluating minimizing player
            int minEvaluation = 999;
            //for all possiable moves
            for (int i = 0; i < position.getBoardSize(); i++) {
                for (int j = 0; j < position.getBoardSize(); j++) {
                    if (position.isAvailable(j, i)) {
                        Piece p = new Piece(j, i, !type);
                        Board childrenPosition = new Board(position);
                        try {    
                            childrenPosition.placePiece(p);
                        } catch (OverridingLocationException e) {
                            System.out.println("Unexpected Error!");
                        }
                        int evaluation = miniMax(childrenPosition, depth - 1, true);
                        minEvaluation = Math.min(minEvaluation, evaluation);
                    }
                }
            }
            return minEvaluation;
        }
    }
}