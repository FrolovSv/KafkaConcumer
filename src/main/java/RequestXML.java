import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RequestXML {

    DocumentBuilderFactory dbf;
    DocumentBuilder db ;
    Document doc;

    private String messageID;
    private String rqUid;
    private String rqTime;
    private String SPName;
    private String SCName;
    private List<String> participantDocID = new ArrayList<>();

    public String getMessageID() {
        return messageID;
    }

    public String getRqUid() {
        return rqUid;
    }

    public String getRqTime() {
        return rqTime;
    }

    public String getSPName() {
        return SPName;
    }

    public String getSCName() {
        return SCName;
    }

    public List<String> getParticipantDocID() {
        return participantDocID;
    }

    public RequestXML(InputStream inputStream){
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            Node node = doc.getElementsByTagName("header").item(0);
            Element eElement1 = (Element) node;
//            System.out.println("messageID : " + eElement1.getElementsByTagName("messageID").item(0).getTextContent());
//            System.out.println("rqUid : " + eElement1.getElementsByTagName("rqUid").item(0).getTextContent());
//            System.out.println("rqTime : " + eElement1.getElementsByTagName("rqTime").item(0).getTextContent());
//            System.out.println("SPName : " + eElement1.getElementsByTagName("SPName").item(0).getTextContent());
//            System.out.println("SCName : " + eElement1.getElementsByTagName("SCName").item(0).getTextContent());
            messageID = eElement1.getElementsByTagName("messageID").item(0).getTextContent();
            rqUid = eElement1.getElementsByTagName("messageID").item(0).getTextContent();
            rqTime = eElement1.getElementsByTagName("messageID").item(0).getTextContent();
            SPName = eElement1.getElementsByTagName("messageID").item(0).getTextContent();
            SCName = eElement1.getElementsByTagName("messageID").item(0).getTextContent();

            Node node1 = doc.getElementsByTagName("participant").item(0);
            Element eElement = (Element) node1;
            for (int i = 0; i < eElement.getElementsByTagName("docID").getLength();i++) {
                //System.out.println("docID : " + eElement.getElementsByTagName("docID").item(i).getTextContent());
                participantDocID.add(eElement.getElementsByTagName("docID").item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
