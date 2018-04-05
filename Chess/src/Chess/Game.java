package Chess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Game.
 * @author Yue Wing Lau (Tony)
 * @version 1.0
 */
public class Game extends Application implements Serializable{

    /**
     * The board of chess.
     */
    private Board chessGame;
    
    /** 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     * @param arg0 primary Stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome To Tony's Chess Game!");
        System.out.println("Enter 1 for single board chess\nEnter 2 for triple board chess\n");
        Group root;
        if (input.nextInt() == 1) {
            chessGame = new OneDBoard(333, 40, 8, 8);
            
            Button save = new Button("Save");
            save.setTranslateX(400);
            save.setTranslateY(50);
            save.setOnAction(this::save);
            Button load = new Button("Load");
            load.setTranslateX(400);
            load.setTranslateY(100);
            load.setOnAction(this::load);
            root = new Group(chessGame, save, load);
            
        } else {
            chessGame = new ThreeDBoard(1000, 120, 24, 8);
            root = new Group(chessGame);
        }
        
        Scene scene1 = new Scene(root);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Chess Game with placeholder pieces");
        primaryStage.show();
        
    }

    /**
     * Save method for serialization.
     * @param event
     */
    public void save(ActionEvent event) {
        try {
            FileOutputStream fileOut =
            new FileOutputStream("chess.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(chessGame.saveBoard());
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in chess.ser");
         } catch (IOException i) {
            i.printStackTrace();
         }
    }
    
    /**
     * Loading method for serialization.
     * @param event
     */
    public void load(ActionEvent event) {
        Object[][] loadSave;
        try {
            FileInputStream fileIn = new FileInputStream("chess.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadSave = (Object[][]) in.readObject();
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return;
         } catch (ClassNotFoundException c) {
            System.out.println("Board class not found");
            c.printStackTrace();
            return;
         }
        chessGame.loadBoard(loadSave);
    }
    /**
     * Drives the main.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }

}
