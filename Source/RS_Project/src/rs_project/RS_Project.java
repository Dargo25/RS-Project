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
                //--Тут выводит все предметы в консоль
                String [] info = GetInfo();
                for (int i=0;i<info.length;i++){
                    System.out.println(info[i]);
                }
                Scanner scan = new Scanner(System.in);
                //Поиск предмета по номеру + проверка на корректность
                System.out.println("Введите номер предмета");
                int subjNumber = scan.nextInt();
                if (GetSubjetForEvent(subjNumber)!=null){
                    System.out.println(GetSubjetForEvent(subjNumber));
                }
                else {
                    System.out.println("Неверный номер предмета");
                }
                
                  //Создание события и заполнение полей
                    SubjectEvent myEvent = new SubjectEvent();
                    
                    //Заполнение типа
                    System.out.println("Введите тип");
                    String type = scan.next();
                    if ("single".equals(type)){
                    myEvent.setType(EventType.SINGLE);
                    }
                    else{
                     if ("pereodic".equals(type))  {
                       myEvent.setType(EventType.SINGLE);  
                     }
                     else {
                         System.out.println("Неверный формат");
                     }
                     }
                    
                    //Заполнение заголовка
                    System.out.println("Введите заголовок");
                    myEvent.setHeader(scan.next());
                    //Заполнение текста
                    System.out.println("Введите текст");
                    myEvent.setContent(scan.next());
                     
                        
                    
            }
        });
        
        Pane root = new Pane();
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
//        GetInfo();
    }
    
    public static  String [] GetInfo (){
        ParseXML.scanXml(subjectList);
        String [] info = new String[subjectList.size()];
        for (int i=0;i<subjectList.size();i++) {
            info[i] = String.format(subjectList.get(i).getSubjectName()+"\n\t"+subjectList.get(i).getNumberInTable()+"."+subjectList.get(i).getLecturerName() + "; " + subjectList.get(i).getAudience() + "/" + subjectList.get(i).getHousing());
            }
        return info;
    }
    
    public static String GetSubjetForEvent (int i){
    String subjectName = null;
            for (Subject subjectList1 : subjectList) {
                if (subjectList1.getNumberInTable()==i){
                    subjectName = subjectList1.getSubjectName();
                }
            }
            return subjectName;
            }
    }
    
