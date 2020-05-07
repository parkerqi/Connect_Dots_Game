
/**
 * This class represents the human player.
 * @author Parker Qi
 * @author Parker.qi@hotmail.com
 * @version 1.1.0
 * 
 */
public class HumanPlayer extends Player{

    /**
     * The constructor for human player
     * @param type if playing X, type = true; if playing O, type = false
     */
    public HumanPlayer(boolean type) {
        super(type);
    }

    /**
     * this method places a piece on the board
     * !!!Attention!!! x, y location is the array location which means from 0 to size-1
     * @param x the x axis location  
     * @param y the y axis location
     * @return if the piece can be placed
     */
    public boolean place(int x, int y) {
        try {
            Board.placePiece(x - 1, y - 1, super.type);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}