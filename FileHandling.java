import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
