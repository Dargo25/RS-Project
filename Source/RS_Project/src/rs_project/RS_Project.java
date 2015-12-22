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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Егор
 */
public class RS_Project extends Application {
    private static List<Subject> subjectList = new ArrayList<>();
    Timer timer;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Wake up, Artem!'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
//                try {
                DisplayShedule.Display(subjectList);
//                    ParseXML.ChangeXML();
////                timer = new Timer();
////                TimerTask task = new TimerTask() {
////
////                    @Override
////                    public void run() {
////                        System.out.println("'Wake up, Artem!'");
////                    }
////                };
////                timer.schedule(task, new Date(2015-1900, 12-1, 16, 14, 21));
//                } catch (SAXException ex) {
//                    Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ParserConfigurationException ex) {
//                    Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (TransformerException ex) {
//                    Logger.getLogger(RS_Project.class.getName()).log(Level.SEVERE, null, ex);
//                }
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
    
    
