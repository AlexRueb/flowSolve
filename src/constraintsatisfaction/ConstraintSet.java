package constraintsatisfaction;

class ConstraintSet {

    //this is where we will have all of our constraints
    //1 --> each non-startNode will have exactly 2 of the same color neighbors
    //2 --> each Node will have a color --> no "_"
    //3 --> each origin node has exactly 1 of the same color neighbors
    
    
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
    
    public boolean checkOrigin(Node[][] board){
        for(Node[] row : board){
            for(Node n : row){
                int same = 0;
                if (n.up.color.equals(n.color)) {
                    same++;
                }
                if (n.down.color.equals(n.color)) {
                    same++;
                }
                if (n.left.color.equals(n.color)) {
                    same++;
                }
                if (n.right.color.equals(n.color)) {
                    same++;
                }
                return same <= 1;
            }
        }
        return true;
    }
    
    public boolean notBroken(Node[][] board){
        return true;
    }
    
    public boolean isComplete(Node[][] board){
        for(Node[] row : board){
            for(Node n : row){
                if(n.color.equals("_")){
                    return false;
                }
            }
        }
        return true;
    }
}
