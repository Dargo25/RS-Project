/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

/**
 *
 * @author Samosad
 */
public final class ParseXML {
    private static File xmlFile = new File("src/MainClasses/Subjects.xml");
    
    public static void scanXml(ArrayList<Subject> subjectList) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            if (doc.hasChildNodes()) {
                scanNode(doc.getChildNodes(), subjectList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void scanNode(NodeList nodeList, ArrayList<Subject> subjectList) {
        Subject subject = new Subject();
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node subjectChildNode = nodeList.item(i);
            
            if ("Subject".equals(subjectChildNode.getNodeName())) {
                subject = new Subject();
            }
            boolean isLastNode = false;
            // make sure it's element node.
            if (subjectChildNode.getNodeType() == Node.ELEMENT_NODE) {
                // get node name and value
                if ("subjectName".equals(subjectChildNode.getNodeName())) {
                    subject.setSubjectName(subjectChildNode.getTextContent());
                }
                if ("lecturerName".equals(subjectChildNode.getNodeName())) {
                    subject.setLecturerName(subjectChildNode.getTextContent());
                }
                if ("audience".equals(subjectChildNode.getNodeName())) {
                    subject.setAudience(Integer.parseInt(subjectChildNode.getTextContent()));
                }
                if ("housing".equals(subjectChildNode.getNodeName())) {
                    subject.setHousing(Integer.parseInt(subjectChildNode.getTextContent()));
                }
                if ("dayOfWeek".equals(subjectChildNode.getNodeName())) {
                    
                    /*ВОЗМОЖНО ПЕРЕДЕЛАТЬ НА СРАВНЕНИЕ ЧИСЕЛ И В XML ХРАНИТЬ НЕ НАЗВАНИЯ ДНЯ НЕДЕЛИ А ЕГО НОМЕР Т.К. ЭТО ЕНАМ И ЕГО МОЖНО ПРЕДСТАВЛЯТЬ ЧИСЛАМИ*/
                    
                    if ("Monday".equals(subjectChildNode.getTextContent())) {
                        subject.setDay(DaysOfWeek.MONDAY);
                    }
                    if ("Tuesday".equals(subjectChildNode.getTextContent())) {
                        subject.setDay(DaysOfWeek.TUESDAY);
                    }
                    if ("Wednesday".equals(subjectChildNode.getTextContent())) {
                        subject.setDay(DaysOfWeek.WEDNESDAY);
                    }
                    if ("Thursday".equals(subjectChildNode.getTextContent())) {
                        subject.setDay(DaysOfWeek.THURSDAY);
                    }
                    if ("Friday".equals(subjectChildNode.getTextContent())) {
                        subject.setDay(DaysOfWeek.FRIDAY);
                    }
                    if ("Saturday".equals(subjectChildNode.getTextContent())) {
                        subject.setDay(DaysOfWeek.SATURDAY);
                    }
                }

                if ("numberInTable".equals(subjectChildNode.getNodeName())) {
                    subject.setNumberInTable(Integer.parseInt(subjectChildNode.getTextContent()));
                }
                
                if ("Events".equals(subjectChildNode.getNodeName())) {
                    NodeList events = subjectChildNode.getChildNodes(); //Список событий для предмета
                    if (events.getLength() != 0) {
                        for (int k = 0; k < events.getLength(); k++) { //Проходимся по тегам Event
                            if ("Event".equals(events.item(k).getNodeName())) {
                                SubjectEvent currentEvent = new SubjectEvent(); //Текущее просматриваемое событие
                                EventTime currentTime = new EventTime();  
                                Date date = new Date();
                             
                                Node currentEventNode = events.item(k);  //Текущий тег Event
                                NodeList eventNodeChildren = currentEventNode.getChildNodes(); //Его дети
                                
                                for (int z = 0; z < eventNodeChildren.getLength(); z++) { //Проходимся по дочерним тегам Event
                                    Node eventChild = eventNodeChildren.item(z); //Текущий тег-ребенок от тега Event
                                    if ("type".equals(eventChild.getNodeName())) {
                                        if ("SINGLE".equals(eventChild.getTextContent())) {
                                            currentEvent.setType(EventType.SINGLE);
                                        }
                                        else if ("PERIODIC".equals(eventChild.getTextContent())) {
                                            currentEvent.setType(EventType.PERIODIC);
                                        }
                                    }
                                    if ("header".equals(eventChild.getNodeName())) {
                                        currentEvent.setHeader(eventChild.getTextContent());
                                    }
                                    if ("content".equals(eventChild.getNodeName())) {
                                        currentEvent.setContent(eventChild.getTextContent());
                                    }
                                    if ("date".equals(eventChild.getNodeName())) {
                                        String[] dateArray = eventChild.getTextContent().split("[.]");
                                        date.setDate(Integer.parseInt(dateArray[0]));
                                        date.setMonth(Integer.parseInt(dateArray[1]) - 1);
                                        date.setYear(Integer.parseInt(dateArray[2]) - 1900);
                                        date.setHours(Integer.parseInt(dateArray[3]));
                                        date.setMinutes(Integer.parseInt(dateArray[4]));
                                        date.setSeconds(0);
                                    }
                                }
                                
                                currentTime.setDate(date);
//                                currentEvent.getTimeList().add(currentTime);
                                currentEvent.setTime(currentTime);
                                subject.getEventList().add(currentEvent);
                            }
                        }
                    }
                }
                
                /*Добавляет предмет в лист предметов когда обошел все дочерние тэги*/
                if ("Events".equals(subjectChildNode.getNodeName())) {
                    subjectList.add(subject);
                }

                if (subjectChildNode.hasChildNodes()) {
                    // loop again if has child nodes
                    scanNode(subjectChildNode.getChildNodes(), subjectList);
                }
            }
        }
    }
    
    public static void AddNewEventToXML(Subject currentSubject) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        //String xmlFile = "src/MainClasses/Subjects.xml";
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        
        NodeList subjects = document.getFirstChild().getChildNodes(); // Получаем список тегов Subject
        
        for(int i = 0; i < subjects.getLength(); i++){
            Node curSubject = subjects.item(i); //текущий тег Subject
            NodeList subjectChildren = curSubject.getChildNodes(); //дочерние теги Subject
            boolean isCorrectDay = false; /*для проверки, работаем ли мы с предметами того дня, который указал пользователь*/
            boolean isCorrectSubjectNumber = false; /*для проверки, работаем ли мы с нужным пользователю предметом, т.е. проверяем номер предмета в расписании*/
            for (int j = 0; j < subjectChildren.getLength(); j++ ) {
                Node child = subjectChildren.item(j); //текущий дочерний тег Subject-a
                if ("dayOfWeek".equals(child.getNodeName()) && currentSubject.getDay().toString().toLowerCase().equals(child.getTextContent().toLowerCase())) {
                    isCorrectDay = true;
                }
                if (isCorrectDay && "numberInTable".equals(child.getNodeName()) && Integer.parseInt(child.getTextContent()) == currentSubject.getNumberInTable()) {
                    isCorrectSubjectNumber = true;
                }
                if (isCorrectSubjectNumber && "Events".equals(child.getNodeName())) {
                    SubjectEvent currentEvent = currentSubject.getEventList().get(currentSubject.getEventList().size() - 1);
//                    EventTime currentTime = currentEvent.getTimeList().get(0);
                    EventTime currentTime = currentEvent.getTime();
                    Element event = document.createElement("Event");
                    
                    /*УЧЕСТЬ В ДАЛЬНЕЙШЕМ ДРУГОЙ ТИП СОБЫТИЯ*/
                    
                    Element type = document.createElement("type");
                    type.setTextContent(currentEvent.getType().toString());
                    Element header = document.createElement("header");
                    header.setTextContent(currentEvent.getHeader());
                    Element content = document.createElement("content");
                    content.setTextContent(currentEvent.getContent());
                    Element date = document.createElement("date");
                    
                    /*УЧЕСТЬ В ДАЛЬНЕЙШЕМ ЧТО В ЛИСТЕ МОЖЕТ БЫТЬ НЕСКОЛЬКО ВРЕМЕН */
                    
                    date.setTextContent(currentTime.getDate().getDate() + "." + (currentTime.getDate().getMonth() + 1) + "." + (currentTime.getDate().getYear() + 1900)
                            + "." + currentTime.getDate().getHours() + "." + currentTime.getDate().getMinutes());
//                    Element startTime = document.createElement("startTime");
//                    startTime.setTextContent(currentTime.getDate().getHours() + ":" + currentTime.getDate().getMinutes());
                    
                    /*УЧЕСТЬ ДОБАВЛЕНИЕ ТЭГОВ endTime и interval*/
                    
                    event.appendChild(type);
                    event.appendChild(header);
                    event.appendChild(content);
                    event.appendChild(date);
//                    event.appendChild(startTime);
                    child.appendChild(event);
                }
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }
    
    public static void RemoveEventFromXml(Subject currentSubject, SubjectEvent currentEvent) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        
        NodeList subjects = document.getFirstChild().getChildNodes(); // Получаем список тегов Subject
        Date currentEventDate = currentEvent.getTime().getDate();
        String dateInString = String.valueOf(currentEventDate.getDate()) + "." + String.valueOf(currentEventDate.getMonth() + 1) + "." + String.valueOf(currentEventDate.getYear() + 1900) + 
                "." + String.valueOf(currentEventDate.getHours()) + "." + String.valueOf(currentEventDate.getMinutes());
        
        for(int i = 0; i < subjects.getLength(); i++){
            Node curSubject = subjects.item(i); //текущий тег Subject
            NodeList subjectChildren = curSubject.getChildNodes(); //дочерние теги Subject
            boolean isCorrectDay = false; /*для проверки, работаем ли мы с предметами того дня, который указал пользователь*/
            boolean isCorrectSubjectNumber = false; /*для проверки, работаем ли мы с нужным пользователю предметом, т.е. проверяем номер предмета в расписании*/
            for (int j = 0; j < subjectChildren.getLength(); j++ ) { //Идем по дочерним тегам Subject-a
                Node subjectChild = subjectChildren.item(j); //текущий дочерний тег Subject-a
                if ("dayOfWeek".equals(subjectChild.getNodeName()) && currentSubject.getDay().toString().toLowerCase().equals(subjectChild.getTextContent().toLowerCase())) {
                    isCorrectDay = true;
                }
                if (isCorrectDay && "numberInTable".equals(subjectChild.getNodeName()) && Integer.parseInt(subjectChild.getTextContent()) == currentSubject.getNumberInTable()) {
                    isCorrectSubjectNumber = true;
                }
                if (isCorrectSubjectNumber && "Events".equals(subjectChild.getNodeName())) {
                    Node events = subjectChild;
                    NodeList eventList = subjectChild.getChildNodes(); //Лист тегов Event
                    
                    for (int v = 0; v < eventList.getLength(); v++) { // Идем по Event-ам
                        Node event = eventList.item(v); //Текущий тег Event
                        NodeList eventChildren = event.getChildNodes(); //Его дочерние теги
                        boolean isCorrectType = false; 
                        boolean isCorrectHeader = false;  /*ЭТО ВСЁ ДЛЯ ПОИСКА НУЖНОГО СОБЫТИЯ ДЛЯ УДАЛЕНИЯ*/
                        boolean isCorrectContent = false;
                        boolean isCorrectDate = false;
                        for (int b = 0; b < eventChildren.getLength(); b++) { //Идем по списку дочерних тегов Event-a
                            Node eventChild = eventChildren.item(b); //Текущий дочерний тег
                            if ("type".equals(eventChild.getNodeName()) && currentEvent.getType().toString().toLowerCase().equals(eventChild.getTextContent().toLowerCase())) {
                                isCorrectType = true;
                            }
                            if ("header".equals(eventChild.getNodeName()) && currentEvent.getHeader().toString().equals(eventChild.getTextContent())) {
                                isCorrectHeader = true;
                            }
                            if ("content".equals(eventChild.getNodeName()) && currentEvent.getContent().toString().equals(eventChild.getTextContent())) {
                                isCorrectContent = true;
                            }
                            if ("date".equals(eventChild.getNodeName()) && dateInString.equals(eventChild.getTextContent())) {
                                isCorrectDate = true;
                            }
                        }
                        
                        if (isCorrectType && isCorrectHeader && isCorrectContent && isCorrectDate) {
                            events.removeChild(event);
                            break;
                        }
                    }
                }
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }
}
