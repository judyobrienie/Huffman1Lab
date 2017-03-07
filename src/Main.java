


import java.io.File;

import java.util.Scanner;


import utils.Serializer;
import utils.XMLSerializer;



/**
 * Creates a new scanner for i/o
 * @param a new File called dictionary.xml
 * @returns an array and hashtree of data
 * @
 */
public class Main {


	private static Scanner input = new Scanner(System.in);

	
	public static void main(String[] args) throws Exception {

		File  code = new File("code.xml");
		Serializer serializer = new XMLSerializer(code);
		HuffmanAPI h = new HuffmanAPI(serializer); 
		Huffman hm = new Huffman();
		if (code.isFile())
		{
			h.load();
		}
		else h.loadDefaultFiles();
	

	/**
	 * Runs the program using i/o from user
	 * 
	 */

	System.out.println("Huffman Coding");
	System.out.println("-----------------");

	boolean goodInput = false;
	do{
		try{
			int option = mainMenu();
			while (option != 0)
			{
				goodInput = true;

				switch (option)
				{
				case 1:
					
				 System.out.println("go go gophers\n\n");
				// print out results
		         System.out.println("FREQ\tSYMBOL\tHUFFMAN CODE");


				 CharacterCounter c = new CharacterCounter();
			      c.increment(Item.item);
			        Huffman.printMap(c);
			     
			            
			            
			             
					
					break;
				
				    
				default:    System.out.println("Invalid option entered: " + option);
				mainMenu();
				break;
				}

				//pause the program so that the user can read what we just printed to the terminal window
				System.out.println("\n\nPress any key to continue...");
				input.nextLine();

				//display the main menu again
				option = mainMenu();
				//the user chose option 0, so exit the program
			}
			System.out.println("Exiting... bye");
			h.store();
			System.exit(0);

		}

		catch (Exception e) {
			input.nextLine(); //swallows bug
			System.err.println("Num expected - You Entered Text. Try Again...\n");
		}
	} while (!goodInput);  	




}


/**
 * mainMenu() - This method displays the menu for the application, 
 * reads the menu option that the user entered and returns it.
 * @return     the users menu choice
 */

private static  int mainMenu() {
	System.out.println("\n1)Print Huffman Code");
	
	


	System.out.println("0) Exit");
	System.out.print("==>>");

int option = input.nextInt();
	return option;
}








}//end of main

