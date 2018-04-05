package Chess;

import javafx.scene.paint.Color;
import java.io.Serializable;
import javafx.geometry.HPos;
import javafx.scene.input.MouseEvent;

/**
 * Board.
 * @author Yue Wing Lau (Tony)
 * @version 1.0
 */
public class OneDBoard extends Board implements Serializable{

    /**
     * Stores the pieces objects.
     */
    private Piece[] pieceSet;
    
    /**
     * Keeps tracks of the turn.
     */
    private int turn;
    
    /**
     * Stores the tiles of the board.
     */
    private Tile[][] tileSet;
    
    /**
     * Tracks the last Piece that was clicked.
     */
    private Piece currentPiece;
    
    /**
     * Tracks the last Tile that was clicked.
     */
    private Tile currentTile;
    
    /**
     * Constructs an object of type Board.
     */
    public OneDBoard(int boardSizeX, int boardSizeY, int tileX, int tileY) {
        createBoard(boardSizeX, boardSizeY, tileX, tileY);
        layPieces();
        setOnMousePressed(this::processMousePressed);
    }
    
    /**
     * Removes the Piece that was click and moves it to the new position.
     * @param event Mouse press event
     */
    public void processMousePressed(MouseEvent event) {
        
    	Object target = event.getTarget();
    	if (target instanceof Tile && currentPiece != null) {
    	    movePiece(target);
	    } else if (target instanceof Piece){
	        currentPiece = (Piece) target;
	        if (turn % 2 != currentPiece.getTeam()) {
	            currentPiece = null;
	        }
    	}
    }
    
    /**
     * Creates the chess board.
     */
    public void createBoard(int boardSizeX, int boardSizeY, int tileX, int tileY) {
        
        setPrefSize(boardSizeX, boardSizeY);
        tileSet = new Tile[tileY][tileX];
        int colour = 0;
        for (int y = 0; y < tileY; y++) {
            for (int x = 0; x < tileX; x++) {
                tileSet[y][x] = new Tile(x, y, 0, colour);
                colour++;
                add(tileSet[y][x], x, y);
            }
            colour++;
        }
    }
    
    public void layPieces() {
        ChessOrder chessOrder = new ChessOrder();
        pieceSet = new Piece[32];
        Piece[][] chessPiece = chessOrder.getChessLayout();
        int count = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (y < 2 || y > 5) {
                    setHalignment(chessPiece[y][x], HPos.CENTER);
                    add(chessPiece[y][x], x, y);
                    pieceSet[count] = chessPiece[y][x];
                    tileSet[y][x].setOccupied(1);
                    count++;
                }
            }
        }
    }
    
    /**
     * Moves Piece to the new Tile.
     * @param target current Tile that was clicked
     */
    public void movePiece(Object target) {
        
        currentTile = (Tile) target;
        if (currentPiece.validMove(currentTile.getxPos(), currentTile.getyPos(), 0, tileSet)) {
            
            tileSet[currentPiece.getyPos()][currentPiece.getxPos()].setOccupied(0);
            currentPiece.setxPos(currentTile.getxPos());
            currentPiece.setyPos(currentTile.getyPos());
            tileSet[currentPiece.getyPos()][currentPiece.getxPos()].setOccupied(1);
            getChildren().remove(currentPiece);
            add(currentPiece, currentPiece.getxPos(), currentPiece.getyPos());
            currentPiece = null;
            currentTile = null;
            turn++;
        }
    }
    
    /**
     * Saves the board states for serialization. 
     * Can only save position because pieces extends stackpane which doesn't allow serialization.
     * @return 2D int array that stores the piece position info
     */
    public Object[][] saveBoard() {
        
        Object[][] piecePlacement = new Object[33][4];
        for (int index = 0; index < 32; index++) {
                    
            //Stores the values of the chess pieces
            piecePlacement[index][0] = pieceSet[index].getxPos(); 
            piecePlacement[index][1] = pieceSet[index].getyPos();
            piecePlacement[index][2] = pieceSet[index].getText();
            piecePlacement[index][3] = pieceSet[index].getTeam();
        }
        piecePlacement[32][0] = turn;
        return piecePlacement;
    }
    
    /*
     * Loads the board back into a previous save state.
     */
    public void loadBoard(Object[][] loadPieces) {
        
        int xIndex;
        int yIndex;
        int colour = 0;
        resetBoard();
        for (yIndex = 0; yIndex < 8; yIndex++) {
            for (xIndex = 0; xIndex < 8; xIndex++) {
                tileSet[yIndex][xIndex] = new Tile(xIndex, yIndex, 0, colour);
                colour++;
                add(tileSet[yIndex][xIndex], xIndex, yIndex);
            }
            colour++;
        }
        setPiece(loadPieces);
    }
    
    /**
     * Sets the pieces to a specific spot.
     * @param loadPieces information on where the pieces need to be set
     */
    public void setPiece(Object[][] loadPieces) {
        int pieceIndex = 0;
        int xIndex;
        int yIndex;
        while (pieceIndex < 32) {
            xIndex = (int) loadPieces[pieceIndex][0];
            yIndex = (int) loadPieces[pieceIndex][1];
            currentPiece = createPiece(xIndex, yIndex, (int) loadPieces[pieceIndex][3], (String) loadPieces[pieceIndex][2]);
            setHalignment(currentPiece, HPos.CENTER);
            pieceSet[pieceIndex] = currentPiece;
            colorPiece(currentPiece);
            add(currentPiece, xIndex, yIndex);
            tileSet[yIndex][xIndex].setOccupied(1);
            pieceIndex++;
        }
        turn = (int) loadPieces[pieceIndex][0];
    }
    
    /**
     * Colours the pieces.
     * @param curPiece piece being coloured
     */
    public void colorPiece(Piece curPiece) {
        if (curPiece.getTeam() == 0) {
            curPiece.setFillCircle(Color.BLACK);
            curPiece.setFillText(Color.WHITE);
        } else {
            curPiece.setFillCircle(Color.WHITE);
            curPiece.setFillText(Color.BLACK);
        }
    }
    
    /**
     * Creates the specific piece needed for the board when loading.
     * @param xPos x position of piece
     * @param yPos y position of piece
     * @param team piece's team
     * @param type the kind of piece it is
     * @return the chess piece unit
     */
    public Piece createPiece(int xPos, int yPos, int team, String type) {
        if (type.equalsIgnoreCase("pawn")) {
            return new Pawn(xPos, yPos, team);
        }
        if (type.equalsIgnoreCase("bishop")) {
            return new Bishop(xPos, yPos, team);
        }
        if (type.equalsIgnoreCase("rook")) {
            return new Rook(xPos, yPos, team);
        }
        if (type.equalsIgnoreCase("knight")) {
            return new Knight(xPos, yPos, team);
        }
        if (type.equalsIgnoreCase("queen")) {
            return new Queen(xPos, yPos, team);
        }
        return new King(xPos, yPos, team);
    }
    
    /**
     * Resets the board.
     */
    public void resetBoard() {
        getChildren().clear();
    }
    
    public OneDBoard getBoard() {
        return this;
    }
}
