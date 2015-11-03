/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Samosad
 */
public class ParseXML { 
    static NodeList list; 
    public static void Inicialize () throws ParserConfigurationException, SAXException, IOException{ 
    File xmlFile = new File("Subjects.xml");
    DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(xmlFile);
    list = doc.getElementsByTagName("Subjects");
    }
    
    public static void GetSubjectInformation (){
        
        for (int i = 0;i<list.getLength();i++) {
            
            Node node = list.item(i);
        }
        }
            
    }
    
