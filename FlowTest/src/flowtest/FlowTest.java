/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowtest;

import javax.swing.*;
import java.awt.*;



/**
 *
 * @author Station 4
 */
public class FlowTest {
    //Initialize array
    private int sudoGrid[] = new int [87];

    /**
     * @param args the command line arguments
     */
    
    public static void createAndShowGUI(){
        JFrame frame = new JFrame("FlowTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel1 panel1 = new JPanel1();
        
        frame.add(panel1);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    createAndShowGUI();
                }
        });
     
        // TODO code application logic here
    }
    
}
