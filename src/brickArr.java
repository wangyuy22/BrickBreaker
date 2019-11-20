import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class brickArr {

	private brick[][] blocks = new brick[8][8];


	//Constructor 1
	public brickArr(int[][] numbers, int courtWidth, int courtHeight) {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				if (numbers[i][j] != 0) {
					blocks[i][j] = new brick(courtWidth, courtHeight, numbers[i][j], i * 80, j * 30, i, j);
				}
			}
		}
	}

	//Constructor 2 which creates the brickArr from text file
	public brickArr(String textFile, int courtWidth, int courtHeight) throws IOException {
		try {
			//Reading the file
			FileReader fr = new FileReader(textFile);
			BufferedReader reader = new BufferedReader(fr);
			
			//Loops through rows and then columns
			for (int j = 0; j < 8; j++) {
				String row = reader.readLine();
				for (int i = 0; i < 8; i++) {
					if (row != null) {
						
						//Finds digit and if it's not 0 creates a brick at the location 
						int digit = row.charAt(i) - '0';
						if (digit != 0) {
							blocks[i][j] = new brick(courtWidth, courtHeight, digit,
									i * 80, j * 30, i, j);
						}
					} else {
						blocks[i][j] = null;
					}
				}
			}
		} catch (FileNotFoundException e) {
				System.out.println("File not found exception.");
		}

	}
	
	//Removes brick
	public void removeBrick(brick rem) {
		blocks[rem.getX()][rem.getY()] = null; 
	}
	
	//Gets brick rom an index
	public brick getBrick(int i, int j) {
		return blocks[i][j];
	}
	
	//Get length X
	public int getLengthX() {
		return blocks.length;
	}
	
	//Get length Y
	public int getLengthY() {
		return blocks[0].length;
	}
	
	//Check if brickArr is empty
	public boolean isEmpty() {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {
				if (blocks[i][j] != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Saves the brickArr into a file
	public void saveBricks() throws IOException {
		try {
			//Creates filewriter and buffer writer
			FileWriter fr = new  FileWriter("files/savedgame.txt");
			BufferedWriter writer = new BufferedWriter(fr);
			
			//Loops through rows and then columns
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					
					//If no brick then 0
					if (blocks[i][j] == null) {
						writer.write('0');
						
					//Or else write the number of hits remaining	
					} else {
						writer.write("" + blocks[i][j].getHits());
					}
				}
				writer.newLine();
			}
			writer.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found exception.");
		}
	}
} 
