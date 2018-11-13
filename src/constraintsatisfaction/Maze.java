package constraintsatisfaction;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alex Rueb
 */
class Maze {

    Node[][] board;
    ConstraintSet constraint = new ConstraintSet();
    public List<String> colors = new ArrayList<>();
    public int printCt = 0;
    //Choice is whether to do the smart way (forward checking) and 
    //dumb way (no forward checking)
    public void solveMaze(String[][] board, int choice) {
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
    public Node[][] setup(String[][] board) {
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
                nodeArr[i][j].colors = colors;
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
            }
        }
        return nodeArr;
    }

    public Node[][] smartSolve(Node[][] board) {

        return board;
    }

    public Node[][] dumbSolve(Node[][] board) {
        /*pseudo-code
        find first color where solved != true
        find coordinates of destination color
        change a neighboring non-color node that would be closer to the same color
        check if any constraint is violated
        check possible for all neighboring nodes
        repeat with new node until start node is connected to end node
        solved = true
         */

        return dumbBackTrack(board, constraint);
    }

    //Nodes are variables
    //Colors are the domain for each Node
    public Node[][] dumbBackTrack(Node[][] board, ConstraintSet csp) {
        if (csp.isComplete(board) && csp.notBroken(board)) {
            return board;
        }
        Node curNode = findNextColor(board);
        for (String s : curNode.colors) {
            curNode.color = s;
            
            printBoard(board);
            System.out.println(printCt);
            if (csp.notBroken(board)) {
                Node[][] assignment = dumbBackTrack(board, csp);
                if (assignment == null) {
                    ;
                } else {
                    return assignment;
                }
            }
            curNode.color = "_";
        }
        return null;
    }
//    public Node[][] dumbBackTrack(Node[][] board, ConstraintSet csp) {
//        //if succeeds
//        if (csp.isComplete(board)) {
//            return board;
//        }
//        Node[][] backup = new Node[board.length][board.length];
//
//        backup = board;
//
//        //find an unassigned node
//        Node curNode = findNextColor(backup);
//        //curNode.colors = this.colors;
//        //loop through every possible color this node could be
//        for (String s : curNode.colors) {
//            //assign that color to this node
//            curNode.color = s;
//            printBoard(backup);
//            //pass the new board to csp to see if it fails
//            if (csp.notBroken(backup)) {
//                dumbBackTrack(backup, csp);
//            } else {
//                curNode.color = "_";
//            }
//            //if it doesnt fail, pass new board to dumbBackTrack
//            //if fails
//
//        }
//        return board;
//    }

    public Node findNextColor(Node[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].color.equals("_")) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public void printBoard(Node[][] board) {
        printCt++;
        //System.out.println(board.length);
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
