// Gregory Ho
// Photo Album Culminating
// Jan 20/16
// This program launches a GUI that displays a photo. There are several buttons. 2 of which can change the displayed picture to the next picture or
// previous picture.
// Another 2 of the buttons give a picture a rating, either like or dislike. The 5th button shows the statistics page which gives a tally of all the
// ratings given for
// each category of pictures. The statistics page can be hidden by clicking the hide statistics button
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Photo_Viewer_Culminating_Gregory implements ActionListener
{
	JFrame window = new JFrame("Photo Viewer");                     // Create the JFrame component
	JPanel background = new JPanel();                                       // Create the Panel that will contain the photo and appropriate buttons
	JPanel StatsPanel = new JPanel();                                       // Create the Panel that will contain the statistics and hide statistics button
	JButton next = new JButton("Next");                                     // Create the Button that changes the picture
	JButton prev = new JButton("Previous");                         // Create the Button that displays the previous picture
	JButton like = new JButton("Like");                                     // Create the Button that gives a photo a like rating
	JButton dislike = new JButton("Dislike");                       // Create the Button that gives a photo a dislike rating
	JButton stats = new JButton("Show Statistics");         // Create the Button that will either show or hide the statistics page
	JLabel photo = new JLabel();                                            // Create the Label that will act as a display for my picture
	JTextArea Output = new JTextArea(33, 100);                      // Create a TextArea that will display the Statistics and set its size

	String[] DisplayOrder = new String[15];                         // Create an Array to determine the random order of which the pictures will be displayed

	int[] Opinion = new int[15];                                            // Create an Array that will store the ratings of each photo

	int PictureID = -1;                                                                     // Create an int that will be used to specify the array index of DisplayOrder

	String Title;                                                                           // Create a String that will hold the title for the Stats page
	String Statistics;                                                                      // Create a String that will hold the Statistics for the photos

	public Photo_Viewer_Culminating_Gregory()                       // Create the GUI
	{

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set the close operation
		window.setResizable(false);                                                             // Dont allow the user to change the size of the frame

		background.setBackground(Color.white);                                  // Set the background panel's colour to white

		StatsPanel.setBackground(Color.white);                                  // Set the StatsPanel's colour to white

		prev.addActionListener(this);                                                   // Set the actionlistener for the prev button
		prev.setActionCommand("Previous Picture");                              // Set the actionCommand for the button to determine what code to execute when the
		// button is clicked
		prev.setPreferredSize(new Dimension(160, 50));                  // Set the size of the button

		next.addActionListener(this);                                                   // Set the actionlistener for the prev button
		next.setActionCommand("Next Picture");                                  // Set the actionCommand for the button to determine what code to execute when the
		// button is clicked
		next.setPreferredSize(new Dimension(160, 50));                  // Set the size of the button

		like.addActionListener(this);                                                   // Set the actionlistener for the prev button
		like.setActionCommand("Like Picture");                                  // Set the actionCommand for the button to determine what code to execute when the
		// button is clicked
		like.setPreferredSize(new Dimension(160, 50));                  // Set the size of the button

		dislike.addActionListener(this);                                                // Set the actionlistener for the prev button
		dislike.setActionCommand("Dislike Picture");                    // Set the actionCommand for the button to determine what code to execute when the
		// button is clicked
		dislike.setPreferredSize(new Dimension(160, 50));               // Set the size of the button

		stats.addActionListener(this);                                                  // Set the actionlistener for the prev button
		stats.setActionCommand("Show Stats");                                   // Set the actionCommand for the button to determine what code to execute when the
		// button is clicked
		stats.setPreferredSize(new Dimension(160, 50));                 // Set the size of the button

		photo.setIcon(new ImageIcon(Photo_Viewer_Culminating_Gregory.class.getResource("blank.png")));  // Set the Image to display on startup
		photo.setBorder(BorderFactory.createLineBorder(Color.black, 5));                                                                // Create a black border around the image

		Output.setTabSize(25);                                                                  // Set the TabSize for the TextArea for the Stats
		Output.setEditable(false);                                                              // Dont allow the user to edit the values on the StatsPage/TextArea

		background.add(photo);                                                                                  // Add the Photo Label to the panel
		background.add(Box.createRigidArea(new Dimension(840, 10)));    // Create empty space for formatting the location of the buttons
		background.add(like);                                                                                   // Add the Like Button to the panel
		background.add(prev);                                                                                   // Add the Prev Button to the panel
		background.add(next);                                                                                   // Add the Next Button to the panel
		background.add(dislike);                                                                                // Add the Dislike Button to the panel
		background.add(stats);                                                                                  // Add the Show Stats Button to the panel

		StatsPanel.add(Output);                                                                                 // Add the TextArea to the StatsPanel
		StatsPanel.add(Box.createRigidArea(new Dimension(840, 20)));    // Create empty space to format the location of the Hide Stats Button

		window.setContentPane(background);                                                              // Set the Frame panel
		window.setSize(new Dimension(840, 730));                                                // Set the Size of the Frame
		window.setVisible(true);                                                                                // Display the Frame

		RandomPhotoOrder(DisplayOrder);                                                                 // Run the method to randomly organize the DisplayOrder of the photos
	}

	public void actionPerformed(ActionEvent e)                                      // The ActionListener
	{
		String actionCommand = e.getActionCommand();                    // Get the ActionCommand of the button to determine what code to execute
		if (actionCommand.equals("Next Picture"))                               // Execute if the Next Button is clicked
		{
			if (PictureID == DisplayOrder.length - 1)                       // If the PictureID is at the end, move the ID to the start of the list
			{
				PictureID = 0;                                                                  // Change the value to the Staring Picture's ID
			}
			else
			{
				PictureID++;                                                                    // Change the PictureID by adding 1 to show the next picture in the sequence
			}
			photo.setIcon(new ImageIcon(Photo_Viewer_Culminating_Gregory.class.getResource(DisplayOrder[PictureID]  // Change the picture based on the
																													// PictureID and DisplayOrder
																													// Array
					+ ".png")));

		}
		if (actionCommand.equals("Previous Picture"))                   // Execute if the Previous Button is clicked
		{
			NegativeIDException(PictureID);                                         // Make the sure numbers to handle are not negative otherwise Errors will occur
			if (PictureID == 0)                                                                     // If the picture is at the start, dont subtract 1 to get a negative number, set the
			// ID to the last photo's ID number
			{
				PictureID = DisplayOrder.length - 1;                    // Set it to the Index of the last photo's Array
			}
			else
			{
				PictureID--;                                                                    // Subtract 1 to show the previous picture
			}
			photo.setIcon(new ImageIcon(Photo_Viewer_Culminating_Gregory.class.getResource(DisplayOrder[PictureID]  // Change the picture based on the
																													// PictureID and DisplayOrder
																													// Array
					+ ".png")));

		}
		if (actionCommand.equals("Like Picture"))                               // Execute if the Like Button is clicked
		{
			if (PictureID > -1)                                                                     // If a picture is showing then...
			{
				Opinion[Integer.parseInt(DisplayOrder[PictureID])] = 1;                                                                 // Set the Opinion Array's corresponding photo // ID to a 1 to represent a
																		// like
			}

		}
		if (actionCommand.equals("Dislike Picture"))                    // Execute if the Dislike Button is clicked
		{
			if (PictureID > -1)                                                                     // If a picture is showing then...
			{
				Opinion[Integer.parseInt(DisplayOrder[PictureID])] = -1;                                                                // Set the Opinion Array's corresponding photo
			}
		}
		if (actionCommand.equals("Show Stats"))                                 // If the user clicked the Show Statistics Button then change the photo panel and show
		// the stats panel
		{
			background.setVisible(false);                                           // Hide the photo panel-set False
			StatsPanel.setVisible(true);                                            // Show the stats panel-set True
			window.setContentPane(StatsPanel);                                      // Change the contentpanel of the Frame to the Stats panel from the photo panel, the
			// TextArea has already been added to Stats Panel
			StatsPanel.add(stats);                                                          // Add the Show Statistics Button to the panel
			stats.setActionCommand("Hide Stats");                           // Set the actionCommand to Hide Stats
			stats.setText("Hide Statistics");                                       // Set the new text to display on the button

			// Picture classification by value:
			// 0-2 Butterfly
			// 3-5 Flowers
			// 6-8 Mountains
			// 9-11 Nature
			// 12-14 Stars

			Title = "Category\tDislikes\tNo Rating\tLikes\n\n";                                                     // Set the string of the Title in the Text Area
			Statistics = "Butterflies\t" + tally(Opinion, 0, 2, -1) + "\t" + tally(Opinion, 0, 2, 0) + "\t"
					+ tally(Opinion, 0, 2, 1) + "\t" + "\nFlowers\t" + tally(Opinion, 3, 5, -1) + "\t"
					+ tally(Opinion, 3, 5, 0) + "\t" + tally(Opinion, 3, 5, 1) + "\t" + "\nMountains\t"
					+ tally(Opinion, 6, 8, -1) + "\t" + tally(Opinion, 6, 8, 0) + "\t" + tally(Opinion, 6, 8, 1) + "\t"
					+ "\nNature\t" + tally(Opinion, 9, 11, -1) + "\t" + tally(Opinion, 9, 11, 0) + "\t"
					+ tally(Opinion, 9, 11, 1) + "\t" + "\nStars\t" + tally(Opinion, 12, 14, -1) + "\t"
					+ tally(Opinion, 12, 14, 0) + "\t" + tally(Opinion, 12, 14, 1) + "\t" + "\n\nTotals\t"
					+ tally(Opinion, 0, 14, -1) + "\t" + tally(Opinion, 0, 14, 0) + "\t" + tally(Opinion, 0, 14, 1)
					+ "\t";            // Set the string of the statistics by
			// specifying the category followed by the
			// number of likes,no ratings, and dislikes.
			// The number of ratings is calculated using
			// the tally method. use \t and \n to format
			// the string

			Output.append(Title);                   // Add the title string to the TextArea
			Output.append(Statistics);              // Add the Statistics string to the TextArea
		}
		if (actionCommand.equals("Hide Stats"))                 // If the user clicked the Hide Statistics Button then hide the stats panel and show the photo
		// panel
		{
			StatsPanel.setVisible(false);                           // Hide the stats panel
			background.setVisible(true);                            // Show the photo panel
			window.setContentPane(background);                      // Set the contentpanel to the photopanel
			background.add(stats);                                          // Add the Show Statistics Button back to the photo panel
			stats.setActionCommand("Show Stats");           // Set the ActionCommand back to Show Stats
			stats.setText("Show Statistics");                       // Set the new text on the Stats Button
			Output.setText(null);                                           // Clear the TextArea
		}
	}

	public static void RandomPhotoOrder(String[] DisplayOrder)              // The RandomPhotoOrder Method that randomizes the sequence of photos to display
	{

		int RandomValue;                                                                // Integer variable that will
		int x = 0;                                                                              // Variable to determine when each array has a picture assigned
		boolean ValidRandNum = true;                                    // Create a boolean value that will help determine if the random value determined is unique
		// and has not been chosen

		for (int i = 0; i < DisplayOrder.length; i++)   // Iterate through the array to initialize the values
		{
			DisplayOrder[i] = "-1";                                         // Set a place holder of -1
		}

		do                                                                                              // Use a do while loop to find the random numbers until each photo has been assigned
		{
			ValidRandNum = true;                    // Reset the value to true
			RandomValue = (int) (Math.random() * DisplayOrder.length);              // Pick a random value
			for (int i = 0; i < DisplayOrder.length; i++)                                   // Iterate through each array's index
			{
				if (RandomValue == Integer.parseInt(DisplayOrder[i]))           // Determine if the number has already been picked
				{
					i = (DisplayOrder.length) + 1;                  // Exit the loop to be efficient
					ValidRandNum = false;                                   // Set the value to false because the number has been picked already
				}
			}
			if (ValidRandNum)                                                               // If the random number is unique then execute...
			{
				if (RandomValue < 10)                                           // If the number is less than 10 then it needs to have a 0 before it
				{
					DisplayOrder[x] = "0" + Integer.toString(RandomValue);          // Use Catenation to add a 0 in front of the number as the photo names
																			// are 2 digits
					x++;                                                                    // Add 1 to x so the do while loop will stop when all photos have been assigned
				}
				else
				{
					DisplayOrder[x] = Integer.toString(RandomValue);                        // Convert int to string and assign it to the array
					x++;                                                                    // Add 1 to x so the do while loop will stop when all photos have been assigned
				}
			}

		}
		while (x < DisplayOrder.length);                                        // Run so long each photo has not been assigned to an array yet
	}

	public static String tally(int[] values, int index1, int index2, int valueToCount)              // Tally method-Counts values of an array of a certain
	// index range
	{                                                                                                                                                                               // Arguments (Array to Search, Index Range Min, Index
		// Range Max, Number to look for)
		int count = 0;                                                                                                                                          // Variable to keep count of the correct numbers found

		for (int i = index1; i <= index2; i++)                                                                                          // Iterate through each index of the array within the
		// range
		{
			if (values[i] == valueToCount)                                                                                                  // If the value in the array is equal to the value being
			// searched for then execute...
			{
				count++;                                                                                                                                        // Add 1 to count
			}
		}
		return Integer.toString(count);                                                                                                         // Return the number of correct values found converted to
		// a String type
	}

	public void NegativeIDException(int ID)                                                                                                 // Invalid PictureID method-Prevents Erros
	{
		if (ID == -1)                                                                                                                                           // If the PictureID is invalid for an array then change it
		{
			PictureID++;                                                                                                                                    // Change PictureID's value so that it becomes valid to
			// use in an array, cant have negative index
		}
	}

	public static void main(String[] args) throws Exception, InstantiationException, IllegalAccessException,                // Throw Errors, Exceptions, and
			// Interruptions caused by the UI
			// manager
			UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());                            // Set the UIManager's look and feel to the System's
		new Photo_Viewer_Culminating_Gregory();                                                                                         // Create a new Photo_Viewer object which will "Execute"
		// the GUI

	} // main method
} // Photo_Viewer_Culminating_Gregory class
