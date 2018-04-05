package Chess;

import java.io.Serializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public abstract class Piece extends StackPane {

    /**
     * x position.
     */
    protected int xPos;
    
    /**
     * y position.
     */
    protected int yPos;
    
    /**
     * z position.
     */
    protected int zPos;
    
    /**
     * team number
     */
    protected int team;
    
    /**
     * Gets the piece name.
     * @return returns piece name
     */
    public abstract String getText();
    
    /**
     * Checks if a move is valid
     * @param x destination x
     * @param y destination y
     * @param tileSet tiles on the board
     * @return true/false
     */
    public abstract boolean validMove(int x, int y, int z, Tile[][] tileSet);
    
    /**
     * Gets x position.
     * @return x position
     */
	public abstract int getxPos();
	
	/**
	 * Gets y position.
	 * @return y position
	 */
	public abstract int getyPos();
	
	/**
	 * Gets z position.
	 * @return z position
	 */
	public abstract int getzPos();
	
	/**
	 * Sets the y position.
	 * @param yPos new y position 
	 */
	public abstract void setyPos(int yPos);
	
	/**
	 * Sets the x position.
	 * @param xPos new x position
	 */
	public abstract void setxPos(int xPos);
	
	/**
	 * Sets the z position
	 * @param zPos new z position
	 */
	public abstract void setzPos(int zPos);
	
	/**
	 * Processes mouse press.
	 * @param event mouse event
	 */
	public abstract void processMousePressed(MouseEvent event);
	
	/**
	 * Sets circle colour.
	 * @param colorFill type of colour
	 */
	public abstract void setFillCircle(Color colorFill);
	
	/**
	 * Sets the text colour.
	 * @param colorFill type of colour
	 */
	public abstract void setFillText(Color colorFill);
	
	/**
	 * Sets the highlight size.
	 * @param size size of highlight
	 */
	public abstract void setHighlight(int size);
	
	/**
	 * Gets the team of the piece.
	 * @return team number
	 */
	public abstract int getTeam();
}
