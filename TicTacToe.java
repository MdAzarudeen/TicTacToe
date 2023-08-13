package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Button buttons[][] = new Button[3][3];
    private Label playerXscoreLabel, playerOscoreLabel;
    private boolean Xturn = true;
    private int xscore =0, oscore =0;
    private  BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

//        game title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 34pt; -fx-font-weight : bold;");
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

//        game board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                Button button = new Button("");
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                button.setOnAction(actionEvent -> buttonClicked(button));
                button.setPrefSize(100,100);
                buttons[i][j]=button;
                gridPane.add(button,j,i);
            }
        }

        root.setCenter(gridPane);
//        game score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXscoreLabel = new Label("Player X : 0");
        playerXscoreLabel.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold;");
        playerOscoreLabel = new Label("Player O : 0");
        playerOscoreLabel.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXscoreLabel, playerOscoreLabel);
        root.setBottom(scoreBoard);
        return root;
    }

    private void buttonClicked(Button button){
        if(button.getText().equals(""))
        {
            if(Xturn)
                button.setText("X");
            else
                button.setText("O");

            Xturn = !Xturn;
            checkWinner();
        }
        return;
    }
    private void checkWinner(){
//        row
        for(int row=0; row<3; row++)
        {
            if(  !buttons[row][0].getText().isEmpty() &&
                    buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText())
              )
            {
                String winner =  buttons[row][1].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
//        column

        for(int col=0; col<3; col++)
        {
            if(  !buttons[0][col].getText().isEmpty() &&
                    buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText())
            )
            {
                String winner =  buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
//        diagonal 1
        if(  !buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())
        )
        {
            String winner =  buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
//        diagonal 2
        if(  !buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())
        )
        {
            String winner =  buttons[0][2].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
//        tie
        boolean tie = true;
        for(Button[]row : buttons)
        {
            for(Button button: row)
            {
                if(button.getText().isEmpty())
                {
                    tie=false;
                    break;
                }
            }
        }
        if(tie) {
            showTieDialog();
            resetBoard();
        }
    }
    private void showWinnerDialog(String winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congrats "+winner+"! You won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over. It's  a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner){
        if(winner.equals("X"))
        {
            xscore++;
            playerXscoreLabel.setText("Player X : "+xscore);
        }
        else
        {
            oscore++;
            playerOscoreLabel.setText("Player O : "+oscore);
        }
    }
    private void resetBoard(){
        for(Button[]row : buttons)
        {
            for(Button button: row)
            {
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}