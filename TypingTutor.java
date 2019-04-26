public class TypingTutor {
	public static void main(String args[]) {
		System.out.println("*--* Typing Tutor started *--*");
		init();
	}
	
	public static void init() {
		TypingTutorMenu mainMenu = new TypingTutorMenu();
		TypingTest typingTest = new TypingTest();
		FileHandling fileHandling = new FileHandling();
		
		if (!fileHandling.userFileExists()) {
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