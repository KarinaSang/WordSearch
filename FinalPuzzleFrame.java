/**
 * Date:Jan 1st 2017 
 * Name: FinalPuzzleFrame 
 * Description: 
 * This is the FinalPuzzle frame 
 * It 
 */
import java.util.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;//For the action listener 
import java.io.*;

public class FinalPuzzleFrame extends JFrame implements ActionListener{
  
  static char[][] puzzle;
  static JButton[][] displayPuzzle;
  static JPanel puzzlePanel;
  static JLabel title;
  static JLabel outputMessage;
  static JLabel enterPuzzleFileName;
  static JTextField enterNameHere;
  static JButton okButton;
  static JButton restart;
  
  
  /**
   * Name:FinalPuzzleFrame
   * Parameters:ActionEvent event 
   * Return Type:void
   * Description:
   * 
   */
  public FinalPuzzleFrame(char[][] solution, int numOfRows, int numOfColumns){
    puzzle = new char[numOfRows][numOfColumns];
    displayPuzzle = new JButton[numOfRows][numOfColumns];
    for (int i = 0; i < puzzle.length; i++){
      for(int j = 0; j < puzzle[0].length; j++){
        puzzle[i][j] = solution[i][j];
      } 
    }
    
    title = new JLabel("Final Word Puzzle");
    title.setFont(new Font("Times New Roman",Font.PLAIN,26));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    outputMessage = new JLabel("Here is the final puzzle:");
    outputMessage.setFont(new Font("Times New Roman",Font.PLAIN,16));
    outputMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    enterPuzzleFileName = new JLabel("Enter Puzzle File Name:");
    enterPuzzleFileName.setFont(new Font("Times New Roman",Font.PLAIN,14));
    enterPuzzleFileName.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    enterNameHere = new JTextField("Enter File Name + Extension");
    enterNameHere.setFont(new Font("Times New Roman",Font.PLAIN,14));
    enterNameHere.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    okButton = new JButton("OK");
    okButton.addActionListener(this);//Implement action listener 
    okButton.setBackground(Color.GREEN);
    okButton.setOpaque(true);
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    restart = new JButton("Restart Application to Create Another Word Puzzle");
    restart.addActionListener(this);//Implement action listener 
    restart.setBackground(Color.ORANGE);
    restart.setOpaque(true);
    restart.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    setLayout(new BoxLayout( getContentPane(),BoxLayout.PAGE_AXIS));
    GridLayout gridLayout = new GridLayout (numOfRows,numOfColumns);//For the solution panel 
    puzzlePanel = new JPanel();
    puzzlePanel.setLayout(gridLayout);
    
    add(title);
    add(outputMessage);
    createFinalPuzzle(puzzle);
    add(puzzlePanel);
    add(enterPuzzleFileName);
    add(enterNameHere);
    add(okButton);
    add(restart);
    
    
    //-------------Set The Frame------------//
    setTitle("Word Search Generator");
    setSize(650,420);
    setVisible(true);

  }
  
  /**
   * Name:actionPerformed
   * Parameters:ActionEvent event 
   * Return Type:void
   * Description:
   * 
   */
  public void actionPerformed(ActionEvent event){
    String action = event.getActionCommand();
    
    if(action.equals("OK")){
      try{
      saveFinalPuzzle(puzzle);
      }catch(IOException ex){
        
      }
    }
    else if(action.equals("Restart Application to Create Another Word Puzzle")){
     dispose();
     WelcomeFrame welcome = new WelcomeFrame(); 
    }
    
  }
  
  public void createFinalPuzzle(char[][] puzzle){
    Random myRandom = new Random();
    
    for(int i = 0; i < puzzle.length; i++){
      for(int j = 0; j < puzzle[0].length; j++){
        if(puzzle[i][j] == ' '){
          char randomLetter = (char)(myRandom.nextInt(26)+'A');
          puzzle[i][j] = randomLetter;
        }
        displayPuzzle[i][j] = new JButton(Character.toString(puzzle[i][j]));
        puzzlePanel.add(displayPuzzle[i][j]);
      } 
    }
    
  }//End of the method createFinalPuzzle
  
  public void saveFinalPuzzle(char[][] puzzle) throws IOException{
    File myFile = new File(enterNameHere.getText());
    PrintWriter fileWriter = new PrintWriter(myFile);
    for(int i = 0; i < puzzle.length; i++){
      for(int j = 0; j < puzzle[0].length; j++){
        fileWriter.print(puzzle[i][j]); 
      }
      fileWriter.println("");
    }
    fileWriter.close();
    
  }//End of the method saveFinalPuzzle
  
  
  
}//End of the class 