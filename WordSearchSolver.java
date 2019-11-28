package wordSearchPuzzle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class WordSearchSolver {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		
		boolean invalidFile = true;
		ArrayList <String> words = new ArrayList <String> ();
		
		//collect words from the word file
		while (invalidFile == true){
			invalidFile = false;
			System.out.println ("Word file name");
			String wordFileName = input.nextLine();
			Scanner getWords = new Scanner (new File (wordFileName));
			
			int countWord = 0; //count the total number of words
			while (getWords.hasNext() && invalidFile == false){
				String currentWord = getWords.nextLine();
				
				//check for length of words
				if (currentWord.length()<4 || currentWord.length()>8){
					invalidFile = true;
				}
				
				//check for capitalization
				for (int i = 0; i < currentWord.length(); i++){
					if (currentWord.charAt(i) == Character.toUpperCase(currentWord.charAt(i))){
						invalidFile = true;
						i = currentWord.length();
					}
				}
				
				//if file has more than ten words
				if (countWord > 10){
					invalidFile = true;
				}
				
				countWord++;	
				
			}
			
			//if the file is empty
			if (countWord == 0){
				invalidFile = true;
			}
			getWords.close();
		}
		
		
		//collect puzzle file
		invalidFile = true;
		
		ArrayList <String> puzzleList = new ArrayList <String> ();
		while (invalidFile == true){
			invalidFile = false;
			String puzzleFilename = input.nextLine();
			Scanner retrievePuzzle = new Scanner (new File (puzzleFilename));
			
			while (retrievePuzzle.hasNext()){
				puzzleList.add(retrievePuzzle.nextLine());
		}
			
			//if file is empty
			if (puzzleList.size() == 0){
				invalidFile = true;
			}
			
			retrievePuzzle.close();
		}
		
		int columnNum = puzzleList.get(0).length();
		int rowNum = puzzleList.size();
		
		char [][] puzzle = new char [rowNum][columnNum];
		
		int countLetter = 0;
		for (int i = 0; i < rowNum; i++){
			for (int a = 0; a < columnNum; a++){
				puzzle [i][a] = puzzleList.get(i).charAt(countLetter);
				countLetter++;
			}
		}
		

		

	}
	
	public static void puzzleSolver (ArrayList <String> words, char [][] puzzle){
		for (int i = 0; i < words.size(); i++){
			String targetWord = words.get(i);
			
			int numberOfRows = puzzle.length;
			int numberOfColumns = puzzle[0].length;
			for (int a = 0; a < numberOfRows; a++){
				for (int b = 0; b < numberOfColumns; b++){
					int initialColumn = 0;
					int initialRow = 0;
					
					if (targetWord.charAt(0) == puzzle[a][b]){
						initialColumn = a;
						initialRow = b;
					}
							String remainingWord = targetWord.substring(2);
							char secondLetter = targetWord.charAt(1);
							
							//vertically upward
							if (secondLetter == puzzle [initialRow-1][initialColumn] && initialRow-1-remainingWord.length() >= 0){
								if (checkVertically ("up", remainingWord, 2, puzzle, initialRow-1, initialColumn) == true){
									
								}
							}
							
							//vertically downward
							else if (secondLetter == puzzle[initialRow+1][initialColumn] && initialRow+1+remainingWord.length() <= numberOfRows){
								if (checkVertically("down", remainingWord, 2, puzzle, initialRow+1, initialColumn) == true){
				
								}

							}
							
							//horizontally forward
							else if (secondLetter == puzzle [initialRow][initialColumn-1] && initialColumn-1-remainingWord.length() >= 0){
								if (checkHorizontally("left", remainingWord, 2, puzzle, initialRow, initialColumn-1)){
					
								}
							
							}
							
							//horizontally backward
							else if (secondLetter == puzzle [initialRow][initialColumn+1] && initialColumn + remainingWord.length()<= numberOfRows){
								if (checkHorizontally("right", remainingWord, 2, puzzle, initialRow, initialColumn+1) == true){
							
								}
							
							}
							
							//diagonally upleft
							else if (secondLetter == puzzle [initialRow-1][initialColumn-1] && initialColumn-1-remainingWord.length()>= 0 && initialRow-1-remainingWord.length()>=0){
								if (checkDiagonally("upLeft", remainingWord, 2, puzzle, initialRow-1, initialColumn-1) == true){
								
								}
								
							}
							
							//diagonally downLeft
							else if (secondLetter == puzzle [initialRow+1][initialColumn-1] && initialColumn-1-remainingWord.length()>=0 && initialRow+1+remainingWord.length()<=numberOfRows){
								if (checkDiagonally("downLeft", remainingWord, 2, puzzle, initialRow+1, initialColumn-1) == true){
									
								}
							
							}
							
							//diagonally upRight
							else if (secondLetter == puzzle [initialRow-1][initialColumn+1] && initialColumn+1+remainingWord.length()<= numberOfColumns && initialRow-1-remainingWord.length() >=0){
								if (checkDiagonally("upRight", remainingWord, 2, puzzle, initialRow-1, initialColumn+1) == true){
					
								}
							
							}
							
							//diagonally downRight
							else if (secondLetter == puzzle [initialRow+1][initialColumn+1] && initialColumn+1+remainingWord.length() <= numberOfColumns && initialRow+1+remainingWord.length()<= numberOfRows){
								if (checkDiagonally("downRight", remainingWord, 2, puzzle, initialRow+1, initialColumn+1)){
								
								}
							
							}
				
				}
			}
			
		}
		

		
	}
		
		
		public static boolean checkVertically (String direction, String remainingWord, int currentIndex, char [][] puzzle, int rowNum, int columnNum){
			if (currentIndex == remainingWord.length()){
				return true;
			}
			
			if (direction.equals("up")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum-1][columnNum]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum-1, columnNum);
				}
				else{
					return false;
				}
			}
			
			else if (direction.equals("down")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum+1][columnNum]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum+1, columnNum);
				}
				else{
					return false;
				}
			}
			
			else{
				return false;
			}
			
			
			
		}
		
		public static boolean checkHorizontally (String direction, String remainingWord, int currentIndex, char [][] puzzle, int rowNum, int columnNum){
			if (currentIndex == remainingWord.length()){
				return true;
			}
			
			if (direction.equals("left")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum][columnNum-1]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum, columnNum-1);
				}
				else{
					return false;
				}
			}
			
			else if (direction.equals("right")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum][columnNum+1]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum, columnNum+1);
				}
				else{
					return false;
				}
			}
			
			else{
				return false;
			}
			
			
			
		}
		
		
		public static boolean checkDiagonally (String direction, String remainingWord, int currentIndex, char [][] puzzle, int rowNum, int columnNum){
			if (currentIndex == remainingWord.length()){
				return true;
			}
			
			if (direction.equals("upLeft")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum-1][columnNum-1]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum-1, columnNum-1);
				}
				else{
					return false;
				}
			}
			
			else if (direction.equals("downLeft")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum+1][columnNum-1]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum+1, columnNum-1);
				}
				else{
					return false;
				}
			}
			
			else if (direction.equals("upRight")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum-1][columnNum+1]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum-1, columnNum+1);
				}
				else{
					return false;
				}
			}
			
			else if (direction.equals("downRight")){
				if (remainingWord.charAt(currentIndex) == puzzle[rowNum+1][columnNum+1]){
					return checkVertically(direction, remainingWord, currentIndex+1, puzzle, rowNum+1, columnNum+1);
				}
				else{
					return false;
				}
			}
			
			else{
				return false;
			}
			
			
			
		}

	
	
	

}//end of class
