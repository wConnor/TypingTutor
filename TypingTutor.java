import java.io.File;

public class TypingTutor {
	public static void main(String args[]) {
		
		System.out.println("*--* Typing Tutor started *--*");
		
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
			TypingTest.completeFlag = true;
			mainMenu.menu();
		}
		


	}
}