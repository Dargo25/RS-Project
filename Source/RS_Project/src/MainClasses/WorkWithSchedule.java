/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
public final class WorkWithSchedule {

    public static void Display(List<Subject> subjectList) {
        String[] info = GetInfo(subjectList);
        
        for (int i = 0; i < info.length; i++) {
            System.out.println(info[i]);
        }
    }

    private static String[] GetInfo(List<Subject> subjectList) {
        ParseXML.scanXml(subjectList);
        String[] info = new String[subjectList.size()];
        for (int i = 0; i < subjectList.size(); i++) {
            info[i] = subjectList.get(i).getNumberInTable() + ". " + subjectList.get(i).getSubjectName() + "\n\t" + subjectList.get(i).getLecturerName() + "; " + subjectList.get(i).getAudience() + "/" + subjectList.get(i).getHousing();
        }
        return info;
    }

    private static Subject GetSubjetForEvent(int i, List<Subject> subjectList) {
        Subject subject = null;
        for (Subject subjectList1 : subjectList) {
            if (subjectList1.getNumberInTable() == i) {
                subject = subjectList1;
            }
        }
        return subject;
    }
    
    public static void addEventToSubject(List<Subject> subjectList) {
        Subject currentSubject = null;
        Scanner scan = new Scanner(System.in);
        //Поиск предмета по номеру + проверка на корректность
        System.out.println("Введите номер предмета");
        //int subjNumber = scan.nextInt();
        int subjNumber = 1;
        if (GetSubjetForEvent(subjNumber, subjectList) != null) {
            currentSubject = GetSubjetForEvent(subjNumber, subjectList);
            System.out.println(currentSubject.getSubjectName());
        } else {
            System.out.println("Неверный номер предмета");
        }

        //Создание события и заполнение полей
        SubjectEvent event = new SubjectEvent();

        //Заполнение типа
        System.out.println("Введите тип");
        //String type = scan.next();
        String type = "single";
        if ("single".equals(type)) {
            event.setType(EventType.SINGLE);
        } else {
            if ("periodic".equals(type)) {
                event.setType(EventType.PERIODIC);
            } else {
                System.out.println("Неверный формат");
            }
        }

        //Заполнение заголовка
        System.out.println("Введите заголовок");
        //String header = scan.nextLine();
        String header = "Новое событие";
        event.setHeader(header);
        //Заполнение текста
        System.out.println("Введите текст");
        //String content = scan.nextLine();
        String content = "Текст события";
        event.setContent(content);
        //Добавим время запуска
        System.out.println("Введите год, месяц, день, час, минуты");
//        int year = scan.nextInt();
//        int month = scan.nextInt();
//        int day = scan.nextInt();
//        int hour = scan.nextInt();
//        int minutes = scan.nextInt();
        int year = 2015;
        int month = 12;
        int day = 24;
        int hour = 21;
        int minutes = 59;
        EventTime time = new EventTime();
        time.setDate(new Date(year-1900, month-1, day, hour, minutes));
        event.getTimeList().add(time);

        currentSubject.getEventList().add(event);
        CreateTimer(event);
        
        try {
            ParseXML.AddNewEventToXML(currentSubject);
        } catch (SAXException ex) {
            Logger.getLogger(WorkWithSchedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithSchedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WorkWithSchedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(WorkWithSchedule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void CreateTimer(SubjectEvent event) {
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println(event.getHeader() + "\n\t" + event.getContent());
            }
        };
        
        timer.schedule(task, event.getTimeList().get(event.getTimeList().size() - 1).getDate());
    }
}
