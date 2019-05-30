/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.mnesweeper.ui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import tira.minesweeper.logic.Board;

/**
 * FXML Controller class
 *
 * @author ida
 */
public class SceneController implements Initializable {
    
    @FXML
    private Canvas canvas;  
    private GraphicsContext gc; 
    private Button startButton;
    private AnchorPane anchorpane;
    private Label unfoundMines;
    
    int rows, cols, mines;
    double fieldsize;
    private Board board;

    public SceneController() {

    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
    }
    
    /**
     * Draws the MineSweeper board. Fires when 'New Game' button is clicked.
     * @param event 
     */
    @FXML
    private void handleGame(ActionEvent event) {
        
        board = new Board(rows, cols);
        board.placeMines(mines);
        board.placeNumbers();
        
        //to-do: take animation timer away
        new AnimationTimer() {
            
            private long previous;
            
            @Override
            public void handle(long now) {
                if (now - previous < 400_000_000) {
                    return;
                }
                previous = now;
                
                //there is a bug               
                //unfoundMines.setText("mines: " + board.numberUnfoundMines());
                
                //to-do: add square borders, images, colors and solved/failed
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, rows * fieldsize, cols * fieldsize);
                
                gc.setFill(Color.BLACK);
                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {
                        String status = board.getState(board.getFieldAt(x, y));
                        gc.fillText(status, (x + 0.5) * fieldsize, (y + 0.5) * fieldsize);
                        
                        if (board.isFailed(x, y)) {
                            this.stop();
                        }
                        
                        if (board.isSolved()) {
                            this.stop();
                        }
                    }
                } 
                
            }
        
        }.start();
    }
    
    /**
     * Open square on left click and set/remove flag on right click
     * @param event 
     */
    @FXML
    private void handleOnMouseReleased(MouseEvent event) {
        if (event.getEventType().equals(event.MOUSE_RELEASED)) {
            int row = (int) event.getX() / (int) fieldsize;
            int col = (int) event.getY() / (int) fieldsize;
            if (event.getButton() == MouseButton.PRIMARY) {
                board.openField(board.getFieldAt(row, col));
            } else {
                board.setFlag(board.getFieldAt(row, col));
            }
            
        }
    }
  
    
    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setFieldsize(double fieldsize) {
        this.fieldsize = fieldsize;
    }    
}
