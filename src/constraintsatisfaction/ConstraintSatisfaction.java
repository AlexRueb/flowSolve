package constraintsatisfaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author alex
 */
class ConstraintSatisfaction {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String[][] board = import_maze("src/constraintsatisfaction/5x5maze.txt");
        Maze maze = new Maze();
        maze.solveMaze(board, 1);
        maze.solveMaze(board, 2);
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

    static String[][] import_maze(String f) throws IOException {
        int boardX = 100;
        int boardY = 100;
        //rewrite to make it read in the first line, then set boardX/Y to length of string
        //then make array
        //then assign all values
        //assumes that board is a square
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

    
    

    
    

    

}
