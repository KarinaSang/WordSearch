/**
 * Date:Dec 21st 2016 
 * Name: GeneratorInputFrame
 * Description: 
 * This input frame consists of two parts:
 * Part one enables the user to enter a plain word text file, and validates whether the textfile is usable or not 
 * Part two enables the user to specify the puzzle dimensions
 */

import java.util.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;//For the action listener 
import java.io.*;

public class SolverInputFrame extends JFrame implements ActionListener{
  static String fileName;//Used to store the file name the user enters 
  JPanel instructionPanel;
  JPanel inputWordPanel;
  JPanel inputPuzzlePanel;
  JLabel title;//This label indicates that this is the input file of the application 
  JLabel instructionOne;
  JLabel instructionTwo;
  JLabel instructionThree;
  JLabel instructionFour;
  JLabel instructionFive;
  JLabel instructionSix;
  JLabel enterFileName;
  JTextField enterFileHere;
  JButton okButton;
  JLabel outputMessage;
  JLabel blank;
  JLabel blankTwo;
  JLabel blankThree;
  JLabel blankFour;
  JLabel enterRowNum;
  JLabel enterColumnNum;
  JTextField rowNum;
  JTextField columnNum;
  JButton solvePuzzle;
  JLabel puzzleFileLabel;
  JTextField puzzleFileText;
  
  public SolverInputFrame(){
    
    title = new JLabel("Input Word File",JLabel.CENTER);
    title.setFont(new Font("Times New Roman",Font.PLAIN, 26));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    instructionOne = new JLabel("            *Please do not enter an empty file");
    instructionOne.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionTwo = new JLabel("            *Please ensure that all of your words are in lower case");
    instructionTwo.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionThree = new JLabel("            *Please ensure that all of your words are between 4 to 8 letters");
    instructionThree.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionFour = new JLabel("            *Please ensure that your word file contains no more than 10 words");
    instructionFour.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionFive = new JLabel("            *Please enter a puzzle file and press solvePuzzle to solve the word puzzle");
    instructionFive.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionSix = new JLabel("            *The solved puzzle will be displayed via HTML");
    instructionSix.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
    enterFileName = new JLabel("Enter File Name:");
    enterFileName.setFont(new Font("Times New Roman",Font.PLAIN,15));
    enterFileHere = new JTextField("Enter File Name Here");
    enterFileHere.setFont(new Font("Times New Roman",Font.PLAIN,14));

    okButton = new JButton("OK");
    okButton.addActionListener(this);//Implement action listener 
    okButton.setBackground(Color.GREEN);
    okButton.setOpaque(true);
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    outputMessage = new JLabel("        ");
    outputMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    outputMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    puzzleFileLabel = new JLabel ("Please enter the name of the puzzle file");
    puzzleFileLabel.setFont(new Font ("Times New Roman", Font.PLAIN, 14));
    puzzleFileText = new JTextField("Enter puzzle filename here");
    puzzleFileText.setFont(new Font ("Times New Roman", Font.PLAIN, 14));
    
    inputPuzzlePanel = new JPanel();
    GridLayout inputPuzzleLayout = new GridLayout(2,1);
    inputPuzzlePanel.add(puzzleFileLabel);
    inputPuzzlePanel.add(puzzleFileText);
    
    solvePuzzle = new JButton("Solve Puzzle");
    solvePuzzle.addActionListener(this);//Implement action listener 
    solvePuzzle.setBackground(Color.GREEN);
    solvePuzzle.setOpaque(true);
    solvePuzzle.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    blank = new JLabel("                                    ");
    blankTwo = new JLabel("                                    ");
    blankThree = new JLabel("                                    ");
    blankFour = new JLabel("                                    ");
    
    setLayout(new BoxLayout( getContentPane(),BoxLayout.PAGE_AXIS));
    GridLayout gridLayout = new GridLayout(5,1);
    FlowLayout flowLayout = new FlowLayout();
    
    instructionPanel = new JPanel();
    instructionPanel.setLayout(gridLayout);
    instructionPanel.add(instructionOne);
    instructionPanel.add(instructionTwo);
    instructionPanel.add(instructionThree);
    instructionPanel.add(instructionFour);
    
    inputWordPanel = new JPanel();
    inputWordPanel.setLayout(flowLayout);
    inputWordPanel.add(enterFileName);
    inputWordPanel.add(enterFileHere);
    inputWordPanel.add(outputMessage);
    
    add(title);
    add(blank);
    add(blankTwo);
    add(instructionPanel);
    add(inputWordPanel);
    add(okButton);
    
    //-------------Set The Frame------------//
    setTitle("Word Search Solver");
    setSize(650,300);
    setVisible(true);
   
  }//End of Constructor 
  
  public void actionPerformed(ActionEvent event){
    String action = event.getActionCommand();
    
    if(action.equals("OK")){
      try{
        if(validateFile() == true){
          title.setText("Input Puzzle File");
          add(blankThree);
          remove(inputWordPanel);
          remove(okButton);
          validate();
          repaint();
          instructionPanel.removeAll();
          instructionPanel.revalidate();
          instructionPanel.repaint();
          
          instructionPanel.add(instructionFive);
          instructionPanel.add(blankFour);
          instructionPanel.add(instructionSix);
          add(inputPuzzlePanel);
          add(solvePuzzle);

        }
        else{
           enterFileHere.setText("Invalid! Please re-renter!");
        }
      }catch(IOException ex){
        
      }
      
      if(enterFileHere.getText().equals("Enter File Name Here") || enterFileHere.getText().equals(" ")){
       outputMessage.setText("Please enter a file"); 
      }
      
    }//End of pressing the OK button 
    
    if(action.equals("Solve Puzzle")){//If the user presses the createSolutionButton
     //WordSearchSolver mySolver = new WordSearchSolver();
     
    }//End of pressing create solution

  }//End of the method actionPerformed 
  
  public boolean validateFile() throws IOException{
    ArrayList <String> words = new ArrayList <String> ();//Array List to store the words from the word text file 
    String wordFileName = enterFileHere.getText();
    File wordFile = new File (wordFileName);
    boolean invalidFile = true;
    
    Scanner fileReader = new Scanner (wordFile);
    String currentWord;
    
    while (fileReader.hasNext()){
      invalidFile = false;
      currentWord=fileReader.nextLine();
      
      if(currentWord.length() < 4 || currentWord.length() > 8){//If the words are shorter than 4 letters or longer than 8 letters 
        System.out.println("Your words cannot be shorter than 4 letters or longer than 8 letters");
        invalidFile = true;
        outputMessage.setText("Your words cannot be shorter than 4 letters or longer than 8 letters");
        return false;
      }
      for(int i=0;i<currentWord.length();i++){
        if(Character.isUpperCase(currentWord.charAt(i)) == true ){
          invalidFile = true;
          outputMessage.setText("Your words cannot contain any uppercase letters. All letters must be of lowercase.");
          return false;
        }
      }
      if(invalidFile == false){  
        words.add(currentWord);
      }
    }//End of reading the all the words in file 
    
    if(words.size() >= 10){//If there are more than 10 words in the file 
      outputMessage.setText("Your file cannot contain more than 10 words. Please re-enter file.");
      return false;
    }
    else if (words.size() == 0 ){
      outputMessage.setText("Your file cannot be an empty file");
      return false;
    }
    
    fileReader.close(); 
    
    return true;
  }//End of the method validateFile
    
  public static void main(String[] args){
   SolverInputFrame inputFrame = new SolverInputFrame(); 
  }
  
}//End of class 