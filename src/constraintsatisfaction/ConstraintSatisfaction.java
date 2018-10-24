/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constraintsatisfaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class ConstraintSatisfaction {

    
    public ConstraintSet constraint = new ConstraintSet();
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String[][] board = import_maze("src/constraintsatisfaction/5x5maze.txt");
        solveMaze(board, 1);
        solveMaze(board, 2);
//        board = import_maze("src/constraintsatisfaction/7x7maze.txt");
//        solveMaze(board);
//        board = import_maze("src/constraintsatisfaction/8x8maze.txt");
//        solveMaze(board);
//        board = import_maze("src/constraintsatisfaction/9x9maze.txt");
//        solveMaze(board);
//        board = import_maze("src/constraintsatisfaction/10x10maze.txt");
//        solveMaze(board);
//        board = import_maze("src/constraintsatisfaction/12x12maze.txt");
//        solveMaze(board);
//        board = import_maze("src/constraintsatisfaction/14x14maze.txt");
//        solveMaze(board);

    }

    public static String[][] import_maze(String f) throws IOException {
        int boardX = 100;
        int boardY = 100;
        //Im too lazy to make a dynamically sized 2D array
        switch (f) {
            case "src/constraintsatisfaction/5x5maze.txt":
                boardX = 5;
                boardY = 5;
                break;
            case "src/constraintsatisfaction/7x7maze.txt":
                boardX = 7;
                boardY = 7;
                break;
            case "src/constraintsatisfaction/8x8maze.txt":
                boardX = 8;
                boardY = 8;
                break;
            case "src/constraintsatisfaction/9x9maze.txt":
                boardX = 9;
                boardY = 9;
                break;
            case "src/constraintsatisfaction/10x10maze.txt":
                boardX = 10;
                boardY = 10;
                break;
            case "src/constraintsatisfaction/12x12maze.txt":
                boardX = 12;
                boardY = 12;
                break;
            case "src/constraintsatisfaction/14x14maze.txt":
                boardX = 14;
                boardY = 14;
                break;
            default:
                System.out.println("Error: we do not support this maze!");
        }
        String[][] board = new String[boardY][boardX];

        try (BufferedReader b = new BufferedReader(new FileReader(f))) {
            int i = 0;
            while (i < boardY) {
                String line = b.readLine();
                char[] charArr = line.toCharArray();
                String[] boardArr = new String[line.length()];
                for (int q = 0; q < line.length(); q++) {
                    boardArr[q] = String.valueOf(charArr[q]);
                }
                board[i] = boardArr;
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return board;

    }
    
    //Choice is whether to do the smart way (forward checking) and 
    //dumb way (no forward checking)
    public static void solveMaze(String[][] board, int choice) {
        Node[][] nodeArr = setup(board);

        //If smart choice
        if (choice == 1) {
            System.out.println("smart");
            nodeArr = smartSolve(nodeArr);
            printBoard(nodeArr);
            
            //If dumb choice
        } else {
            System.out.println("dumb");
            dumbSolve(nodeArr);
            printBoard(nodeArr);
        }
    }
    
    //This method converts the String array to a Node array
    //and assigns each Node with the information necessary to solve the board
    public static Node[][] setup(String[][] board){
        List<String> colors = new ArrayList<>();

        //Creating node array
        int x = board.length;
        int y = board.length;
        Node[][] nodeArr = new Node[x][y];

        for (int i = 0; i < y; i++) {
            
            for (int j = 0; j < x; j++) {
                //Makes a new node that represents the space of the board
                nodeArr[i][j] = new Node();
                //If there is a color initially
                if (!board[i][j].equals("_")) {
                    //It's a start node
                    nodeArr[i][j].startNode = true;
                    //dynamically keeping track of the amount of colors
                    if (colors.indexOf(board[i][j]) == -1) {
                        colors.add(board[i][j]);
                    }
                }
                
                nodeArr[i][j].color = board[i][j];
                nodeArr[i][j].xCord = j;
                nodeArr[i][j].yCord = i;
            }
        }

        //assign neighbors
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                //if not top row
                if (i != 0) {
                    nodeArr[i][j].up = nodeArr[i - 1][j];
                }
                //if not left side
                if (j != 0) {
                    nodeArr[i][j].left = nodeArr[i][j - 1];
                }
                //if not bottom row
                if (i != x - 1) {
                    nodeArr[i][j].down = nodeArr[i + 1][j];
                }
                //if not right side
                if (j != y - 1) {
                    nodeArr[i][j].right = nodeArr[i][j + 1];
                }
                //adds all of the colors to the nodes possible colors
                if (nodeArr[i][j].color.equals("_")) {
                    nodeArr[i][j].colors.addAll(colors);
                }
            }
        }
        return nodeArr;
    }
    
    public static Node[][] smartSolve(Node[][] board){
        
        return board;
    }
    
    public static Node[][] dumbSolve(Node[][] board){
        
        return board;
    }

    public static void printBoard(Node[][] board) {
        System.out.println(board.length);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j].color);
            }
            System.out.println("");
        }
        System.out.println(" ");
        System.out.println(" ");
    }
    

}
