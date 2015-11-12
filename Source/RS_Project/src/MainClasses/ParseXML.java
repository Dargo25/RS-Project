/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
}
