/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Егор
 */
public final class DisplayShedule {

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
        int subjNumber = scan.nextInt();
        if (GetSubjetForEvent(subjNumber, subjectList) != null) {
            currentSubject = GetSubjetForEvent(subjNumber, subjectList);
            System.out.println(currentSubject.getSubjectName());
        } else {
            System.out.println("Неверный номер предмета");
        }

        //Создание события и заполнение полей
        SubjectEvent myEvent = new SubjectEvent();

        //Заполнение типа
        System.out.println("Введите тип");
        String type = scan.next();
        if ("single".equals(type)) {
            myEvent.setType(EventType.SINGLE);
        } else {
            if ("periodic".equals(type)) {
                myEvent.setType(EventType.PERIODIC);
            } else {
                System.out.println("Неверный формат");
            }
        }

        //Заполнение заголовка
        System.out.println("Введите заголовок");
        String header = scan.nextLine();
        myEvent.setHeader(header);
        //Заполнение текста
        System.out.println("Введите текст");
        String content = scan.nextLine();
        myEvent.setContent(content);
        //Добавим время запуска
        System.out.println("Введите год, месяц, день, час, минуты");
        int year = scan.nextInt();
        int month = scan.nextInt();
        int day = scan.nextInt();
        int hour = scan.nextInt();
        int minutes = scan.nextInt();
        EventTime time = new EventTime();
        time.setDate(new Date(year-1900, month-1, day, hour, minutes));
        myEvent.getTimeList().add(time);
        
        currentSubject.getEventList().add(myEvent);
        
        try {
            ParseXML.AddNewEventToXML(currentSubject);
        } catch (SAXException ex) {
            Logger.getLogger(DisplayShedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DisplayShedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DisplayShedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DisplayShedule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
