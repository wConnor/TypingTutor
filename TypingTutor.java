import java.io.File;


public class TypingTutor {
	public static void main(String args[]) {
		System.out.println("*--* Typing Tutor started *--*");
		init();
	}
	
	public static void init() {
		File fileCheck = new File("data/user.txt");
		TypingTutorMenu mainMenu = new TypingTutorMenu();
		TypingTest typingTest = new TypingTest();
		
		if (!fileCheck.exists()) {
			mainMenu.menu();
			mainMenu.disposeMenu();
			typingTest.setCompleteFlag(false);
			typingTest.startGUI();
		}
		else {
			typingTest.setCompleteFlag(true);
			mainMenu.menu();
		}
	}
}