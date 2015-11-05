/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.util.List;
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        //Поиск предмета по номеру + проверка на корректность
        System.out.println("Введите номер предмета");
        int subjNumber = scan.nextInt();
        if (GetSubjetForEvent(subjNumber, subjectList) != null) {
            System.out.println(GetSubjetForEvent(subjNumber, subjectList));
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
            if ("pereodic".equals(type)) {
                myEvent.setType(EventType.SINGLE);
            } else {
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

    private static String[] GetInfo(List<Subject> subjectList) {
        ParseXML.scanXml(subjectList);
        String[] info = new String[subjectList.size()];
        for (int i = 0; i < subjectList.size(); i++) {
            info[i] = String.format(subjectList.get(i).getNumberInTable() + ". " + subjectList.get(i).getSubjectName() + "\n\t" + subjectList.get(i).getLecturerName() + "; " + subjectList.get(i).getAudience() + "/" + subjectList.get(i).getHousing());
        }
        return info;
    }

    private static String GetSubjetForEvent(int i, List<Subject> subjectList) {
        String subjectName = null;
        for (Subject subjectList1 : subjectList) {
            if (subjectList1.getNumberInTable() == i) {
                subjectName = subjectList1.getSubjectName();
            }
        }
        return subjectName;
    }
}
