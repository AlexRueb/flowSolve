package constraintsatisfaction;

import java.util.ArrayList;
import java.util.List;

class Node {
    public int xCord;
    public int yCord;
    public String color;
    public Node left;
    public Node right;
    public Node up;
    public Node down;
    public boolean startNode = false;
    public int sameCol = 0;
    public List<String> colors= new ArrayList<>();
    
    public boolean checkPossible(char color){
        return true;
    }
}
