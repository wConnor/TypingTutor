import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class FileHandling {

	// methods that are used to check whether or not the courseProgress.xml
	// file exists and, in the case that it doesn't, creates the file.
	public void createXmlFile() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// creates the root course element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("course");
			doc.appendChild(rootElement);
			
			// creates each of the difficulties as individual elements
			// simply to make the .xml file more visually appealing
			Element introDifficulty = doc.createElement("Introduction");
			Element beginnerDifficulty = doc.createElement("Beginner");
			Element intermDifficulty = doc.createElement("Intermediate");
			Element advancedDifficulty = doc.createElement("Advanced");
			Element expertDifficulty = doc.createElement("Expert");
			
			rootElement.appendChild(introDifficulty);
			rootElement.appendChild(beginnerDifficulty);
			rootElement.appendChild(intermDifficulty);
			rootElement.appendChild(advancedDifficulty);
			rootElement.appendChild(expertDifficulty);
			
			// creates the introduction difficulty element and
			// its individual lessons as children
			Node introLesson1Node = createLessonNode("introLesson1", doc, "11");
			Node introLesson2Node = createLessonNode("introLesson2", doc, "12");
			Node introLesson3Node = createLessonNode("introLesson3", doc, "13");
			Node introLesson4Node = createLessonNode("introLesson4", doc, "14");
			Node introLesson5Node = createLessonNode("introLesson5", doc, "15");
			Node introLesson6Node = createLessonNode("introLesson6", doc, "16");
			Node introLesson7Node = createLessonNode("introLesson7", doc, "17");
			
			introDifficulty.appendChild(introLesson1Node);
			introDifficulty.appendChild(introLesson2Node);
			introDifficulty.appendChild(introLesson3Node);
			introDifficulty.appendChild(introLesson4Node);
			introDifficulty.appendChild(introLesson5Node);
			introDifficulty.appendChild(introLesson6Node);
			introDifficulty.appendChild(introLesson7Node);
			
			// creates the beginner difficulty element and
			// its individual lessons as children
			Node beginnerLesson1Node = createLessonNode("beginnerLesson1", doc, "21");
			Node beginnerLesson2Node = createLessonNode("beginnerLesson2", doc, "22");
			Node beginnerLesson3Node = createLessonNode("beginnerLesson3", doc, "23");
			Node beginnerLesson4Node = createLessonNode("beginnerLesson4", doc, "24");
			Node beginnerLesson5Node = createLessonNode("beginnerLesson5", doc, "25");
			Node beginnerLesson6Node = createLessonNode("beginnerLesson6", doc, "26");
			
			beginnerDifficulty.appendChild(beginnerLesson1Node);
			beginnerDifficulty.appendChild(beginnerLesson2Node);
			beginnerDifficulty.appendChild(beginnerLesson3Node);
			beginnerDifficulty.appendChild(beginnerLesson4Node);
			beginnerDifficulty.appendChild(beginnerLesson5Node);
			beginnerDifficulty.appendChild(beginnerLesson6Node);
			
			// creates the intermediate difficulty element and
			// its individual lessons as children
			Node intermLesson1Node = createLessonNode("intermLesson1", doc, "31");
			Node intermLesson2Node = createLessonNode("intermLesson2", doc, "32");
			Node intermLesson3Node = createLessonNode("intermLesson3", doc, "33");
			Node intermLesson4Node = createLessonNode("intermLesson4", doc, "34");
			
			intermDifficulty.appendChild(intermLesson1Node);
			intermDifficulty.appendChild(intermLesson2Node);
			intermDifficulty.appendChild(intermLesson3Node);
			intermDifficulty.appendChild(intermLesson4Node);
			
			// creates the advanced difficulty element and
			// its individual lessons as children
			Node advancedLesson1Node = createLessonNode("advancedLesson1", doc, "41");
			Node advancedLesson2Node = createLessonNode("advancedLesson2", doc, "42");
			Node advancedLesson3Node = createLessonNode("advancedLesson3", doc, "43");
			Node advancedLesson4Node = createLessonNode("advancedLesson4", doc, "44");
			Node advancedLesson5Node = createLessonNode("advancedLesson5", doc, "45");
			Node advancedLesson6Node = createLessonNode("advancedLesson6", doc, "46");
			
			advancedDifficulty.appendChild(advancedLesson1Node);
			advancedDifficulty.appendChild(advancedLesson2Node);
			advancedDifficulty.appendChild(advancedLesson3Node);
			advancedDifficulty.appendChild(advancedLesson4Node);
			advancedDifficulty.appendChild(advancedLesson5Node);
			advancedDifficulty.appendChild(advancedLesson6Node);
			
			// creates the expert difficulty element and
			// its individual lessons as children
			Node expertLesson1Node = createLessonNode("expertLesson1", doc, "51");
			Node expertLesson2Node = createLessonNode("expertLesson2", doc, "52");
			Node expertLesson3Node = createLessonNode("expertLesson3", doc, "53");

			expertDifficulty.appendChild(expertLesson1Node);
			expertDifficulty.appendChild(expertLesson2Node);
			expertDifficulty.appendChild(expertLesson3Node);
			
			// creates courseProgress.xml in data directory.
		    Source xmlSource = new DOMSource(doc);
			try {
				Result result = new StreamResult(new FileOutputStream("data/courseProgress.xml"));
			    TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			    transformer.transform(xmlSource, result);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			System.out.println("data/courseProgress.xml successfully created.");
			
		} catch (ParserConfigurationException pce) {
			System.out.println("Failed to create data/courseProgress.xml");
			pce.printStackTrace();
		} catch (TransformerException tce) {
			System.out.println("Failed to create data/courseProgress.xml");
			tce.printStackTrace();
		}
	}

	public Node createLessonNode(String name, Document document, String id) {
		Element complete = document.createElement("Complete");
		Element stars = document.createElement("Stars");
		
		complete.appendChild(document.createTextNode("No"));
		stars.appendChild(document.createTextNode("0"));
		
		Element lesson = document.createElement(name);
		
		Attr idAttr = document.createAttribute("id");
		idAttr.setValue(id);
		
		lesson.setAttributeNode(idAttr);
		lesson.appendChild(complete);
		lesson.appendChild(stars);
		
		return lesson;
	}
	
	public void editXmlFile(String lesson) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(new File("data/courseProgress.xml"));
		XPath xPath = XPathFactory.newInstance().newXPath();
		Node completeNode = null;
		
		switch(lesson) {
		case "introL1":
			completeNode = (Node) xPath.compile("/course/Introduction/introLesson1/Complete").evaluate(doc, XPathConstants.NODE);
			
			break;
			
		default:
			break;	
		}
		completeNode.setTextContent("Yes");
		
	    Source xmlSource = new DOMSource(doc);
		try {
			Result result = new StreamResult(new FileOutputStream("data/courseProgress.xml"));
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		    transformer.transform(xmlSource, result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void checkXmlComplete() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("data/courseProgress.xml");
		
		doc.getDocumentElement().normalize();
	}
	
	public Boolean xmlFileExists() {
		File xmlFile = new File("data/courseProgress.xml");
		if (!xmlFile.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	// methods that are used to create the files that are used
	// with Typing Tutor if they don't exist. Creates a .txt file for both
	// the text names and the actual texts into a data directory where the
	// application is launched.
	public void createTextNamesFile() {
		File textNamesFile = new File("data/textNames.txt");

		try {
			textNamesFile.getParentFile().mkdirs();
			try {
				textNamesFile.createNewFile();
				addTextName("A Confederacy Of Dunces");    
				addTextName("The Stranger");               
				addTextName("How I Feel");                 
				addTextName("Encyclopedia of Networking"); 
				addTextName("The Fifth Mountain");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SecurityException se) {
			se.printStackTrace();
		}
	}

	public void createTextTextFile() {
		File textTextFile = new File("data/textText.txt");
		try {
			textTextFile.getParentFile().mkdirs();
			try {
				textTextFile.createNewFile();
				addTextText(
						"A green hunting cap squeezed the top of the fleshy balloon of a head. The green earflaps, full of large ears and uncut hair and the fine bristles that grew in the ears themselves, stuck out on either side like turn signals indicating two directions at once.");
				addTextText(
						"Mother died today. Or maybe yesterday, I don't know. I had a telegram from the home: 'Mother passed away. Funeral tomorrow. Yours sincerely.' That doesn't mean anything. It may have been yesterday.");
				addTextText(
						"You're asking how do I really feel, and I'm asking now how is this real. Oh, love is a plan that we can't control. All I can hope, that I'll fill the role. No one will ever know how I feel for you. Throw me a lifeline.");
				addTextText(
						"In synchronous communications, the sender and receiver must synchronize with one another before data is sent. To maintain clock synchronization over long periods, a special bit-transition pattern is embedded in the digital signal that assists in maintaining the timing between sender and receiver.");
				addTextText(
						"All life's battles teach us something, even those we lose. When you grow up, you'll discover that you have defended lies, deceived yourself, or suffered foolishness. If you're a good warrior you will not blame yourself for this, but neither will you allow your mistakes to repeat themselves.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SecurityException se) {
			se.printStackTrace();
		}
	}
	
	public Boolean textNamesExists() {
		File textNames = new File("data/textNames.txt");
		if (!textNames.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	public Boolean textTextExists() {
		File textNames = new File("data/textText.txt");
		if (!textNames.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	// methods that are used to add both the names of the texts
	// and the actual text of which a user will input into
	// the solo practice mode if they wish to make a custom
	// entry.
	public void addTextName(String name) {
		try {
			FileWriter textNameWriter = new FileWriter("data/textNames.txt", true);
			BufferedWriter textNameBufferedWriter = new BufferedWriter(textNameWriter);
			PrintWriter textNamePrintWriter = new PrintWriter(textNameBufferedWriter);

			textNamePrintWriter.println(name);
			textNamePrintWriter.flush();
			textNamePrintWriter.close();

		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public void addTextText(String text) {
		try {
			FileWriter textTextWriter = new FileWriter("data/textText.txt", true);
			BufferedWriter textTextBufferedWriter = new BufferedWriter(textTextWriter);
			PrintWriter textTextPrintWriter = new PrintWriter(textTextBufferedWriter);

			textTextPrintWriter.println(text);
			textTextPrintWriter.flush();
			textTextPrintWriter.close();

		} catch (Exception E) {
			E.printStackTrace();
		}
	}
	
	// methods used to deal with the file that contains the
	// wpm records of the user, including both writing to and
	// reading from.
	public void createWPMFile() {
		File wpmFile = new File("data/wpm.txt");

		try {
			wpmFile.getParentFile().mkdirs();
			try {
				wpmFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SecurityException se) {
			se.printStackTrace();
		}
	}
	
	public void writeWPMtoFile(double wpm) throws IOException {
		FileWriter wpmFileWriter = new FileWriter("data/wpm.txt", true);
		BufferedWriter wpmFileBufferedWriter = new BufferedWriter(wpmFileWriter);
		PrintWriter wpmFilePrintWriter = new PrintWriter(wpmFileBufferedWriter);

		wpmFilePrintWriter.println(wpm);
		wpmFilePrintWriter.flush();
		wpmFilePrintWriter.close();
	}

	public Boolean wpmFileExists() {
		File wpmFile = new File("data/wpm.txt");
		if (wpmFile.exists() == false) {
			return false;
		} else {
			return true;
		}
	}
}
