import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TakerXmlClass {
    private String pageWidth, pageHeight;
    private List<Column> listOfColumns;

    public void getSettings (File file) throws SAXException, IOException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList page = doc.getElementsByTagName("page");
        NodeList nList = doc.getElementsByTagName("column");

        Node nPage = page.item(0);
        //System.out.println("Page: " + nPage.getNodeName());

        if (nPage.getNodeType() == Node.ELEMENT_NODE) {
            Element pageElem = (Element) nPage;

            Node nodePageWidth = pageElem.getElementsByTagName("width").item(0);
            pageWidth = nodePageWidth.getTextContent();

            Node nodePageHeight = pageElem.getElementsByTagName("height").item(0);
            pageHeight = nodePageHeight.getTextContent();
        }
        listOfColumns = new ArrayList<Column>();
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

           // System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                Node node2 = elem.getElementsByTagName("width").item(0);
                String width = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("title").item(0);
                String title = node3.getTextContent();
                if(title.equals("Номер")) listOfColumns.add(new NumberColumn(title, width));
                if(title.equals("Дата")) listOfColumns.add(new DateColumn(title, width));
                if(title.equals("ФИО")) listOfColumns.add(new NameColumn(title, width));
            }
        }
    }
    public String getPageWidth(){
        return pageWidth;
    }
    public String getPageHeight(){
        return pageHeight;
    }
    public List<Column> getListOfColumns(){
        return listOfColumns;
    }
}

