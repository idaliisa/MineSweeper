/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.main;

import tira.mnesweeper.ui.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of MineSweeper graphical user interface
 * @author ida
 */
public class JavaFXMineSweeper extends Application {
    //Supports row number 9-16, col number 9-30 and mine number 10-99.
    //easy: 9,9,10; intermediate 16, 16, 40; expert 16, 30, 99
    public static int rows = 16;
    public static int cols = 16;
    public static int mines = 40;
    public static double fieldSize = 30.0;
    
    
    
    @Override
    public void start(Stage stage)  throws Exception {
        //read fxml-file to build GUI
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        
        //add GUI controller
        SceneController controller = fxmlLoader.<SceneController>getController();
        
        //set board size and mine count
        controller.setCols(cols);
        controller.setRows(rows);
        controller.setFieldsize(fieldSize);
        controller.setMines(mines);
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
