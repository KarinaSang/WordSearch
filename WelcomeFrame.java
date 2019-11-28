/**
 * Date:Dec 21st 2016 
 * Name: Welcome Frame 
 * Description: 
 * This is the welcome frame 
 * It explains the basics of the program and allows the user to enter the application 
 */

import java.util.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;//For the action listener 
import java.io.*;

public class WelcomeFrame extends JFrame implements ActionListener{
  JPanel welcomePanel;//Contains the application title and the welcome label 
  JPanel instructionPanel;//Contains the instructions that explain how the program works 
  JLabel blank;//Blank lable for spacing and formatting 
  JLabel blankTwo;
  JLabel appTitle;//The title of the application 
  JLabel welcome;//Welcoming words 
  JLabel instructionOne;
  JLabel instructionTwo;
  JLabel instructionThree;
  JLabel instructionFour;
  JLabel instructionFive;
  JLabel instructionSix;
  JLabel instructionSeven;
  JLabel instructionEight;
  JButton enter;//Allows the user to enter the input frame 
  
  public WelcomeFrame(){
    welcomePanel = new JPanel();
    appTitle = new JLabel("Word Search Generator", JLabel.CENTER);
    appTitle.setFont(new Font("Times New Roman", Font.BOLD, 36));
    welcome = new JLabel("Welcome to the word puzzle generating application!", JLabel.CENTER);
    welcome.setFont(new Font("Times New Roman",Font.PLAIN,18));
    welcome.setForeground(Color.blue);
    
    blank = new JLabel("                                                                   ");
    blankTwo = new JLabel("                                                                   ");
    
    instructionPanel = new JPanel();
    instructionOne = new JLabel("  *This application will enable you to create a word search puzzle",JLabel.LEFT);
    instructionOne.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionTwo = new JLabel("  *Firstly, you will be prompted for a plain text file word list and the dimensions of the puzzle", JLabel.LEFT);
    instructionTwo.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionThree = new JLabel("  *Based on the word list, the program will randomly generate a solution where your words will be", JLabel.LEFT);
    instructionThree.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionFour = new JLabel("   placed vertically, horizontally or diagonally and forwards or backwards",JLabel.LEFT);
    instructionFour.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionFive = new JLabel("  *After viewing the solution you may choose to save it and proceed to generate the final file or ",JLabel.LEFT);
    instructionFive.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionSix =  new JLabel("  regenerate the solution.", JLabel.LEFT);
    instructionSix.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionSeven = new JLabel("  *After the puzzle is generated, you will be asked to input a file name to save it", JLabel.LEFT);
    instructionSeven.setFont(new Font("Times New Roman",Font.PLAIN,14));
    instructionEight = new JLabel("  *Finally, you may choose to quit this applicatio or create another word search puzzle", JLabel.LEFT);
    instructionEight.setFont(new Font("Times New Roman",Font.PLAIN,14));
    
    enter = new JButton ("ENTER");
    enter.addActionListener(this);//Implement action listener 
    enter.setBackground(Color.GREEN);
    enter.setOpaque(true);
    enter.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    setLayout(new BoxLayout( getContentPane(),BoxLayout.PAGE_AXIS));
    GridLayout gridLayout = new GridLayout(8,1);
    FlowLayout flowLayout = new FlowLayout();
   
    welcomePanel.setLayout(flowLayout);
    welcomePanel.add(appTitle);
    welcomePanel.add(welcome);
    
    instructionPanel.setLayout(gridLayout);
    instructionPanel.add(instructionOne);
    instructionPanel.add(instructionTwo);
    instructionPanel.add(instructionThree);
    instructionPanel.add(instructionFour);
    instructionPanel.add(instructionFive);
    instructionPanel.add(instructionSix);
    instructionPanel.add(instructionSeven);
    instructionPanel.add(instructionEight);
    add(welcomePanel);
    add(instructionPanel);
    add(blank);
    add(blankTwo);
    add(enter);
    
    
    setTitle("Word Search Generator");
    setSize(650,400);
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent event){
    String action = event.getActionCommand();
    
    if(action.equals("ENTER")){
      dispose();
      InputFrame inputFrame = new InputFrame();
    }
  }//End of action Performed 
  
  public static void main(String[] args){
    
   WelcomeFrame myFrame = new WelcomeFrame();
    
  }
  

  
}//End of class 