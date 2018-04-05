package Chess;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Tile.
 * @author Yue Wing Lau (Tony)
 * @version 1.0
 */
public class Tile extends Rectangle{
    
    /**
     * Stores if the tile is occupied.
     */
    private int occupied;
    
    /**
     * Stores the Tile x position.
     */
    private int xPos;
    
    /**
     * Stores the Tile y position.
     */
    private int yPos;
    
    /**
     * Stores the Tile z position.
     */
    private int zPos;
    
    /**
     * Constructs an object of type Tile.
     * @param xPos x position of the Tile
     * @param yPos y position of the Tile
     * @param colour of the Tile
     */
    public Tile(int xPos, int yPos, int zPos, int colour) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        occupied = 0;
        setWidth(50);
        setHeight(50);
        if ((colour % 2) == 0) {
            setFill(Color.YELLOW);
        } else {
            setFill(Color.GREEN);
        }
    }
    
    /**
     * Returns the xPos for this Tile.
     * @return the xPos
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Returns the yPos for this Tile.
     * @return the yPos
     */
    public int getyPos() {
        return yPos;
    }
    
    /**
     * Returns the zPos for this Tile.
     * @return the zPos;
     */
    public int getzPos() {
        return zPos;
    }

    /**
     * Returns the occupied for this Tile.
     * @return the occupied
     */
    public int getOccupied() {
        return occupied;
    }

    /**
     * Sets the occupied for this Tile.
     * @param occupied the occupied to set
     */
    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }
    
}
