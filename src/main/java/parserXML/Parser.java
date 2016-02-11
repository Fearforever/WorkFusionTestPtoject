package parserXML;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Parser {
	
	public Float parseNB(String str){
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Float usdbyrRate = 0f;
		
		try {
			
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new ByteArrayInputStream(str.getBytes("utf-8"))));
			NodeList currencyList = doc.getElementsByTagName("Currency");
			
			for (int i=0; i<currencyList.getLength(); i++){
				
				Node curr = currencyList.item(i);
				NamedNodeMap currAttr = curr.getAttributes();
				Node idAttr = currAttr.getNamedItem("Id");
				String idValue = idAttr.getNodeValue();
				
				if ("145".equals(idValue)){
					
					NodeList usdNodeList = curr.getChildNodes();
					
					for (int j = 0; j<usdNodeList.getLength(); j++){
						
						Node usdNode = usdNodeList.item(j);
						
						if("Rate".equals(usdNode.getNodeName())){
							
							Node rateNode = usdNode.getFirstChild();
							String strRate = rateNode.getNodeValue();
							usdbyrRate = new Float(strRate);
						}
					}
				}		
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usdbyrRate;
	}
	
	public Float parseOnda(String str){
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Float usdCrcRate = 0f;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new ByteArrayInputStream(str.getBytes("utf-8"))));
			NodeList midpointList = doc.getElementsByTagName("midpoint");
			for (int i = 0; i < midpointList.getLength(); i++){
				Node midpoint = midpointList.item(i);
				Node midpointValue =  midpoint.getFirstChild();
				String rate = midpointValue.getNodeValue();
				usdCrcRate = new Float(rate);				
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usdCrcRate;
	}

}
