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
import com.sun.deploy.trace.Trace;
import com.sun.javafx.geom.Vec2f;
import com.sun.javaws.Main;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Samosad
 */
public class frmEventInfo extends Application {

    ArrayList<SubjectEvent> events;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Задания");
        
        ArrayList<Label> lblList=CreateLabels(events);
        GridPane grid = CreateGrid(lblList);
        //GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public void SetEvents(ArrayList<SubjectEvent> ev){
        events = ev;
    }
    
    private ArrayList<Label> CreateLabels(ArrayList<SubjectEvent> events){
        ArrayList<Label> lblList = new ArrayList<>();
        for (SubjectEvent sbjEv : events ){
            Label tempLbl = new Label(GetLabelText(sbjEv));
            lblList.add(tempLbl);
        }
        return lblList;
    }
    
    private GridPane CreateGrid(ArrayList<Label> lblList){
        GridPane grid = new GridPane();
        for (int i = 0; i<lblList.size();i++){
        grid.add(lblList.get(i), 1, i+1);
    }
        return grid;
    }
    
    private String GetLabelText(SubjectEvent sbjEv){
        String strLabel = "";
        
        strLabel = sbjEv.getHeader()+", Дата -"+ sbjEv.getTimeList().get(0).getDate().toString();
        
        return strLabel;
    }
    
}
