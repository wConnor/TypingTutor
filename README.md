# TypingTutor
## Introduction
Typing Tutor is a basic Java applcation that is used to teach the user the various skills that come into play when they're typing, such as the basic keys that their fingers should lay on. It has a basic course system that a user may follow, with difficulties ranging from introduction to expert. In addition to a course system, the user also has the option of a solo practice mode, where they can practice their typing with paragraphs from well-known pieces of literature. They also have the option to add their own texts!

Typing Tutor has the following features:
* Typing course system with ranging difficulties
* Statistics such as the user's average WPM and their problem keys
* A mode where one can practice their typing ability
* Settings such as font size, font colour, background colour etc.

Typing Tutor has been developed as a part of my programming project for the OCR A Level Computer Science programming project.

Typing Tutor requires the JFreeChart library to compile. Available at github.com/jfree/jfreechart. This is necessary for the WPM graph available under the statistics section. 

## Compilation
In order to compile the program (assuming JFreeChart libraries are under $CLASSPATH), do as follows:
```
$ git clone https://github.com/wConnor/TypingTutor.git
$ cd TypingTutor
$ javac -cp $CLASSPATH *.java
```

To finally run the program, use:
```
$ java TypingTutor
```

## Screenshots
![Introduction Screen](/images/typingtutor-start.png)
Introduction screen that the user is prompted with upon first launch of the program.

![Main Menu](/images/typingtutor-menu.png)
Main menu of the program where the user is able to select which lesson they would like to do.

![Typing Session Screen](/images/typingtutor-session.png)
Typing session screen where the user is to copy what is shown on the screen. An on-screen keyboard is also present to dissuade the user from looking at their own.
