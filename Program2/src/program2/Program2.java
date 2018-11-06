package program2;

import java.util.Scanner;
import java.io.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Program2 {
    
    public void start(){
        try{
            createWindow();
            initGL();
            
            File file = new File("src/program2/coordinates.txt");
            
            while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                readFile(file);
            }
            
            Display.destroy();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void readFile(File file){
        
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
}
