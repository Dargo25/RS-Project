/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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
