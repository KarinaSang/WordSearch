/**
 * Date:Dec 21st 2016 
 * Name: Input Frame
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

public class InputFrame extends JFrame implements ActionListener{
  static String fileName;//Used to store the file name the user enters 
  JPanel instructionPanel;
  JPanel inputPanel;
  JLabel title;//This label indicates that this is the input file of the application 
  JLabel instructionOne;
  JLabel instructionTwo;
  JLabel instructionThree;
  JLabel instructionFour;
  JLabel instructionFive;
  JLabel dimensionInstructionOne;
  JLabel dimensionInstructionTwo;
  JLabel dimensionInstructionThree;
  JLabel dimensionInstructionFour;
  JLabel enterFileName;
  JTextField enterFileHere;
  JButton okButton;
  JLabel outputMessage;
  JLabel blank;
  JLabel blankTwo;
  JLabel enterRowNum;
  JLabel enterColumnNum;
  JTextField rowNum;
  JTextField columnNum;
  JButton createSolution;
  
  public InputFrame(){
    
    createSolution = new JButton("Create Solution");
    createSolution.addActionListener(this);//Implement action listener 
    createSolution.setBackground(Color.GREEN);
    createSolution.setOpaque(true);
    createSolution.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    dimensionInstructionOne = new JLabel("                            *Please specify the dimensions for your word puzzle.");
    dimensionInstructionOne.setFont(new Font("Times New Roman",Font.PLAIN,14));
    dimensionInstructionTwo = new JLabel("                            *Please note that the minimum value for both row and column is 10");
    dimensionInstructionTwo.setFont(new Font("Times New Roman",Font.PLAIN,14));
    dimensionInstructionThree = new JLabel("                            *The maximum value for both row and column is 20");
    dimensionInstructionThree.setFont(new Font("Times New Roman",Font.PLAIN,14));
    dimensionInstructionFour = new JLabel ("                            *Your value for rows and columns may or may not be the same");
    dimensionInstructionFour.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
    enterRowNum = new JLabel("Enter the number of rows:");
    enterRowNum.setFont(new Font("Times New Roman",Font.PLAIN,14));
    enterRowNum.setAlignmentX(Component.CENTER_ALIGNMENT);
    enterColumnNum = new JLabel("Enter the number of columns");
    enterColumnNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    enterColumnNum.setAlignmentX(Component.CENTER_ALIGNMENT);
    rowNum = new JTextField ("# of rows");
    rowNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    rowNum.setAlignmentX(Component.CENTER_ALIGNMENT);
    columnNum = new JTextField ("# of columns");
    columnNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    columnNum.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    title = new JLabel("Input File",JLabel.CENTER);
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
    
    blank = new JLabel("                                    ");
    
    setLayout(new BoxLayout( getContentPane(),BoxLayout.PAGE_AXIS));
    GridLayout gridLayout = new GridLayout(5,1);
    FlowLayout flowLayout = new FlowLayout();
    
    instructionPanel = new JPanel();
    instructionPanel.setLayout(gridLayout);
    instructionPanel.add(instructionOne);
    instructionPanel.add(instructionTwo);
    instructionPanel.add(instructionThree);
    instructionPanel.add(instructionFour);
    
    inputPanel = new JPanel();
    inputPanel.setLayout(flowLayout);
    inputPanel.add(enterFileName);
    inputPanel.add(enterFileHere);
    inputPanel.add(outputMessage);
    
    add(title);
    add(blank);
    add(instructionPanel);
    add(inputPanel);
    add(okButton);
    
    //-------------Set The Frame------------//
    setTitle("Word Search Generator");
    setSize(650,300);
    setVisible(true);
   
  }//End of Constructor 
  
  public void actionPerformed(ActionEvent event){
    String action = event.getActionCommand();
    
    if(action.equals("OK")){
      try{
        if(validateFile() == true){
          fileName = enterFileHere.getText();
          title.setText("Specify Dimensions");
          instructionPanel.removeAll();
          instructionPanel.revalidate();
          instructionPanel.repaint();
          instructionPanel.add(dimensionInstructionOne);
          instructionPanel.add(dimensionInstructionTwo);
          instructionPanel.add(dimensionInstructionThree);
          
          inputPanel.remove(enterFileName);
          inputPanel.remove(enterFileHere);
          inputPanel.remove(outputMessage);
          inputPanel.revalidate();
          inputPanel.repaint();
          inputPanel.add(enterRowNum);
          inputPanel.add(rowNum);
          inputPanel.add(enterColumnNum);
          inputPanel.add(columnNum);
          remove(okButton);
          validate();
          repaint();
          
          add(createSolution);

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
    
    if(action.equals("Create Solution")){//If the user presses the createSolutionButton
     int numOfRows = Integer.parseInt(rowNum.getText());
     int numOfColumns = Integer.parseInt(columnNum.getText());
     
     if(numOfRows >= 10 && numOfRows <= 20 && numOfColumns >=10 && numOfColumns <= 20){
      dispose();
      SolutionFrame solutionFrame = new SolutionFrame(fileName,numOfRows,numOfColumns); 
     }
     else if(numOfRows < 10 || numOfRows > 20){
      rowNum.setText("Invalid! Please re-enter"); 
     }
     else if(numOfColumns < 10 || numOfColumns >20){
      columnNum.setText("Invalid! Please re-enter"); 
     }
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
   InputFrame inputFrame = new InputFrame(); 
  }
  
}//End of class 