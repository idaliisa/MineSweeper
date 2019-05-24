/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.mnesweeper.ui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tira.minesweeper.logic.Board;

/**
 * FXML Controller class
 *
 * @author ida
 */
public class SceneController implements Initializable {
    
    @FXML public AnchorPane anchorpane;
    @FXML public GridPane gridpane;
    @FXML public Button button;
    int rows, cols, mines;
    private Board board;

    public SceneController() {

    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            board = new Board(rows, cols);
            board.placeMines(mines);
            board.placeNumbers();
            
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    gridpane.add(new Button(), x, y);
                }
            }
            

        });

    }
    
    @FXML
    private void handleGame(ActionEvent event) {
        
        new AnimationTimer() {
            
            private long previous;
            
            @Override
            public void handle(long now) {
                if (now - previous < 400_000_000) {
                    return;
                }
                //to-do: simulation
                
            }
        
        }.start();
    }
    
    /*
    @FXML
    private void handleOnMouseReleased(MouseEvent event) {
        
       //there is a bug
        if (event.getEventType().equals(event.MOUSE_RELEASED)) {
            int row = (int) event.getX();
            int col = (int) event.getY();
            board.openField(board.getFieldAt(row, col));

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    for (Node node : gridpane.getChildren()) {
                        if (gridpane.getRowIndex(node) == y && gridpane.getColumnIndex(node) == x && board.getFieldAt(x, y).isOpened()) {
                            Button button = (Button)node;
                            //to-do
                        }
                    }
                }
            }      
        }       
    }
    */

    

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }
    
}
