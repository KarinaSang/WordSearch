

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class WordSearchGenerator{

  static Random myRandom = new Random ();
  static int numOfRows=0;
  static int numOfColumns=0;
  static int randomNum = 0;//Randomly generate a number to determine whether the word will be placed vertically, horizontally or diagonally 
  static int columnNum = 0;//To store the randomly generated column number 
  static int rowNum = 0;//To store the randomly generated row number 
  static int slopeNum;
  static char [][] puzzle;
  
  public static boolean ensureValidOverlap(int orientationNum, char forwardBackwards, int countLetterIndex, String targetWord){
     if(countLetterIndex == targetWord.length()-1){
      return true;
    }

    //-----------------------IF THE WORD IS GOING TO BE PLACED VERTICALLY-------------------//
    if (orientationNum == 0){
      
      if (forwardBackwards == 'A'){//If the word is to be placed forwards 
        while (puzzle[rowNum+countLetterIndex][columnNum]!=' ' && targetWord.charAt(countLetterIndex) != puzzle[rowNum+countLetterIndex][columnNum]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(rowNum+targetWord.length()>numOfRows){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'A',countLetterIndex+1,targetWord);
      }
      else if (forwardBackwards == 'B'){//If the word is to be placed backwards
        while (puzzle[rowNum+countLetterIndex][columnNum]!=' ' && targetWord.charAt(targetWord.length()-1-countLetterIndex) != puzzle[rowNum+countLetterIndex][columnNum]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(rowNum + targetWord.length() > numOfRows){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'B',countLetterIndex+1,targetWord);
      }
        
    }//-----------------------END OF IF THE WORD IS GOING TO BE PLACED VERTICALLY-------------------//
    
    //-----------------------IF THE WORD IS GOING TO BE PLACED HORIZONTALLY-------------------//
    
    else if(orientationNum == 1){
      
      if (forwardBackwards == 'A'){//If the word is to be placed forwards 
        while (puzzle[rowNum][columnNum+countLetterIndex]!=' ' && targetWord.charAt(countLetterIndex) != puzzle[rowNum][columnNum+countLetterIndex]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(numOfRows-rowNum <= targetWord.length()){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'A',countLetterIndex+1,targetWord);
      }
      else if (forwardBackwards == 'B'){//If the word is to be placed backwards
        while (puzzle[rowNum][columnNum+countLetterIndex]!=' ' && targetWord.charAt(targetWord.length()-1-countLetterIndex) != puzzle[rowNum][columnNum+countLetterIndex]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(numOfRows-rowNum <= targetWord.length()){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'B',countLetterIndex+1,targetWord);
      }
     
    }//-----------------------END OF IF THE WORD IS GOING TO BE PLACED HORINZONTALLY-------------------//
    
     
     
    //-----------------------IF THE WORD IS GOING TO BE PLACED DIAGONALLY-------------------//
    else if(orientationNum == 2){
     //----//----//If the word is to be placed forwards//----//----// 
     if (forwardBackwards == 'A'){
       if(slopeNum == 0){
        while (puzzle[rowNum+countLetterIndex][columnNum+countLetterIndex]!=' ' && targetWord.charAt(countLetterIndex) != puzzle[rowNum+countLetterIndex][columnNum+countLetterIndex]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(numOfRows-rowNum <= targetWord.length()){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'A',countLetterIndex+1,targetWord);
      }
     else if(slopeNum == 1){
       while (puzzle[rowNum+countLetterIndex][columnNum-countLetterIndex]!=' ' && targetWord.charAt(countLetterIndex) != puzzle[rowNum+countLetterIndex][columnNum-countLetterIndex]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(numOfRows-rowNum <= targetWord.length()){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'A',countLetterIndex+1,targetWord);
      }
     }
    
      //----//----//If the word is to be placed backwards//----//----// 
      else if (forwardBackwards == 'B'){//If the word is to be placed backwards
        if(slopeNum == 0){//Negative slope 
        while (puzzle[rowNum+countLetterIndex][columnNum+countLetterIndex]!=' ' && targetWord.charAt(countLetterIndex) != puzzle[rowNum+countLetterIndex][columnNum+countLetterIndex]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(numOfRows-rowNum <= targetWord.length()){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'B',countLetterIndex+1,targetWord);
      }
     else if(slopeNum == 1){//Positive slope 
       while (puzzle[rowNum+countLetterIndex][columnNum-countLetterIndex]!=' ' && targetWord.charAt(countLetterIndex) != puzzle[rowNum+countLetterIndex][columnNum-countLetterIndex]){
          rowNum = myRandom.nextInt(numOfRows);
          columnNum = myRandom.nextInt(numOfColumns);
          while(numOfRows-rowNum <= targetWord.length()){
            rowNum = myRandom.nextInt(numOfRows);
          }
          countLetterIndex = 0;//Regenerate a position from the very beginning 
        }
        return ensureValidOverlap(0,'B',countLetterIndex+1,targetWord);
      }
      }//End of if the word is to be placed backwards
    
     }//-----------------------END OF IF THE WORD IS GOING TO BE PLACED DIAGONALLY-------------------//
 
      return false;
    }//End of the method validateOverlap 
  
 public static void main(String[] args) throws IOException {
  Scanner input = new Scanner(System.in);
  boolean invalidFile=true;
  ArrayList <String> words = new ArrayList <String> ();//Array List to store the words from the word text file 
  
  
  while(invalidFile){//While the input file is invalid 
    invalidFile=false;
    words.clear();
    System.out.println("Please enter the name of the word file (including the extensions)");
    String wordFileName = input.nextLine();
    
    File wordFile = new File (wordFileName);
    Scanner fileReader = new Scanner (wordFile);
    String currentWord=" ";
    
    while (fileReader.hasNext()){
      currentWord=fileReader.nextLine();
      if(currentWord.length() < 4 || currentWord.length() > 8){//If the words are shorter than 4 letters or longer than 8 letters 
        System.out.println("Your words cannot be shorter than 4 letters or longer than 8 letters");
        invalidFile = true;
        break;
      }
      for(int i=0;i<currentWord.length();i++){
        if(Character.isUpperCase(currentWord.charAt(i)) == true ){
          System.out.println("Your words cannot contain uppercase letters");
          invalidFile=true;
          break; 
        }
      }
      if(invalidFile == false){  
        words.add(currentWord);
      }
    }
    
    if(words.size() > 10){//If there are more than 10 words in the file 
     invalidFile = true; 
     System.out.println("Your file cannot contain more than 10 words");
    }
    fileReader.close();
  }//End of checking whether the input file of words is valid 
 
  
  //Prompt the user to enter the number of rows 
  System.out.println("Please enter the number of rows");
   numOfRows = input.nextInt();
  while (numOfRows < 10 || numOfRows > 20){
   System.out.println("Invalid number of rows");
   numOfRows = input.nextInt();
  }
  
  //Prompt the user to enter the number of columns 
  System.out.println("Please enter the number of columns");
  numOfColumns = input.nextInt();
  while (numOfColumns < 10 || numOfColumns > 20){
   System.out.println("Invalid number of columns");
   numOfColumns = input.nextInt();
  }
  
  
  //Assign an empty character value to every position in the puzzle 
  puzzle = new char [numOfRows][numOfColumns];
  for(int i = 0; i< puzzle.length; i++){
    for(int j = 0; j< puzzle[0].length; j++){
     puzzle[i][j]=' '; 
    }
  }
  
  
  for (int i = 0; i < words.size(); i++){
   String targetWord = words.get(i);
   char [] letters = targetWord.toCharArray();
   
   char forwardBackwards = (char)(myRandom.nextInt(2)+'A');//Randomly generate A or B to determine whether the word will be placed forwards or backwards 
   System.out.println("");
  
   randomNum = myRandom.nextInt(3);//Randonly generate a number from 1 to 3 
   rowNum = myRandom.nextInt(numOfRows);//Randomly generate a row number to determine which row the letter will be placed in 
   columnNum = myRandom.nextInt(numOfColumns);//Randomly generate a column number to determine which column the letter will be placed in 
   
   //----------------------------IF THE WORD IS TO BE PLACED VERTICALLY---------------------------//
   if (randomNum == 0){ 
    while(rowNum+targetWord.length() > numOfRows){
     rowNum = myRandom.nextInt(numOfRows);
    }
    
    if (forwardBackwards == 'A'){//If the word is to be placed forwards 
      ensureValidOverlap(0, 'A', 0, targetWord);
    }
    else if (forwardBackwards == 'B'){//If the word is to be placed backwards
      ensureValidOverlap(0, 'B', 0, targetWord);
    }
    
    
    int countLetter = 0;
    for (int a = rowNum; a < rowNum+targetWord.length(); a++){
     if (forwardBackwards == 'A'){//If the word is to be placed forwards 
      puzzle[a][columnNum] = targetWord.charAt(countLetter);
     }
     else if (forwardBackwards == 'B'){//If the word is to be placed backwards 
      puzzle[a][columnNum] = targetWord.charAt(targetWord.length()-1-countLetter);
     }
     countLetter++;
    }
   }//------------------------------End of placing the word vertically-------------------------------//
   
   //----------------------------IF THE WORD IS TO BE PLACED HORIZONTALLY---------------------------//
   else if (randomNum == 1){ 
     while(columnNum+targetWord.length()>numOfColumns){
       columnNum = myRandom.nextInt(numOfColumns);
     }
     
     int countLetterIndex = 0;
     if (forwardBackwards == 'A'){//If the word is to be placed forwards 
       ensureValidOverlap(1, 'A', 0, targetWord);
     }
     else if (forwardBackwards == 'B'){//If the word is to be placed backwards
      ensureValidOverlap(1, 'B', 0, targetWord);
     }
     
     int countLetter = 0;
     for (int a = columnNum; a < columnNum+targetWord.length(); a++){//Adding the characters to the puzzle structure 
       if (forwardBackwards == 'A'){//If the word is to be placed forwards
         puzzle[rowNum][a] = targetWord.charAt(countLetter);
       }
       else if (forwardBackwards == 'B'){//If the word is to be placed backwards
         puzzle[rowNum][a] = targetWord.charAt(targetWord.length()-1-countLetter);
       }
       countLetter++;
     }
     
   }//------------------------------End of placing the word horizontally-------------------------------//
   
   //----------------------------IF THE WORD IS TO BE PLACED DIAGONALLY---------------------------//
   else if (randomNum == 2){
     //Randomly generate a number to determine whether the diagonal or be of positive or negative slope
     slopeNum=myRandom.nextInt(2); 
     int countLetter = 0;
     
     //Make sure that the word will be placed in bound
     while(rowNum+targetWord.length() > numOfRows || rowNum-targetWord.length() < 0){
       rowNum = myRandom.nextInt(numOfRows);
     }
     while(columnNum+targetWord.length() > numOfColumns || columnNum-targetWord.length()< 0){
       columnNum = myRandom.nextInt(numOfColumns);
     }
     
     int countLetterIndex = 0;
     if (forwardBackwards == 'A'){//If the word is to be placed forwards
       ensureValidOverlap(2, 'A', 0, targetWord);
     }
     else if (forwardBackwards == 'B'){//If the word is to be placed forwards
       ensureValidOverlap(2, 'B', 0, targetWord);
     }
     
     for (int a = rowNum; a < rowNum+targetWord.length(); a++){
       if (forwardBackwards == 'A'){//If the word is to be placed forwards
         if(slopeNum == 0){
           puzzle[a][columnNum+countLetter] = targetWord.charAt(countLetter);
         }
         else if(slopeNum == 1){
           puzzle[a][columnNum-countLetter] = targetWord.charAt(countLetter);
         }
       }
       else if (forwardBackwards == 'B'){//If the word is to be placed forwards
         if(slopeNum == 0){
           puzzle[a][columnNum+countLetter] = targetWord.charAt(countLetter);
         }
         else if(slopeNum == 1){
           puzzle[a][columnNum-countLetter] = targetWord.charAt(countLetter);
         }
       }
       countLetter++;
     }
     

   }//------------------------------End of placing the word diagonally-------------------------------//
   
   
  }//End of for all words to be included into the solution  
  
  
 //Solution 
  for (int i = 0; i < puzzle.length; i++){
   for (int a = 0; a < puzzle[0].length; a++){
    System.out.print(puzzle[i][a]);
   }
   System.out.println("");
  }

 }

}
