
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import utils.Serializer;

public class HuffmanAPI  {

	private Serializer serializer;

    public Map<Character, Integer> code = new TreeMap<>();
    

    public  HuffmanAPI(Serializer serializer)throws Exception {

		this.serializer = serializer; 

	}

	/**
	 * @param an empty Constructor 
	 */
	public HuffmanAPI() {

	}


	/**
	 * @param load from xml file 
	 */
	@SuppressWarnings("unchecked")
	public void load() throws Exception
	{
		serializer.read();
		
		code = (Map<Character, Integer>) serializer.pop();
		

	}

	/**
	 * @param save to xml file

	 */


	public void store() throws Exception
	{

		serializer.push(code);
		//serializer.push(maxHeap);
		serializer.write(); 


	}

	
	
	/**
	 *@param loadDefaultfiles external files with dictionary data
	 *@return populated   hashtree of dictionary 
	 */

	public void loadDefaultFiles() throws FileNotFoundException{

		File usersFile = new File("../Huffman/src/lib/hello.txt");
		Scanner inUsers = new Scanner(usersFile);
		String delims = " ";//each field in the file is separated(delimited) by a space.
		while (inUsers.hasNextLine()) {
			// get name and meaning from data source
			String userDetails = inUsers.nextLine();
			//userDetails=userDetails.trim();

			// parse user details string
			String[] userTokens = userDetails.split(delims);

			Item items = new Item(userDetails);
			//char[] text = items.item.toCharArray();
	       
		}
		inUsers.close();


	}
}

