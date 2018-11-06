/**
 *
 * @author Bad and Boujee
 */

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import my.HangmanUI.*;

public class Hangman extends JFrame implements KeyListener{

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
                mainScreen.addKeyListener(new MainScreen());
            }
        };
              
        Timer timer = new Timer(transitionDelay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
        
        Thread.sleep(5000);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if(keyCode == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    