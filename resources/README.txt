Files :  - IViewModel.java 				(interface of a view) - ConsoleView.java				(implements IViewModel) - IMusicEditor.java				(interface of the music editor model) - MusicEditorModel.java			(implements IMusicEditor) - Note.java					(Class to represent notes) - Pitch.java					(Enum of all the Different Pitches) - DrawComposition.java - GuiViewImpl.java - MidiViewImpl.java - DrawComposition.java - MockReciever.java - MockSynthesizer.java - ConcealModel.java - ConsoleView.java - IView.java - ViewFactory.java - MusicEditor.java - KeyBoardHandler.java
 - MouseHandler.java
 - Controllers.java	
 - CompositeView.java
 - GuiView.java
Test Files: 					 - NoteTest.java				 - PitchTest.java				 - MusicModelTest.java			 - ConsoleViewTest.java	 - MidiViewTest.java - ViewFactoryTest.java
 - KeyBoardTest.java
 - SimpleRunnable1.java
 - SimpleRunnable2.javaFor the Music Editor Model there exists an interface with methods that help us get the lowest tone, the highest tone, the total duration and the composition itself. There are also methods that to add a given note, to remove a given node if it exists, to edit a node and replace it with another if it exists, to combine another composition with this one or to queue another composition. The Model itself uses a List<List<Note>> to represent the composition where each note is placed int a list at its start time. It also contains a private method that helps keep the lowest tone, highest tone, total duration updated. It can be instantiated with a construct that initializes the composition to an empty list of list. Each Note is represented by the note class which maintains the notes start time, pitch, octave, beats. It can be initialized by giving it these four parameters where the start time is alway > 0, the beats are greater than 0, the octave is greater than 0 and less than 10, as this is the range in which humans beings can process sound, and the Pitch is on of the 12 enumerations. It has methods to get the beats, tone, start time, check if 2 Notes are equal and a method to modify the start time by adding the given number of beats.The Pitches are enumerated as C C# D D# E F F# G G# A A# B. There exists a method to represent each pitch as a string. There also exists a method which takes an int between 0 & 11 and returns the corresponding pitch. For the view there exists an interface that has only one function which takes in a given model and renders. This is implemented in the console view class which represents the model as a string using stringBuilders and combining them before printing to the console. It also contains a helper method that helps it create each individual Note.DrawComposition draws the composition data.  The composition data includes the notes, scrollbar and chart.  The only public method is paint component which adds the rendered image to a swing component.GuiViewImpl formats the window and draws the text based data on the window.  This also creates the window and specifies the window layout.MidiViewImpl plays sound through java’s midi system.  The only public method is render which plays the song.  The song notes are played with the specified time length and at the specified times to create music.  This is because Wikipedia defines music as “medium is sound and silence, which exist in time“ which our model has control of.MockReciever is a receiver which logs sent messages.  It is not a real Receiver it is only a mock.  That is to say that it does not play the notes it only logs them.MockSythesizer is a synthesizer which logs.  It is not a real synthesizer it is only a mock.  That is to say that it does not play the notes it only logs them.ConcealModel is a class which functions as a very very very basic controller to hide the model implementation details from the views.  Adding an interface to this would be pointless as one could just extend this class.ConsoleView is refactored to serve as proper view.  It is no longer a fake view but a real one.IView allows all of the views to hide behind it like the cowards they are.  It provides the one public methods which the views have, render, which renders the music in the given medium.  It does not render in the not given mediums.ViewFactory creates the views from the given string.MusicEditor provides the launching point for the program.

Add Note: Click on the pitch and duration where you would like to add a note and drag to create a note of length n 
Remove Note: Press D and then click on the where the note starts click d to exit removing mode

The program can be run via a command such as java -jar Music\ Editor.jar <enter filename here> <enter view type here>EDITS:The keyboard handler is used to get input from the keyboard.

The mouse handler is used to get the input from the mouse.

Implemented a new interface called GuiView.java that expresses the additional functionality such as key listeners.

The composite view is used to get both a visual and audio view.  It uses modified versions of both, to enable the additional functionality such as a pausing.

Modifies sending the notes all at once in the midi view to sending them one at a time.  This enables us to pause the midi view.  Otherwise there is no easy way to implement the desired behavior.

Updated tests, to include the keyboard handler and to reflect minor changes for the midi view.

Updated both the factory view creator and Music Editor.java to reflect the addition of the controller.





Assignment 8:













