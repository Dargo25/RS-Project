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
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;


/**
 *
 * @author Samosad
 */
public class frmEventMessage extends Application {

    Label lblEventName;
    Label lblEventContent;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Сработало событие!");
        GridPane root = CreateGrid();
        Scene scn = new Scene(root,350,200);
        primaryStage.setScene(scn);
        primaryStage.show();
        
        
    }

    private GridPane CreateGrid() {
    GridPane root = new GridPane();
    lblEventName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
    lblEventContent.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
    root.add(lblEventName, 0, 0, 2, 1);
    root.add(lblEventContent, 2,2);
    return root;
    }
    
    public void SetEventName(String eventName){
        lblEventName = new Label(eventName);
    }
    
    public void SetEventContent(String eventContent){
        lblEventContent= new Label(eventContent);
    }
    
}
