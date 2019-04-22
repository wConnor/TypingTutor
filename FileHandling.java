import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileHandling {
	
	public void createXmlFile() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// creates the root course element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("course");
			doc.appendChild(rootElement);
			
			// creates the introduction difficulty element and
			// its individual lessons as children
			Element introDifficulty = doc.createElement("Introduction");
			rootElement.appendChild(introDifficulty);
			
			Element introLessonOne = doc.createElement("Lesson1");
			Element introLessonTwo = doc.createElement("Lesson2");
			Element introLessonThree = doc.createElement("Lesson3");
			Element introLessonFour = doc.createElement("Lesson4");
			Element introLessonFive = doc.createElement("Lesson5");
			Element introLessonSix = doc.createElement("Lesson6");
			Element introLessonSeven = doc.createElement("Lesson7");

			introLessonOne.setAttribute("Complete", "No");
			introLessonTwo.setAttribute("Complete", "No");
			introLessonThree.setAttribute("Complete", "No");
			introLessonFour.setAttribute("Complete", "No");
			introLessonFive.setAttribute("Complete", "No");
			introLessonSix.setAttribute("Complete", "No");
			introLessonSeven.setAttribute("Complete", "No");
			
			introLessonOne.setAttribute("Stars", "0");
			introLessonTwo.setAttribute("Stars", "0");
			introLessonThree.setAttribute("Stars", "0");
			introLessonFour.setAttribute("Stars", "0");
			introLessonFive.setAttribute("Stars", "0");
			introLessonSix.setAttribute("Stars", "0");
			introLessonSeven.setAttribute("Stars", "0");
			
			introDifficulty.appendChild(introLessonOne);
			introDifficulty.appendChild(introLessonTwo);
			introDifficulty.appendChild(introLessonThree);
			introDifficulty.appendChild(introLessonFour);
			introDifficulty.appendChild(introLessonFive);
			introDifficulty.appendChild(introLessonSix);
			introDifficulty.appendChild(introLessonSeven);
			
			// creates the beginner difficulty element and
			// its individual lessons as children
			Element beginnerDifficulty = doc.createElement("Beginner");
			rootElement.appendChild(beginnerDifficulty);
			
			Element beginnerLessonOne = doc.createElement("Lesson1");
			Element beginnerLessonTwo = doc.createElement("Lesson2");
			Element beginnerLessonThree = doc.createElement("Lesson3");
			Element beginnerLessonFour = doc.createElement("Lesson4");
			Element beginnerLessonFive = doc.createElement("Lesson5");
			Element beginnerLessonSix = doc.createElement("Lesson6");

			beginnerLessonOne.setAttribute("Complete", "No");
			beginnerLessonTwo.setAttribute("Complete", "No");
			beginnerLessonThree.setAttribute("Complete", "No");
			beginnerLessonFour.setAttribute("Complete", "No");
			beginnerLessonFive.setAttribute("Complete", "No");
			beginnerLessonSix.setAttribute("Complete", "No");
			
			beginnerLessonOne.setAttribute("Stars", "0");
			beginnerLessonTwo.setAttribute("Stars", "0");
			beginnerLessonThree.setAttribute("Stars", "0");
			beginnerLessonFour.setAttribute("Stars", "0");
			beginnerLessonFive.setAttribute("Stars", "0");
			beginnerLessonSix.setAttribute("Stars", "0");
			
			beginnerDifficulty.appendChild(beginnerLessonOne);
			beginnerDifficulty.appendChild(beginnerLessonTwo);
			beginnerDifficulty.appendChild(beginnerLessonThree);
			beginnerDifficulty.appendChild(beginnerLessonFour);
			beginnerDifficulty.appendChild(beginnerLessonFive);
			beginnerDifficulty.appendChild(beginnerLessonSix);
			
			// creates the intermediate difficulty element and
			// its individual lessons as children
			Element intermDifficulty = doc.createElement("Intermediate");
			rootElement.appendChild(intermDifficulty);
			
			Element intermLessonOne = doc.createElement("Lesson1");
			Element intermLessonTwo = doc.createElement("Lesson2");
			Element intermLessonThree = doc.createElement("Lesson3");
			Element intermLessonFour = doc.createElement("Lesson4");
            
			intermLessonOne.setAttribute("Complete", "No");
			intermLessonTwo.setAttribute("Complete", "No");
			intermLessonThree.setAttribute("Complete", "No");
			intermLessonFour.setAttribute("Complete", "No");
			
			intermLessonOne.setAttribute("Stars", "0");
			intermLessonTwo.setAttribute("Stars", "0");
			intermLessonThree.setAttribute("Stars", "0");
			intermLessonFour.setAttribute("Stars", "0");
			
			intermDifficulty.appendChild(intermLessonOne);
			intermDifficulty.appendChild(intermLessonTwo);
			intermDifficulty.appendChild(intermLessonThree);
			intermDifficulty.appendChild(intermLessonFour);

			
			// creates the advanced difficulty element and
			// its individual lessons as children
			Element advancedDifficulty = doc.createElement("Advanced");
			rootElement.appendChild(advancedDifficulty);
			
			Element advancedLessonOne = doc.createElement("Lesson1");
			Element advancedLessonTwo = doc.createElement("Lesson2");
			Element advancedLessonThree = doc.createElement("Lesson3");
			Element advancedLessonFour = doc.createElement("Lesson4");
			Element advancedLessonFive = doc.createElement("Lesson5");
			Element advancedLessonSix = doc.createElement("Lesson6");
					
			advancedLessonOne.setAttribute("Complete", "No");
			advancedLessonTwo.setAttribute("Complete", "No");
			advancedLessonThree.setAttribute("Complete", "No");
			advancedLessonFour.setAttribute("Complete", "No");
			advancedLessonFive.setAttribute("Complete", "0");
			advancedLessonSix.setAttribute("Complete", "0");
			
			advancedLessonOne.setAttribute("Stars", "0");
			advancedLessonTwo.setAttribute("Stars", "0");
			advancedLessonThree.setAttribute("Stars", "0");
			advancedLessonFour.setAttribute("Stars", "0");
			advancedLessonFive.setAttribute("Stars", "0");
			advancedLessonSix.setAttribute("Stars", "0");
			
			advancedDifficulty.appendChild(advancedLessonOne);
			advancedDifficulty.appendChild(advancedLessonTwo);
			advancedDifficulty.appendChild(advancedLessonThree);
			advancedDifficulty.appendChild(advancedLessonFour);
			advancedDifficulty.appendChild(advancedLessonFive);
			advancedDifficulty.appendChild(advancedLessonSix);
			
			// creates the expert difficulty element and
			// its individual lessons as children
			Element expertDifficulty = doc.createElement("Expert");
			rootElement.appendChild(expertDifficulty);
			        
			Element expertLessonOne = doc.createElement("Lesson1");
			Element expertLessonTwo = doc.createElement("Lesson2");
			Element expertLessonThree = doc.createElement("Lesson3");

			expertLessonOne.setAttribute("Complete", "No");
			expertLessonTwo.setAttribute("Complete", "No");
			expertLessonThree.setAttribute("Complete", "No");
            
			expertLessonOne.setAttribute("Stars", "0");
			expertLessonTwo.setAttribute("Stars", "0");
			expertLessonThree.setAttribute("Stars", "0");

			expertDifficulty.appendChild(expertLessonOne);
			expertDifficulty.appendChild(expertLessonTwo);
			expertDifficulty.appendChild(expertLessonThree);
			
			// creates courseProgress.xml in data directory.
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("data/courseProgress.xml"));			
			
			transformer.transform(source, result);
			
			System.out.println("data/courseProgress.xml successfully created.");
			
		} catch (ParserConfigurationException pce) {
			System.out.println("Failed to create data/courseProgress.xml");
			pce.printStackTrace();
		} catch (TransformerException tce) {
			System.out.println("Failed to create data/courseProgress.xml");
			tce.printStackTrace();
		}
	}

	public Boolean xmlFileExists() {
		File xmlFile = new File("data/courseProgress.xml");
		if (!xmlFile.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
}
