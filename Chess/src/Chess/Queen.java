package Chess;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Queen.
 * @author Yue Wing Lau (Tony)
 * @version 1.0
 */
public class Queen extends Piece{

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
    public Queen(int xPos, int yPos, int team) {
        setWidth(80);
        setHeight(80);
        this.team = team;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = 0;
        piece = new Circle();
        piece.setRadius(25);
        piece.setMouseTransparent(true);
        text = new Text("Queen");
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
    
    @Override
    public boolean validMove(int x, int y, int z, Tile[][] tileSet) {
        
        int validX;
        int validY;
        int validZ;
        if (zPos == z) {
            validX = xPos - 1;
            validY = yPos; 
            //Check left
            while (validX > -1 && zPos == z && tileSet[yPos][validX + z * 8].getOccupied() == 0) {
                if (tileSet[yPos][validX + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validX--;
            }
            validX = xPos + 1;
            validY = yPos;
            //Check right
            while (validX < 8 && zPos == z && tileSet[yPos][validX + z * 8].getOccupied() == 0) {
                if (tileSet[yPos][validX + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validX++;
            }
            //Check up
            validX = xPos;
            validY = yPos - 1;
            while (validY > -1 && zPos == z && tileSet[validY][xPos + z * 8].getOccupied() == 0) {
                if (tileSet[validY][xPos + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validY--;
            }
            //Check down
            validX = xPos;
            validY = yPos + 1;
            while (validY < 8 && zPos == z && tileSet[validY][xPos + z * 8].getOccupied() == 0) {
                if (tileSet[validY][xPos + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validY++;
            }
            //Check diagonal bottom right
            validX = xPos + 1;
            validY = yPos + 1;
            while (validY < 8 && zPos == z && validX < 8 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
                if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validY++;
                validX++;
            }
            //Check diagonal bottom left
            validX = xPos - 1;
            validY = yPos + 1;
            while (validY < 8 && zPos == z && validX > -1 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
                if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validY++;
                validX--;
            }
            //Check diagonal top right
            validX = xPos + 1;
            validY = yPos - 1;
            while (validY > -1 && zPos == z && validX < 8 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
                if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validY--;
                validX++;
            }
            //Check diagonal top left
            validX = xPos - 1;
            validY = yPos - 1;
            while (validY > -1 && zPos == z && validX > -1 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
                if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                    return true;
                }
                validY--;
                validX--;
            }
        } 
        if ((zPos == 0 || zPos == 2) && z == 1 && (Math.abs(xPos - x) > 1 || Math.abs(yPos - y) > 1)) {
            return false;
        } 
        //Checks upward floor
        validX = xPos - 1;
        validY = yPos;
        validZ = zPos + 1;
        //Check up left
        while (validX > -1 && validZ < 3 && tileSet[yPos][validX + z * 8].getOccupied() == 0) {
            if (tileSet[yPos][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validX--;
            validZ++;
        }
        validX = xPos + 1;
        validY = yPos;
        validZ = zPos + 1;
        //Check right
        while (validX < 8 && validZ < 3 && tileSet[yPos][validX + z * 8].getOccupied() == 0) {
            if (tileSet[yPos][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validX++;
            validZ++;
        }
        //Check up forward
        validX = xPos;
        validY = yPos - 1;
        validZ = zPos + 1;
        while (validY > -1 && validZ < 3 && tileSet[validY][xPos + z * 8].getOccupied() == 0) {
            if (tileSet[validY][xPos + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY--;
            validZ++;
        }
        //Check up backward
        validX = xPos;
        validY = yPos + 1;
        validZ = zPos + 1;
        while (validY < 8 && validZ < 3 && tileSet[validY][xPos + z * 8].getOccupied() == 0) {
            if (tileSet[validY][xPos + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY++;
            validZ++;
        }
        //Check up diagonal bottom right
        validX = xPos + 1;
        validY = yPos + 1;
        validZ = zPos + 1;
        while (validY < 8 && validZ < 3 && validX < 8 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY++;
            validX++;
            validZ++;
        }
        //Check up diagonal bottom left
        validX = xPos - 1;
        validY = yPos + 1;
        validZ = zPos + 1;
        while (validY < 8 && validZ < 3 && validX > -1 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY++;
            validX--;
            validZ++;
        }
        //Check up diagonal top right
        validX = xPos + 1;
        validY = yPos - 1;
        validZ = zPos + 1;
        while (validY > -1 && validZ < 3 && validX < 8 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY--;
            validX++;
            validZ++;
        }
        //Check up diagonal top left
        validX = xPos - 1;
        validY = yPos - 1;
        validZ = zPos + 1;
        while (validY > -1 && validZ < 3 && validX > -1 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY--;
            validX--;
            validZ++;
        }
        
      //Checks downward floor
        validX = xPos - 1;
        validY = yPos;
        validZ = zPos - 1;
        //Check down left
        while (validX > -1 && validZ > -1 && tileSet[yPos][validX + z * 8].getOccupied() == 0) {
            if (tileSet[yPos][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validX--;
            validZ--;
        }
        validX = xPos + 1;
        validY = yPos;
        validZ = zPos - 1;
        //Check down right
        while (validX < 8 && validZ > -1 && tileSet[yPos][validX + z * 8].getOccupied() == 0) {
            if (tileSet[yPos][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validX++;
            validZ--;
        }
        //Check down forward
        validX = xPos;
        validY = yPos - 1;
        validZ = zPos - 1;
        while (validY > -1 && validZ > -1 && tileSet[validY][xPos + z * 8].getOccupied() == 0) {
            if (tileSet[validY][xPos + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY--;
            validZ--;
        }
        //Check down backward
        validX = xPos;
        validY = yPos + 1;
        validZ = zPos - 1;
        while (validY < 8 && validZ > -1 && tileSet[validY][xPos + z * 8].getOccupied() == 0) {
            if (tileSet[validY][xPos + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY++;
            validZ--;
        }
        //Check down diagonal bottom right
        validX = xPos + 1;
        validY = yPos + 1;
        validZ = zPos - 1;
        while (validY < 8 && validZ > -1 && validX < 8 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY++;
            validX++;
            validZ--;
        }
        //Check down diagonal bottom left
        validX = xPos - 1;
        validY = yPos + 1;
        validZ = zPos - 1;
        while (validY < 8 && validZ > -1 && validX > -1 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY++;
            validX--;
            validZ--;
        }
        //Check down diagonal top right
        validX = xPos + 1;
        validY = yPos - 1;
        validZ = zPos - 1;
        while (validY > -1 && validZ > -1 && validX < 8 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY--;
            validX++;
            validZ--;
        }
        //Check down diagonal top left
        validX = xPos - 1;
        validY = yPos - 1;
        validZ = zPos - 1;
        while (validY > -1 && validZ > -1 && validX > -1 && tileSet[validY][validX + z * 8].getOccupied() == 0) {
            if (tileSet[validY][validX + z * 8] == tileSet[y][x + z * 8]) {
                return true;
            }
            validY--;
            validX--;
            validZ--;
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
}

