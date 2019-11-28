/**
 * Date:Dec 21st 2016 
 * Name: SolutionFrame 
 * Description: 
 * This is the welcome frame 
 * It explains the basics of the program and allows the user to enter the application 
 */

import java.util.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;//For the action listener 
import java.io.*;

public class SolutionFrame extends JFrame implements ActionListener{
  
  static int numOfRows;//The number of rows in the puzzle, retrieved from the previous frame 
  static int numOfColumns;//The number of columns in the puzzle, retrieved from the previous frame
  static String wordFileName;//Used to store the name of the file the user has entered  
  static char[][] puzzle;//Used to store all the characters on the puzzle 
  static Random myRandom = new Random();//Used to generate random numbers and letters 
  static int slopeNum;//Used to determine whether the word will be placed diagonally in a positive slope or negative slope 
  
  JPanel solutionPanel;//The panel that contains the solution 
  JLabel title;
  JLabel outputMessage;
  JButton[][] displayPuzzle;//The JButton 2D array that actually displays the solution to the user 
  JButton generateFinalPuzzle;//Allows the user to proceed to generating the final puzzle 
  JLabel enterSolutionFileName;//Tells the user to enter a name for a file so that the system may save it 
  JTextField enterNameHere;//The text box for the user to enter the name to save the file 
  JButton okButton;//The user will press this button to confirm their input 
  JButton regenerate;//Used to regenerate a solution if the user does not like the current solution 
  
  /**
   * Name:SolutionFrameG
   * Parameters:String fileName,int numOfRows,int numOfColumns
   * Description:
   * This is the constructor method 
   */
  public SolutionFrame(String fileName,int numOfRows,int numOfColumns){
    wordFileName = fileName;
    this.numOfRows = numOfRows;
    this.numOfColumns = numOfColumns;
    
    
    title = new JLabel("Solution",JLabel.CENTER);
    title.setFont(new Font("Times New Roman",Font.PLAIN,26));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    outputMessage = new JLabel("Here is the randomly generated word puzzle solution:");
    outputMessage.setFont(new Font("Times New Roman",Font.PLAIN,16));
    outputMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    displayPuzzle = new JButton[numOfRows][numOfColumns];//For outputing the solution to the user 
    
    generateFinalPuzzle = new JButton("Save Solution File and Generate Final Puzzle");
    generateFinalPuzzle.addActionListener(this);//Implement action listener 
    generateFinalPuzzle.setBackground(Color.GREEN);
    generateFinalPuzzle.setOpaque(true);
    generateFinalPuzzle.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    enterSolutionFileName = new JLabel("Enter Solution File Name:");
    enterSolutionFileName.setFont(new Font("Times New Roman",Font.PLAIN,14));
    enterSolutionFileName.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    enterNameHere = new JTextField("Enter File Name + Extension");
    enterNameHere.setFont(new Font("Times New Roman",Font.PLAIN,14));
    enterNameHere.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    okButton = new JButton("OK");
    okButton.addActionListener(this);//Implement action listener 
    okButton.setBackground(Color.GREEN);
    okButton.setOpaque(true);
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    regenerate = new JButton("Regenerate Solution");
    regenerate.addActionListener(this);//Implement action listener 
    regenerate.setBackground(Color.ORANGE);
    regenerate.setOpaque(true);
    regenerate.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    setLayout(new BoxLayout( getContentPane(),BoxLayout.PAGE_AXIS));
    GridLayout gridLayout = new GridLayout (numOfRows,numOfColumns);//For the solution panel 
    solutionPanel = new JPanel();
    solutionPanel.setLayout(gridLayout);
    
    add(title);
    add(outputMessage);
    try{
    solutionGenerator();//Generates a solution 
    }catch(IOException ex){
      
    }
    add(solutionPanel);
    add(generateFinalPuzzle);
    add(regenerate);
    
    
    //-------------Set The Frame------------//
    setTitle("Word Search Generator");
    setSize(650,420);
    setVisible(true);
    
    
  }//End of the constructor 
  
  /**
   * Name:actionPerformed
   * Parameters:ActionEvent event 
   * Return Type:void
   * Description:
   * 
   */
  public void actionPerformed(ActionEvent event){
    String action = event.getActionCommand();
    
    if(action.equals("Save Solution File and Generate Final Puzzle")){//If the user presses the button to generate the final puzzle
      remove(generateFinalPuzzle);
      remove(regenerate);
      validate();
      repaint();
      
      add(enterSolutionFileName);
      add(enterNameHere);
      add(okButton);
    }
    
    if(action.equals("OK")){//If the user presses OK to confirm their input file name 
      File solutionFile = new File(enterNameHere.getText());
      try{
        PrintWriter fileWriter = new PrintWriter (solutionFile);
        for(int i = 0; i < puzzle.length; i++){
          for(int j = 0; j < puzzle[0].length; j++ ){
            fileWriter.print(puzzle[i][j]); 
          }
          fileWriter.println(" ");
        }
        fileWriter.close();
      }catch(IOException ex){
        
      }
      dispose();
      FinalPuzzleFrame finalFrame = new FinalPuzzleFrame(puzzle,numOfRows,numOfColumns);
      
    }//End of if the user presses OK 
    
    if(action.equals("Regenerate Solution")){//If the user does not like the solution and chooses to regenerate it 
      solutionPanel.removeAll();
      solutionPanel.revalidate();
      solutionPanel.repaint();
      try{
        solutionGenerator();//Generates a solution 
      }catch(IOException ex){
        
      }
    }

  }//End of the method actionPerformed
  
  /**
   * Name:solutionGenerator
   * Parameters:None
   * Return Type:void
   * Description:
   * 
   */
  public void solutionGenerator() throws IOException{
    File wordFile = new File (wordFileName);//Open the file the user specified in the input frame 
    Scanner fileReader = new Scanner (wordFile);//Declare a Scanner to read the words from the file 
    ArrayList<String> words = new ArrayList<String>();//Used to store the words in the file 
    
    puzzle = new char[numOfRows][numOfColumns];//This 2D array stores all values in the puzzle 
    
    //Assign an empty character to every position in the puzzle initially 
    for(int i = 0; i < puzzle.length ; i++){
      for(int j = 0; j < puzzle[0].length; j++){
        puzzle[i][j] = ' ';
      }
    }
    
    while(fileReader.hasNext()){//While there is still something in the file to read 
      String currentWord = fileReader.nextLine();
      words.add(currentWord);//Add the current word being read into the array list words 
    }
    fileReader.close(); 
    
    for (int k = 0; k < words.size(); k++){//For all the words from the file 
      
      String targetWord = words.get(k);//Get one word out of the array list of words 
      
    
      int orientationNum = myRandom.nextInt(3);//Randonly generate a number from 1 to 3 to determine whether the word will be placed vertically, horizontally or diagonally 
      int rowNum = myRandom.nextInt(numOfRows);//Randomly generate a row number to determine which row the letter will be placed in 
      int columnNum = myRandom.nextInt(numOfColumns);//Randomly generate a column number to determine which column the letter will be placed in 
      char forwardBackwards = (char)(myRandom.nextInt(2) +'A');//Randomly generate A or B to determine whether the word will be placed forwards or backwards
      
      //----------------------------IF THE WORD IS TO BE PLACED HORIZONTALLY---------------------------//
      if (orientationNum == 0){ 
        System.out.println("Testing while loop validation: ");
        System.out.println("columnNum: "+columnNum);
        System.out.println("rowNum: "+rowNum);
        System.out.println("targetWord: "+targetWord);
        System.out.println("targetWord.length(): "+targetWord.length());
        System.out.println("columnNum+targetWord.length() > numOfColumns"+(columnNum+targetWord.length() > numOfColumns));
        while(columnNum+targetWord.length() > numOfColumns){//Boundary validation of puzzle 
          System.out.println("Entered validation while loop");
          columnNum = myRandom.nextInt(numOfColumns);
        }
        
        if (forwardBackwards == 'A'){//If the word is to be placed forwards 
          validateOverlap(0, 'A', 0, targetWord,columnNum,rowNum);//Ensures that the rowNum and columnNum we start with allows for valid positioning of the entire word 
          int validationCounter = 0;//used to keep track of how many times a number is regenerated 
          int resetCounter = 0;//used to keep track of how many times a word is repositioned.
          
          while (validateOverlap(0, 'A', 0, targetWord,columnNum,rowNum) == false){
            
            if(validationCounter >= 5){//If the rowNum and columnNum has to be regenerated for 5 or more times 
             orientationNum = myRandom.nextInt(3);//change orientation since this orientation does not seem to work well
             validationCounter = 0;
             resetCounter++;
             if(resetCounter >= 5){
              k=0;//Re-position the entire word list 
              resetCounter = 0;
             }
            }
            
            //Regenerate columnNum and rowNum for the current targetWord to be positioned 
            rowNum = myRandom.nextInt(numOfRows);
            columnNum = myRandom.nextInt(numOfColumns);
            System.out.println("Testing while loop validation: ");
            System.out.println("columnNum: "+columnNum);
            System.out.println("rowNum: "+rowNum);
            System.out.println("targetWord: "+targetWord);
            System.out.println("targetWord.length(): "+targetWord.length());
            System.out.println("columnNum+targetWord.length() > numOfColumns: "+(columnNum+targetWord.length() > numOfColumns));
            while(columnNum+targetWord.length() > numOfColumns){//Make sure that the word does not go out of bound
              System.out.println("Entered validation while loop");
              columnNum = myRandom.nextInt(numOfColumns);
            }
            validateOverlap(0, 'A', 0, targetWord,columnNum,rowNum);//validate the new positions 
            validationCounter++;
          }//End of while loop 
        }
        else if (forwardBackwards == 'B'){//If the word is to be placed backwards
          validateOverlap(0, 'B', 0, targetWord,columnNum,rowNum);//Ensures that the rowNum and columnNum we start with allows for valid positioning of the entire word 
          int validationCounter = 0;//used to keep track of how many times a number is regenerated 
          int resetCounter = 0;//used to keep track of how many times a word is repositioned.
          
          while (validateOverlap(0, 'B', 0, targetWord,columnNum,rowNum) == false){
            
            if(validationCounter >= 5){//If the rowNum and columnNum has to be regenerated for 5 or more times 
             orientationNum = myRandom.nextInt(3);//change orientation since this orientation does not seem to work well
             validationCounter = 0;
             resetCounter++;
             if(resetCounter >= 5){
              k=0;//Re-position the entire word list 
              resetCounter = 0;
             }
            }
            
            //Regenerate ColumnNum and rowNum for the current targetWord to be positioned 
            rowNum = myRandom.nextInt(numOfRows);
            columnNum = myRandom.nextInt(numOfColumns);
            System.out.println("Testing while loop validation: ");
            System.out.println("columnNum: "+columnNum);
            System.out.println("rowNum: "+rowNum);
            System.out.println("targetWord: "+targetWord);
            System.out.println("targetWord.length(): "+targetWord.length());
            System.out.println("columnNum+targetWord.length() > numOfColumns: "+(columnNum+targetWord.length() > numOfColumns));
            while(columnNum+targetWord.length() > numOfColumns){//Make sure that the word does not go out of bound 
              System.out.println("Entered validation while loop");
              columnNum = myRandom.nextInt(numOfColumns);
            }
            validateOverlap(0, 'B', 0, targetWord,columnNum,rowNum);//validate the new positions 
            validationCounter++;
          }//End of while loop 
        }
        
        //Position the words onto the puzzle 
        for (int i = 0; i < targetWord.length(); i++){
          if (forwardBackwards == 'A'){//If the word is to be placed forwards 
            puzzle[rowNum][columnNum+i] = Character.toUpperCase(targetWord.charAt(i));
          }
          else if (forwardBackwards == 'B'){//If the word is to be placed backwards 
            puzzle[rowNum][columnNum+i] = Character.toUpperCase(targetWord.charAt(targetWord.length()-1-i));
          }
        }
      }//------------------------------End of placing the word horizontally-------------------------------//
      
      //----------------------------IF THE WORD IS TO BE PLACED VERTICALLY---------------------------//
      else if (orientationNum == 1){ 
        System.out.println("Testing while loop validation: ");
        System.out.println("columnNum: "+columnNum);
        System.out.println("rowNum: "+rowNum);
        System.out.println("targetWord: "+targetWord);
        System.out.println("targetWord.length(): "+targetWord.length());
        System.out.println("rowNum+targetWord.length() > numOfRows: "+(rowNum+targetWord.length() > numOfRows));
        while(rowNum+targetWord.length() > numOfRows){
          System.out.println("Entered validation while loop");
          rowNum = myRandom.nextInt(numOfRows);
        }
        
         if (forwardBackwards == 'A'){//If the word is to be placed forwards 
          validateOverlap(1, 'A', 0, targetWord,columnNum,rowNum);//Ensures that the rowNum and columnNum we start with allows for valid positioning of the entire word 
          int validationCounter = 0;//used to keep track of how many times a number is regenerated 
          int resetCounter = 0;//used to keep track of how many times a word is repositioned.
          
          while (validateOverlap(1, 'A', 0, targetWord,columnNum,rowNum) == false){
            
            if(validationCounter >= 5){//If the rowNum and columnNum has to be regenerated for 5 or more times 
             orientationNum = myRandom.nextInt(3);//change orientation since this orientation does not seem to work well
             validationCounter = 0;
             resetCounter++;
             if(resetCounter >= 5){
              k=0;//Re-position the entire word list 
              resetCounter = 0;
             }
            }
            
            //Regenerate ColumnNum and rowNum for the current targetWord to be positioned 
            rowNum = myRandom.nextInt(numOfRows);
            columnNum = myRandom.nextInt(numOfColumns);
            System.out.println("Testing while loop validation: ");
            System.out.println("columnNum: "+columnNum);
            System.out.println("rowNum: "+rowNum);
            System.out.println("targetWord: "+targetWord);
            System.out.println("targetWord.length(): "+targetWord.length());
            System.out.println("rowNum+targetWord.length() > numOfRows: "+(rowNum+targetWord.length() > numOfRows));
            while(rowNum+targetWord.length() > numOfRows){//Make sure that the word does not go out of bound 
              System.out.println("Entered validation while loop");
              rowNum = myRandom.nextInt(numOfRows);
            }
            validateOverlap(1, 'A', 0, targetWord,columnNum,rowNum);//validate the new positions 
            validationCounter++;
          }//End of while loop 
        }//End of placing the word forwards 
         
        else if (forwardBackwards == 'B'){//If the word is to be placed backwards
          validateOverlap(1, 'B', 0, targetWord,columnNum,rowNum);//Ensures that the rowNum and columnNum we start with allows for valid positioning of the entire word 
          int validationCounter = 0;//used to keep track of how many times a number is regenerated 
          int resetCounter = 0;//used to keep track of how many times a word is repositioned.
          
          while (validateOverlap(1, 'B', 0, targetWord,columnNum,rowNum) == false){
            
            if(validationCounter >= 5){//If the rowNum and columnNum has to be regenerated for 5 or more times 
             orientationNum = myRandom.nextInt(3);//change orientation since this orientation does not seem to work well
             validationCounter = 0;
             resetCounter++;
             if(resetCounter >= 5){
              k=0;//Re-position the entire word list 
              resetCounter = 0;
             }
            }
            
            //Regenerate ColumnNum and rowNum for the current targetWord to be positioned 
            rowNum = myRandom.nextInt(numOfRows);
            columnNum = myRandom.nextInt(numOfColumns);
            System.out.println("Testing while loop validation: ");
            System.out.println("columnNum: "+columnNum);
            System.out.println("rowNum: "+rowNum);
            System.out.println("targetWord: "+targetWord);
            System.out.println("targetWord.length(): "+targetWord.length());
            System.out.println("rowNum+targetWord.length() > numOfRows: "+(rowNum+targetWord.length()>numOfRows));
            while(rowNum+targetWord.length()>numOfRows){//Make sure that the word does not go out of bound 
              rowNum = myRandom.nextInt(numOfRows);
            }
            validateOverlap(1, 'B', 0, targetWord,columnNum,rowNum);//validate the new positions 
            validationCounter++;
          }//End of while loop 
        }//End of placing the word backwards 
        
       
        for (int i = 0; i < targetWord.length(); i++){//Adding the characters to the puzzle structure 
          if (forwardBackwards == 'A'){//If the word is to be placed forwards
            puzzle[rowNum+i][columnNum] = Character.toUpperCase(targetWord.charAt(i));
          }
          else if (forwardBackwards == 'B'){//If the word is to be placed backwards
            puzzle[rowNum+i][columnNum] = Character.toUpperCase(targetWord.charAt(targetWord.length()-1-i));
          }
        }
        
      }//------------------------------End of placing the word vertically -------------------------------//
      
      //----------------------------IF THE WORD IS TO BE PLACED DIAGONALLY---------------------------//
      else if (orientationNum == 2){
        //Randomly generate a number to determine whether the diagonal or be of positive or negative slope
        slopeNum=myRandom.nextInt(2); 
        
        //Make sure that the word will be placed in bound
        System.out.println("Testing while loop validation: ");
        System.out.println(columnNum);
        System.out.println(targetWord.length());
        System.out.println("rowNum+targetWord.length() > numOfRows: "+(rowNum+targetWord.length() > numOfRows));
        System.out.println("rowNum+1-targetWord.length() < 0: "+(rowNum+1-targetWord.length() < 0));
        while((rowNum+targetWord.length() > numOfRows) || (rowNum+1-targetWord.length() < 0)){
          System.out.println("rowNum: "+rowNum);
          System.out.println("targetWord: "+targetWord+"hello");
          System.out.println("length of targetWord: "+targetWord.length());
          System.out.println((rowNum+targetWord.length() > numOfRows));
          System.out.println((rowNum+1-targetWord.length() < 0));
          rowNum = myRandom.nextInt(numOfRows);
          System.out.println("Newly generated Num:"+rowNum);
        }
        while((columnNum+targetWord.length()>numOfColumns) || (columnNum+1-targetWord.length()<0)){
          columnNum = myRandom.nextInt(numOfColumns);
          System.out.println("Newly generated Num:"+rowNum);
        }
        

        if (forwardBackwards == 'A'){//If the word is to be placed forwards 
          validateOverlap(2, 'A', 0, targetWord,columnNum,rowNum);//Ensures that the rowNum and columnNum we start with allows for valid positioning of the entire word 
          int validationCounter = 0;//used to keep track of how many times a number is regenerated 
          int resetCounter = 0;//used to keep track of how many times a word is repositioned.
          
          while (validateOverlap(2, 'A', 0, targetWord,columnNum,rowNum) == false){
            
            if(validationCounter >= 5){//If the rowNum and columnNum has to be regenerated for 5 or more times 
             orientationNum = myRandom.nextInt(3);//change orientation since this orientation does not seem to work well
             validationCounter = 0;
             resetCounter++;
             if(resetCounter >= 5){
              k=0;//Re-position the entire word list 
              resetCounter = 0;
             }
            }
            
            //Regenerate ColumnNum and rowNum for the current targetWord to be positioned 
            rowNum = myRandom.nextInt(numOfRows);
            columnNum = myRandom.nextInt(numOfColumns);
            System.out.println("Testing while loop validation: ");
            System.out.println(columnNum);
            System.out.println(targetWord);
            System.out.println(targetWord.length());
            System.out.println(rowNum+targetWord.length() > numOfRows);
            System.out.println(rowNum+1-targetWord.length() < 0);
            while(((rowNum+targetWord.length())>numOfRows) || ((rowNum+1-targetWord.length())<0)){
              rowNum = myRandom.nextInt(numOfRows);
            }
            while(((columnNum+targetWord.length())>numOfColumns) || ((columnNum+1-targetWord.length())<0)){
              columnNum = myRandom.nextInt(numOfColumns);
            }
            validateOverlap(2, 'A', 0, targetWord,columnNum,rowNum);//validate the new positions 
            validationCounter++;
          }//End of while loop 
        }//End of placing the word forwards 
         
        else if (forwardBackwards == 'B'){//If the word is to be placed backwards
          validateOverlap(2, 'B', 0, targetWord,columnNum,rowNum);//Ensures that the rowNum and columnNum we start with allows for valid positioning of the entire word 
          int validationCounter = 0;//used to keep track of how many times a number is regenerated 
          int resetCounter = 0;//used to keep track of how many times a word is repositioned.
          
          while (validateOverlap(2, 'B', 0, targetWord,columnNum,rowNum) == false){
            
            if(validationCounter >= 5){//If the rowNum and columnNum has to be regenerated for 5 or more times 
             orientationNum = myRandom.nextInt(3);//change orientation since this orientation does not seem to work well
             validationCounter = 0;
             resetCounter++;
             if(resetCounter >= 5){
              k=0;//Re-position the entire word list 
              resetCounter = 0;
             }
            }
            
            //Regenerate ColumnNum and rowNum for the current targetWord to be positioned 
            rowNum = myRandom.nextInt(numOfRows);
            columnNum = myRandom.nextInt(numOfColumns);
            while(((rowNum+targetWord.length()) > numOfRows) || ((rowNum+1-targetWord.length()) < 0)){
              rowNum = myRandom.nextInt(numOfRows);
            }
            while(((columnNum+targetWord.length()) > numOfColumns) || ((columnNum+1-targetWord.length()) < 0)){
              columnNum = myRandom.nextInt(numOfColumns);
            }
            validateOverlap(2, 'B', 0, targetWord,columnNum,rowNum);//validate the new positions 
            validationCounter++;
          }//End of while loop 
        }//End of placing the word backwards 
        
        
        //Position the word onto the puzzleboard 
        for (int i = 0; i < targetWord.length(); i++){
          if (forwardBackwards == 'A'){//If the word is to be placed forwards
            if(slopeNum == 0){//negative slope
              puzzle[rowNum+i][columnNum+i] = Character.toUpperCase(targetWord.charAt(i));
            }
            else if(slopeNum == 1){//positive slope
              puzzle[rowNum-i][columnNum+i] = Character.toUpperCase(targetWord.charAt(i));
            }
          }
          else if (forwardBackwards == 'B'){//If the word is to be placed forwards
            if(slopeNum == 0){//negative slope
              puzzle[rowNum+i][columnNum+i] = Character.toUpperCase(targetWord.charAt(targetWord.length()-1-i));
            }
            else if(slopeNum == 1){//positive slope
              puzzle[rowNum-i][columnNum+i] = Character.toUpperCase(targetWord.charAt(targetWord.length()-1-i));
            }
          }
        }//End of placing the word onto the puzzle 
        
      }//End of if the word is to be placed diagonally 
    }//End of for all words to be included into the solution 
    
    //Show the user the solution 
    for(int i = 0; i < puzzle.length; i++){
      for(int j = 0; j< puzzle[0].length; j++){
        displayPuzzle[i][j] = new JButton(Character.toString(puzzle[i][j]));
        solutionPanel.add(displayPuzzle[i][j]);
        //System.out.println("Got to this line");
      }
    }
    
    
  }//END OF THE METHOD
  
  /**
   * Name: validateOverlap
   * Parameters:int orientationNum, char forwardBackwards, int countLetterIndex, String targetWord
   * Return Type: boolean
   * Description:
   * 
   */
  public static boolean validateOverlap(int orientationNum, char forwardBackwards, int letterIndex, String targetWord,int columnNum, int rowNum){
    if(letterIndex == 0){
      System.out.println("orientationNum: "+orientationNum);
      System.out.println("forwardsBackwards: "+forwardBackwards);
      System.out.println("targetWord: "+targetWord);
      System.out.println("Initial columnNum: "+columnNum);
      System.out.println("Initial rowNum: "+rowNum);
      System.out.println(" ");
    }
    if(letterIndex > targetWord.length()-1){
     return true; 
    }
    
   //----------------------------ValidateOverlap for the horizontal placement of the word-----------------------// 
    if(orientationNum == 0){
      if(forwardBackwards == 'A'){//Validate forward placement of word 
        System.out.println("columnNum: "+columnNum);
        System.out.println("rowNum: "+rowNum); 
        if((targetWord.charAt(letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
          return false;
        }
        return validateOverlap(0,'A',letterIndex+1,targetWord,columnNum+1,rowNum);
      }//End of validating forward placement of word 
      if(forwardBackwards == 'B'){//Validate backward placement of word 
        System.out.println("columnNum: "+columnNum);
        System.out.println("rowNum: "+rowNum);
        if((targetWord.charAt(targetWord.length()-1-letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
          return false;
        }
        return validateOverlap(0,'B',letterIndex+1,targetWord,columnNum+1,rowNum);
      }//End of validating backward placement of word 
      
    }//ValidateOverlap for the horizontally placed word
    
   //----------------------------ValidateOverlap for the vertical placement of the word-----------------------// 
    else if(orientationNum == 1){
      System.out.println("columnNum: "+columnNum);
      System.out.println("rowNum: "+rowNum);
      if(forwardBackwards == 'A'){//Validate forward placement of word 
        if((targetWord.charAt(letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
          return false;
        }
        return validateOverlap(1,'A',letterIndex+1,targetWord,columnNum,rowNum+1);
      }//End of validating forward placement of word 
      if(forwardBackwards == 'B'){//Validate backward placement of word 
        if((targetWord.charAt(targetWord.length()-1-letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
          return false;
        }
        return validateOverlap(1,'B',letterIndex+1,targetWord,columnNum,rowNum+1);
      }//End of validating backward placement of word 
      
    }//--------End of ValidateOverlap for the vertical placement of the word---------//
    
    //----------------------------ValidateOverlap for the diagonal placement of the word-----------------------// 
    else if(orientationNum == 2){
      System.out.println("columnNum: "+columnNum);
      System.out.println("rowNum: "+rowNum); 
      
      if (slopeNum == 0){//Validate negative slope
        if(forwardBackwards == 'A'){//Validate forward placement of word 
          if((targetWord.charAt(letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
            return false;
          }
          return validateOverlap(2,'A',letterIndex+1,targetWord,columnNum+1,rowNum+1);
        }//End of validating forward placement of word 
        
        if(forwardBackwards == 'B'){//Validate backward placement of word 
          if((targetWord.charAt(targetWord.length()-1-letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
            return false;
          }
          return validateOverlap(2,'B',letterIndex+1,targetWord,columnNum+1,rowNum-1);
        }//End of validating backward placement of word 
        
      }//End of validating negative slope 
      
      
      else if (slopeNum == 1){//Validate positive slope
         if(forwardBackwards == 'A'){//Validate forward placement of word 
          if((targetWord.charAt(letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
            return false;
          }
          return validateOverlap(2,'A',letterIndex+1,targetWord,columnNum+1,rowNum+1);
        }//End of validating forward placement of word 
        
        if(forwardBackwards == 'B'){//Validate backward placement of word 
          if((targetWord.charAt(targetWord.length()-1-letterIndex) != puzzle[rowNum][columnNum]) && (puzzle[rowNum][columnNum]!=' ')){
            return false;
          }
          return validateOverlap(2,'B',letterIndex+1,targetWord,columnNum+1,rowNum-1);
        }//End of validating backward placement of word 
        
      }//End of validating positive slope 
      
      
    }//--------End of ValidateOverlap for the diagonal placement of the word---------//
    return false; 
  }//End of the method validateOverlap 
  
  public static void main(String[] args){
    SolutionFrame solutionFrame = new SolutionFrame("valid.txt",20,20);
    
  }
  

  
}//End of class 