/**
 * Date:Dec 21st 2016 
 * Name: SolverWelcomeFrame 
 * Description: 
 * This is the welcome frame 
 * It explains the basics of the program and allows the user to enter the application 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.*;

public class SolverWelcomeFrame extends JFrame implements ActionListener{
  
  JPanel welcomePanel;
  JPanel instructionPanel;
  JLabel blank;
  JLabel blankTwo;
  JLabel title; 
  JLabel welcome; 
  JLabel instructionOne;
  JLabel instructionTwo;
  JLabel instructionThree;
  JButton contiButton;
  
  public SolverWelcomeFrame (){
    setTitle ("Word Search Solver");
    setSize (650, 350);
    //setResizable(false);
    
    title = new JLabel ("Word Puzzle Solver");
    title.setFont (new Font ("Times New Roman", Font.PLAIN, 46));
    title.setForeground(Color.orange);
    
    welcome = new JLabel ("Welcome to Word Puzzle Solver!");
    welcome.setFont(new Font ("Times New Roman", Font.BOLD, 20));
    welcome.setForeground(Color.pink);
    welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    instructionOne = new JLabel ("            *This program takes in a words file and a puzzle file and solves the puzzle");
    instructionOne.setFont (new Font ("Times New Roman", Font.PLAIN, 14));
    instructionTwo = new JLabel ("            *The final solution will be generated in an HTML file");
    instructionTwo.setFont (new Font ("Times New Roman", Font.PLAIN, 14));
    instructionThree = new JLabel ("            *Click on 'continue' to go to the next page");
    instructionThree.setFont (new Font ("Times New Roman", Font.PLAIN, 14));
    
    contiButton = new JButton ("Continue");
    contiButton.setFont (new Font ("Times New Roman", Font.PLAIN, 14));
    //contiButton.setSize(60, 60);
    contiButton.addActionListener(this);
    contiButton.setBackground (Color.PINK);
    contiButton.setOpaque(true);
    contiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    welcomePanel = new JPanel();      
    instructionPanel = new JPanel();
    
    blank = new JLabel("                                                                   ");
    blankTwo = new JLabel("                                                                   ");
    
    setLayout(new BoxLayout( getContentPane(),BoxLayout.PAGE_AXIS));
    GridLayout gridLayout = new GridLayout(8,1);
    FlowLayout flowLayout = new FlowLayout();
    
    welcomePanel.setLayout(flowLayout);
    welcomePanel.add(title);
    welcomePanel.add(welcome);
    welcomePanel.add(blank);
    
    instructionPanel.setLayout(gridLayout);
    JLabel blankThree = new JLabel ("                         ");
    JLabel blankFour = new JLabel ("                         ");
    JLabel blankFive = new JLabel ("                         ");
    instructionPanel.add(blankThree);
    instructionPanel.add(instructionOne);
    instructionPanel.add(blankFour);
    instructionPanel.add(instructionTwo);
    instructionPanel.add(blankFive);
    instructionPanel.add(instructionThree);
    
    add(welcomePanel);
    add(instructionPanel);
    add(contiButton);
    add(blank);
    add(blankTwo);
    
    setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent event) {
    String action = event.getActionCommand();
    
    if(action.equals("Continue")){
      dispose();
      SolverInputFrame wordFrame = new SolverInputFrame();
    }
    
  }
  
  public static void main (String[] args){
    SolverWelcomeFrame welcomeFrame = new SolverWelcomeFrame ();
  }
}
