/**
 * This is an abstract class for human and AI player.
 * @author Parker Qi
 * @version 1.0.0
 */
public abstract class Player{
    public final boolean type;

    /**
     * Constructor of Player
     * @param type if playing X, type = true; if playing O, type = false 
     */
    public Player(Boolean type) {
        this.type = type;
    }
}