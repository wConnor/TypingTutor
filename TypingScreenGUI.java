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
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TypingScreenGUI extends JFrame implements KeyListener, ActionListener {

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
	public double wpm, accuracy, points, startTime, endTime, timeElapsed, score;
	public String inputText, localTime, textToType;
	private Border border;
	public Font promptFont, typingFont, completeFont, summaryFont;
	private DateTimeFormatter dtf;
	private LocalDateTime now;
	public Highlighter highlighter;
	private HighlightPainter correctPainter, incorrectPainter;

	// Function used to start the GUI.
	public void startGUI() {

		scene = new JFrame();
		endScene = new JFrame();

		quitButton = new JButton("Quit");

		
		//textToType = mainMenu.getText();
		//textToType = typingTest.assignedText;
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
		promptFont = new Font("SansSerif", Font.PLAIN, 36);
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
				scene.setVisible(false);
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

		scene.setSize(1280, 720);
		scene.setTitle("Typing Tutor - In Typing Session");
		scene.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		scene.setLocationRelativeTo(null);
		scene.setResizable(false);
		scene.setVisible(true);

		scene.requestFocusInWindow();

	}

	public void endScreen() {
		finishButton = new JButton("Main Menu");
		endTime = (System.nanoTime() - startTime) / 1e+9;
		accuracy = ((double) correctCharacters / (double) totalCharactersInput) * 100;
		summaryLabel = new JLabel(
				"<html><b>Time Elapsed:</b> " + new DecimalFormat("#0.00").format(endTime) + " seconds <br/><b>Speed:</b> " + (int) wpm
						+ "wpm<br/><b>Score:</b> " + new DecimalFormat("#0").format(score) + "<br/><b>Accuracy</b>: " + new DecimalFormat("#0.00").format(accuracy) + "%");
		

		System.out.println(dtf.format(now) + " - COMPLETE!");

		scene.setVisible(false);

		if (TypingTutorMenu.wpmBoolean == true) {
			TypingTutorMenu.totalWPM = TypingTutorMenu.totalWPM + wpm;
			TypingTutorMenu.totalTrials = TypingTutorMenu.totalTrials + 1;
		} else {
			System.out.println("wpmBoolean set to " + TypingTutorMenu.wpmBoolean + ". Not counting towards total records.");
		}

		System.out.println(TypingTutorMenu.totalWPM + " and " + TypingTutorMenu.totalTrials);


		finishButton.setBounds(450, 160, 125, 40);
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endScene.setVisible(false);
				TypingTest.completeFlag = true;
				mainMenu.menu();
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

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

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
}
