import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.Border;

public class TypingTest extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JFrame initialStart;
	private JTextArea nameInput;
	private JButton startTestButton;
	private JLabel titleLabel, descriptionLabel, testInfoLabel, inputNameLabel;
	private Font titleFont, descriptionsFont, inputNameFont;
	private static Border border;
	public static Boolean completeFlag;
	
	public static String username, assignedText;

	public void startGUI() {

		TypingScreenGUI typingScreen = new TypingScreenGUI();
		
		initialStart = new JFrame();
		nameInput = new JTextArea();
		startTestButton = new JButton("Begin Test");
		
		titleLabel = new JLabel("<html>Typing Tutor</html>");
		descriptionLabel = new JLabel("<html> <center>Welcome to Typing Tutor! Typing Tutor is a program that has been developed in order to assist you, the user, with improving your ability to type and identify areas where you may be weak. Typing Tutor offers a selection of different lessons and even a mode where you can practice your ability to type with real-life situations!");
		testInfoLabel = new JLabel("<html> <center>It appears that this is your first time launching Typing Tutor. As a result of this, you'll soon be prompted to take the placement test. This placement test will present you with a piece of text of which you must type out at your own speed. The program will take your score and then suggest to you a difficulty to start at. You don't have to begin at your recommended difficulty if you don't wish to, but it's an option. Good luck and enjoy Typing Tutor!");
		inputNameLabel = new JLabel("<html><center>Enter your name</center></html");
		
		titleFont = new Font("SansSerif", Font.BOLD, 34);
		descriptionsFont = new Font("SansSerif", Font.PLAIN, 11);
		inputNameFont = new Font("SansSerif", Font.BOLD,13);
		
		border = BorderFactory.createLineBorder(Color.BLACK);

		completeFlag = false;
		
		nameInput.setBounds(110, 450, 180, 30);
		nameInput.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		nameInput.getDocument().putProperty("filterNewLines", Boolean.TRUE);
		
		startTestButton.setBounds(110,490,180,30);
		startTestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = nameInput.getText();
				System.out.println("Username: " + username);
				
				try {
					createUserFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				initialStart.setVisible(false);
				typingScreen.textToType = "A good thesis is a statement of roughly one to three sentences that says something intelligent about a literary work. It is not sufficient simply to identify a theme in your thesis.";
				typingScreen.startGUI();
				
			}
		});
		
		// startTestButton.setBounds();

		titleLabel.setBounds(75,0,300,150);
		titleLabel.setFont(titleFont);
		
		descriptionLabel.setBounds(50,100,300,150);
		descriptionLabel.setFont(descriptionsFont);
		
		testInfoLabel.setBounds(50,230,300,150);
		testInfoLabel.setFont(descriptionsFont);
		
		inputNameLabel.setBounds(138,420,180,30);
		inputNameLabel.setFont(inputNameFont);
		
		initialStart.add(startTestButton);
		initialStart.add(nameInput);
		initialStart.add(titleLabel);
		initialStart.add(descriptionLabel);
		initialStart.add(testInfoLabel);
		initialStart.add(inputNameLabel);
		
		
		
		initialStart.setSize(400, 575);
		initialStart.setLayout(null);
		initialStart.setTitle("Typing Tutor");
		initialStart.setLocationRelativeTo(null);
		initialStart.setResizable(false);
		initialStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialStart.setVisible(true);

	}

	
	public void createUserFile() throws IOException {
		File userFile = new File("data/user.txt");
		FileWriter userFileWriter = new FileWriter("data/user.txt", true);
		BufferedWriter userFileBufferedWriter = new BufferedWriter(userFileWriter);
		PrintWriter userFilePrintWriter = new PrintWriter(userFileBufferedWriter);
		
		try {
			userFile.getParentFile().mkdirs();
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch(SecurityException se) {
			
		}
	
		userFilePrintWriter.print(username);
		userFilePrintWriter.flush();
		userFilePrintWriter.close();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}


}
