package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    private static Container panel; // Contains the frame of the window mainly used of the calendar at the moment

    private InfoPanel infoPanel; // Shows the list of actions that user have made
    private Instructions instructions; // Shows information of how to use the app
    private static final JButton infoPoint = new JButton("Calendar"); // Button that transitions to the calendar of actions/explanation
    private CalendarForProject calendar; // The calendar of years/months/days that may or may not added actions to it
    private static final JButton timingPoint = new JButton("Set Time"); // Button that transitions to the timing of action (10 actions counts as 1)
    private static TimeSet timer; // Sets the time and actions of user's input
    private static final JButton actionPoint = new JButton("Make A Move"); // Button that transitions to the MAM (Make A Move)
    private MAM action; // Sets the time of each action that the user gave and the potions of each action that user gave
    private static final JButton exit = new JButton(new ImageIcon("src/Resources/x.png")); // Exits/closes the window
    private static final JButton goDown = new JButton(new ImageIcon("src/Resources/hide.png")); // Hides/minimises the window

    private static final JButton confirmOption = new JButton("CONFIRM");
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); // Gets the size of user's window
    //User's width/height window
    private final int windowWidth = size.width;
    private final int windowHeight = size.height;

    private static JPanel boxOfNavigation = new JPanel(); // Box of the 3 buttons mentioned before
    private static JPanel boxOfWindowOp = new JPanel(); //Box of the 2 window buttons
//    private static JPanel panel3 = new JPanel();
    public Window(){
        //Window setting
        this.setTitle("Robotic Calendar");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Sets "info point" button
        infoPoint.setPreferredSize(new Dimension((windowWidth/6)-4,(windowHeight/10)-3));
        infoPoint.setFont(new Font("Arial",Font.BOLD, 26));
        infoPoint.setForeground(Color.white);
        infoPoint.setBackground(Color.blue);
        infoPoint.setFocusPainted(false);

        //Sets "timing point" button
        timingPoint.setPreferredSize(new Dimension((windowWidth/6)-4,(windowHeight/10)-3));
        timingPoint.setFont(new Font("Arial",Font.BOLD, 26));
        timingPoint.setForeground(Color.white);
        timingPoint.setBackground(Color.blue);
        timingPoint.setFocusPainted(false);

        //Sets "action point" button
        actionPoint.setPreferredSize(new Dimension((windowWidth/6)-4,(windowHeight/10)-3));
        actionPoint.setFont(new Font("Arial",Font.BOLD, 26));
        actionPoint.setForeground(Color.white);
        actionPoint.setBackground(Color.blue);
        actionPoint.setEnabled(false);
        actionPoint.setFocusPainted(false);

        //Adds actions to the 3 buttons mentioned before
        infoPoint.addActionListener(this);
        timingPoint.addActionListener(this);
        actionPoint.addActionListener(this);

        //Sets "box of navigation" panel
        boxOfNavigation.setBackground(Color.gray);
        boxOfNavigation.setPreferredSize(new Dimension((windowWidth/2)-8,windowHeight/10));
        boxOfNavigation.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
        //Adds to the "box of navigation" panel the 3 buttons mentioned before
        boxOfNavigation.add(infoPoint);
        boxOfNavigation.add(timingPoint);
        boxOfNavigation.add(actionPoint);

        //Sets the button of "exit" that exits/closes the window and adds action to it
        exit.setPreferredSize(new Dimension((windowWidth/6)-4,(windowHeight/10)-3));
        exit.addActionListener(this);
        //Sets the button of "go down" that hides/minimises the window and adds action to it
        goDown.setPreferredSize(new Dimension((windowWidth/6)-4,(windowHeight/10)-3));
        goDown.addActionListener(this);

        //Sets the "box of window operations" and adds the 2 buttons mentioned before
        boxOfWindowOp.setBackground(Color.gray);
        boxOfWindowOp.setPreferredSize(new Dimension((windowWidth/2)-8,windowHeight/10));
        boxOfWindowOp.setLayout(new FlowLayout(FlowLayout.RIGHT,5,1));
        boxOfWindowOp.add(goDown);
        boxOfWindowOp.add(exit);

        //Sets the container
        panel = this.getContentPane(); //Get content pane
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        confirmOption.setPreferredSize(new Dimension((windowWidth/6)-4,(windowHeight/10)-3));
        confirmOption.addActionListener(this);

        //Adds/makes the operations
        this.add(boxOfNavigation);
        this.add(boxOfWindowOp);
        infoPanel = new InfoPanel(windowWidth,windowHeight);
        timer = new TimeSet(windowWidth,windowHeight,confirmOption);
        this.add(timer);
        this.calendar = new CalendarForProject(windowWidth,windowHeight);
        this.action =new MAM(windowWidth,windowHeight, timer.getActionToList());
        this.add(action);
        instructions = new Instructions(windowWidth,windowHeight);
        panel.add(calendar);
        panel.add(infoPanel);
        panel.add(instructions);




    }
    //Sets the visibility
    public void showWindow(){
        this.setVisible(true);

    }


    //Sets the actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exit){
            System.exit(0); // Faster exit than dispose
//            this.dispose();
        }
        if (e.getSource()==goDown){
            this.setState(JFrame.ICONIFIED); // Makes the window hide
        }
        timer.setVisible(e.getSource() == timingPoint);
        confirmOption.setVisible(e.getSource() == timingPoint);
        calendar.setVisible(e.getSource() == infoPoint);
        infoPanel.setVisible(e.getSource() == infoPoint);
        instructions.setVisible(e.getSource() == infoPoint);
        if (e.getSource() == confirmOption){
            this.remove(timer);
            timer=new TimeSet(windowWidth,windowHeight,confirmOption);
            this.add(timer);
            actionPoint.setEnabled(e.getSource() == confirmOption);
        }
        action.setVisible(e.getSource() == actionPoint);
    }
}
