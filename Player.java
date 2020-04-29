public class Player {
    private final boolean type;

    public Player(boolean type) {
        this.type = type;
    }

    public boolean place(int x, int y, Board b) {
        Piece p = new Piece(x, y, this.type);
        try {
            b.placePiece(p);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        } catch (OverridingLocationException e) {
            return false;
        }
        return true;
    }
}