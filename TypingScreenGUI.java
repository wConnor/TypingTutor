import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import java.io.*;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

public class TypingScreenGUI extends JFrame implements KeyListener, ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;

	TypingTutorMenu mainMenu = new TypingTutorMenu();
	TypingTest typingTest = new TypingTest();
	
	// Declaration of all the variables that will be used within the
	// GUI of the program.
	private JFrame scene, endScene;
	private JButton finishButton, quitButton;
	private JTextArea promptArea;
	private JTextField typingArea;
	private JLabel wpmLabel, scoreLabel, accuracyLabel, correctLabel, incorrectLabel, fixInputLabel, completeLabel,
			summaryLabel;
	private int currentCharacter, correctCharacters, totalCharactersInput, incorrectCharacters;
	public static double wpm;

	public double accuracy;

	public double points;

	public double startTime;

	public double endTime;

	public double timeElapsed;

	public static double score;
	public String inputText, localTime;

	public static String textToType;
	private Border border;
	public Font promptFont, typingFont, completeFont, summaryFont;
	private DateTimeFormatter dtf;
	private LocalDateTime now;
	public Highlighter highlighter;
	private HighlightPainter correctPainter, incorrectPainter;

	private String qwerty;
	private int rowBreak1, rowBreak2, rowBreak3;
	private List<JButton> buttons;
	
	// Function used to start the GUI.
	public void startGUI() {

		scene = new JFrame();
		endScene = new JFrame();

		qwerty = "1234567890-=qwertyuiop[]asdfghjkl;'#zxcvbnm,./";
		
		rowBreak1 = 11;
		rowBreak2 = 24;
		rowBreak3 = 36;
		
		buttons = new ArrayList<JButton>();
		
		quitButton = new JButton("Quit");

		typingArea = new JTextField(inputText);
		promptArea = new JTextArea(textToType);

		startTime = System.nanoTime();

		fixInputLabel = new JLabel("INPUT: " + textToType.charAt(currentCharacter));
		accuracyLabel = new JLabel("Accuracy: " + new DecimalFormat("#0").format(accuracy) + "%");
		scoreLabel = new JLabel("Score: " + new DecimalFormat("#0").format(score));
		wpmLabel = new JLabel("WPM: " + new DecimalFormat("#0").format(wpm));
		correctLabel = new JLabel("\u2713 CORRECT \u2713"); // \u2713 = âœ“ (CHECK MARK)
		incorrectLabel = new JLabel("\u2718 INCORRECT \u2718"); // \u2718 = âœ˜ (HEAVY BALLOT X)
		completeLabel = new JLabel("Complete!");
		border = BorderFactory.createLineBorder(Color.BLACK);
		typingFont = new Font("SansSerif", Font.PLAIN, 30);
		promptFont = new Font("Consolas", Font.PLAIN, 36);
		completeFont = new Font("SansSerif", Font.BOLD, 30);
		summaryFont = new Font("SansSerif", Font.PLAIN, 26);
		highlighter = promptArea.getHighlighter();
		correctPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
		incorrectPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);

		// Setting these variables to 0 ensures that previous use of them
		// does not override the value of the next use; resets them to 0
		// after coming back to the typing screen.
		currentCharacter = 0;
		accuracy = 0;
		wpm = 0;
		totalCharactersInput = 0;
		correctCharacters = 0;
		incorrectCharacters = 0;

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		quitButton.setBounds(15,650,150,40);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scene.dispose();
				TypingTest.completeFlag = true;
				mainMenu.menu();
			}
		});
		quitButton.setVisible(true);


		wpmLabel.setBounds(1175, 12, 100, 10);

		accuracyLabel.setBounds(1145, 25, 150, 20);

		scoreLabel.setBounds(10, 15, 150, 20);

		correctLabel.setOpaque(true);
		correctLabel.setBackground(Color.GREEN);
		correctLabel.setBounds(100, 0, 1025,40);
		correctLabel.setVisible(false);
    	correctLabel.setHorizontalAlignment(JLabel.CENTER);

		incorrectLabel.setOpaque(true);
		incorrectLabel.setBackground(Color.RED);
		incorrectLabel.setBounds(100, 0, 1025, 40);
		incorrectLabel.setVisible(false);
    	incorrectLabel.setHorizontalAlignment(JLabel.CENTER);

		fixInputLabel.setBounds(615, 23, 150, 15);
		fixInputLabel.setVisible(false);

		promptArea.setLineWrap(true);
		promptArea.setWrapStyleWord(true);
		promptArea.setText(textToType);
		promptArea.setEditable(false);
		promptArea.setFont(promptFont);
		promptArea.setBounds(30, 50, 1200, 275);
		promptArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		typingArea.addKeyListener(this);
		typingArea.setFocusable(true);
		typingArea.setFont(typingFont);
		typingArea.setEditable(true);
		typingArea.setBounds(30, 350, 1200, 75);
		typingArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
        // Deals with auto focusing on the typing area so that
        // the user doesn't waste time clicking it.
        scene.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				typingArea.requestFocus();
			}
		});

		// Sets the scene up.
		scene.setLayout(null);

		scene.add(quitButton);
		scene.add(typingArea);
		scene.add(promptArea);
		scene.add(wpmLabel);
		scene.add(accuracyLabel);
		scene.add(scoreLabel);
		scene.add(correctLabel);
		scene.add(incorrectLabel);
		scene.add(fixInputLabel);
		scene.add(completeLabel);

		addButtons(qwerty);

		scene.setSize(1280, 720);
		scene.setTitle("Typing Tutor - In Typing Session");
		scene.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		scene.setLocationRelativeTo(null);
		scene.setResizable(false);
		scene.setVisible(true);

		scene.requestFocusInWindow();

	}

    // Deals with the output of the end screen. This is seen after the user has
    // completed their specified prompt. It includes some statistics including the 
    // time that they took, their WPM, their score and their accuracy.
	public void endScreen() {
		finishButton = new JButton("Main Menu");
		endTime = (System.nanoTime() - startTime) / 1e+9;
		accuracy = ((double) correctCharacters / (double) totalCharactersInput) * 100;
		summaryLabel = new JLabel(
				"<html><b>Time Elapsed:</b> " + new DecimalFormat("#0.00").format(endTime) + " seconds <br/><b>Speed:</b> " + (int) wpm
						+ "wpm<br/><b>Score:</b> " + new DecimalFormat("#0").format(score) + "<br/><b>Accuracy</b>: " + new DecimalFormat("#0.00").format(accuracy) + "%");
		

		System.out.println(dtf.format(now) + " - COMPLETE!");

		scene.dispose();

        // Checks to see whether or not the wpmBoolean flag is 
        // set to false. This is needed so that lessons that are flagged
        // to count towards the user's average WPM are recorded and those
        // that aren't, simply aren't recorded. wpmBoolean is set to true
        // for those that are, and false for those that aren't.
		if (TypingTutorMenu.wpmBoolean == true) {
			TypingTutorMenu.totalWPM = TypingTutorMenu.totalWPM + wpm;
			TypingTutorMenu.totalTrials = TypingTutorMenu.totalTrials + 1;
			
			try {
				TypingTutorMenu.writeWPMtoFile(wpm);
				System.out.println(wpm);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			TypingTutorMenu.getAverageWPM();
		} else {
			System.out.println("wpmBoolean set to " + TypingTutorMenu.wpmBoolean + ". Not counting towards total records.");
		}

		System.out.println(TypingTutorMenu.totalWPM + " and " + TypingTutorMenu.totalTrials);


		finishButton.setBounds(450, 160, 125, 40);
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endScene.setVisible(false);
				
				if (TypingTest.completeFlag != true) {
					TypingTest.completeFlag = true;
					mainMenu.menu();
					TypingTutorMenu.recommendDifficulty();
				}
					
				else {
					mainMenu.menu();
				}	
			}
		});

		completeLabel.setBounds(15, 5, 200, 40);
		completeLabel.setFont(completeFont);
		completeLabel.setVisible(true);

		summaryLabel.setBounds(40, 15, 500, 200);
		summaryLabel.setFont(summaryFont);

		endScene.setLayout(null);

		endScene.add(finishButton);
		endScene.add(completeLabel);
		endScene.add(summaryLabel);

		endScene.setSize(600, 250);
		endScene.setTitle("Typing Tutor - Session Complete");
		endScene.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		endScene.setResizable(false);
		endScene.setLocationRelativeTo(null);
		endScene.setVisible(true);

	}

    // Function that's used to calculate the score that the user has
    // earned after inputting a character correctly. Each time a user 
    // inputs a correct key, this is called. The points they achieved as
    // a result of their input key is calculated, and those points are 
    // then added to their total score.
	public void calculateScore() {
		points = (wpm * (double) correctCharacters / (double) totalCharactersInput);
		score = score + points;
		scoreLabel.setText("Score: " + new DecimalFormat("#0000").format(score));
		System.out.println(dtf.format(now) + " - Points: " + points);
		System.out.println(dtf.format(now) + " - Score: " + score);
	}

	// Function used to calculate the current accuracy of the user.
	// It is necessary to convert incorrectCharacters and
	// totalCharactersInput to double, as they were previously defined
	// as int, giving 'accuracy' an int result otherwise.
	public void calculateAccuracy() {
		double accuracy = ((double) correctCharacters / (double) totalCharactersInput) * 100;
		accuracyLabel.setText("Accuracy: " + new DecimalFormat("#000.00").format(accuracy) + "%");
		System.out.println(dtf.format(now) + " - " + accuracy + "% accuracy");
	}

	// Function used to calculate the current WPM (Words
	// Per Minute). Takes the current time as to how long the
	// program has been running, and subtracts it from the time
	// that the program started at, assigning it to timeElapsed
	// each time called.
	public void calculateWPM() {
		timeElapsed = System.nanoTime() - startTime;
		double charToWords = correctCharacters / 4.5;
		wpm = charToWords / (timeElapsed / 1_000_000_000) * 60;
		wpmLabel.setText("WPM: " + new DecimalFormat("#000.0").format(wpm));
		System.out.println(dtf.format(now) + " - " + wpm + "wpm");
		System.out.println("---------");
	}
	

    // Function used to add the keys of the on-screen keyboard to the 
    // frame. It uses the qwerty array which simply contains the characters
    // that are on the UK qwerty keyboard, and makes use of a for loop to 
    // put it out onto the screen instead of having to manually specify
    // each size + location of the buttons.	
	private void addButtons(String qwerty) {
		addSpecialKeys();
		for (int i = 0; i < qwerty.length()-1; i++) {
			buttons.add(new JButton(Character.toString(qwerty.charAt(i))));
			buttons.get(i).setSize(60,60);
			
			if (i <= rowBreak1) {
				buttons.get(i).setLocation(255 + 60*i, 450);
			}
			else if (i >= rowBreak1 && i < rowBreak2) {
				buttons.get(i).setLocation(260 + 60 * (i-rowBreak1), 510);
			}	
			else if (i >= rowBreak2 && i < rowBreak3) {
				buttons.get(i).setLocation(330 + 60 *(i-rowBreak2), 570);
			}
			else if (i >= rowBreak3) {
				buttons.get(i).setLocation(365 + 60 * (i-rowBreak3), 630);
			}
			scene.add(buttons.get(i));
		}
				
	}
	

	// Simply adds some keys that are difficult to implement within the 
    // qwerty array, including the backspace button and the shift button.
	public void addSpecialKeys() {
		JButton graveButton = new JButton("`");
		JButton backspaceButton = new JButton("<<-----");
		
		JButton tabButton = new JButton("TAB");
		JButton enterButton2 = new JButton("<--");
		
		JButton capsButton = new JButton("CAPS");
		JButton enterButton1 = new JButton("ENTER");
		
		
		JButton shiftButton1 = new JButton("SHIFT");
		JButton shiftButton2 = new JButton("SHIFT");
		
		graveButton.setBounds(190,450,65,60);
		backspaceButton.setBounds(975,450,165,60);
		
		tabButton.setBounds(190,510,130,60);
		enterButton2.setBounds(1040,510,100,60);
		
		capsButton.setBounds(190,570,140,60);
		enterButton1.setBounds(990,570,150,60);
		
		shiftButton1.setBounds(190,630,175,60);
		shiftButton2.setBounds(900,630,240,60);
		
		scene.add(graveButton);
		scene.add(backspaceButton);
		
		scene.add(tabButton);
		scene.add(enterButton1);
		
		scene.add(capsButton);
		scene.add(enterButton2);
		
		scene.add(shiftButton1);
		scene.add(shiftButton2);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		now = LocalDateTime.now();
		System.out.println(dtf.format(now) + " - KEY TYPED: " + e.getKeyChar());
		
		// If the user has input the same character of the point where they're
		// currently at within the prompt, increment currentCharacter within the
		// prompt, correctCharacters input, and the totalCharactersInput. Ensures that
		// the 'INCORRECT' label is NOT visible to avoid overlapping.
		if (e.getKeyChar() == textToType.charAt(currentCharacter)) {
			if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				typingArea.selectAll();
				typingArea.setText("");
			}

			try {
				highlighter.addHighlight(currentCharacter, currentCharacter + 1, correctPainter);
			
			}
			catch (BadLocationException e1) {
			
			}
			

			fixInputLabel.setVisible(false);
			incorrectLabel.setVisible(false);
			correctLabel.setVisible(true);
			System.out.println(dtf.format(now) + " - CORRECT");
			currentCharacter++;
			correctCharacters++;
			totalCharactersInput++;
			System.out.println(dtf.format(now) + " - Correct characters: " + correctCharacters);
			System.out.println(dtf.format(now) + " - Total characters: " + totalCharactersInput);
			calculateScore();
		}
		// If the user has INCORRECTLY input the character of the point where
		// they're currently at within the prompt, increment the incorrectCharacters
		// total and the totalCharactersInput. Tells the user which key to also
		// correct.
		else if (e.getKeyChar() != textToType.charAt(currentCharacter)) {
			// If statement makes it so that when the user is told to input
			// a letter once incorrect (and it's a space), then it's outputs
			// [SPACE] as opposed to a simple blank character.
			if (textToType.charAt(currentCharacter) == KeyEvent.VK_SPACE) {
				fixInputLabel.setHorizontalAlignment(JLabel.CENTER);
				fixInputLabel.setBounds(100,20,1025,40);
				fixInputLabel.setText("INPUT: [SPACE]");
			}
			else {
				incorrectLabel.setHorizontalAlignment(JLabel.CENTER);
				incorrectLabel.setBounds(100, 0, 1025, 40);
				fixInputLabel.setText("INPUT: " + textToType.charAt(currentCharacter));
			}	

			try {
				highlighter.addHighlight(currentCharacter, currentCharacter+1, incorrectPainter);
			} catch (BadLocationException e1) {
			
			}

			fixInputLabel.setVisible(true);
			correctLabel.setVisible(false);
			incorrectLabel.setVisible(true);
			System.out.println(dtf.format(now) + " - INCORRECT");
			incorrectCharacters++;
			totalCharactersInput++;
			System.out.println(dtf.format(now) + "- Incorrect characters: " + incorrectCharacters);
		}

		// Executes the functions calculateAccuracy and calculateWPM after
		// their required variables have been altered.
		calculateAccuracy();
		calculateWPM();

    if (correctCharacters == textToType.length()) {
		completeLabel.setVisible(true);
		endScreen();
		
    	}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(dtf.format(now) + " - Returning to main menu...");
		endScene.setVisible(false);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
