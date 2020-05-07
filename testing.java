public class testing {
    public static char[][] b = {{'X', 'X', 'X', 'O', 'O'},
                                {'X', 'O', 'X', 'O', 'd'},
                                {'O', 'X', 'O', 'X', 'X'},
                                {'O', 'X', 'X', 'X', 'X'},
                                {'X', 'X', 'O', 'X', 'O'}};
                                
                                
    public static char[][] c = {{'O', 'O', 'X'},
                                {'X', 'X', 'O'},
                                {'O', 'X', 'X'}};                        
    public static void main(String[] arg) {
        /*
        b[1][4] = 0;
        Board board = new Board(b, 4);
        AI a = new AI(true, board.getWinCondition());
        System.out.print(a.ifImmidiateWin(b));
        */
        Board board = new Board(c, 3);
        System.out.println(Board.checkWin());
    }
}