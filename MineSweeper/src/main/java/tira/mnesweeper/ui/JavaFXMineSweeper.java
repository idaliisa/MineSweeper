/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.mnesweeper.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *Main class of MineSweeper graphical user interface
 * @author ida
 */
public class JavaFXMineSweeper extends Application {

    @Override
    public void start(Stage primaryStage)  throws Exception {
        //read fxml-file to build GUI
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        
        //add GUI controller
        SceneController controller = fxmlLoader.<SceneController>getController();
        
        //set board size and mine count
        controller.setCols(3);
        controller.setRows(3);
        controller.setMines(2);
        
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
