/***************************************************************
* file: ColorGame.java
* author: Bad and Boujee
* class: CS 245 – GUI Development
*
* assignment: program 1.1
* date last modified: 2/8/17
*
* purpose: Launches game application
*
****************************************************************/

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import my.HangmanUI.*;

public class Hangman extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        IntroScreen intro = new IntroScreen();
        MainScreen mainScreen = new MainScreen();
        
        intro.setVisible(true);
        
        int transitionDelay = 3000;
        
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                intro.setVisible(false);
                mainScreen.setVisible(true);
            }
        };
              
        Timer timer = new Timer(transitionDelay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
        
        Thread.sleep(5000);
    }
}