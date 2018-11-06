package Test;

/**
 * *************************************************************
 * file: Project1.java
 * author: Vu Dao
 * class: CS445.01 – Computer Graphics
 *
 * assignment: Project 1
 * date last modified: 10/6/2016
 *
 * purpose:
 *
 ***************************************************************
 */
//package project1;

//import .*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Galagale
 */
//C:\\Users\\Galagale\\Desktop\\Project1\\src\\project1\\
public class Project1 {

    static int sizee = 1;
    static int[] colors = {1, 0, 0};
    ArrayList<String> thing = new ArrayList<String>();

    // Method:  start
    // Purpose: reads in files and call the corresponding render method
    public void start() {
        try {
            createWindow();
            initGL();
//            Display.update();
            FileReader newFile = new FileReader("C:\\Users\\Galagale\\Desktop\\Project1\\src\\project1\\coordinates.txt");
            Scanner reader = new Scanner(newFile);
            String y;
            String[] first = new String[2];
            String[] second = new String[2];
            String type = "k";
            int i = 0,temp;

            boolean damnit = true;
            while (reader.hasNext()) {
                thing.add(reader.next());
            }
            while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {

                if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    sizee++;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    temp = colors[1];
                    colors[0]+= colors[2];
                    colors[1]+= colors[0];
                    colors[2]+= temp;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && sizee > 1) {
                    sizee--;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
                    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                }
                if (i >= thing.size() && damnit == true) {
                    System.out.println("hqdsait");
                    damnit = false;
                    Display.sync(60);
                }
                if (i >= thing.size()) {
                    Display.update();
                }
                if (i == thing.size()) {
                    i = 0;
                }
//                TimeUnit.SECONDS.sleep(1);
                type = (String) thing.get(i);
//                System.out.println(((String) thing.get(i)));
                i++;
//                System.out.println(((String) thing.get(i)));
                first = ((String) thing.get(i)).split(",");
//                System.out.print("this : " + first[1]);
                i++;
                switch (type) {
                    case "l":
                        second = ((String) thing.get(i)).split(",");
                        i++;
                        renderLine(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(second[0]), Integer.parseInt(second[1]));
                        break;
                    case "c":

                        String x = (String) thing.get(i);
                        i++;
                        renderCircle(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(x));
                        break;
                    case "e":
                        second = ((String) thing.get(i)).split(",");
                        i++;
                        renderellipse(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(second[0]), Integer.parseInt(second[1]));
                        break;
                }

//                Display.update();
//                Display.sync(60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method:  initGL
    // Purpose: Creates the window 
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
    }

    // Method: renderLine
    // Purpose: Takes in 2 sets of coordinates and draws a line between the two
    private void renderLine(int x1, int y1, int x2, int y2) {
        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        float dx = x2 - x1;
        float dy = y2 - y1;
        float dy2 = Math.abs(y2 - y1);
        float d, side, diag;
        if (dx == 0) {
            d = 1;
            side = 1;
            diag = 1;
        } else if (Math.abs(dy / dx) < 1) {
            d = 2 * (dy - dx);
            side = 2 * dy;
            diag = 2 * (dy - dx);
            //     System.out.println("hor");
        } else {
            d = 2 * (dx - dy);
            side = 2 * dx;
            diag = 2 * (dx - dy);
        }

        while (x1 <= x2) {
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                System.exit(0);
            }
            try {
//                glClear(GL_COLOR_BUFFER_BIT
//                        | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                glColor3f(colors[0], colors[1], colors[2]);
                glPointSize(sizee);

                glBegin(GL_POINTS);
                glVertex2f(x2, y2);
                glVertex2f(x1, y1);
                if (dx == 0) {
                    if (y2 > y1) {
                        y1++;
                    }
                    if (y2 < y1) {
                        y1--;
                    }
                    dy2--;
                    if (dy2 == 0) {
                        break;
                    }
                } else if (Math.abs(dy / dx) < 1) {
                    if (d >= 0) {
                        x1++;
                        if (dy / dx >= 0) {
                            y1++;
                        } else {
                            y1--;
                        }
                        d += diag;
                    } else {
                        x1++;
                        d += side;
                    }
                } else if (Math.abs(dy / dx) >= 1) { //////////////////////////////////////////////
                    if (d >= 0) {
                        x1++;
                        if (dy / dx >= 0) {
                            y1++;
                        } else {
                            y1--;
                        }
                        d += diag;
                    } else {
                        y1++;
                        d += side;
                    }
                }
                glEnd();
//                Display.update();
//                Display.sync(60);
            } catch (Exception e) {
            }
        }

        //   Display.destroy();
    }
    // Method: renderCircle
    // Purpose: Takes in one pair of coordinates and a radius to draw a circle with that radius centered around the point

    private void renderCircle(int x, int y, int rad) {

        float x1 = x;
        float y1 = y;
        float circle = 0;
        try {
//            glClear(GL_COLOR_BUFFER_BIT
//                    | GL_DEPTH_BUFFER_BIT);
            glBegin(GL_LINE_LOOP);
            while (circle <= 2 * Math.PI) {
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    System.exit(0);
                }
                glLoadIdentity();
                glColor3f(colors[1], colors[2], colors[0]);
                glPointSize(sizee);
                glBegin(GL_POINTS);
                x1 = (float) (x + (rad * Math.cos(circle)));
                y1 = (float) (y + (rad * Math.sin(circle)));
                glVertex2f(x1, y1);
                circle += Math.PI / 360;
                glEnd();
//                Display.update();
//                Display.sync(60);
            }

        } catch (Exception e) {
        }
    }
    // Method: renderCircle
    // Purpose: Takes in on pair of coordinates and a pair of radii to draw an ellipse
    // with each radii correlating to the x and y of the ellipse

    private void renderellipse(int x, int y, int radH, int radV) {
        x += radH;
        float x1 = x;
        float y1 = y;
        float circle = 0;
        try {
//            glClear(GL_COLOR_BUFFER_BIT
//                    | GL_DEPTH_BUFFER_BIT);

            while (circle <= 2 * Math.PI) {
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    System.exit(0);
                }
                glLoadIdentity();
                glColor3f(colors[2], colors[0], colors[1]);
                glPointSize(sizee);

                glBegin(GL_POINTS);
                //  System.out.println(x1 + "  ,  " + y1);
                glVertex2f(x1, y1);
                x1 = (float) (x - radH + (radH * Math.cos(circle)));
                y1 = (float) (y + (radV * Math.sin(circle)));
                circle += Math.PI / 180;
                glEnd();
//                Display.update();
//                Display.sync(60);
            }

        } catch (Exception e) {
        }
    }

    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Project 1");
        Display.create();
    }
    // Method: main
    // Purpose: simple driver method to start the program. calls start()

    public static void main(String[] args) {
        // TODO code application logic here\
        Project1 proc = new Project1();
        proc.start();
    }
}
