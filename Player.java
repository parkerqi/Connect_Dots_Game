
/**
 * This class represents the human player.
 * @author Parker Qi
 * @author Parker.qi@hotmail.com
 * @version 1.0.0
 */
public class Player {
    private final boolean type;

    /**
     * The constructor for human player
     * @param type if human is playing X, type = true; if playing O, type = false
     */
    public Player(boolean type) {
        this.type = type;
    }

    /**
     * this method places a piece on the board
     * !!!Attention!!! x, y location is the array location which means from 0 to size-1
     * @param x the x axis location  
     * @param y the y axis location
     * @param b the board object to pleace piece on
     * @return if the piece can be placed
     */
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