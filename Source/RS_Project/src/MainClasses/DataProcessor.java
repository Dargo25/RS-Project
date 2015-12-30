/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import rs_project.RS_Project;
import rs_project.frmAddSubjectEvent;

/**
 *
 * @author Егор
 */
public final class DataProcessor {
    public static DaysOfWeek GetNextDay(DaysOfWeek cDay) {
        if (cDay == DaysOfWeek.MONDAY) {
            return DaysOfWeek.TUESDAY;
        }

        if (cDay == DaysOfWeek.TUESDAY) {
            return DaysOfWeek.WEDNESDAY;
        }

        if (cDay == DaysOfWeek.WEDNESDAY) {
            return DaysOfWeek.THURSDAY;
        }

        if (cDay == DaysOfWeek.THURSDAY) {
            return DaysOfWeek.FRIDAY;
        }

        if (cDay == DaysOfWeek.FRIDAY) {
            return DaysOfWeek.SATURDAY;
        }

        if (cDay == DaysOfWeek.SATURDAY) {
            return DaysOfWeek.MONDAY;
        }
        return null;
    }

    public static DaysOfWeek GetPreviousDay(DaysOfWeek cDay) {
        if (cDay == DaysOfWeek.TUESDAY) {
            return DaysOfWeek.MONDAY;
        }

        if (cDay == DaysOfWeek.WEDNESDAY) {
            return DaysOfWeek.TUESDAY;
        }

        if (cDay == DaysOfWeek.THURSDAY) {
            return DaysOfWeek.WEDNESDAY;
        }

        if (cDay == DaysOfWeek.FRIDAY) {
            return DaysOfWeek.THURSDAY;
        }

        if (cDay == DaysOfWeek.SATURDAY) {
            return DaysOfWeek.FRIDAY;
        }

        if (cDay == DaysOfWeek.MONDAY) {
            return DaysOfWeek.SATURDAY;
        }
        return null;
    }
    
    public static DaysOfWeek ChooseCurrentDay(boolean isFirstLaunch, DaysOfWeek currentDay) {
        if (isFirstLaunch) {
            Date today = new Date();
            DaysOfWeek[] d = DaysOfWeek.values();
            int dayNumber = today.getDay();
            if (dayNumber == 0) {
                dayNumber = 6;
            }
            return d[dayNumber - 1];
        }
        return currentDay;
    }
    
    public static Subject GetSubjectByNumber(ArrayList<Subject> currentSbj, int number) {
        for (Subject sbj : currentSbj) {
            if (sbj.getNumberInTable() == number) {
                return sbj;
            }
        }
        return null;
    }
    
    public static void WriteEventToXML(Subject userSubject) {
        try {
            WorkWithXML.AddNewEventToXML(userSubject);
        } catch (SAXException ex) {
            Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(frmAddSubjectEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String GetLabelText(SubjectEvent sbjEv) {
        String strLabel = "";

        strLabel = sbjEv.getHeader();
        String strDate = sbjEv.getTime().getDate().toString();

        String strDay = strDate.substring(8, 20);

        strDate = strDate.substring(4, 10);
        String strMonth = strDate.substring(0, 3);
        String currentMonth;
        switch (strMonth) {
            case "Dec":
                currentMonth = "Декабрь";
                break;
            case "Jan":
                currentMonth = "Январь";
                break;
            case "Feb":
                currentMonth = "Февраль";
                break;
            case "Mar":
                currentMonth = "Март";
                break;
            case "Apr":
                currentMonth = "Апрель";
                break;
            case "May":
                currentMonth = "Май";
                break;
            case "Jun":
                currentMonth = "Июнь";
                break;
            case "Jul":
                currentMonth = "Июль";
                break;
            case "Aug":
                currentMonth = "Август";
                break;
            case "Sep":
                currentMonth = "Сентябрь";
                break;
            case "Oct":
                currentMonth = "Октябрь";
                break;
            case "Nov":
                currentMonth = "Ноябрь";
                break;
            default:
                currentMonth = "-1";
                break;
        }
        strDate = currentMonth;
        strLabel = strLabel + "\n\t" + strDate + " " + strDay;
        return strLabel;
    }
    
    public static ArrayList<Subject> ScanXML(ArrayList<Subject> subjectList) {
        WorkWithXML.scanXml(subjectList);
        return subjectList;
    }
}
