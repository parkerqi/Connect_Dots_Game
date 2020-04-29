public class AI {
    private final boolean type;

    public AI(boolean type) {
        this.type = type;
    }
    
    public void placePiece(Board b) {
        int[] a = bestMove(b);
        Piece p = new Piece(a[1],a[0], this.type);
        try {
            b.placePiece(p);
        } catch (OverridingLocationException e) {
            System.out.println("Unexpected Error!");
        }
    }

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
                    int score = miniMax(childBoard, 0, false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

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
        if (c != 'E') {
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
                        int evaluation = miniMax(childrenPosition, depth + 1, false);
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
                        int evaluation = miniMax(childrenPosition, depth + 1, true);
                        minEvaluation = Math.min(minEvaluation, evaluation);
                    }
                }
            }
            return minEvaluation;
        }
    }
}