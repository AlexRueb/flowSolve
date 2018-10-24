/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constraintsatisfaction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
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
