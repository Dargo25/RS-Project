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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import MainClasses.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 *
 * @author Егор
 */
public class RS_Project extends Application {
    //Это поле коллекция пар. Юзай его.
    private static List<Subject> subjectList = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Wake up, Artem!'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            /*При нажатии клавиши на форме парсится xml,
             * а затем выводится название предмета у 1-го объекта коллекции,
             * т.е. выводится нахвание предмета первой пары
             */
            @Override
            public void handle(ActionEvent event) {
                DisplayShedule.Display(subjectList);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}    
    
    
