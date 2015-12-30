/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import MainClasses.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Samosad
 */
public class frmEventInfo extends Application {

    ArrayList<SubjectEvent> events;
    int currentEventNumber;

    Button btnNext;
    Button btnPrevious;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Задания");

        SubjectEvent currentEvent = events.get(currentEventNumber);
        Label lblInfo = CreateLabels(currentEvent);

        btnNext = new Button();
        btnNext.setText("Next Event");

        btnPrevious = new Button();
        btnPrevious.setText("Previous Event");

        GridPane grid = CreateGrid(lblInfo);
        ScrollPane sp = new ScrollPane(grid);
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        //ScrollPane sp= new ScrollPane();
        btnNext.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                if (currentEventNumber < events.size() - 1) {
                    currentEventNumber++;
                } else {
                    currentEventNumber = 0;
                }
                try {
                    start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(frmEventInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                if (currentEventNumber != 0) {
                    currentEventNumber--;
                } else {
                    currentEventNumber = events.size() - 1;
                }
                try {
                    start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(frmEventInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void SetEvents(ArrayList<SubjectEvent> ev) {
        events = ev;
    }

    private Label CreateLabels(SubjectEvent events) {
        Label tempLbl = new Label(DataProcessor.GetLabelText(events));
        tempLbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        return tempLbl;
    }

    private GridPane CreateGrid(Label lblList) {
        GridPane grid = new GridPane();
        grid.add(lblList, 1, 1);

        grid.add(btnNext, 2, 2);
        grid.add(btnPrevious, 1, 2);

        return grid;
    }
}
