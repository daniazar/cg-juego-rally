package ar.edu.itba.it.cg.Rally3D.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtil {

	private static final Logger logger = Logger.getLogger(XMLUtil.class.getName());

	private static DocumentBuilder getDocumentBuilder() {
		try {
			DocumentBuilderFactory factory;
			factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setIgnoringComments(true);
			return factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {

			logger.severe("Parsing XML files" + e.getMessage());
			throw new AssertionError(e);
		}
	}

	public static Document newDocument() {
		return getDocumentBuilder().newDocument();
	}

	public static Document parseXML(String xml) throws SAXException,
			IOException {
		URL url;
//		System.out.println("name: "+xml); 
		url = XMLUtil.class.getClassLoader().getResource(xml);		
		return getDocumentBuilder().parse(url.openStream());
	}

	public static Document parseXML(InputStream stream) throws SAXException,
			IOException {
		Document document = getDocumentBuilder().parse(stream);
		stream.close();
		return document;
	}

    public static String getNodeAttribute(Element element, String attribute)
    {
        return element.getAttribute(attribute);
    }

    public static List<Element> getChildNodes(Element parent)
    {
        NodeList list = parent.getChildNodes();
        List<Element>  children = new ArrayList<Element> (list.getLength());
        for(int i = 0; i < list.getLength(); i++)
        {
            Node child = list.item(i);
            if(child.getNodeType() == 1)
                children.add((Element)child);
        }

        return children;
    }

    public static List<Element>  getChildNodes(Element parent, String name)
    {
        NodeList list = parent.getElementsByTagName(name);
        List<Element>  children = new ArrayList<Element> (list.getLength());
        for(int i = 0; i < list.getLength(); i++)
        {
            Node child = list.item(i);
            if(child.getNodeType() == 1)
                children.add((Element)child);
        }

        return children;
    }

    public static Element getFirstChild(Element parent, String name)
    {
        NodeList list = parent.getElementsByTagName(name);
        for(int i = 0; i < list.getLength(); i++)
        {
            Node child = list.item(i);
            if(child.getNodeType() == 1)
                return (Element)child;
        }

        return null;
    }

    public static String getChildAttribute(Element parent, String childName, String aName)
    {
        Element child = getFirstChild(parent, childName);
        return child != null ? getNodeAttribute(child, aName) : null;
    }


    

}
