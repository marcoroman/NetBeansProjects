/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.FileNotFoundException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Galagale
 */
public class Project2 {

    ArrayList holder = new ArrayList();
    static int count = 0;

    private void start() throws Exception {
        createWindow();
        initGL();

        String type, set[];
        FileReader newFile;
        Scanner reader = null;
        try {
            newFile = new FileReader("src//project2//coordinates.txt");
            reader = new Scanner(newFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
        }
        String y;
        while (reader.hasNext()) {
            holder.add(reader.nextLine());
        }
        //!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)
        while (count < holder.size()) {
            type = (String) holder.get(count);
            System.out.println("outside : " + type);
            count++;
            set = type.split(" ");
            if (set[0].equals("P")) {
                makePoly(set);
            }
        }

        System.out.println("End : Count = 0");
        count = 0;
        while (true) {
        }
    }

    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Project 1");
        Display.create();
    }

    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-320,320 , -240, 240, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT,
                GL_NICEST);
    }

    private void makePoly(String[] set) {
        polygon thing1 = new polygon();
        String type = (String) holder.get(count);
        thing1.colors[0] = Float.parseFloat(set[1]);
        thing1.colors[1] = Float.parseFloat(set[2]);
        thing1.colors[2] = Float.parseFloat(set[3]);
        while (count < holder.size()) {
            type = (String) holder.get(count);
            set = (type.split(" "));
            count++;
            if (set[0].equals("T") == false) {
                System.out.println("inside " + type);
                thing1.coor.add(Integer.parseInt(set[0]));
                thing1.coor.add(Integer.parseInt(set[1]));
            } else {
                drawPoly(thing1);

                System.out.println("YUOH");
                transformPoly();
            }
        }
    }

    private void transformPoly() {
        String[] set;
        String type;

        System.out.println("TRANSFORMING");
//        = (String) holder.get(count);
//        set = (type.split(" "));
//        count++;
        while (count < holder.size()) {
            type = (String) holder.get(count);
            System.out.println("inside2 " + type);
            set = (type.split(" "));
            count++;
            if (set[0].equals("t")) {

            } else if (set[0].equals("r")) {

            } else if ((set[0].equals("s"))) {

            } else if ((set[0].equals("P"))) {
                makePoly(set);
            }
        }
    }

    // Method: main
    // Purpose: simple driver method to start the program. calls start()
    public static void main(String[] args) throws Exception {
        Project2 proc = new Project2();
        proc.start();
    }

    private void printE(edges[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println("Printing : " + x[i].ymin + "  " + x[i].ymax + "  " + x[i].xval + "  " + x[i].m);
        }
        System.out.println();
    }

    private edges[] sortEdges(edges[] sortE) {
        edges[] ret = new edges[sortE.length];
//        printE(sortE);  
        System.out.println("sorting ");
        edges temp = new edges();
        int counter = 0;
        for (int j = 0; j < sortE.length; j++) {
            for (int i = j + 1; i < sortE.length; i++) {
//            System.out.println("sorting " + sortE[i].ymin + "  " + sortE[i].ymax + "  " + sortE[i].xval + "  " + sortE[i].m);
                if (sortE[j].ymin > sortE[i].ymin) {
//                    System.out.println("swapping : " + i + " with " +j);
                    temp = sortE[i];
                    sortE[i] = sortE[j];
                    sortE[j] = temp;;
                }
                if (sortE[j].ymin == sortE[i].ymin) {
                    if (sortE[j].xval > sortE[i].xval) {
//                        System.out.println("swapping : " + i + " with " +j);
                        temp = sortE[i];
                        sortE[i] = sortE[j];
                        sortE[j] = temp;
                    }
                }
//                temp = sortE[i];
//                sortE[i] = sortE[j];
//                sortE[j] = temp;

            }
//            printE(sortE);
            ret[counter] = sortE[j];
//            System.out.println("sorted " + ret[i].ymin + "  " + ret[i].ymax + "  " + ret[i].xval + "  " + ret[i].m);
            counter++;
        }
        return ret;
    }

    private edges[] sortEdgesX(edges[] sortE) {
        edges[] ret = new edges[sortE.length];
//        printE(sortE);  
        System.out.println("sorting ");
        edges temp = new edges();
        int counter = 0;
        for (int j = 0; j < sortE.length; j++) {
            for (int i = j + 1; i < sortE.length; i++) {

                if (sortE[j].xval > sortE[i].xval) {
//                        System.out.println("swapping : " + i + " with " +j);
                    temp = sortE[i];
                    sortE[i] = sortE[j];
                    sortE[j] = temp;
                }

//                temp = sortE[i];
//                sortE[i] = sortE[j];
//                sortE[j] = temp;
            }
//            printE(sortE);
            ret[counter] = sortE[j];
//            System.out.println("sorted " + ret[i].ymin + "  " + ret[i].ymax + "  " + ret[i].xval + "  " + ret[i].m);
            counter++;
        }
        return ret;
    }

    private void drawPoly(polygon thing1) {
        int[] xList = new int[thing1.coor.size() / 2];
        int[] yList = new int[thing1.coor.size() / 2];
        edges[] all_edges = new edges[xList.length];
        for (int i = 0; i < (thing1.coor.size()) / 2; i++) {
            all_edges[i] = new edges();
            xList[i] = (int) thing1.coor.get(2 * i);
            yList[i] = (int) thing1.coor.get((2 * i) + 1);
        }
        for (int i = 0; i < xList.length; i++) {
//            System.out.print("x values : " + xList[i]);
//            System.out.println("      y values : " + xList[i]);
            if (i == xList.length - 1) {
                if (yList[i] >= yList[0]) {
                    all_edges[i].ymax = yList[i];
                    all_edges[i].xval = xList[i];
                    all_edges[i].ymin = yList[i];
                } else {
                    all_edges[i].ymax = yList[i + 1];
                    all_edges[i].xval = xList[i];
                    all_edges[i].ymin = yList[i];
                }

            } else if (yList[i] >= yList[i + 1]) {
                all_edges[i].ymax = yList[i];
                all_edges[i].xval = xList[i + 1];
                all_edges[i].ymin = yList[i + 1];
            } else {
//                    System.out.println(all_edges[i] + "      y values : " + yList[i + 1]);
                all_edges[i].ymax = yList[i + 1];
                all_edges[i].xval = xList[i];
                all_edges[i].ymin = yList[i];
            }
            if (i == xList.length - 1) {
                if ((yList[0] > yList[i])) {
                    all_edges[i].m = (float) (xList[0] - xList[i]) / (yList[0] - yList[i]);
                } else if ((yList[0] < yList[i])) {
                    all_edges[i].m = (float) (xList[i] - xList[0]) / (yList[i] - yList[0]);
                } else {
                    all_edges[i].m = -9999;
                }
            } else if ((yList[i + 1] > yList[i])) {
//                    System.out.println((yList[i + 1] - yList[i]));
//                    System.out.println((xList[i + 1] - xList[i]));
                all_edges[i].m = (float) (xList[i + 1] - xList[i]) / (yList[i + 1] - yList[i]);
            } else if ((yList[i + 1] < yList[i])) {
                all_edges[i].m = (float) (xList[i] - xList[i + 1]) / (yList[i] - yList[i + 1]);
            } else {
                all_edges[i].m = -9999;
            }
        }
        int counter = 0;
        for (int i = 0; i < all_edges.length; i++) {
            if (all_edges[i].m != -9999) {
                counter++;
            }
            System.out.println(all_edges[i].ymin + "  " + all_edges[i].ymax + "  " + all_edges[i].xval + "  " + all_edges[i].m);
        }
        edges[] global_edges = new edges[counter];
        int countx = 0;
        for (int i = 0; i < counter; i++) {
            global_edges[i] = new edges();
            if (all_edges[i].m != -9999) {
//                for (int j = 0; j < countx; j++) {
                global_edges[countx] = all_edges[i];
                countx++;
//                }
            }
//            System.out.println("global : " + global_edges[i].ymin + "  " + global_edges[i].ymax + "  " + global_edges[i].xval + "  " + global_edges[i].m);
        }
        global_edges = sortEdges(global_edges);
        printE(global_edges);

        double ysetter = global_edges[0].ymin;
        int activeC = 0, CC = 0;
        for (int i = 0; i < global_edges.length; i++) {
            if (global_edges[i].ymin == ysetter) {
                activeC++;
            }
        }
        edges[] active_edges = new edges[activeC];
        for (int i = 0; i < global_edges.length; i++) {
            if (global_edges[i].ymin == ysetter) {
                active_edges[CC] = new edges();
                active_edges[CC] = global_edges[i];
                CC++;
            }
        }
        printE(active_edges);
        drawEdges(active_edges, global_edges, thing1.colors);
    }

    private void drawEdges(edges[] active, edges[] global,float[] colors){

        double scanline = active[0].ymin;
//        while (true) {
        glLoadIdentity();
        glColor3f(colors[0], colors[1], colors[2]);
        glPointSize(1);
        int min = 0;
        while (active.length != 0) {
//            glBegin(GL_LINES);
            active = sortEdgesX(active);
            for (int i = 0; i < active.length; i++) {
                if (i % 2 == 0) {
                    glBegin(GL_LINES);
                    System.out.println(active[i].xval + " to " + active[i + 1].xval + "  inc  " + active[i].m);
                    glVertex2f((float) active[i].xval, (float) scanline);
                    glVertex2f((float) active[i + 1].xval, (float) scanline);
                    glEnd();
                }
                active[i].xval += active[i].m;
//                System.out.println("  inc  " + active[i].m);
                if (active[i].ymax == (scanline + 1)) {
                    System.out.println("subbing length");
                    min++;
                    active[i] = null;
                }

            }
            int max = 0;
            for (int k = 0; k < global.length; k++) {
                if (global[k].ymin == (scanline + 1)) {
                    System.out.println("adding length");
                    max++;
                }
            }

            edges[] transfer = new edges[active.length - min + max];
            System.out.println(active.length + " min : " + min + "  max  " + max);
            min = 0;
            int tran = 0;
            for (int k = 0; k < active.length; k++) {
                if (active[k] != null) {
                    transfer[tran] = active[k];
                    tran++;
                }
            }

            for (int k = 0; k < global.length; k++) {
                if (global[k].ymin == (scanline + 1)) {
                    transfer[tran] = global[k];
                    tran++;
                }
            }
            active = transfer;
            System.out.println("Scanline at " + scanline + " new active table ");
            printE(active);
//            glEnd();
            Display.update();
            Display.sync(60);

            scanline++;
        }
//        }

    }

    public class polygon {

        ArrayList coor = new ArrayList<Integer>();
        float[] colors = {0, 0, 0};
    }

    public class edges {

        double ymax = 1;
        double ymin = 1;
        double xval = 1;
        double m = 1.0;

        public void edges() {
            ymax = 1;
            ymin = 1;
            xval = 1;
            m = 1;
        }

    }
}
