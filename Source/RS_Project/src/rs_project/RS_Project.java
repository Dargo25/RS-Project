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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 *
 * @author Егор
 */
public class RS_Project extends Application {
    //Это поле коллекция пар. Юзай его.
    private List<Subject> subjectList = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Button btn = new Button();
        btn.setText("Say 'Wake up, Artem!'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            /*При нажатии клавиши на форме парсится xml,
             * а затем выводится название предмета у 1-го объекта коллекции,
             * т.е. выводится нахвание предмета первой пары
             */
            @Override
            public void handle(ActionEvent event) {
                ParseXML.scanXml(subjectList);
                System.out.println(subjectList.get(0).getSubjectName());
            }
        });
        
        Pane root = new Pane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /* Писать начиная с этой строки. Твоя задача - сделать вывод в консоль рассписания. Выглядеть должно так:
         * 1. Математика
         *     Плетнев; 528/1
         * 2. Физика
         *     Плетнев; 528/1
         * 3. Химия
         *     Плетнев; 528/1
         * 4. БЛабла
         *     Плетнев; 528/1
         * 5. Блабла
         *     Плетнев; 528/1
         * 6. Блаблаа
         *     Плетнев; 528/1
         * Далее прописывай ввод в консоль. Т.е. ты вводишь цифру от 1 до 6,
         * тем самым выбирая предмет, в котором ты хочешь создать событие.
         * Сравниваешь цифру с полем numberInTable у объектов коллеции.
         * Создаешь объект события. Начинаешь делать стандартную хрень: (пример Консоль)
         * Тип напоминания:
         * (человек пишет) Single или Periodic
         * Введите заголовок:
         * (человек пишет) блаблабла
         * Введите содержимое напоминания:
         * (человек пишет) блабалабал
         * -->(Конец примера)
         * Если есть желание и время можешь доделать до конца (т.е. создается объект EventTime, ввод даты,
         * ввод времени (тут должна быть проверка, если single, то вводишь singleEventTime, если Periodic
         * то вводишь startTime и endTime)).
         * Далее все что ввел ты запихиваешь в объект события (если сделаешь и дату/время, то объект EventTime запихиваешь
         * в поле timeList у объекта события.
         * А затем объект события пихаешь в поле eventList у объкта предмета.
         * P.S. Пока что пусть в xml будет только одна пара. Но вывод рассписания и выбор пары для события делай с учетом что их до 6 штук. 
         * Всё очень просто, так что дерзай!
         * 
         */
    }
    
}
