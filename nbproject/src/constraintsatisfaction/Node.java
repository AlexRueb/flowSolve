package constraintsatisfaction;

import java.util.ArrayList;
import java.util.List;

class Node {
    //each node is a variable
    
    //where the node is
    public int xCord;
    public int yCord;
    public String color;
    public Node left;
    public Node right;
    public Node up;
    public Node down;
    public boolean startNode = false;
    public int sameCol = 0;
    
    //domain
    public List<String> colors= new ArrayList<>();
    public boolean solved;
    
    //these are the distances to goal
    public int xDist;
    public int yDist;
    
    //updates the possible colors that a node can have --> inference
    public boolean checkPossible(char color){
        return true;
    }
    public void updateGoal(int x, int y){
        xDist = x - xCord;
        yDist = y - yCord;
    }
}
