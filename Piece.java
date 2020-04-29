/**
 * connect 5 pieces
 * player 1 uses X which is type true
 * player 2 uses O which is typr false
 */
public class Piece {
    private int[] location;
    private boolean type;
    private String symbol;

    public Piece(int x, int y, boolean type) {
        location = new int[]{x, y};
        this.type = type;
        if (type) {
            symbol = "X";
        } else {
            symbol = "O";
        }
    }
    
    public int[] getLocation() {
        return location;
    }

    public boolean getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }
}