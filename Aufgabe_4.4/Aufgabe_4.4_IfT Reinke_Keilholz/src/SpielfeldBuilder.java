import java.io.*;

/**
 * This class builds a push puzzle out of the string that is
 * entered by the user.
 * 
 * @author Julia Reinke & Frieder Keilholz
 * @version 28/10/2020
 */
public class SpielfeldBuilder {
	
	/**
	 * This method asks the user which size his push puzzle has.
	 * @return the size of the puzzle.
	 */
	public int getPuzzleSize(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/* Meant here are sizes 2x2, 3x3 and 4x4, but for better interaction with the
		 * Spielfeld class, the user is asked to type in 2,3 or 4.
		 */
		
		System.out.println("Is your puzzle size 2, 3 or 4?");
		String sizestr = "";
		int size = 0;
		try {
			sizestr = reader.readLine();
			//throw an exception, if the entered value is not a number.
			size = Integer.parseInt(sizestr);
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please enter a valid number.");
		}
		return size;
	}
	
	public String getFieldInput() {
		
		String puzzle = "";		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the unsorted numbers of your puzzle "
				+ "by entering all unsorted numbers.\n\n"
				+ "Example: 5 7 3 8 2 1 4 6 0");	
		
		try {
			//read from input
			puzzle = reader.readLine();	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return puzzle;
	}
	
	/**
	 * This method builds a Spielfeld out of the original string the user has
	 * entered.
	 * 
	 * @return a new Spielfeld
	 */
	public Spielfeld buildField() {
		//How big is the puzzle?
		int size = getPuzzleSize();
		int field[][] = null;
		//get the value of each field
		String puzzleInput = getFieldInput();
		String[] numbers;
		
		switch (size) {
			/* if the push puzzle is of size 2, create a new 2x2 matrix and
			 * put the numbers in it.
			 */
			case 2:
				field = new int[2][2];
				numbers = puzzleInput.split(" ");
				int i2 = 0;
				int j2 = 0;
				for(String string: numbers){
					if(j2 == field.length){
						j2 = 0;
						i2++;
					}
					field[i2][j2++] = Integer.parseInt(string);
				}
				break;
			/* if the push puzzle is of size 3, create a new 3x3 matrix and
			 * put the numbers in it.
			 */	
			case 3:
				field = new int[3][3];
				numbers = puzzleInput.split(" ");
				int i3 = 0;
				int j3 = 0;
				for(String string: numbers){
					if(j3 == field.length){
						j3 = 0;
						i3++;
					}
					field[i3][j3++] = Integer.parseInt(string);
				}
				
				break;
			/* if the push puzzle is of size 4, create a new 4x4 matrix and
			 * put the numbers in it.
			 */	
			case 4:
				field = new int[4][4];
				numbers = puzzleInput.split(" ");
				int i4 = 0;
				int j4 = 0;
				for(String string: numbers){
					if(j4 == field.length){
						j4 = 0;
						i4++;
					}
					field[i4][j4++] = Integer.parseInt(string);
				}			
				break;
		}
		
		Spielfeld realfield = new Spielfeld(size);
		realfield.fillSpielfeld(field);
		System.out.println(realfield.toString());
		return realfield;
	}
	
}
