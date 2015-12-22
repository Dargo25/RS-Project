/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.File;
import java.io.IOException;
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

    public static void scanXml(List<Subject> subjectList) {
        try {
            File file = new File("src/MainClasses/Subjects.xml");
            
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                scanNode(doc.getChildNodes(), subjectList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void scanNode(NodeList nodeList, List<Subject> subjectList) {
        Subject subj = new Subject();
        
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if ("Subject".equals(tempNode.getNodeName())) {
                subj = new Subject();
            }

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                // get node name and value
                if ("subjectName".equals(tempNode.getNodeName())) {
                    subj.setSubjectName(tempNode.getTextContent());
                }
                if ("lecturerName".equals(tempNode.getNodeName())) {
                    subj.setLecturerName(tempNode.getTextContent());
                }
                if ("audience".equals(tempNode.getNodeName())) {
                    subj.setAudience(Integer.parseInt(tempNode.getTextContent()));
                }
                if ("housing".equals(tempNode.getNodeName())) {
                    subj.setHousing(Integer.parseInt(tempNode.getTextContent()));
                }
                if ("numberInTable".equals(tempNode.getNodeName())) {
                    subj.setNumberInTable(Integer.parseInt(tempNode.getTextContent()));
                }
                if ("dayOfWeek".equals(tempNode.getNodeName())) {
                    if ("Monday".equals(tempNode.getTextContent())) {
                        subj.setDay(DaysOfWeek.MONDAY);
                    }
                    if ("Tuesday".equals(tempNode.getTextContent())) {
                        subj.setDay(DaysOfWeek.TUESDAY);
                    }
                    if ("Wednesday".equals(tempNode.getTextContent())) {
                        subj.setDay(DaysOfWeek.WEDNESDAY);
                    }
                    if ("Thursday".equals(tempNode.getTextContent())) {
                        subj.setDay(DaysOfWeek.THURSDAY);
                    }
                    if ("Friday".equals(tempNode.getTextContent())) {
                        subj.setDay(DaysOfWeek.FRIDAY);
                    }
                    if ("Saturday".equals(tempNode.getTextContent())) {
                        subj.setDay(DaysOfWeek.SATURDEY);
                    }
                }

                if ("dayOfWeek".equals(tempNode.getNodeName())) {
                    subjectList.add(subj);
                }

                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    scanNode(tempNode.getChildNodes(), subjectList);
                }
            }
        }
    }
    
    public static void ChangeXML(/*DaysOfWeek day, int numberInTable, EventType type, String header, String content, EventTime time*/) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String xmlFile = "src/MainClasses/Subjects.xml";
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        
        NodeList subjects = document.getFirstChild().getChildNodes();
        
//        for(int i = 0; i < subjects.getLength(); i++){
//            Node curSubject = subjects.item(i);
//            if(curSubject.getAttributes().getNamedItem("id").getTextContent().equals("2")){
//                Node quantity = curSubject.getChildNodes().item(1);
//                quantity.setTextContent("250");
//            }
//        }
        
        
        Node rootElement = document.getFirstChild();
        Element subject = document.createElement("Subject");
        rootElement.appendChild(subject);
        Element subjectName = document.createElement("subjectName");
        subjectName.setTextContent("Хахаха");
        Element lecturerName = document.createElement("lecturerName");
        lecturerName.setTextContent("Препод");
        Element audience = document.createElement("audience");
        audience.setTextContent("666");
        Element housing = document.createElement("housing");
        housing.setTextContent("9");
        Element numberInTable = document.createElement("numberInTable");
        numberInTable.setTextContent("3");
        Element dayOfWeek = document.createElement("dayOfWeek");
        dayOfWeek.setTextContent("Monday");
        subject.appendChild(subjectName);
        subject.appendChild(lecturerName);
        subject.appendChild(audience);
        subject.appendChild(housing);
        subject.appendChild(numberInTable);
        subject.appendChild(dayOfWeek);
        
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(xmlFile));
        transformer.transform(source, result);
    }
}
