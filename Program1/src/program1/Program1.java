package program1;

import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Program1 {
    public void start() {
        try {
            createWindow();
            initGL();
            
            File file = new File("src/program1/coordinates.txt");
            
            while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){    
                readFile(file);
            }
            
            Display.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readFile(File file) throws FileNotFoundException{
        Scanner reader = new Scanner(file);
        
        while(reader.hasNext()){
            String shape = reader.next();
            String first[] = new String[2];
            String second[] = new String[2];
            
            first = reader.next().split(",");
            
            switch(shape){
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
    
    public static void drawLine(int x1, int y1, int x2, int y2){
        glColor3f(1.0f, 0.0f, 0.0f);
        glPointSize(1);
        
        glBegin(GL_POINTS);
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int d = Math.abs((2 * dy) - dx);
            int right = 2 * dy;
            int upRight = 2 * (dy - dx);
            
            int ix = x1 < x2 ? 1 : -1;
            int iy = y1 < y2 ? 1 : -1;
            
            while(x1 != x2){
                glVertex2f(x1, y1);
                
                if(d > 0){
                    d += upRight;
                    x1 += ix;
                    y1 += iy;
                }else{
                    d += right;
                    x1 += ix;
                }
            }
        glEnd();
        Display.update();
        Display.sync(70);
    }
    
    public static void drawCircle(int x, int y, int rad){
        try{    
            glColor3f(0.0f, 0.0f, 1.0f);
            glLineWidth(1);

            int line = 360;
            float doublePI = (float) Math.PI * 2.0f;

            glBegin(GL_LINE_LOOP);
                for(int i = 0; i <= line; ++i){
                    glVertex2f(x + (rad * (float) Math.cos(i * doublePI / line)), y + (rad * (float) Math.sin(i * doublePI / line)));
                }
            glEnd();

            Display.update();
            Display.sync(70);
        }catch(Exception e){
        }
    }
    
    public static void drawEllipse(int x, int y, int xrad, int yrad){
        try{    
            glColor3f(0.0f, 1.0f, 0.0f);
            glLineWidth(1);

            int line = 360;
            float doublePI = (float) Math.PI * 2.0f;

            glBegin(GL_LINE_LOOP);
                for(int i = 0; i <= line; ++i){
                    glVertex2f(x + (xrad * (float) Math.cos(i * doublePI / line)), y + (yrad * (float) Math.sin(i * doublePI / line)));
                }
            glEnd();

            Display.update();
            Display.sync(70);
        }catch(Exception e){
        }
    }

    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Marco Roman Graphics Program 1");
        Display.create();
    }

    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }

    public static void main(String[] args) {
        Program1 basic = new Program1();
        basic.start();
    }
}