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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import tira.minesweeper.solver.Solver;

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
    
    int rows, cols, mines;
    double fieldsize;
    private Solver solver;

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
     * @param event click 'New Game' button
     */
    @FXML
    private void handleGame(ActionEvent event) {
        
        solver = new Solver(rows, cols, mines);
        solver.init();
        
        //to-do: take animation timer away
        new AnimationTimer() {
            
            private long previous;
            
            @Override
            public void handle(long now) {
                if (now - previous < 400_000_000) {
                    return;
                }
                previous = now;
                               
                //to-do: add square borders, images, colors and solved/failed
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, cols * fieldsize, rows * fieldsize);
                
                gc.setFill(Color.BLACK);
                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {
                        String status = solver.board.getState(solver.board.getFieldAt(x, y));
                        gc.fillText(status, (x + 0.5) * fieldsize, (y + 0.5) * fieldsize);
                        
                        if (solver.board.isFailed()) {
                            this.stop();
                        }
                        
                        if (solver.board.isSolved()) {
                            this.stop();
                        }
                    }
                } 
                
            }
        
        }.start();
    }
    
    
    
    /**
     * Open square on left click and set/remove flag on right click
     * @param event right/left mouse click
     */
    @FXML
    private void handleOnMouseReleased(MouseEvent event) {
        if (event.getEventType().equals(event.MOUSE_RELEASED)) {
            int row = (int) event.getX() / (int) fieldsize;
            int col = (int) event.getY() / (int) fieldsize;
            if (!solver.board.inBounds(row, col)) {
                return;
            }
            if (event.getButton() == MouseButton.PRIMARY) {
                solver.board.openField(solver.board.getFieldAt(row, col));
            } else {
                solver.board.setFlag(solver.board.getFieldAt(row, col));
            }
            
        }
    }
    
    
    /**
     * Fires when 'Simulate' button is clicked. Note that 'New Game' needs to be clicked first.
     */
    @FXML
    private void handleSimulation() {
        solver.DSSP();
    }
  
    
    /**
     * Sets the number of columns and they need to be between 9 and 30.
     * @param cols number of columns in the board
     */
    public void setCols(int cols) {
        if (cols >= 9 && cols <= 30) {
        this.cols = cols;            
        } else {
            new Exception("Column number is not supported. Number should be between 9 and 30.");            
        }

    }

    
    /**
     * Sets the number of rows and they need to be between 9 and 16.
     * @param rows number of rows in the board
     */
    public void setRows(int rows) {
        if (rows >= 9 && rows <= 16) {
            this.rows = rows;
        } else {
            new Exception("Row number is not supported. Number should be between 9 and 16.");
        }

    }

    
    /**
     * Sets the number of mines and they need to be between 10 and 99.
     * @param mines number of mines on the board
     */
    public void setMines(int mines) {
        if (mines >= 10 && mines <= 99) {
            this.mines = mines;
        } else {
            new Exception("Mine number is not supported. Number should be between 10 and 99.");
        }

    }

    
    /**
     * Help method for GUI to increase the fieldsize
     * @param fieldsize 
     */
    public void setFieldsize(double fieldsize) {
        if (fieldsize >= 1.0) {
            this.fieldsize = fieldsize;
        } else {
            new Exception("Should increase the fieldsize");
        }
    } 
    
    
}