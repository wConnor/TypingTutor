import javax.swing.*;
import javax.swing.border.Border;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class TypingTutorMenu extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  public static JFrame menuFrame, soloPracticeFrame, confirmationFrame, addButtonFrame, recommendFrame;
  private JButton introDifficulty, beginnerDifficulty, intermediateDifficulty, advancedDifficulty, practiceButton,
      expertDifficulty;
  private JButton settingsButton, startButton, closeProgramButton;
  private JButton lessonOne, lessonTwo, lessonThree, lessonFour, lessonFive, lessonSix;
  private JButton addButton, continueButton, backButtonSolo, backButtonAdd, addButtonAdd;
  private JButton yesButton, noButton;
  private static JLabel username;

  private JLabel typingTutorTitle;

  private static JLabel averageWPMLabel;

  private JLabel practiceChoiceLabel;

  private JLabel confirmationLabel;
  private JLabel textNameLabel, textTextLabel;
  private JTextArea titleArea, descriptionArea;
  private JTextArea textNameArea, textTextArea;
  private static JComboBox<String> choicesList;
  public static double averageWPM, totalWPM;
  private Font typingTutorTitleFont, titleAreaFont, descriptionAreaFont;
  private Border border;
  public static String assignedText, line, data, name;
  private static File textNamesFile, textTextFile;
  private static String textNamesFilePath, textTextFilePath, wpmFilePath;
  public static int totalTrials;
  public static Boolean wpmBoolean;

  private static Scanner scan;
  
  private static List<String> textNames;
  private static List<String> textTexts;
  private static List<Double> averageWPMs;
  

  public void menu() {

    TypingScreenGUI typingScreen = new TypingScreenGUI();
    
    menuFrame = new JFrame();
    soloPracticeFrame = new JFrame();
    confirmationFrame = new JFrame();
    addButtonFrame = new JFrame();
    recommendFrame = new JFrame();

    introDifficulty = new JButton("Introduction");
    beginnerDifficulty = new JButton("Beginner");
    advancedDifficulty = new JButton("Advanced");
    intermediateDifficulty = new JButton("Intermediate");
    expertDifficulty = new JButton("Expert");
    practiceButton = new JButton("Solo Practice");
    settingsButton = new JButton("Settings");
    startButton = new JButton("Start");
    closeProgramButton = new JButton("Quit");
    yesButton = new JButton("Yes");
    noButton = new JButton("No");

    lessonOne = new JButton();
    lessonTwo = new JButton();
    lessonThree = new JButton();
    lessonFour = new JButton();
    lessonFive = new JButton();
    lessonSix = new JButton();
    
    addButton = new JButton("+");
    continueButton = new JButton("Continue");
    backButtonSolo = new JButton("Back");
    backButtonAdd = new JButton("Back");
    addButtonAdd = new JButton("Add");
    practiceChoiceLabel = new JLabel("Select a piece of text: ");
    confirmationLabel = new JLabel("<html><center>Are you sure you want to close Typing Tutor?</center></html>");

    choicesList = new JComboBox();
    textNameLabel = new JLabel("Enter a name: ");
    textTextLabel = new JLabel("Enter the text: ");
    textNameArea = new JTextArea();
    textTextArea = new JTextArea();


    
    username = new JLabel();
    typingTutorTitle = new JLabel("<html>Typing <br> \u0000 \u0000 Tutor</html>");
    averageWPMLabel = new JLabel("Average WPM: " + new DecimalFormat("#0").format(averageWPM));

    titleArea = new JTextArea();
    descriptionArea = new JTextArea();

    typingTutorTitleFont = new Font("SansSerif", Font.BOLD, 34);
    titleAreaFont = new Font("SansSerif", Font.BOLD, 24);
    descriptionAreaFont = new Font("SansSerif", Font.PLAIN, 18);

    border = BorderFactory.createLineBorder(Color.BLACK);

    typingScreen.textToType = "This is the default assignedText. This shouldn't be seen as the contents of this variable should be overwritten by the lesson or solo practice mode.";

    wpmBoolean = true;

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
    recommendFrame.setVisible(false);
    
    /*if (typingTest.completeFlag = true) {
    	String difficulty = "";
    	JLabel recommendedDifficultyLabel = new JLabel("<html><b>Recommended Difficulty: </b>" + difficulty);
    	
    	if (typingScreen.score > 1000) {
    		difficulty = "Expert";
    	} else {
    		difficulty = "Easy";
    	}
    	
    	typingTest.completeFlag = false;
    	
    	recommendedDifficultyLabel.setBounds(20,20,150,150);
    	
    	recommendFrame.add(recommendedDifficultyLabel);
    	
    	recommendFrame.setSize(400,250);
    	recommendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	recommendFrame.setLocationRelativeTo(null);
    	recommendFrame.setLayout(null);
    	recommendFrame.setResizable(false);
    	recommendFrame.setVisible(true);
    } */
    

    // Handles the files. If the contents of the data folder that are expected
    // to be there aren't, then the program checks each of them and creates
    // said file. Ensures consistency when dealing with files.
    if (!textNamesFile.exists()) {
      System.out.println(textNamesFilePath + " does not exist. Creating file...");
      createTextNamesFile();

      addTextName("A Confederacy Of Dunces");
      addTextName("The Stranger");
      addTextName("American Tabloid");
    }
    else {
      System.out.println(textNamesFilePath + " already exists. Not creating.");
    }

    
    if (!textTextFile.exists()) {
      System.out.println(textTextFilePath + " does not exist. Creating file...");
      createTextTextFile();
      addTextText("A green hunting cap squeezed the top of the fleshy balloon of a head. The green earflaps, full of large ears and uncut hair and the fine bristles that grew in the ears themselves, stuck out on either side like turn signals indicating two directions at once.");
      addTextText("Mother died today. Or maybe yesterday, I don't know. I had a telegram from the home: 'Mother passed away. Funeral tomorrow. Yours sincerely.' That doesn't mean anything. It may have been yesterday.");
      addTextText("America was never innocent. We popped our cherry on the boat over and looked back with no regrets. You can't ascribe our fall from grace to any single event or set of circumstances. You can't lose what you lacked at conception.");
    }
    else {
      System.out.println(textTextFilePath + " already exists. Not creating.");
    }

    if (!wpmFile.exists()) {
    	System.out.println(wpmFilePath + " does not exist. Creating file...");
    	try {
    		createWPMFile();
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    else {
    	System.out.println(wpmFilePath + " already exists. Not creating.");
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
    // Setting up the JButtons that are all necessary for navigating through the different stages 
    // as well as their individual levels. Their bounds will be set, and action listeners are also 
    // set on each button so that the user can (naturally) interact with them. In addition, clicking
    // each lesson sets the assignedText variable to whatever the text for the lesson will be, allowing
    // TypingScreenGUI.java to then access it.
    lessonOne.setBounds(175,150,175,60);
    lessonOne.setVisible(false);

    lessonTwo.setBounds(175,210,175,60);
    lessonTwo.setVisible(false);

    lessonThree.setBounds(175,270,175,60);
    lessonThree.setVisible(false);

    lessonFour.setBounds(175,330,175,60);
    lessonFour.setVisible(false);

    lessonFive.setBounds(175,390,175,60);
    lessonFive.setVisible(false);

    lessonSix.setBounds(175,450,175,60);
    lessonSix.setVisible(false);

    introDifficulty.setBounds(0, 150, 175, 60);
    introDifficulty.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        

        if (lessonOne.isVisible() == true) {
          lessonOne.setVisible(false);
          lessonTwo.setVisible(false);
          lessonThree.setVisible(false);
          lessonFour.setVisible(false);
          lessonFive.setVisible(false);
          lessonSix.setVisible(false);
        }

        lessonOne.setText("<html><center>Lesson 1 - The F and J Keys</center></html>");
        lessonOne.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {

            titleArea.setVisible(true);
            descriptionArea.setVisible(true);
            startButton.setVisible(true);

            typingScreen.textToType = "ffjfjfjj";

            titleArea.setText("The F and J Keys");
            descriptionArea.setText("In this lesson, you will be practicing the use of the F and J keys when it comes to typing. Both of these keys are very important and it's critical that you come to familiarise yourself with these keys to learn the ability of touch typing.\n\nAs you may have noticed on the keyboard, there's small little lumps on the F and J keys. This is because these are the keys where both of your index fingers should be placed whilst you're typing.");

          }
        });

        lessonTwo.setText("<html><center>Lesson 2 - Common Keys</center></html>");
        lessonTwo.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            titleArea.setVisible(true);
            descriptionArea.setVisible(true);
            startButton.setVisible(true);

            typingScreen.textToType = "f e i f d k r f e j t k d g f l e f a h h f g d h j d k d s";
            
            titleArea.setText("Common Keys");
            descriptionArea.setText("In this lesson, the common keys that are used when typing will be practiced. These keys will be close to the F and J keys, and are used with most words that'll be typed on the keyboard, such as the letters t, d, and k (for example).\n\nIn later lessons, more practice of keys will be present, notably the more difficult and less common ones.\n\n ** Does not count towards average WPM **");

          }
        });

        lessonThree.setText("<html><center>Lesson 3 - More Common Keys</center></html>");
        lessonFour.setText("<html><center>Lesson 4 - Uncommon Keys</center></html>");

        lessonOne.setVisible(true);
        lessonTwo.setVisible(true);
        lessonThree.setVisible(true);
        lessonFour.setVisible(true);

      }
    });

    beginnerDifficulty.setBounds(0,210,175,60);
    beginnerDifficulty.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (lessonOne.isVisible() == true) {
          lessonOne.setVisible(false);
          lessonTwo.setVisible(false);
          lessonThree.setVisible(false);
          lessonFour.setVisible(false);
          lessonFive.setVisible(false);
          lessonSix.setVisible(false);

        }

        lessonOne.setText("<html><center>Lesson 1 - Simple Words</center></html>");
        lessonTwo.setText("<html><center>Lesson 2 - More Word Practice</center></html>");
        lessonThree.setText("<html><center>Lesson 3 - Incorporating Basic Punctuation</center></html>");
        lessonFour.setText("<html><center>Lesson 4 - Basic Sentences</center></html>");
        lessonFive.setText("<html><center>Lesson 5 - More Basic Sentences</center></html>");

        lessonOne.setVisible(true);
        lessonTwo.setVisible(true);
        lessonThree.setVisible(true);
        lessonFour.setVisible(true);
        lessonFive.setVisible(true);
      }
    });

    intermediateDifficulty.setBounds(0,270,175,60);

    advancedDifficulty.setBounds(0,330,175,60);

    expertDifficulty.setBounds(0,390,175,60);

    startButton.setBounds(860,505,100,35);
    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	wpmBoolean = false;
        menuFrame.setVisible(false);
        soloPracticeFrame.setVisible(false);
        typingScreen.startGUI();
      }
    });
    startButton.setVisible(false);

    titleArea.setBounds(385,125,575,50);
    titleArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    titleArea.setEditable(false);
    titleArea.setFont(titleAreaFont);
    titleArea.setVisible(false);

    descriptionArea.setBounds(385,200,575,300);
    descriptionArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    descriptionArea.setEditable(false);
    descriptionArea.setFont(descriptionAreaFont);
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    descriptionArea.setVisible(false);

    // -- SOLO PRACTICE --
    // Here, all the elements (mostly JButtons) that will be used with the solo practice
    // mode are setup. This is, of course, different to the core typing course that the
    // program is intended for. This gives the user an area to type in and practice their
    // typing with real-world practice.
    practiceButton.setBounds(600,0,150,50);
    practiceButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
			fileToArrays();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
        


        addButton.setVisible(true);
        backButtonSolo.setVisible(true);
        continueButton.setVisible(true);

        practiceChoiceLabel.setBounds(20,10,175,30);

        choicesList.setBounds(180,15,150,20);
        choicesList.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  typingScreen.textToType = textTexts.get(choicesList.getSelectedIndex());

           }
        });

        soloPracticeFrame.setLayout(null);
        soloPracticeFrame.setSize(400,300);
        soloPracticeFrame.setTitle("Typing Tutor - Solo Practice Options");
        soloPracticeFrame.setLocationRelativeTo(null);
        soloPracticeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        soloPracticeFrame.setResizable(false);

        soloPracticeFrame.add(addButton);
        soloPracticeFrame.add(backButtonSolo);
        soloPracticeFrame.add(continueButton);
        soloPracticeFrame.add(choicesList);
        soloPracticeFrame.add(practiceChoiceLabel);

        soloPracticeFrame.setVisible(true);

      }
    });

    continueButton.setBounds(275,225,110,40);
    continueButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	System.out.println(choicesList.getSelectedIndex());
    	System.out.println(textNames.get(choicesList.getSelectedIndex()));
    	System.out.println(textTexts.get(choicesList.getSelectedIndex()));

    	
    	wpmBoolean = true;
        menuFrame.setVisible(false);
        soloPracticeFrame.setVisible(false);
        typingScreen.startGUI();
      }
    });
    continueButton.setVisible(false);


    backButtonSolo.setBounds(10,225,110,40);
    backButtonSolo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        soloPracticeFrame.setVisible(false);
      }
    });
    backButtonSolo.setVisible(false);

    addButton.setBounds(335,15,50,20);
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      backButtonAdd.setVisible(true);
      addButtonAdd.setVisible(true);
      textNameLabel.setVisible(true);
      textTextLabel.setVisible(true);
      textNameArea.setVisible(true);
      textTextArea.setVisible(true);

      addButtonFrame.setLayout(null);
      addButtonFrame.setSize(500,350);
      addButtonFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    addButton.setVisible(false);

    backButtonAdd.setBounds(10,280,110,40);
    backButtonAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addButtonFrame.setVisible(false);
      }
    });
    backButtonAdd.setVisible(false);

    addButtonAdd.setBounds(380,280,110,40);
    addButtonAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {    	
    	  
         addTextName(textNameArea.getText());
         addTextText(textTextArea.getText());
         textNames.add(textNameArea.getText());
         textTexts.add(textTextArea.getText());

         choicesList.addItem(textNameArea.getText());
         addButtonFrame.setVisible(false);
      }
    });

    textNameLabel.setBounds(20,30,200,30);
    textNameLabel.setVisible(false);

    textNameArea.setBounds(200,30,250,20);
    textNameArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
    textNameArea.addKeyListener(new KeyAdapter() {
      @SuppressWarnings("deprecation")
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
          if (e.getModifiers() > 0) {
            textNameArea.transferFocusBackward();
          }
          else {
            textNameArea.transferFocus();
          }
          e.consume();
        }
      }
    });
    textNameArea.setVisible(false);

    textTextLabel.setBounds(20,60,200,20);
    textTextLabel.setVisible(false);

    textTextArea.setBounds(200,60,250,150);
    textTextArea.setLineWrap(true);
    textTextArea.setWrapStyleWord(true);
    textTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(3, 3, 3, 3)));
    textTextArea.addKeyListener(new KeyAdapter() {
    @SuppressWarnings("deprecation")
	@Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
          if (e.getModifiers() > 0) {
            textTextArea.transferFocusBackward();
          }
          else {
            textTextArea.transferFocus();
          }
          e.consume();
        }
      }
    });
    textTextArea.setVisible(false);

    // -- OTHER MENU BUTTONS -- 
    // This section will deal with the buttons that are displayed on the main menu,
    // but aren't a major part of the program's core function. Examples will include
    // the quit button and the settings button.
    settingsButton.setBounds(875,0,125,50);

    closeProgramButton.setBounds(15,510,150,50);
    closeProgramButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  
    	int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to close Typing Tutor?", "Typing Tutor", JOptionPane.YES_NO_OPTION);
    	if (reply == JOptionPane.YES_OPTION) {
    		System.exit(0);
    	}
      }
    });

    confirmationLabel.setBounds(90,20,500,50);
    confirmationLabel.setVisible(false);
    
    yesButton.setBounds(15,115,150,50);
    yesButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    yesButton.setVisible(false);

    noButton.setBounds(335,115,150,50);
    noButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        confirmationFrame.setVisible(false);
      }
    });
    noButton.setVisible(false);

    username.setBounds(800,550,300,20);

    typingTutorTitle.setBounds(20,10,200,100);
    typingTutorTitle.setFont(typingTutorTitleFont);

    averageWPMLabel.setBounds(30,100,250,20);

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

    menuFrame.add(lessonOne);
    menuFrame.add(lessonTwo);
    menuFrame.add(lessonThree);
    menuFrame.add(lessonFour);
    menuFrame.add(lessonFive);
    menuFrame.add(lessonSix);

    menuFrame.add(username);
    menuFrame.add(typingTutorTitle);
    menuFrame.add(averageWPMLabel);
    
    menuFrame.add(titleArea);
    menuFrame.add(descriptionArea);

    menuFrame.setSize(1000,600);
    menuFrame.setTitle("Typing Tutor");
    menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    menuFrame.setLocationRelativeTo(null);
    menuFrame.setResizable(false);

   	menuFrame.setVisible(true);
  
  
  }

  public String getText() {
		return TypingTutorMenu.assignedText;
	}

  // Functions that are used to add both the names of the texts
  // and the actual text of which a user will input into
  // the solo practice mode if they wish to make a custom
  // entry.
  public static void addTextName(String textName) {
	choicesList.addItem(textName);
	try
    {
      FileWriter textNameWriter= new FileWriter(textNamesFilePath, true);
      BufferedWriter textNameBufferedWriter = new BufferedWriter(textNameWriter);
      PrintWriter textNamePrintWriter = new PrintWriter(textNameBufferedWriter);

      textNamePrintWriter.println(textName);
      textNamePrintWriter.flush();
      textNamePrintWriter.close();

      
    }
    catch(Exception E)
    {

    }
  }

  public static void addTextText(String text) {
    try
    {
      FileWriter textTextWriter= new FileWriter(textTextFilePath, true);
      BufferedWriter textTextBufferedWriter = new BufferedWriter(textTextWriter);
      PrintWriter textTextPrintWriter = new PrintWriter(textTextBufferedWriter);

      textTextPrintWriter.println(text);
      textTextPrintWriter.flush();
      textTextPrintWriter.close();
      


    }
    catch(Exception E)
    {
    	
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
		}
		catch(SecurityException se) {
			
		}
		

	}
  
	public static void writeWPMtoFile(double wpm) throws IOException {
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
  }

  public static void getAverageWPM() {
	  try {
		scan = new Scanner(new File("data/wpm.txt"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  
	  int sum = 0;
	  
	  averageWPMs.clear();
	  
	  while (scan.hasNextDouble()) {
		  averageWPMs.add(scan.nextDouble());
		  
	  }
	  
	  
	  double[] wpmList = averageWPMs.stream().mapToDouble(d -> d).toArray();
	  
	  for (int i = 0; i < wpmList.length; i++) {
		 sum += wpmList[i];

		 
	  }
	  
	  if (wpmList.length == 0) {
		  averageWPM = 0;
	  } else {
	  averageWPM = (sum / wpmList.length);
	  }
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
    }
    catch(SecurityException se) {

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
    }
    catch(SecurityException se) {

    } 
  }

  public static void nameToArray() throws IOException {
	  
	  for (String line : Files.readAllLines(Paths.get("data/textName.txt"))) {
		  
	  }
  }
  
  // Function used for TypingScreenGUI.java to check whether
  // or not to count the WPM achieved for that session towards 
  // the user's average WPM. 
  public Boolean wpmSwitch() {
    return TypingTutorMenu.wpmBoolean;
  }

  public static void getUsername() {
	  File file = new File("data/user.txt");
	  String line = null;
	  try {
		  FileReader fileReader = new FileReader("data/user.txt");
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
	  
		  while((line = bufferedReader.readLine()) != null) {
			  
			  username.setText("Logged in as: " + line);
		  }
	  } catch (FileNotFoundException ex) {
		  System.out.println("Unable to open data/user.txt");
		  
	  } catch (IOException ex) {
		  ex.printStackTrace();
	  }
	  
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {

  }

} 
