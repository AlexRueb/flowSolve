package constraintsatisfaction;

class ConstraintSet {

    //this is where we will have all of our constraints
    //1 --> each non-startNode will have exactly 2 of the same color neighbors
    //2 --> each Node will have a color --> no "_"
    //3 --> 
    
    
    //always run immediately after assigning a previously blank node a new color
    public boolean doesZig(Node x) {
        int same = 0;
        if (x.up.color.equals(x.color)) {
            same++;
        }
        if (x.down.color.equals(x.color)) {
            same++;
        }
        if (x.left.color.equals(x.color)) {
            same++;
        }
        if (x.right.color.equals(x.color)) {
            same++;
        }
        return same > 1;
    }
    
    public boolean isComplete(Node[][] board){
        return true;
    }
}
