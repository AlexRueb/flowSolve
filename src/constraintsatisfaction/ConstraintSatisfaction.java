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
        long curTime = System.currentTimeMillis();
        //maze.solveMaze(board, 1);
        maze.solveMaze(board, 2);
        curTime =System.currentTimeMillis() - curTime;
        System.out.println(curTime + " Milliseconds");
        board = import_maze("src/constraintsatisfaction/7x7maze.txt");
        curTime = System.currentTimeMillis();
        maze.solveMaze(board, 2);
        curTime =System.currentTimeMillis() - curTime;
        System.out.println(curTime + " Milliseconds");
        board = import_maze("src/constraintsatisfaction/8x8maze.txt");
        curTime = System.currentTimeMillis();
        maze.solveMaze(board, 2);
        curTime =System.currentTimeMillis() - curTime;
        System.out.println(curTime + " Milliseconds");
        board = import_maze("src/constraintsatisfaction/9x9maze.txt");
        curTime = System.currentTimeMillis();
        maze.solveMaze(board, 2);
        curTime =System.currentTimeMillis() - curTime;
        System.out.println(curTime + " Milliseconds");
        board = import_maze("src/constraintsatisfaction/10x10maze.txt");
        curTime = System.currentTimeMillis();
        maze.solveMaze(board, 2);
        curTime =System.currentTimeMillis() - curTime;
        System.out.println(curTime + " Milliseconds");
        //board = import_maze("src/constraintsatisfaction/12x12maze.txt");
        //curTime = System.currentTimeMillis();
        //maze.solveMaze(board, 2);
        //curTime =System.currentTimeMillis() - curTime;
        //System.out.println(curTime + " Milliseconds");
    }

    static String[][] import_maze(String f) throws IOException {

        try (BufferedReader b = new BufferedReader(new FileReader(f))) {
            String line = b.readLine();
            int boardX = line.length();
            int boardY = line.length();
            String[][] board = new String[boardY][boardX];
            int i = 0;
            while (i < boardY) {
                char[] charArr = line.toCharArray();
                String[] boardArr = new String[line.length()];

                for (int q = 0; q < line.length(); q++) {
                    boardArr[q] = String.valueOf(charArr[q]);
                }
                board[i] = boardArr;
                i++;
                line = b.readLine();
            }
            return board;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    
    

    
    

    

}
