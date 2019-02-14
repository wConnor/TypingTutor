import java.io.File;
import javax.swing.*;

public class TypingTutor {
	public static void main(String args[]) {
		
		File fileCheck = new File("data/user.txt");
		TypingTutorMenu mainMenu = new TypingTutorMenu();
		TypingTest placementTest = new TypingTest();
		
		if (!fileCheck.exists()) {
			TypingTest.completeFlag = false;
			mainMenu.menu();
			TypingTutorMenu.menuFrame.dispose();
			placementTest.startGUI();
			

		}
		else {
			mainMenu.menu();
		}
		
		System.out.println("*--* Typing Tutor started *--*");

	}
}