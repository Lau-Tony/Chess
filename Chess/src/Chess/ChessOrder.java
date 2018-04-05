package Chess;

import javafx.scene.paint.Color;

/**
 * ChessOrder.
 * @author Yue Wing Lau (Tony)
 * @version 1.0
 */
public class ChessOrder {

    /**
     * Sets the layout of the pieces.
     */
    private Piece[][] chessLayout;
    
    /**
     * Constructs an object of type ChessOrder.
     */
    public ChessOrder() {
        chessLayout = new Piece[8][8];
        chessLayout[0][0] = new Rook(0, 0, 0);
        chessLayout[0][1] = new Knight(1, 0, 0);
        chessLayout[0][2] = new Bishop(2, 0, 0);
        chessLayout[0][3] = new Queen(3, 0, 0);
        chessLayout[0][4] = new King(4, 0, 0);
        chessLayout[0][5] = new Bishop(5, 0, 0);
        chessLayout[0][6] = new Knight(6, 0, 0);
        chessLayout[0][7] = new Rook(7, 0, 0);
        int z = 0;
        for (int y = 1; y < 7; y++) {
            for (int x = 0; x < 8; x++) {
                chessLayout[y][x] = new Pawn(x, y, z);
            }
            y += 4;
            z += 1;
        }
        chessLayout[7][0] = new Rook(0, 7, 1);
        chessLayout[7][1] = new Knight(1, 7, 1);
        chessLayout[7][2] = new Bishop(2, 7, 1);
        chessLayout[7][3] = new Queen(3, 7, 1);
        chessLayout[7][4] = new King(4, 7, 1);
        chessLayout[7][5] = new Bishop(5, 7, 1);
        chessLayout[7][6] = new Knight(6, 7, 1);
        chessLayout[7][7] = new Rook(7, 7, 1);
        
        
        // Sets the colour of the pieces
        int yIndex = 0;
        while (yIndex < 8) {
            for (int xIndex = 0; xIndex < 8; xIndex++) {
                if (yIndex < 3) {
                    chessLayout[yIndex][xIndex].setFillCircle(Color.BLACK);
                    chessLayout[yIndex + 1][xIndex].setFillCircle(Color.BLACK);
                    chessLayout[yIndex][xIndex].setFillText(Color.WHITE);
                    chessLayout[yIndex + 1][xIndex].setFillText(Color.WHITE);
                    
                } else {
                    chessLayout[yIndex][xIndex].setFillCircle(Color.WHITE);
                    chessLayout[yIndex + 1][xIndex].setFillCircle(Color.WHITE);
                    chessLayout[yIndex][xIndex].setFillText(Color.BLACK);
                    chessLayout[yIndex + 1][xIndex].setFillText(Color.BLACK);
                }
            }
            yIndex += 6;
        }
    }

    /**
     * Returns the chessLayout for this ChessOrder.
     * @return the chessLayout
     */
    public Piece[][] getChessLayout() {
        return chessLayout;
    }
    
}
