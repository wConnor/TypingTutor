import javax.swing.*;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class TypingTutorMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private static JFrame menuFrame, soloPracticeFrame, statsFrame, addButtonFrame, recommendFrame, graphFrame,
			settingsFrame, aboutFrame;
	private static JButton introDifficulty;
	private static JButton beginnerDifficulty;
	private static JButton intermediateDifficulty;
	private static JButton advancedDifficulty;
	private JButton practiceButton;
	private static JButton expertDifficulty;
	private static JButton settingsButton, startButton, closeProgramButton;
	private static JButton statsButton;

	private JButton introLessonOne, introLessonTwo, introLessonThree, introLessonFour, introLessonFive, introLessonSix,
			introLessonSeven;
	private JButton beginnerLessonOne, beginnerLessonTwo, beginnerLessonThree, beginnerLessonFour, beginnerLessonFive,
			beginnerLessonSix;
	private JButton intermLessonOne, intermLessonTwo, intermLessonThree, intermLessonFour;
	private JButton advancedLessonOne, advancedLessonTwo, advancedLessonThree, advancedLessonFour, advancedLessonFive,
			advancedLessonSix;
	private JButton expertLessonOne, expertLessonTwo, expertLessonThree;

	private static JLabel username;

	private JLabel typingTutorTitle;

	private static JLabel averageWPMLabel, starLabel;

	private static JTextArea titleArea;
	private static JTextArea descriptionArea;

	private static JComboBox<String> choicesList;
	private static double averageWPM, totalWPM;
	private Font typingTutorTitleFont, titleAreaFont, descriptionAreaFont, typingAreaFont, promptAreaFont;
	private Border border;
	private static String assignedText;
	private static File textNamesFile, textTextFile;
	private static String textNamesFilePath, textTextFilePath, wpmFilePath;
	private static int totalTrials, lines, typingAreaSize, promptAreaSize;
	private static Boolean wpmBoolean;

	private static Scanner scan;

	private static List<String> textNames;
	private static List<String> textTexts;
	private static List<Double> averageWPMs;

	TypingTutor typingTutorInit = new TypingTutor();

	public void menu() {

		TypingScreenGUI typingScreen = new TypingScreenGUI();

		menuFrame = new JFrame();
		soloPracticeFrame = new JFrame();
		statsFrame = new JFrame();
		graphFrame = new JFrame();
		addButtonFrame = new JFrame();
		recommendFrame = new JFrame();
		settingsFrame = new JFrame();
		aboutFrame = new JFrame();

		introDifficulty = new JButton("Introduction");
		beginnerDifficulty = new JButton("Beginner");
		advancedDifficulty = new JButton("Advanced");
		intermediateDifficulty = new JButton("Intermediate");
		expertDifficulty = new JButton("Expert");
		practiceButton = new JButton("Solo Practice");
		settingsButton = new JButton("Settings");
		startButton = new JButton("Start");
		closeProgramButton = new JButton("Quit");
		statsButton = new JButton("Statistics");

		introLessonOne = new JButton();
		introLessonTwo = new JButton();
		introLessonThree = new JButton();
		introLessonFour = new JButton();
		introLessonFive = new JButton();
		introLessonSix = new JButton();
		introLessonSeven = new JButton();

		beginnerLessonOne = new JButton();
		beginnerLessonTwo = new JButton();
		beginnerLessonThree = new JButton();
		beginnerLessonFour = new JButton();
		beginnerLessonFive = new JButton();
		beginnerLessonSix = new JButton();

		intermLessonOne = new JButton();
		intermLessonTwo = new JButton();
		intermLessonThree = new JButton();
		intermLessonFour = new JButton();

		advancedLessonOne = new JButton();
		advancedLessonTwo = new JButton();
		advancedLessonThree = new JButton();
		advancedLessonFour = new JButton();
		advancedLessonFive = new JButton();
		advancedLessonSix = new JButton();

		expertLessonOne = new JButton();
		expertLessonTwo = new JButton();
		expertLessonThree = new JButton();

		choicesList = new JComboBox();

		username = new JLabel();
		typingTutorTitle = new JLabel("<html>Typing <br> \u0000 \u0000 Tutor</html>");
		averageWPMLabel = new JLabel("Average WPM: " + new DecimalFormat("#0").format(averageWPM));
		starLabel = new JLabel("");
		
		titleArea = new JTextArea();
		descriptionArea = new JTextArea();

		typingTutorTitleFont = new Font("SansSerif", Font.BOLD, 34);
		titleAreaFont = new Font("SansSerif", Font.BOLD, 24);
		descriptionAreaFont = new Font("SansSerif", Font.PLAIN, 18);

		border = BorderFactory.createLineBorder(Color.BLACK);

		assignedText = "This is the default assignedText. This shouldn't be seen as the contents of this variable should be overwritten by the lesson or solo practice mode.";

		wpmBoolean = true;

		typingAreaSize = 30;
		promptAreaSize = 36;
		
		textNames = new ArrayList<String>();
		textTexts = new ArrayList<String>();
		averageWPMs = new ArrayList<Double>();

		textNamesFilePath = "data/textNames.txt";
		textTextFilePath = "data/textText.txt";
		wpmFilePath = "data/wpm.txt";

		File textNamesFile = new File(textNamesFilePath);
		File textTextFile = new File(textTextFilePath);
		File wpmFile = new File(wpmFilePath);

		getUsername();

		// Handles the files. If the contents of the data folder that are expected
		// to be there aren't, then the program checks each of them and creates
		// said file. Ensures consistency when dealing with files.
		if (!textNamesFile.exists()) {
			System.out.println(textNamesFilePath + " does not exist. Creating file...");
			createTextNamesFile();

			addTextName("A Confederacy Of Dunces");
			addTextName("The Stranger");
			addTextName("How I Feel");
			addTextName("Encyclopedia of Networking");
			addTextName("The Fifth Mountain");
		}

		if (!textTextFile.exists()) {
			System.out.println(textTextFilePath + " does not exist. Creating file...");
			createTextTextFile();
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
		}

		if (!wpmFile.exists()) {
			System.out.println(wpmFilePath + " does not exist. Creating file...");
			try {
				createWPMFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		getAverageWPM();

		////////////////////////////////////////////////////////////////////////////
		try {
			fileToArrays();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JComboBox choicesList = new JComboBox(textNames.toArray());

		// -- STAGES + LESSONS --
		// Setting up the JButtons that are all necessary for navigating through the
		// different stages
		// as well as their individual levels. Their bounds will be set, and action
		// listeners are also
		// set on each button so that the user can (naturally) interact with them. In
		// addition, clicking
		// each lesson sets the assignedText variable to whatever the text for the
		// lesson will be, allowing
		// TypingScreenGUI.java to then access it.

		introLessonOne.setBounds(175, 150, 175, 60);
		introLessonOne.setVisible(false);
		introLessonTwo.setBounds(175, 210, 175, 60);
		introLessonTwo.setVisible(false);
		introLessonThree.setBounds(175, 270, 175, 60);
		introLessonThree.setVisible(false);
		introLessonFour.setBounds(175, 330, 175, 60);
		introLessonFour.setVisible(false);
		introLessonFive.setBounds(175, 390, 175, 60);
		introLessonFive.setVisible(false);
		introLessonSix.setBounds(175, 450, 175, 60);
		introLessonSix.setVisible(false);
		introLessonSeven.setBounds(175, 510, 175, 60);
		introLessonSeven.setVisible(false);

		beginnerLessonOne.setBounds(175, 150, 175, 60);
		beginnerLessonOne.setVisible(false);
		beginnerLessonTwo.setBounds(175, 210, 175, 60);
		beginnerLessonTwo.setVisible(false);
		beginnerLessonThree.setBounds(175, 270, 175, 60);
		beginnerLessonThree.setVisible(false);
		beginnerLessonFour.setBounds(175, 330, 175, 60);
		beginnerLessonFour.setVisible(false);
		beginnerLessonFive.setBounds(175, 390, 175, 60);
		beginnerLessonFive.setVisible(false);
		beginnerLessonSix.setBounds(175, 450, 175, 60);
		beginnerLessonSix.setVisible(false);

		intermLessonOne.setBounds(175, 150, 175, 60);
		intermLessonOne.setVisible(false);
		intermLessonTwo.setBounds(175, 210, 175, 60);
		intermLessonTwo.setVisible(false);
		intermLessonThree.setBounds(175, 270, 175, 60);
		intermLessonThree.setVisible(false);
		intermLessonFour.setBounds(175, 330, 175, 60);
		intermLessonFour.setVisible(false);

		advancedLessonOne.setBounds(175, 150, 175, 60);
		advancedLessonOne.setVisible(false);
		advancedLessonTwo.setBounds(175, 210, 175, 60);
		advancedLessonTwo.setVisible(false);
		advancedLessonThree.setBounds(175, 270, 175, 60);
		advancedLessonThree.setVisible(false);
		advancedLessonFour.setBounds(175, 330, 175, 60);
		advancedLessonFour.setVisible(false);
		advancedLessonFive.setBounds(175, 390, 175, 60);
		advancedLessonFive.setVisible(false);
		advancedLessonSix.setBounds(175, 450, 175, 60);
		advancedLessonSix.setVisible(false);

		expertLessonOne.setBounds(175, 150, 175, 60);
		expertLessonOne.setVisible(false);
		expertLessonTwo.setBounds(175, 210, 175, 60);
		expertLessonTwo.setVisible(false);
		expertLessonThree.setBounds(175, 270, 175, 60);
		expertLessonThree.setVisible(false);

		// !-- INTRODUCTION DIFFICULTY --!
		introDifficulty.setBounds(0, 150, 175, 60);
		introDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultySelected();

				introLessonOne.setText("<html><center>Lesson 1 - The F and J Keys</center></html>");
				introLessonOne.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("The F and J Keys",
								"In this lesson, you will be practicing the use of the F and J keys when it comes to typing. Both of these keys are very important and it's critical that you come to familiarise yourself with these keys to learn the ability of touch typing.\n\nAs you may have noticed on the keyboard, there's small little lumps on the F and J keys. This is because these are the keys where both of your index fingers should be placed whilst you're typing.",
								"fff jjj fff jjj fff fff jjj jjj fff fff jjj jjj fff jjj ff jj fff jjj jjj fff fff jjj",
								false);
					}
				});

				introLessonTwo.setText("<html><center>Lesson 2 - Common Keys</center></html>");
				introLessonTwo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Common Keys",
								"In this lesson, the common keys that are used when typing will be practiced. These keys will be close to the F and J keys, and are used with most words that'll be typed on the keyboard, such as the letters t, d, and k (for example).\n\nIn later lessons, more practice of keys will be present, notably the more difficult and less common ones.\n\n ** Does not count towards average WPM **",
								"f e i f d k r f e j t k d g f l e f a h e f g d h j d k o s", false);

					}
				});

				introLessonThree.setText("<html><center>Lesson 3 - More Common Keys</center></html>");
				introLessonThree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("More Common Keys",
								"This lesson is very similar to the last one - more of the common keys that are used in the English language that one should expect to see / use as they're typing will be practiced here.",
								"t oo o i a e t ii l d ccc aa rr ee mm aa r r e tt ff e r i a rr", false);

					}
				});

				introLessonFour.setText("<html><center>Lesson 4 - Uncommon Keys</center></html>");
				introLessonFour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Uncommon Keys",
								"This lesson is rather different compared to the previous ones. Here, keys that are more difficult to reach and are a little more uncommon will be practiced, including q, z, j, k, x etc..",
								"t oo o i a e t ii l d ccc aa rr ee mm aa r r e tt ff e r i a rr", false);

					}
				});

				introLessonFive.setText("<html><center>Lesson 5 - More Uncommon Keys</center></html>");
				introLessonFive.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("More Uncommon Keys",
								"A continuation of the previous lesson, this will again go over some more uncommon keys and help you become more familiar with the keyboard.",
								"qqqq xx zz rrrr kk vvvv bb y w g ppp zzz zzxxk kz qqjj xkv rrr hhh", false);

					}
				});

				introLessonSix.setText("<html><center>Lesson 6 - Combining the Common and Uncommon</center></html>");
				introLessonSix.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Combining the Common and Uncommon",
								"We'll be combining what we have previously practiced - both common and uncommon keys. They'll be in a completely random order for you to practice and start to become comfortable with hitting any key that is needed.",
								"tt dd x za al f t d i i a ee uuu iii ooxx ppp ggg hhh aaa sss bbb mmm lll zeeii aaaiiie eoop leeerr nnn eeeennn aaaa",
								false);

					}
				});

				introLessonSeven.setText("<html><center>Summary</center></html>");
				introLessonSeven.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Summary of Introduction",
								"Everything that has been learned during this difficulty will be roughly summarised here, with the combination of both the common keys on the keyboard, and the uncommon keys.",
								"eeee ttt ooooo aaa iii fff jjj fff jj f j fj jf f j nnn sss rrr fffhhhjjj lllllf jddd mm ffff jjjj uuuu gggg ccc ggg ffff jjjj www yyy bbb vvvv kk zzzz jjj xxx k bbb www yyy",
								false);

					}
				});

				introLessonOne.setVisible(true);
				introLessonTwo.setVisible(true);
				introLessonThree.setVisible(true);
				introLessonFour.setVisible(true);
				introLessonFive.setVisible(true);
				introLessonSix.setVisible(true);
				introLessonSeven.setVisible(true);

			}
		});

		// !-- BEGINNER DIFFICULTY --!
		beginnerDifficulty.setBounds(0, 210, 175, 60);
		beginnerDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultySelected();

				beginnerLessonOne.setText("<html><center>Lesson 1 - Simple Words</center></html>");
				beginnerLessonOne.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Simple Words",
								"Continuing on from the previous stages of the course, this lesson will combine all of the various keys that we have previously practiced and put them into real-use. Simple words will be practiced here which appear frequently in everyday text.",
								"come put take be do see a will want how why far out who yes but or please when no every body part then there",
								false);

					}
				});

				beginnerLessonTwo.setText("<html><center>Lesson 2 - More Word Practice</center></html>");
				beginnerLessonTwo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("More Word Practice",
								"Similar to lesson one, this lesson will have more English words for you to practice. After becoming familiar with the different keys on the keyboard from the introduction stage of the typing course, hitting those keys which may have been difficult should be much easier.\n\nThese words are taken from a collection of basic English words from an online source to ensure that your typing practice has a real-world use.",
								"blue beautiful equal hollow flat kind male married earth delicate worry fear high ill important kind child happy sharp slow natural tree can where bottle phone card coin pencil poor physical slow special",
								false);

					}
				});

				beginnerLessonThree.setText("<html><center>Lesson 3 - Incorporating Basic Punctuation</center></html>");
				beginnerLessonThree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Incorporating Basic Punctuation",
								"In order to incorporate more real-world use into your learning, we'll be going over some basic punctuation that's used in writing online with a quick lesson showing them off.\n\nSome of the punctuation may include: , (comma), : (colon), . (full stop), and ' (apostrophe). Feel free to look at the on-screen keyboard to find out where the keys are without looking at your own. Eyes on the screen!",
								", . run , aqua. ' . , walk ' , \" ' hello ! ' . thunder , ' . , \" ! take , sun",
								false);

					}
				});

				beginnerLessonFour.setText("<html><center>Lesson 4 - Basic Sentences</center></html>");
				beginnerLessonFour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Basic Sentences",
								"This lesson brings all that has been learnt to the next step - typing sentences. Real-world sentences have grammar and basic punctuation, such as the commas and such that were practiced in the previous lesson.\n\nBoth the basic words and basic punctuation are going to tested out. This lesson aims to help you improve on writing sentences with basic grammar included!",
								"The quick brown fox jumps over the lazy dog and, swiftly, vanishes from the scene. The dog lifts itself from the ground, confused as to what has happened. A young boy comes along and asks himself, \"Where'd that quick brown fox go?",
								false);

					}
				});

				beginnerLessonFive.setText("<html><center>Lesson 5 - More Basic Sentences</center></html>");
				beginnerLessonFive.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("More Basic Sentences",
								"As a continuation from the previous lesson, this one includes another set of basic sentences for you to try out!\n\nMore specifically, this lesson will show off a small segment of a conversation between two coworkers, meaning that you'll get some practice using apostrophes and speech marks.",
								"\"How're you doing?\" asked the coworker. I replied \"I'm doing fine thanks, and how are you? Do you have anything planned for the weekend? It is Friday after all!\" \"Hmmm... nothing much really. How long have you been working here then? I don't really recognise you.\" he replied.",
								true);

					}
				});

				beginnerLessonOne.setVisible(true);
				beginnerLessonTwo.setVisible(true);
				beginnerLessonThree.setVisible(true);
				beginnerLessonFour.setVisible(true);
				beginnerLessonFive.setVisible(true);

			}
		});

		// !-- INTERMEDIATE DIFFICULTY --!
		intermediateDifficulty.setBounds(0, 270, 175, 60);
		intermediateDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultySelected();

				intermLessonOne.setText("<html><center>Lesson 1 - Complex Sentences</center></html>");
				intermLessonOne.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Complex Sentences",
								"A good introduction to the intermediate stage would include having more complex sentences thrown at you. Similar to the previous stage (beginner), you'll be presented with real-world sentences, which include the use of punctuation.\n\nThis lesson will also introduce some more complex grammar structures, such as using embedded clauses. Most notably used in literature.",
								"Although he was wealthy, he was still unhappy. The Scarecrow and the Tin Woodman stood up in a corner and kept quiet all night, although of course they could not sleep. Many years later, as he faced the firing squad, Colonel Aurelian Buendia was to remember that distant afternoon when his father took him to discover ice.",
								true);

					}
				});

				intermLessonTwo.setText("<html><center>Lesson 2 - More Complex Sentences</html></center>");
				intermLessonTwo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("More Complex Sentences",
								"This lesson will give you a little more practice with more complex sentences that, again, have some different grammatical points which must be taken in to account in addition to capital letters for names (very important when it comes to formal writing!)\n\nThe difficulty won't be stepped up significantly compared to the last lesson.",
								"As Grainier drove along in the wagon behind a wide, slow, sand-colored mare, clusters of orange butterflies exploded off the blackish purple piles of bear sign and winked and fluttered magically like leaves without trees. Because he was so small, Stuart was often hard to find around the house.",
								true);

					}
				});

				intermLessonThree.setText("<html><center>Lesson 3 - Introducing More Punctuation</html></center>");
				intermLessonThree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Introducing More Punctuation",
								"We'll take a break from longer complex sentences and put our aim on getting used to some more punctuation, especially the ones that are less common like the semi-colon (;), the brackets (), the colon (:) and such.\n\nBeing able to come to grips with these punctuation marks should help you become a lot more fluent with typing in almost any situation which you may come across.\n\nDon't worry if your WPM is lower than normal, as that's completely natural when they're presented in such a way!",
								": - : ) : ; ( - ! ; @ \" # ; ~ # @ / : ... ; ! - . , : ; . , ) \" / ; '", false);

					}
				});

				intermLessonFour.setText("<html><center>Lesson 4 - Practicing New Punctuation</html></center>");
				intermLessonFour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Practicing New Punctuation",
								"Now we'll be using the punctuation that we have just learnt about with some random words - it's not meant to make sense, so don't worry if it confuses you!\n\nFurther down the line, we'll be practicing the use of these punctuation marks that we've practiced typing with more realistic context.",
								"aqua; hello the - now... with! however despite such to (do not) punctuation action: one word! close@ fluency; gratitude nature... you're perform practice; greet is / was apostrophe ' ! ! - deduction, negligence; parameter.",
								false);

					}
				});

				intermLessonOne.setVisible(true);
				intermLessonTwo.setVisible(true);
				intermLessonThree.setVisible(true);
				intermLessonFour.setVisible(true);

			}
		});

		// !-- ADVANCED DIFFICULTY --!
		advancedDifficulty.setBounds(0, 330, 175, 60);
		advancedDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultySelected();

				advancedLessonOne.setText("<html><center>Lesson 1 - Introducing Difficult Sentences</html></center>");
				advancedLessonOne.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Introducing Difficult Sentences",
								"This lesson will be similar to the previous one, where all punctuation that has previously been learnt throughout Typing Tutor will be practiced, including the basics from full-stops, to the more complex of hyphens.\n\nThe extract that'll be used will come from a complex piece of literature.",
								"My propositions are elucidatory in this way: he who understands me finally recognizes them as senseless, when he has climbed out through them, on them, over them. (He must so to speak throwaway the ladder, after he has climbed up on it). He must surmount these propositions; then he sees the world rightly. Whereof one cannot speak, thereof one must be silent.",
								true);

					}
				});

				advancedLessonTwo.setText("<html><center>Lesson 2 - More Complex Practice</html></center>");
				advancedLessonTwo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("More Complex Practice",
								"After the previous stage which was completely random words with the new punctuation in random places, we'll now be practicing the use of the punctuation into more real scenarios that actually make sense!\n\nHere, your WPM may pick up speed compared to the previous lessons, but if you see a decrease, don't be put off! It simply takes practice to become an expert with a newly learnt skill.",
								"The right method of philosophy would be this. To say nothing except what can be said, i.e. the propositions of natural science, i.e. something that has nothing to do with philosophy: and then always, when someone else wished to say something metaphys-ical, to demonstrate to him that he had given no meaning to certain signs in his propositions.",
								true);

					}
				});

				advancedLessonThree.setText("<html><center>Lesson 3 - Difficult Words</html></center>");
				advancedLessonThree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Difficult Words",
								"Words that aren't used very often in writing will be practiced here. Similar to English, these words will obviously be the ones that contain letters such as Z and Q.\n\nHitting these keys on the keyboard is quite difficult, even for an experienced typist. These keys are placed in their positions the way they are because they're not used very often in the English language - that's how the QWERTY keyboard works.",
								"zaps quiet xenial zebra xanthic quiz zap zipping xyst zorillas zoophile zip zag xeroxing xylol xi zarzuela zanders",
								false);

					}
				});

				advancedLessonFour.setText("<html><center>Lesson 4 - Practicing the Use of Those Keys</html></center>");
				advancedLessonFour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Practicing the Use of Those Keys",
								"Previously, we introduced some words that started with Z and Q, both of which are rather uncommon letters that one types when they're using the keyboard, however it's very possible that you'll eventually need to write a word that contains either one of your keys, so being prepared for it is the key. Always expect the unexpected.",
								"You will zap yourself if you touch the wire. Zach is over at the zoo, so he can't help you either. Also, this is a no parking zone, so please write your zip code here.",
								false);

					}
				});

				advancedLessonFive.setText("<html><center>Lesson 5 - Long, Uncommon Words</html></center>");
				advancedLessonFive.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Long, Uncommon Words",
								"Words that you have probably not seen before and probably won't use on a regular basis will be practiced here. These words are long and you may not know the meanings of them, but they make for a good practice material to keep focus when typing and test whether you're familiar with the positions of the keys on the keyboard.",
								"Floccinaucinihilipilification Ambidextrous Honorificabilitudinitatibus Antidisestabhlishment Catoptromancy Insangelous Quadragenarian Perhendinancer Xylopyrography Solitude Unwelewable",
								false);

					}
				});

				advancedLessonSix.setText("<html><center>Lesson 6 - Practice</html></center>");
				advancedLessonSix.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						lessonSelected("Practice",
								"Simply put, this lesson will simply be a pratcice piece of text presented to you in order consolidate the learning that has taken place throughout the advanced section of Typing Tutor, well done for making it this far!",
								"The differences in communication styles between men and women have been a topic of interest in the research world for many years. These differences may lead to miscommunication, conflict, and even dissatisfaction between couples. This study analyzes the communication styles among genders, more specifically among married couples.",
								true);

					}
				});

				advancedLessonOne.setVisible(true);
				advancedLessonTwo.setVisible(true);
				advancedLessonThree.setVisible(true);
				advancedLessonFour.setVisible(true);
				advancedLessonFive.setVisible(true);
				advancedLessonSix.setVisible(true);

			}
		});

		// !-- ADVANCED DIFFICULTY --!
		expertDifficulty.setBounds(0, 390, 175, 60);
		expertDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultySelected();

				expertLessonOne.setText("<html><center>Lesson 1 - Left Side</center></html>");
				expertLessonOne.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Left Side",
								"This lesson will focus on using the left side of the keyboard and practicing using only the left hand whilst typing as, naturally, the right hand shouldn't go over to the side of that keyboard.",
								"z!!! a\"\"\" a!!! z!!! a!!! z\"\"\" x!!! s!!! s£££ x!!! z!\"! zx\"\"! a! a!! a! s! aa!!! a!! a! a!!! a! a!a! A!!! s!!! SA!!",
								false);
					}
				});

				expertLessonTwo.setText("<html><center>Lesson 2 - Right Side</center></html>");
				expertLessonTwo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Right Side",
								"Similar to the previous lesson, this lesson will be focusing on one side of the keyboard the use of a single hand in order to practice some of the techniques used with touch typing.",
								"l[ l)) -- :: l:: l{{ l>> k}} p{{ \'\' .] m] ol l; l\' \'\' + l+ i+ p-- p_ l; o+ m. m>> l:: p{{",
								false);
					}
				});

				expertLessonThree.setText("<html><center>Lesson 3 - Finale</center></html>");
				expertLessonThree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lessonSelected("Finale",
								"The final lesson of Typing Tutor - this will be a difficult practice lesson that will hopefully put all of the learning that has taken place throughout the program - it will be in the form of a simple piece of text. Thank you for using Typing Tutor, and if oyu wish to continue using the program to learn, feel free to use the solo pratcice mode available at the top! ",
								"\"When I hear you give your reasons,\" I remarked, \"the thing always appears to me to be so ridiculously simple that I could easily do it myself, though at each successive instance of your reasoning I am baffled until you explain your process.\"",
								true);
					}
				});

				expertLessonOne.setVisible(true);
				expertLessonTwo.setVisible(true);
				expertLessonThree.setVisible(true);
			}
		});

		startButton.setBounds(860, 505, 100, 35);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titleArea.setText(null);
				descriptionArea.setText(null);

				disposeAll();

				typingScreen.beginCountdown();
			}
		});
		startButton.setVisible(false);

		titleArea.setBounds(385, 125, 575, 50);
		titleArea
				.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		titleArea.setEditable(false);
		titleArea.setFont(titleAreaFont);
		titleArea.setVisible(false);

		descriptionArea.setBounds(385, 200, 575, 300);
		descriptionArea
				.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		descriptionArea.setEditable(false);
		descriptionArea.setFont(descriptionAreaFont);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setVisible(false);

		// -- SOLO PRACTICE --
		// Here, all the elements (mostly JButtons) that will be used with the solo
		// practice
		// mode are setup. This is, of course, different to the core typing course that
		// the
		// program is intended for. This gives the user an area to type in and practice
		// their
		// typing with real-world practice.
		practiceButton.setBounds(600, 0, 150, 50);
		practiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton continueButton = new JButton("Continue");
				JButton backButtonSolo = new JButton("Back");
				JButton addButton = new JButton("+");
				JLabel practiceChoiceLabel = new JLabel("Select a piece of text: ");
				JLabel previewLabel = new JLabel("Preview of selected text: ");
				JTextArea previewArea = new JTextArea();

				continueButton.setBounds(275, 220, 110, 40);
				continueButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						wpmBoolean = true;
						menuFrame.dispose();
						soloPracticeFrame.setVisible(false);
						typingScreen.beginCountdown();
					}
				});

				backButtonSolo.setBounds(10, 220, 110, 40);
				backButtonSolo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						soloPracticeFrame.dispose();
					}
				});

				try {
					fileToArrays();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				addButton.setBounds(335, 15, 50, 20);
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						JButton addButtonAdd = new JButton("Add");
						JButton backButtonAdd = new JButton("Back");
						JLabel textNameLabel = new JLabel("Enter a name: ");
						JLabel textTextLabel = new JLabel("Enter the text: ");
						JTextArea textNameArea = new JTextArea();
						JTextArea textTextArea = new JTextArea();

						addButtonAdd.setBounds(380, 280, 110, 40);
						addButtonAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								addTextName(textNameArea.getText());
								addTextText(textTextArea.getText());
								textNames.add(textNameArea.getText());
								textTexts.add(textTextArea.getText());

								choicesList.addItem(textNameArea.getText());
								addButtonFrame.dispose();
							}
						});

						backButtonAdd.setBounds(10, 280, 110, 40);
						backButtonAdd.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addButtonFrame.dispose();
							}
						});

						textNameLabel.setBounds(20, 30, 200, 30);
						textNameLabel.setVisible(false);

						textNameArea.setBounds(200, 30, 250, 20);
						textNameArea.setBorder(BorderFactory.createCompoundBorder(border,
								BorderFactory.createEmptyBorder(2, 2, 2, 2)));
						textNameArea.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_TAB) {
									if (e.getModifiers() > 0) {
										textNameArea.transferFocusBackward();
									} else {
										textNameArea.transferFocus();
									}
									e.consume();
								}
							}
						});

						textTextLabel.setBounds(20, 60, 200, 20);

						textTextArea.setBounds(200, 60, 250, 150);
						textTextArea.setLineWrap(true);
						textTextArea.setWrapStyleWord(true);
						textTextArea.setBorder(BorderFactory.createCompoundBorder(border,
								BorderFactory.createEmptyBorder(3, 3, 3, 3)));
						textTextArea.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_TAB) {
									if (e.getModifiers() > 0) {
										textTextArea.transferFocusBackward();
									} else {
										textTextArea.transferFocus();
									}
									e.consume();
								}
							}
						});

						backButtonAdd.setVisible(true);
						addButtonAdd.setVisible(true);
						textNameLabel.setVisible(true);
						textTextLabel.setVisible(true);
						textNameArea.setVisible(true);
						textTextArea.setVisible(true);

						addButtonFrame.setLayout(null);
						addButtonFrame.setSize(500, 350);
						addButtonFrame.setLocationRelativeTo(null);
						addButtonFrame.setResizable(false);

						addButtonFrame.add(backButtonAdd);
						addButtonFrame.add(addButtonAdd);
						addButtonFrame.add(textNameLabel);
						addButtonFrame.add(textTextLabel);
						addButtonFrame.add(textNameArea);
						addButtonFrame.add(textTextArea);

						addButtonFrame.setVisible(true);

					}
				});

				addButton.setVisible(true);
				backButtonSolo.setVisible(true);
				continueButton.setVisible(true);

				practiceChoiceLabel.setBounds(20, 10, 175, 30);

				choicesList.setBounds(180, 15, 150, 20);
				choicesList.setSelectedIndex(0);
				assignedText = textTexts.get(choicesList.getSelectedIndex());
				choicesList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						assignedText = textTexts.get(choicesList.getSelectedIndex());
						previewArea.setText(assignedText);
					}
				});

				previewLabel.setBounds(20, 50, 150, 20);

				previewArea.setBounds(180, 50, 200, 120);
				previewArea.setLineWrap(true);
				previewArea.setWrapStyleWord(true);
				previewArea.setBorder(
						BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(3, 3, 3, 3)));
				previewArea.setText(textTexts.get(choicesList.getSelectedIndex()));
				previewArea.setFont(new Font("Sans Serif", Font.PLAIN, 12));
				previewArea.setEditable(false);

				soloPracticeFrame.setLayout(null);
				soloPracticeFrame.setSize(400, 300);
				soloPracticeFrame.setTitle("Typing Tutor - Solo Practice Options");
				soloPracticeFrame.setLocationRelativeTo(null);
				soloPracticeFrame.setResizable(false);

				soloPracticeFrame.add(addButton);
				soloPracticeFrame.add(backButtonSolo);
				soloPracticeFrame.add(continueButton);
				soloPracticeFrame.add(choicesList);
				soloPracticeFrame.add(practiceChoiceLabel);
				soloPracticeFrame.add(previewLabel);
				soloPracticeFrame.add(previewArea);

				soloPracticeFrame.setVisible(true);

			}
		});

		// -- OTHER MENU BUTTONS --
		// This section will deal with the buttons that are displayed on the main menu,
		// but aren't a major part of the program's core function. Examples will include
		// the quit button and the settings button.
		settingsButton.setBounds(875, 0, 125, 50);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsScreen();
			}
		});

		closeProgramButton.setBounds(15, 510, 150, 50);
		closeProgramButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to close Typing Tutor?",
						"Typing Tutor", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		statsButton.setBounds(750, 0, 125, 50);
		statsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					statisticsScreen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		username.setBounds(800, 550, 300, 20);
		username.setText("Logged in as: " + getUsername());

		typingTutorTitle.setBounds(20, 10, 200, 100);
		typingTutorTitle.setFont(typingTutorTitleFont);

		averageWPMLabel.setBounds(30, 100, 250, 20);

		calculateStars();
		
		menuFrame.setLayout(null);

		menuFrame.add(introDifficulty);
		menuFrame.add(beginnerDifficulty);
		menuFrame.add(intermediateDifficulty);
		menuFrame.add(advancedDifficulty);
		menuFrame.add(expertDifficulty);
		menuFrame.add(practiceButton);
		menuFrame.add(settingsButton);
		menuFrame.add(startButton);
		menuFrame.add(closeProgramButton);
		menuFrame.add(statsButton);

		menuFrame.add(introLessonOne);
		menuFrame.add(introLessonTwo);
		menuFrame.add(introLessonThree);
		menuFrame.add(introLessonFour);
		menuFrame.add(introLessonFive);
		menuFrame.add(introLessonSix);
		menuFrame.add(introLessonSeven);

		menuFrame.add(beginnerLessonOne);
		menuFrame.add(beginnerLessonTwo);
		menuFrame.add(beginnerLessonThree);
		menuFrame.add(beginnerLessonFour);
		menuFrame.add(beginnerLessonFive);
		menuFrame.add(beginnerLessonSix);

		menuFrame.add(intermLessonOne);
		menuFrame.add(intermLessonTwo);
		menuFrame.add(intermLessonThree);
		menuFrame.add(intermLessonFour);

		menuFrame.add(advancedLessonOne);
		menuFrame.add(advancedLessonTwo);
		menuFrame.add(advancedLessonThree);
		menuFrame.add(advancedLessonFour);
		menuFrame.add(advancedLessonFive);
		menuFrame.add(advancedLessonSix);

		menuFrame.add(expertLessonOne);
		menuFrame.add(expertLessonTwo);
		menuFrame.add(expertLessonThree);

		menuFrame.add(username);
		menuFrame.add(typingTutorTitle);
		menuFrame.add(averageWPMLabel);
		menuFrame.add(starLabel);
		
		menuFrame.add(titleArea);
		menuFrame.add(descriptionArea);

		menuFrame.setSize(1000, 600);
		menuFrame.setTitle("Typing Tutor");
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setResizable(false);

		menuFrame.setVisible(true);

	}

	public static void lessonSelected(String title, String description, String prompt, boolean wpmBool) {
		titleArea.setVisible(true);
		descriptionArea.setVisible(true);
		startButton.setVisible(true);

		titleArea.setText(title);
		descriptionArea.setText(description);
		assignedText = prompt;
		wpmBoolean = wpmBool;

	}

	public void recommendDifficulty() {
		String difficulty = "";

		TypingScreenGUI typingScreen = new TypingScreenGUI();
		if (typingScreen.getScore() < 4000) {
			difficulty = "Introduction";
		} else if (typingScreen.getScore() >= 4000 && typingScreen.getScore() < 7500) {
			difficulty = "Beginner";
		} else if (typingScreen.getScore() >= 7500 && typingScreen.getScore() < 10000) {
			difficulty = "Intermediate";
		} else if (typingScreen.getScore() >= 10000 && typingScreen.getScore() < 15000) {
			difficulty = "Advanced";
		} else if (typingScreen.getScore() >= 15000) {
			difficulty = "Expert";
		}

		JLabel testCompleteLabel = new JLabel("Placement Test Complete!");
		JLabel recommendedDifficultyLabel = new JLabel(
				"<html><b>Recommended Difficulty: </b>" + difficulty + "<br>You scored " + (int) typingScreen.getScore()
						+ " points with a typing speed of " + (int) typingScreen.getWPM() + "wpm.");
		JLabel difficultyDescription = new JLabel("<html>You have been recommended the " + difficulty
				+ " difficulty! It is recommended that you start here, however if you wish to go to a different stage instead, then you have the option to do so!");
		JButton okayButton = new JButton("Continue");
		Font testCompleteFont = new Font("SansSerif", Font.PLAIN, 20);

		testCompleteLabel.setBounds(10, 5, 300, 30);
		testCompleteLabel.setFont(testCompleteFont);

		recommendedDifficultyLabel.setBounds(25, 40, 300, 45);

		difficultyDescription.setBounds(25, 85, 350, 100);

		okayButton.setBounds(285, 175, 100, 45);
		okayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recommendFrame.dispose();
			}
		});

		switch (difficulty) {
		case "Introduction":
			introDifficulty.setBackground(Color.GREEN);
			break;
		case "Beginner":
			beginnerDifficulty.setBackground(Color.GREEN);
			break;
		case "Intermediate":
			intermediateDifficulty.setBackground(Color.GREEN);
			break;
		case "Advanced":
			advancedDifficulty.setBackground(Color.GREEN);
			break;
		case "Expert":
			expertDifficulty.setBackground(Color.GREEN);
			break;

		default:
			break;

		}

		recommendFrame.add(testCompleteLabel);
		recommendFrame.add(recommendedDifficultyLabel);
		recommendFrame.add(difficultyDescription);
		recommendFrame.add(okayButton);

		recommendFrame.setSize(400, 250);
		recommendFrame.setLocationRelativeTo(null);
		recommendFrame.setLayout(null);
		recommendFrame.setResizable(false);
		recommendFrame.setVisible(true);
	}

	public void difficultySelected() {
		titleArea.setVisible(false);
		descriptionArea.setVisible(false);

		if (introLessonOne.isVisible() == true || beginnerLessonOne.isVisible() == true
				|| intermLessonOne.isVisible() == true || advancedLessonOne.isVisible() == true) {
			introLessonOne.setVisible(false);
			introLessonTwo.setVisible(false);
			introLessonThree.setVisible(false);
			introLessonFour.setVisible(false);
			introLessonFive.setVisible(false);
			introLessonSix.setVisible(false);
			introLessonSeven.setVisible(false);

			beginnerLessonOne.setVisible(false);
			beginnerLessonTwo.setVisible(false);
			beginnerLessonThree.setVisible(false);
			beginnerLessonFour.setVisible(false);
			beginnerLessonFive.setVisible(false);
			beginnerLessonSix.setVisible(false);

			intermLessonOne.setVisible(false);
			intermLessonTwo.setVisible(false);
			intermLessonThree.setVisible(false);
			intermLessonFour.setVisible(false);

			advancedLessonOne.setVisible(false);
			advancedLessonTwo.setVisible(false);
			advancedLessonThree.setVisible(false);
			advancedLessonFour.setVisible(false);
			advancedLessonFive.setVisible(false);
			advancedLessonSix.setVisible(false);

			expertLessonOne.setVisible(false);
			expertLessonTwo.setVisible(false);
			expertLessonThree.setVisible(false);

		}
	}

	public void statisticsScreen() throws IOException {
		Path usernamePath = Paths.get("data/user.txt");
		BasicFileAttributes usernameAttributes = Files.getFileAttributeView(usernamePath, BasicFileAttributeView.class)
				.readAttributes();
		Date creationDate = new Date(usernameAttributes.creationTime().to(TimeUnit.MILLISECONDS));

		JLabel statsTitle = new JLabel("<html><b>Statistics for " + getUsername() + "</b></html>");
		JLabel statsLabel = new JLabel("<html><b>Start Date:</b> " + creationDate.getDate() + "/"
				+ (creationDate.getMonth() + 1) + "/" + (creationDate.getYear() + 1900) + "<br>"
				+ "<b>Average WPM</b>: " + new DecimalFormat("#0.0").format(averageWPM) + "<br>"
				+ "<b>Sessions Complete</b>: " + +lines + "<br>" + "</html>");
		JButton closeButton = new JButton("Close");
		JButton graphButton = new JButton("WPM Graph");

		statsTitle.setBounds(20, 10, 350, 30);
		statsTitle.setFont(new Font("Sans Serif", Font.BOLD, 24));

		statsLabel.setBounds(20, 30, 350, 100);
		statsLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));

		closeButton.setBounds(20, 225, 125, 45);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statsFrame.dispose();
			}
		});

		graphButton.setBounds(255, 225, 125, 45);
		graphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XYDataset data = createDataset();
				JFreeChart chart = ChartFactory.createXYLineChart("WPM over time", "Sessions Complete",
						"Words Per Minute (wpm)", data, PlotOrientation.VERTICAL, true, true, false);

				ChartPanel chartPanel = new ChartPanel(chart);

				graphFrame.setSize(600, 400);
				graphFrame.setLocationRelativeTo(null);
				graphFrame.setResizable(false);

				graphFrame.setVisible(true);

				graphFrame.getContentPane().add(chartPanel);
			}
		});

		statsFrame.setSize(400, 300);
		statsFrame.setLocationRelativeTo(null);
		statsFrame.setLayout(null);
		statsFrame.setResizable(false);

		statsFrame.add(statsTitle);
		statsFrame.add(statsLabel);
		statsFrame.add(closeButton);
		statsFrame.add(graphButton);

		statsFrame.setVisible(true);
	}

	public void settingsScreen() {
		String[] sizes = { "Small", "Medium", "Large" };

		JLabel muteSoundLabel = new JLabel("Mute Sound:");
		JLabel volumeLabel = new JLabel("Volume:");
		JLabel promptSizeLabel = new JLabel("Prompt Font Size:");
		JLabel inputSizeLabel = new JLabel("Input Font Size:");
		JLabel resetWPMLabel = new JLabel("Reset WPM Records?");
		JLabel resetDataLabel = new JLabel("Reset All Data?");

		JCheckBox muteSoundBox = new JCheckBox();
		JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		JComboBox<String> promptSizeBox = new JComboBox<String>(sizes);
		JComboBox<String> inputSizeBox = new JComboBox<String>(sizes);
		JButton resetWPMButton = new JButton("<html><center>Reset</center></html>");
		JButton resetDataButton = new JButton("<html><center>Reset</center></html>");
		JButton aboutButton = new JButton("<html><center>About Typing Tutor</center></html>");
		JButton backButton = new JButton("Back");

		muteSoundLabel.setBounds(20, 10, 100, 30);
		muteSoundBox.setBounds(170, 15, 20, 20);

		volumeLabel.setBounds(20, 42, 100, 30);
		volumeSlider.setBounds(120, 47, 120, 20);

		promptSizeLabel.setBounds(20, 80, 120, 20);
		promptSizeBox.setBounds(140, 80, 100, 20);
		promptSizeBox.setSelectedIndex(1);
		promptSizeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(promptSizeBox.getSelectedIndex()) {
				case 0: {
					promptAreaSize = 32;
					break;
				}
				case 1: {
					promptAreaSize = 36;
					break;
				}
				case 2: {
					promptAreaSize = 40;
					break;
				}
				default: 
					promptAreaSize = 36;
					break;
				}
			}
		});
		
		inputSizeLabel.setBounds(20, 110, 120, 20);
		inputSizeBox.setBounds(140, 110, 100, 20);
		inputSizeBox.setSelectedIndex(1);
		inputSizeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(inputSizeBox.getSelectedIndex()) {
				case 0: {
					typingAreaSize = 26;
					break;
				}
				
				case 1: {
					typingAreaSize = 30;
					break;
				}
				case 2: {
					typingAreaSize = 34;
					break;
				}
				default: 
					typingAreaSize = 30;
					break;
				}
			}
		});
		
		resetWPMLabel.setBounds(15, 140, 140, 20);
		resetWPMButton.setBounds(152, 140, 80, 20);
		resetWPMButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you would like to reset your WPM data? There's no going back.", "Typing Tutor",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					resetWPM();
					averageWPMLabel.setText("Average WPM: 0");
				}
			}
		});

		resetDataLabel.setBounds(20, 170, 140, 20);
		resetDataButton.setBounds(152, 170, 80, 20);
		resetDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you would like to reset all of your data?", "Typing Tutor",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					resetData();
				}
			}
		});

		aboutButton.setBounds(35, 205, 180, 30);
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel titleLabel = new JLabel("<html>Typing Tutor</html>");
				JLabel descriptionLabel = new JLabel(
						"<html><center>Typing Tutor has been developed as a part of the OCR A Level Computer Science programming project. The problem found has been that students in the current year have rather low typing speeds, and this program intends to assist them in speeding up their WPM.</center></html>");
				JLabel yearLabel = new JLabel("<html><center>2018</center></html>");
				JButton backButton = new JButton("Back");

				titleLabel.setBounds(90, 10, 250, 100);
				titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 36));

				descriptionLabel.setBounds(50, 100, 300, 150);

				yearLabel.setBounds(185, 85, 50, 30);

				backButton.setBounds(10, 238, 70, 30);
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aboutFrame.dispose();
					}
				});

				aboutFrame.setSize(400, 300);
				aboutFrame.setLocationRelativeTo(null);
				aboutFrame.setLayout(null);
				aboutFrame.setResizable(false);

				aboutFrame.add(titleLabel);
				aboutFrame.add(descriptionLabel);
				aboutFrame.add(yearLabel);
				aboutFrame.add(backButton);

				aboutFrame.setVisible(true);
			}
		});

		backButton.setBounds(5, 245, 80, 25);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsFrame.dispose();
			}
		});

		settingsFrame.setSize(250, 300);
		settingsFrame.setLocationRelativeTo(null);
		settingsFrame.setLayout(null);
		settingsFrame.setResizable(false);

		settingsFrame.add(muteSoundLabel);
		settingsFrame.add(volumeLabel);
		settingsFrame.add(promptSizeLabel);
		settingsFrame.add(inputSizeLabel);
		settingsFrame.add(muteSoundBox);
		settingsFrame.add(volumeSlider);
		settingsFrame.add(promptSizeBox);
		settingsFrame.add(inputSizeBox);
		settingsFrame.add(resetWPMLabel);
		settingsFrame.add(resetWPMButton);
		settingsFrame.add(resetDataLabel);
		settingsFrame.add(resetDataButton);
		settingsFrame.add(aboutButton);
		settingsFrame.add(backButton);

		settingsFrame.setVisible(true);

	}

	// Creates the set of data for use by the graphFrame under the
	// statistics screen.
	private static XYDataset createDataset() {

		double[] wpmArray = wpmFilesToArray();

		final XYSeries data = new XYSeries("WPM");

		// Adds the sessions complete and the value of adjacent line
		// to the data set. Each increment of i is one session complete
		// which reads from the next line down.
		for (int i = 0; i < wpmArray.length; i++) {
			data.add(i + 1, wpmArray[i]);
		}
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(data);

		return dataset;
	}

	// Functions that are used to add both the names of the texts
	// and the actual text of which a user will input into
	// the solo practice mode if they wish to make a custom
	// entry.

	public static void addTextName(String textName) {
		choicesList.addItem(textName);
		try {
			FileWriter textNameWriter = new FileWriter(textNamesFilePath, true);
			BufferedWriter textNameBufferedWriter = new BufferedWriter(textNameWriter);
			PrintWriter textNamePrintWriter = new PrintWriter(textNameBufferedWriter);

			textNamePrintWriter.println(textName);
			textNamePrintWriter.flush();
			textNamePrintWriter.close();

		} catch (Exception E) {

		}
	}

	public static void addTextText(String text) {
		try {
			FileWriter textTextWriter = new FileWriter(textTextFilePath, true);
			BufferedWriter textTextBufferedWriter = new BufferedWriter(textTextWriter);
			PrintWriter textTextPrintWriter = new PrintWriter(textTextBufferedWriter);

			textTextPrintWriter.println(text);
			textTextPrintWriter.flush();
			textTextPrintWriter.close();

		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public void createWPMFile() throws IOException {
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

	public static void fileToArrays() throws IOException {

		BufferedReader nameReader = new BufferedReader(new FileReader("data/textNames.txt"));
		BufferedReader textReader = new BufferedReader(new FileReader("data/textText.txt"));
		String line;

		textNames.clear();
		textTexts.clear();

		while ((line = nameReader.readLine()) != null) {
			textNames.add(line);

		}
		while ((line = textReader.readLine()) != null) {
			textTexts.add(line);
		}
		nameReader.close();
		textReader.close();

	}

	public static double[] wpmFilesToArray() {
		try {
			scan = new Scanner(new File("data/wpm.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		averageWPMs.clear();

		while (scan.hasNextDouble()) {
			averageWPMs.add(scan.nextDouble());

		}

		double[] wpmList = averageWPMs.stream().mapToDouble(d -> d).toArray();

		return wpmList;
	}

	public void getAverageWPM() {

		double[] wpmList = wpmFilesToArray();
		double sum = 0;

		for (int i = 0; i < wpmList.length; i++) {
			sum += wpmList[i];
		}

		if (wpmList.length == 0) {
			averageWPM = 0;
		} else {
			averageWPM = (sum / wpmList.length);
		}

		lines = wpmList.length;
		averageWPMLabel.setText("Average WPM: " + new DecimalFormat("#0").format(averageWPM));
	}

	// Functions that are used to create the files that are used
	// with Typing Tutor if they don't exist. Creates a .txt file for both
	// the text names and the actual texts into a data directory where the
	// application is launched.
	public static void createTextNamesFile() {
		textNamesFile = new File(textNamesFilePath);

		try {
			textNamesFile.getParentFile().mkdirs();
			try {
				textNamesFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SecurityException se) {

		}
	}

	public static void createTextTextFile() {
		textTextFile = new File(textTextFilePath);
		try {
			textTextFile.getParentFile().mkdirs();
			try {
				textTextFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SecurityException se) {

		}
	}

	public static String getUsername() {
		String line = null;
		try {
			FileReader fileReader = new FileReader("data/user.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				return line;
			}
			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("data/user.txt does not exist.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "N/A";

	}

	public void resetWPM() {
		File wpmFile = new File("data/wpm.txt");

		if (wpmFile.delete()) {
			System.out.println("data/wpm.txt deleted successfully.");
		} else {
			System.out.println("Failed to delete data/wpm.txt.");
		}
	}

	public void resetData() {
		resetWPM();
		File dataFile = new File("data/user.txt");

		if (dataFile.delete()) {
			System.out.println("data/user.txt deleted successfully.");
		} else {
			System.out.println("Failed to delete data/user.txt.");
		}

		disposeAll();

		JOptionPane.showMessageDialog(null, "All data has been reset.");

		typingTutorInit.init();
	}

	public void disposeAll() {
		menuFrame.dispose();
		soloPracticeFrame.dispose();
		addButtonFrame.dispose();
		recommendFrame.dispose();
		statsFrame.dispose();
		graphFrame.dispose();
		settingsFrame.dispose();
		aboutFrame.dispose();
	}

	// Calculates the stars earned by the user and also sets the 
	// starLabel text accordingly
	public void calculateStars() {
		int currentStars = 0;
		int maxStars = 75;
		
		starLabel.setFont(new Font("Sans Serif", Font.PLAIN, 34));
		starLabel.setText("✯: " + currentStars + " / " + maxStars);
		starLabel.setBounds(350,10,150,60);
	}
	
	// Getter and setter for totalWPM
	public double getTotalWPM() {
		return totalWPM;
	}

	public void setTotalWPM(double x) {
		totalWPM = x;
	}

	// Getter and setter for totalTrials
	public int getTotalTrials() {
		return totalTrials;
	}

	public void incrementTotalTrials() {
		totalTrials += 1;
	}

	// Getter for wpmBoolean
	public boolean getWPMboolean() {
		return wpmBoolean;
	}

	// Getter for assignedText
	public String getText() {
		return assignedText;
	}

	// Getter for promptFontSize 
	public int getPromptSize() {
		return promptAreaSize;
	}
	
	// Getter for inputFontSize
	public int getInputSize() {
		return typingAreaSize;
	}
	
	public void disposeMenu() {
		menuFrame.dispose();
	}

	
	
}
