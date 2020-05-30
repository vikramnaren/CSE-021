import java.io.FileNotFoundException;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileParser {

	public static void main(String[] args) throws IOException {
		
		/*
		 * --------------------
		 * WORKING WITH STRINGS
		 * -------------------- 
		 */
		
		String str = "my string";
		
		System.out.print("Printing String object 'str' one character at a time: ");
		for(int i = 0; i < str.length(); i++)
			System.out.print(str.charAt(i));
		
		System.out.println("\n");
		
		System.out.println("ASCII value of character at position 2: " + (int)str.charAt(2));
		System.out.println("ASCII value of character at position 4: " + (int)str.charAt(4));
		System.out.println("Character at position 4:                " + str.charAt(4));
		
		char[] a = {'T', 'H', 'E'};
		/*
		 * If we attempt to make a String from the character array 'a' using the code
		 * 		String strA = (String)a;
		 * the compiler throws the error "Cannot cast from char[] to String"
		 */
		String strA = new String(a);
		System.out.println("\nString made from character array {'T', 'H', 'E'}: " + strA);
		
		char[] b = {84, 72, 69};
		String strB = new String(b);
		System.out.println("String made from character array {84, 72, 69}:    " + strB); // See ASCII table
		
		/*
		 * -----------------
		 * STRING COMPARISON
		 * -----------------
		 */
		
		String str1 = "hello";
		String str2 = str1;
		if(str1 == str2)
			System.out.println("\nReference variables str1 and str2 point to the same object");
		
		String str3 = "hello";
		String str4 = "hello"; // Does not create a new object
		if(str3 == str4) 
			System.out.println("Reference variables str3 and str4 point to the same object");
		
		String str5 = "hello";
		String str6 = new String("hello");
		if(str5 == str6) 
			System.out.println("Reference variables str5 and str6 point to the same object");
		if (str5.equals(str6))
			System.out.println("Reference variables str5 and str6 have the same value");
		
		/*
		 * -------------
		 * READING FILES
		 * -------------
		 */
		
		System.out.print("\nEnter the name of file to open: ");
		
		/*
		 * Make sure to specify the full path to the file with slash (/) as your directory separator. 
		 * For instance, C:/tmp/num.txt (in Windows).
		 * 
		 * Another option is to import the file to your project and simply specify the name of the file.
		 * To import the file, perform the following steps:
		 * 1. Right-click on the project name in the Package Explorer and select "Import..."
		 * 2. In the Import window, select "File System" and clict "Next >"
		 * 3. In the File System Import window, click "Browse..." and navigate to the directory where your
		 *    file is located (in the above example, C:/tmp) and click "Ok"
		 * 4. You will now notice the two windows in the File System Import window are populated, with the 
		 *    left showing the selected directory and the right showing the list of files in the selected 
		 *    directory. 
		 * 5. In the right-side window, click on the check-box next to the file you wish to read (num.txt in 
		 *    the example above) and click "Finish"
		 * 6. You will now see the file added to your project in the Package Explorer
		 * Now when you run the program, simply specify the file name (num.txt) when prompted.  
		 */
		Scanner kbd = new Scanner(System.in); 
		// You may also specify this java file to parse by entering "./src/FileParser.java" when prompted. 
		// Try it out! 
		String filename = kbd.next(); 
		kbd.close();
		
		int lineno = 0;

		// Read the file one line at a time (RECOMMENDED)
		System.out.println("Reading the file 1 line at a time ...");
		System.out.println("===========================================");
		
		Scanner input = new Scanner(new FileReader(filename)); // Scanner can also be used to hold the entire contents 
		// of a file (or any String), not just data that the user types in the console. More specifically, we pass the 
		// the file to the FileReader method that converts it into a stream of characters. This stream of characters 
		// is then passed to the Scanner object (called input in our case). We can then "extract" this data from the 
		// Scanner using the usual methods like nextInt(), nextFloat(), next(), etc. The difference here is that the 
		// data now comes from the stream of characters generated from the file contents rather than the user input. 
		// This stream of data can be separated into smaller pieces (or items) by the default delimiter (space) or a 
		// custom one as specified below. Note: a delimiter is the thing we use to separate individual items in a 
		// collection of items. 
			
		while(input.hasNextLine()) { // Check to see if the Scanner object (input) is holding additional lines of data.
			Scanner line = new Scanner(input.nextLine()); 	// Take a line of data from 'input' and put it in another 
															// Scanner object called 'line'.
			line.useDelimiter("[:\t \r]"); // Use Colon (:) and/or Tab (\t) and/or space ( ) and/or carriage return (\r) 
			// as the delimiter to separate the items in the line of data held by the Scanner object (line). If we don't 
			// specify this statement, space is used as the default delimiter. You can specify any list of characters as 
			// your list of delimiters. Note: The delimiters will not be part of the output, and will only be used to 
			// separate the items.

			// For this project you will use line.useDelimiter("[\t-]"); as your delimiter needs to separate out the day
			// month and year (integers) that are separated by hyphens (-) in the givenfiles.
			
			// For example, suppose line is holding the data: "age: 24		weight: 32.7", where the long
			// space between 24 and weight is a tab space. This line of data can be interpreted as follows:
			//	   [item 1][Colon][space][item 2][tab space][item 3][Colon][space][item 4][carriage return (not shown)]
			// where, item 1 is the string "age", item 2 is 24, item 3 is "weight" and item 4 is 32.7. Notice that the 
			// colon symbol is used to separate the items, so item 1 and 3 are "age" and "weight", respectively, and not 
			// "age:" and "weight:".
			
			// Since the delimiter is a colon, tab space or a space, the following sequence of operations on the Scanner 
			// object (line) will retrieve individual items in the data as shown below:
			// line.next() --> gets the String "age"
			// line.nextInt() --> gets the int value 24
			// line.next() --> gets the String "weight"
			// line.nextDouble() --> gets the double value 32.7

			// But if we don't know how many (or what kind of) items are held by line, we can use the process 
			// below to retrieve all the individual items in line until there are no more items.
			
			lineno++; // Used for formatting the output of the program
				
			System.out.println("--------READING-LINE-" + lineno + "--------");
			while(line.hasNext()) 	// Check to see if there are more items to retrieve from the data held in the 
									// Scanner object (line)
				System.out.println(line.next()); // Print each word in line
			
			line.close();
		}
		
		System.out.println("===========================================");
		

		// Read the entire file in one go (with error checking) [cf Sections 13.1 - 13.4]
		System.out.println("Reading the entire file in one go ...");
		System.out.println("===========================================");
		try { // TRY it out 
			input = new Scanner(new FileReader(filename)); 
			
			input.useDelimiter("[\t \r]"); // Tab (\t) and/or space ( ) and/or carriage return (\r) delimited file	
			
			while(input.hasNext()) // Checks whether the Scanner object (input) is holding more data.
				System.out.println(input.next()); // Print each word in file
			
			input.close();
			System.out.println("===========================================\n");
		} catch(FileNotFoundException e) { // Catch Error 
			System.out.println(e); 
		} catch(NoSuchElementException e) { // Catch Error 
			System.out.println(e); 
		}

		input.close();
		
	}
}
