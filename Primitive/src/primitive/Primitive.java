package primitive;

/***************************************************************
* file: Primitive.java
* author: Kyle Turchik
* class: CS 445 - Computer Graphics
*
* assignment: program 1
* date last modified: 10/6/2016
*
* purpose: This program reads in instructions and coordinates from 
* a file and draws primitive shapes based on that information.
*
****************************************************************/ 

//import .*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;

public class Primitive {

//method: start
//purpose: This method initializes the canvas and the GL settings
    public void start() {
        try {
            createWindow();
            initGL();
            
            //render
            File file = new File("src/primitive/coordinates.txt");
            readFile(file);
            
            Display.update();
            Display.sync(60);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method: createWindow
    //purpose: 
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Kyle Turchik - Project 1");
        Display.create();
    }
 
    //method: initGL
    //purpose: 
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    //method: drawLine
    //purpose: 
    private void drawLine(float x, float y, float x2, float y2) {
            try{
                glColor3f(1.0f,0.0f,0.0f);
                glLineWidth(1);
                glBegin(GL_LINES);
                glVertex2f (x, y);
                glVertex2f (x2, y2);
                glEnd();
                Display.update();
                Display.sync(60);
            } catch(Exception e){
            }
    }
  
    //method: drawCircle
    //purpose: 
    private void drawCircle(float x, float y, float rad) {
            try{
                glColor3f(0.0f,0.0f,1.0f);
                glLineWidth(1);
                
                int i;
                int lineAmount = 360; 

                float twicePi = 2.0f * (float) Math.PI;

                glBegin(GL_LINE_LOOP);
                    for(i = 0; i <= lineAmount;i++) { 
                        glVertex2f(
                            x + (rad * (float) Math.cos(i *  twicePi / lineAmount)), 
                            y + (rad * (float) Math.sin(i * twicePi / lineAmount))
                        );
                }
                glEnd();
                Display.update();
                Display.sync(60);
            } catch(Exception e){
        }
    }

    //method: drawEllipse
    //purpose:            
    private void drawEllipse(float x, float y, float radx, float rady) {
            try{
                glColor3f(0.0f,1.0f,0.0f);
                glLineWidth(1);
                
                int i;
                int lineAmount = 360; 

                float twicePi = 2.0f * (float) Math.PI;

                glBegin(GL_LINE_LOOP);
                    for(i = 0; i <= lineAmount;i++) { 
                        glVertex2f(
                            x + (radx * (float) Math.cos(i *  twicePi / lineAmount)), 
                            y + (rady * (float) Math.sin(i * twicePi / lineAmount))
                        );
                }
                glEnd();
                Display.update();
                Display.sync(60);
            } catch(Exception e){
        }
    }
 
    //method: readFile
    //purpose:   
    private void readFile(File file) throws IOException {
            Scanner reader = new Scanner(file);
            String[] first = new String[2];
            String[] second = new String[2];
            while (reader.hasNext()) {
                String type = reader.next();
                System.out.print("type: " + type);
                first = reader.next().split(",");
                switch (type) {
                    case "l":
                        second = reader.next().split(",");
                        drawLine(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(second[0]), Integer.parseInt(second[1]));
                        break;
                    case "c":
                        drawCircle(Integer.parseInt(first[0]), Integer.parseInt(first[1]), reader.nextInt());
                        break;
                    case "e":
                        second = reader.next().split(",");
                        drawEllipse(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(second[0]), Integer.parseInt(second[1]));
                        break;
                }
            }
    }
 
    //method: main
    //purpose:
    public static void main(String[] args) throws IOException {
        Primitive primitive = new Primitive();
        primitive.start();
    }
}
