package Chess;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Pawn.
 * @author Yue Wing Lau (Tony)
 * @version 1.0
 */
public class Pawn extends Piece {

    private int firstMove = 1;
    
    /**
     * Text that says the Piece its suppose to be.
     */
    private Text text;
    
    /**
     * Circle for the piece.
     */
    private Circle piece;
    /**
     * Constructs an object of type Pawn.
     * @param xPos Starting x position of the piece
     * @param yPos Starting y position of the piece
     */
    public Pawn(int xPos, int yPos, int team) {
        setWidth(80);
        setHeight(80);
        this.team = team;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = 0;
        piece = new Circle();
        piece.setRadius(25);
        piece.setMouseTransparent(true);
        text = new Text("Pawn");
        text.setMouseTransparent(true);
        getChildren().addAll(piece, text);
        setOnMousePressed(this::processMousePressed);
    } 
    
    /**
     * Highlights the Piece to show its selected.
     * @see Chess.Piece#processMousePressed(javafx.scene.input.MouseEvent)
     * @param event Mouse pressed
     */
    @Override
    public void processMousePressed(MouseEvent event) {
    }
    
    public boolean validMove(int x, int y, int z, Tile[][] tileSet) {
        int validY = y;
        int validX = x;
        
        //Special case for first pawn move
        if (firstMove == 1) {
            //Same floor
            if (team == 0 && yPos < 8 && zPos == z && yPos + 2 == validY && xPos == validX && tileSet[yPos + 2][xPos].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } else if (team == 1 && zPos == z && yPos > -1 && yPos - 2 == validY && xPos == validX && tileSet[yPos - 2][xPos].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } 
            //Diff floor
            if (team == 0 && yPos < 8 && z == 2 && yPos + 2 == validY && xPos == validX && tileSet[yPos + 2][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } else if (team == 1 && z == 2 && yPos > -1 && yPos - 2 == validY && xPos == validX && tileSet[yPos - 2][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } 
        } 

        //Subsequent movement
        if (team == 0) {
            if (zPos == 1 && yPos < 8 && yPos + 1 == validY && xPos == validX && (z == 2 || z == 0) && tileSet[yPos + 1][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } else if ((zPos == 0 || zPos == 2) && yPos < 8 && yPos + 1 == validY && xPos == validX && z == 1 && tileSet[yPos + 1][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } else if (zPos == z && yPos < 8 && yPos + 1 == validY && xPos == validX && tileSet[yPos + 1][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            }
        } else {
            if (zPos == 1 && yPos > -1 && yPos - 1 == validY && xPos == validX && (z == 2 || z == 0) && tileSet[yPos - 1][xPos + 8 * z].getOccupied() == 0) {
               firstMove = 0;
               return true;
            } else if ((zPos == 0 || zPos == 2) && yPos > -1 && yPos - 1 == validY && xPos == validX && z == 1 && tileSet[yPos - 1][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            } else if (zPos == z && yPos > -1 && yPos - 1 == validY && xPos == validX && tileSet[yPos - 1][xPos + 8 * z].getOccupied() == 0) {
                firstMove = 0;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Sets the x position of the Piece.
     * @see Chess.Piece#setxPos(int)
     * @param xPos x position
     */
    @Override
    public void setxPos(int xPos) {
		this.xPos = xPos;
	}

    /**
     * Sets the y position of the Piece.
     * @see Chess.Piece#setyPos(int)
     * @param yPos y position
     */
    @Override
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

    /**
     * Gets the x position.
     * @see Chess.Piece#getxPos()
     * @return x position
     */
    @Override
	public int getxPos() {
		return xPos;
	}

    /**
     * Gets the y position.
     * @see Chess.Piece#getyPos()
     * @return y position
     */
    @Override
	public int getyPos() {
		return yPos;
	}
    
    /**
     * Gets the z position.
     * @see Chess.Piece#getzPos()
     * @return z position
     */
    @Override
    public int getzPos() {
        return zPos;
    }

    /**
     * Sets the z position.
     * @see Chess.Piece#setzPos(int)
     * @param zPos new z position
     */
    @Override
    public void setzPos(int zPos) {
        this.zPos = zPos;
    }
    
    /**
     * Fills the colour of the circle.
     * @see Chess.Piece#setFillCircle(javafx.scene.paint.Color)
     * @param colorFill
     */
    public void setFillCircle(Color colorFill) {
        piece.setFill(colorFill);
    }
    
    /**
     * Fills the text colour.
     * @see Chess.Piece#setFillText(javafx.scene.paint.Color)
     * @param colorFill
     */
    public void setFillText(Color colorFill) {
        text.setFill(colorFill);
    }

    /**
     * Sets the size of the highlight.
     * @see Chess.Piece#setHighlight(int)
     * @param size 
     */
    @Override
    public void setHighlight(int size) {
        piece.setStrokeWidth(size);
    }
    
    /**
     * Gets the team of the piece.
     * @see Chess.Piece#getTeam()
     * @return
     */
    public int getTeam() {
        return team;
    }
    
    /**
     * Gets the text of the piece.
     * @see Chess.Piece#getText()
     * @return
     */
    public String getText() {
        return text.getText();
    }
}
