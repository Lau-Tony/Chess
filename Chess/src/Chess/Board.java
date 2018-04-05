package Chess;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public abstract class Board extends GridPane{
    
    public abstract void processMousePressed(MouseEvent event);
    
    public abstract void createBoard(int boardSizeX, int boardSizeY, int tileX, int tile);
    
    public abstract Object[][] saveBoard();
    
    public abstract void loadBoard(Object[][] loadPieces);
    
}
