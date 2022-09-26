import java.io.*;
import java.lang.Integer;

/**
 * Provides convenience methods for common operations.
 */
public class GameHelper {

	/**
	 * Prompts the user for input, and reads an integer from the command line.
	 */
	public Integer readIntegerFromCmd(int lowest, int highest) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = 0;

		while(number < lowest || number > highest)
		{
			System.out.println("Enter your number:");
			
			String input = "";
			try {
				input = br.readLine();
				try {
					number = Integer.valueOf(input);
				} catch (NumberFormatException e) {
					System.err.println("There is something wrong with the number you entered.");
				}
			} catch (IOException ioe) {
				System.err.println("There was an input error.");
			}
		}

		clearConsole();
		
		return number;
	}

	/*
	 * Time gap of 'millis' milliseconds
	 */
	public void timeGap(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	
	public void clearConsole() {
		try
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch (Exception e)
		{
			//  Handle any exceptions.
		}
	}
}