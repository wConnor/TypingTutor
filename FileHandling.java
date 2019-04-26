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
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

public class FileHandling {

	TypingTutorMenu mainMenu = new TypingTutorMenu();

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
			Node introLesson1Node = createLessonNode("introL1", doc, "11");
			Node introLesson2Node = createLessonNode("introL2", doc, "12");
			Node introLesson3Node = createLessonNode("introL3", doc, "13");
			Node introLesson4Node = createLessonNode("introL4", doc, "14");
			Node introLesson5Node = createLessonNode("introL5", doc, "15");
			Node introLesson6Node = createLessonNode("introL6", doc, "16");
			Node introLesson7Node = createLessonNode("introL7", doc, "17");

			introDifficulty.appendChild(introLesson1Node);
			introDifficulty.appendChild(introLesson2Node);
			introDifficulty.appendChild(introLesson3Node);
			introDifficulty.appendChild(introLesson4Node);
			introDifficulty.appendChild(introLesson5Node);
			introDifficulty.appendChild(introLesson6Node);
			introDifficulty.appendChild(introLesson7Node);

			// creates the beginner difficulty element and
			// its individual lessons as children
			Node beginnerLesson1Node = createLessonNode("beginnerL1", doc, "21");
			Node beginnerLesson2Node = createLessonNode("beginnerL2", doc, "22");
			Node beginnerLesson3Node = createLessonNode("beginnerL3", doc, "23");
			Node beginnerLesson4Node = createLessonNode("beginnerL4", doc, "24");
			Node beginnerLesson5Node = createLessonNode("beginnerL5", doc, "25");

			beginnerDifficulty.appendChild(beginnerLesson1Node);
			beginnerDifficulty.appendChild(beginnerLesson2Node);
			beginnerDifficulty.appendChild(beginnerLesson3Node);
			beginnerDifficulty.appendChild(beginnerLesson4Node);
			beginnerDifficulty.appendChild(beginnerLesson5Node);

			// creates the intermediate difficulty element and
			// its individual lessons as children
			Node intermLesson1Node = createLessonNode("intermL1", doc, "31");
			Node intermLesson2Node = createLessonNode("intermL2", doc, "32");
			Node intermLesson3Node = createLessonNode("intermL3", doc, "33");
			Node intermLesson4Node = createLessonNode("intermL4", doc, "34");

			intermDifficulty.appendChild(intermLesson1Node);
			intermDifficulty.appendChild(intermLesson2Node);
			intermDifficulty.appendChild(intermLesson3Node);
			intermDifficulty.appendChild(intermLesson4Node);

			// creates the advanced difficulty element and
			// its individual lessons as children
			Node advancedLesson1Node = createLessonNode("advancedL1", doc, "41");
			Node advancedLesson2Node = createLessonNode("advancedL2", doc, "42");
			Node advancedLesson3Node = createLessonNode("advancedL3", doc, "43");
			Node advancedLesson4Node = createLessonNode("advancedL4", doc, "44");
			Node advancedLesson5Node = createLessonNode("advancedL5", doc, "45");
			Node advancedLesson6Node = createLessonNode("advancedL6", doc, "46");

			advancedDifficulty.appendChild(advancedLesson1Node);
			advancedDifficulty.appendChild(advancedLesson2Node);
			advancedDifficulty.appendChild(advancedLesson3Node);
			advancedDifficulty.appendChild(advancedLesson4Node);
			advancedDifficulty.appendChild(advancedLesson5Node);
			advancedDifficulty.appendChild(advancedLesson6Node);

			// creates the expert difficulty element and
			// its individual lessons as children
			Node expertLesson1Node = createLessonNode("expertL1", doc, "51");
			Node expertLesson2Node = createLessonNode("expertL2", doc, "52");
			Node expertLesson3Node = createLessonNode("expertL3", doc, "53");

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

	public void editXmlFile(String lesson) throws SAXException, IOException, ParserConfigurationException,
			XPathExpressionException, TransformerException {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(new File("data/courseProgress.xml"));
		XPath xPath = XPathFactory.newInstance().newXPath();
		Node completeNode = null;

		switch (lesson) {
		case "introL1":
			completeNode = (Node) xPath.compile("/course/Introduction/introL1/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "introL2":
			completeNode = (Node) xPath.compile("/course/Introduction/introL2/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "introL3":
			completeNode = (Node) xPath.compile("/course/Introduction/introL3/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "introL4":
			completeNode = (Node) xPath.compile("/course/Introduction/introL4/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "introL5":
			completeNode = (Node) xPath.compile("/course/Introduction/introL5/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "introL6":
			completeNode = (Node) xPath.compile("/course/Introduction/introL6/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "introL7":
			completeNode = (Node) xPath.compile("/course/Introduction/introL7/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;

		case "beginnerL1":
			completeNode = (Node) xPath.compile("/course/Beginner/beginnerL1/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "beginnerL2":
			completeNode = (Node) xPath.compile("/course/Beginner/beginnerL2/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "beginnerL3":
			completeNode = (Node) xPath.compile("/course/Beginner/beginnerL3/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "beginnerL4":
			completeNode = (Node) xPath.compile("/course/Beginner/beginnerL4/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "beginnerL5":
			completeNode = (Node) xPath.compile("/course/Beginner/beginnerL5/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;

		case "intermediateL1":
			completeNode = (Node) xPath.compile("/course/Intermediate/intermL1/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "intermediateL2":
			completeNode = (Node) xPath.compile("/course/Intermediate/intermL2/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "intermediateL3":
			completeNode = (Node) xPath.compile("/course/Intermediate/intermL3/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "intermediateL4":
			completeNode = (Node) xPath.compile("/course/Intermediate/intermL4/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;

		case "advancedL1":
			completeNode = (Node) xPath.compile("/course/Advanced/advancedL1/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "advancedL2":
			completeNode = (Node) xPath.compile("/course/Advanced/advancedL2/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "advancedL3":
			completeNode = (Node) xPath.compile("/course/Advanced/advancedL3/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "advancedL4":
			completeNode = (Node) xPath.compile("/course/Advanced/advancedL4/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "advancedL5":
			completeNode = (Node) xPath.compile("/course/Advanced/advancedL5/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "advancedL6":
			completeNode = (Node) xPath.compile("/course/Advanced/advancedL6/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;

		case "expertL1":
			completeNode = (Node) xPath.compile("/course/Expert/expertL1/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "expertL2":
			completeNode = (Node) xPath.compile("/course/Expert/expertL2/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;
		case "expertL3":
			completeNode = (Node) xPath.compile("/course/Expert/expertL3/Complete").evaluate(doc,
					XPathConstants.NODE);
			completeNode.setTextContent("Yes");
			break;

		default:
			break;
		}

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

	public void writeXmlStars(String stars) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(new File("data/courseProgress.xml"));
		XPath xPath = XPathFactory.newInstance().newXPath();
		Node starsNode = null;
		
		starsNode = (Node) xPath.compile("/course/" + mainMenu.getDifficulty() + "/" + mainMenu.getLesson() + "/Stars").evaluate(doc,
				XPathConstants.NODE);

		starsNode.setTextContent(stars);
		
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
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(new File("data/courseProgress.xml"));
		XPath xPath = XPathFactory.newInstance().newXPath();
		Node completeNode = null;
		try {
			// intro
			for (int i = 1; i != 7; ++i) {
				completeNode = (Node) xPath.compile("/course/Introduction/introL" + i + "/Complete").evaluate(doc,
						XPathConstants.NODE);
				if (completeNode.getTextContent().equals("Yes")) {
					mainMenu.colourButton("introL" + i);
				}
			}
			
			// beginner
			for (int i = 1; i != 5; ++i) {
				completeNode = (Node) xPath.compile("/course/Beginner/beginnerL" + i + "/Complete").evaluate(doc,
						XPathConstants.NODE);
				if (completeNode.getTextContent().equals("Yes")) {
					mainMenu.colourButton("beginnerL" + i);
				}
			}

			// intermediate
			for (int i = 1; i != 4; ++i) {
				completeNode = (Node) xPath.compile("/course/Intermediate/intermL" + i + "/Complete").evaluate(doc,
						XPathConstants.NODE);
				if (completeNode.getTextContent().equals("Yes")) {
					mainMenu.colourButton("intermL" + i);
				}
			}

			// advanced
			for (int i = 1; i != 6; ++i) {
				completeNode = (Node) xPath.compile("/course/Advanced/advancedL" + i + "/Complete").evaluate(doc,
						XPathConstants.NODE);
				if (completeNode.getTextContent().equals("Yes")) {
					mainMenu.colourButton("advancedL" + i);
				}
			}

			// expert
			for (int i = 1; i != 3; ++i) {
				completeNode = (Node) xPath.compile("/course/Expert/expertL" + i + "/Complete").evaluate(doc,
						XPathConstants.NODE);
				if (completeNode.getTextContent().equals("Yes")) {
					mainMenu.colourButton("expertL" + i);
				}
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();

	}

	public int countStars() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		int totalStars = 0;
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(new File("data/courseProgress.xml"));
		XPath xPath = XPathFactory.newInstance().newXPath();
		Node starNode = null;

		// intro
		for (int i = 1; i != 7; ++i) {
			starNode = (Node) xPath.compile("/course/Introduction/introL" + i + "/Stars").evaluate(doc,
					XPathConstants.NODE);
			totalStars += Integer.valueOf(starNode.getTextContent());
		}
		
		// beginner
		for (int i = 1; i != 5; ++i) {
			starNode = (Node) xPath.compile("/course/Beginner/beginnerL" + i + "/Stars").evaluate(doc,
					XPathConstants.NODE);
			totalStars += Integer.valueOf(starNode.getTextContent());
		}
		
		// intermediate
		for (int i = 1; i != 4; ++i) {
			starNode = (Node) xPath.compile("/course/Intermediate/intermL" + i + "/Stars").evaluate(doc,
					XPathConstants.NODE);
			totalStars += Integer.valueOf(starNode.getTextContent());
		}
		
		// advanced
		for (int i = 1; i != 6; ++i) {
			starNode = (Node) xPath.compile("/course/Advanced/advancedL" + i + "/Stars").evaluate(doc,
					XPathConstants.NODE);
			totalStars += Integer.valueOf(starNode.getTextContent());
		}

		// expert
		for (int i = 1; i != 3; ++i) {
			starNode = (Node) xPath.compile("/course/Expert/expertL" + i + "/Stars").evaluate(doc,
					XPathConstants.NODE);
			totalStars += Integer.valueOf(starNode.getTextContent());
		}
		
		return totalStars;
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
