package constraintsatisfaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    public int moveCt = 0;

    //Choice is whether to do the smart way (forward checking) and 
    //dumb way (no forward checking)
    public void solveMaze(String[][] board, int choice) {
        Node[][] nodeArr = setup(board);

        //If smart choice
        if (choice == 1) {
            System.out.println("smart");
            smartSolve(nodeArr);
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

    public void smartSolve(Node[][] board) {

        //smartForwardCheck(board, constraint);
        smarterForwardCheck(board, constraint);
    }

    public Node[][] smartForwardCheck(Node[][] board, ConstraintSet csp) {
        //printBoard(board);
        if (csp.isComplete(board)) {
            System.out.println("You solved the board in " + moveCt + " moves!");
            //printBoard(board);
            return board;
        }
        // SELECT-UNASSIGNED-VARIABLE
        //Node curNode = smarterFindNode(board);
        Node curNode = findNextNode(board);
        if (curNode != null) {
            //ORDER-DOMAIN-VALUES
            for (String s : curNode.colors) {
                curNode.color = s;
                List<String> backupColors = curNode.colors;
                if (inferences(board, csp, curNode)) {
                    if (!csp.isBroke(board)) {
                        Node[][] assignment = smartForwardCheck(board, csp);
                        if (assignment == null) {
                        } else {
                            return assignment;
                        }
                    }
                }
                printBoard(board);
                curNode.color = "_";
                printBoard(board);
                curNode.colors = backupColors;
            }
        }
        return null;
    }

    public boolean inferences(Node[][] board, ConstraintSet csp, Node curNode) {
        // reduce domain of curNode based on forward checking
        return csp.ac3(board, curNode);
    }

    // Returns possible domain values for the current node
    public List<String> orderDomainValues(List<String> colors, ConstraintSet csp) {

        return colors;
    }

    public void dumbSolve(Node[][] board) {
        /*pseudo-code
        find first color where solved != true
        find coordinates of destination color
        change a neighboring non-color node that would be closer to the same color
        check if any constraint is violated
        check possible for all neighboring nodes
        repeat with new node until start node is connected to end node
        solved = true
         */

        dumbBackTrack(board, constraint);
    }

    //Nodes are variables
    //Colors are the domain for each Node
    public Node[][] dumbBackTrack(Node[][] board, ConstraintSet csp) {
        //printBoard(board);
        if (!csp.isComplete(board)) {
        } else {
            System.out.println("You solved the board in " + moveCt + " moves!");
            //printBoard(board);
            return board;
        }
        Node curNode = smartFindNode(board);
        //Node curNode = findNextNode(board);
        if (curNode != null) {
            for (String s : curNode.colors) {
                curNode.color = s;
                if (!csp.isBroke(board)) {
                    Node[][] assignment = dumbBackTrack(board, csp);
                    if (assignment == null) {
                    } else {
                        return assignment;
                    }
                }
                curNode.color = "_";
            }
        }
        return null;
    }

    public Node findNextNode(Node[][] board) {
        moveCt++;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].color.equals("_")) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    //finds the most constrained variable
    //(variable with least amount of unassigned neighbors)
    public Node smartFindNode(Node[][] board) {
        moveCt++;
        Node next = new Node();
        int nextConstraint = 4;
        for (Node[] row : board) {
            for (Node n : row) {
                if (n.color.equals("_")) {
                    int constraints = 4;
                    if (n.up != null && !n.up.color.equals("_")) {
                        constraints--;
                    }
                    if (n.down != null && !n.down.color.equals("_")) {
                        constraints--;
                    }
                    if (n.left != null && !n.left.color.equals("_")) {
                        constraints--;
                    }
                    if (n.right != null && !n.right.color.equals("_")) {
                        constraints--;
                    }
                    if (constraints == 0) {
                        return null;
                    }
                    if (nextConstraint > constraints) {
                        next = n;
                        nextConstraint = constraints;
                    }
                }
            }
        }
        return next;
    }

    //even better way of picking next variable
    //finds a start node that isn't solved, solves for only that color
    public Node smarterFindNode(Node[][] board) {
        moveCt++;
        Node next = new Node();
        for (Node[] row : board) {
            for (Node n : row) {
                if (n.startNode && !n.solved) {
                    next = findNext(board, n);
                    if (next == null) {
                    } else {
                        return next;
                    }
                }
            }
        }
        return next;
    }

    public Node findNext(Node[][] board, Node n) {
        return null;
    }

    public void printBoard(Node[][] board) {
        printCt++;
        boolean print = true;
        for (Node[] row : board) {
            for (Node n : row) {
                if (n.color.equals("_")) {
                    print = false;
                    break;
                }

            }
        }
        print = true;
        if (print) {
            //System.out.println(printCt);
            for (Node[] board1 : board) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board1[j].color);
                }
                System.out.println("");
            }
            System.out.println(" ");
        }
    }

    private Node[][] smarterForwardCheck(Node[][] board, ConstraintSet csp) {
        //printBoard(board);
        if (csp.isComplete(board)) {
            System.out.println("You solved the board in " + moveCt + " moves!");
            //printBoard(board);
            return board;
        }
        // SELECT-UNASSIGNED-VARIABLE
        //Node curNode = smarterFindNode(board);
        Node curNode = findNextNode(board);
        if (curNode != null) {
            //ORDER-DOMAIN-VALUES
            while (!curNode.colors.isEmpty()) {
                curNode.color = curNode.colors.remove(0);
                printBoard(board);
                if (csp.forwardCheck(board)) {
                    return null;
                }
                if (csp.isBroke(board)) {
                    return null;
                }
                if (smartForwardCheck(board, csp) == null) {

                }
            }
        }
        return null;
    }

    //add a color in the domain to the node
    //see if it breaks any other nodes
    //if it doesnt --> move onto next node (recursion)]
    //if it does --> go to the next color
}
