package constraintsatisfaction;

class ConstraintSet {

    //this is where we will have all of our constraints
    //1 --> each non-startNode will have exactly 2 of the same color neighbors
    //2 --> each Node will have a color --> no "_"
    //3 --> each origin node will be connected to the other origin node of same color
      
    //1 --> Is there any zigzags now?
    //returns true if there IS a zig zag
    public boolean doesZig(Node[][] x) {
        for (Node[] row : x) {
            for (Node n : row) {
                int same = 0;
                if (n.up != null && n.up.color.equals(n.color)) {
                    same++;
                }
                if (n.down != null && n.down.color.equals(n.color)) {
                    same++;
                }
                if (n.left != null && n.left.color.equals(n.color)) {
                    same++;
                }
                if (n.right != null && n.right.color.equals(n.color)) {
                    same++;
                }
                if (n.startNode && same >= 2) {
                    return true;
                }
                if ((same > 2) && !n.color.equals("_")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //2 --> Is every Node filled in
    //return true if all constraints are satisfied
    public boolean isComplete(Node[][] board) {
        if (isBroke(board)) {
            return false;
        }
        for (Node[] row : board) {
            for (Node n : row) {
                n.solved = false;
            }
        }
        if (!allIsConnected(board)) {
            return false;
        }
        for (Node[] row : board) {
            for (Node n : row) {
                if (n.color.equals("_")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //3 --> calls isConnected to see if ALL start nodes are connected
    //returns true if all start nodes are connected to eachother
    public boolean allIsConnected(Node[][] maze) {
        for (Node[] row : maze) {
            for (Node n : row) {
                if (n.startNode && !n.solved) {
                    boolean temp = isConnected(n, null);
                    if (temp == false) {
                        return false;
                    } else {
                        n.solved = true;
                    }
                }
            }
        }
        return true;
    }
    
    //recursive method returns true if two start nodes are connected
    public boolean isConnected(Node curNode, Node last) {
        if (curNode.left != null && curNode.left.color.equals(curNode.color)) {
            if (curNode.left != last) {
                return curNode.solved = isConnected(curNode.left, curNode);
            }
        }
        if (curNode.right != null && curNode.right.color.equals(curNode.color)) {
            if (curNode.right != last) {
                return curNode.solved = isConnected(curNode.right, curNode);
            }
        }
        if (curNode.up != null && curNode.up.color.equals(curNode.color)) {
            if (curNode.up != last) {
                return curNode.solved = isConnected(curNode.up, curNode);
            }
        }
        if (curNode.down != null && curNode.down.color.equals(curNode.color)) {
            if (curNode.down != last) {
                return curNode.solved = isConnected(curNode.down, curNode);
            }
        }
        if (curNode.startNode) {
            return curNode.solved = true;
        } else {
            return false;
        }
    }
    
    //makes sure that no nodes are blocked in
    //returns true if a node IS blocked in
    public boolean blocked(Node[][] board) {
        for (Node[] row : board) {
            for (Node n : row) {
                int same = 0;
                if (n.color.equals("_")) {
                    ;
                } else {
                    if (n.up != null && n.up.color.equals(n.color)) {
                        same++;
                    }
                    if (n.down != null && n.down.color.equals(n.color)) {
                        same++;
                    }
                    if (n.left != null && n.left.color.equals(n.color)) {
                        same++;
                    }
                    if (n.right != null && n.right.color.equals(n.color)) {
                        same++;
                    }
                    if (same == 0) {
                        int blank = 0;
                        if (n.up != null && n.up.color.equals("_")) {
                            blank++;
                        }
                        if (n.down != null && n.down.color.equals("_")) {
                            blank++;
                        }
                        if (n.left != null && n.left.color.equals("_")) {
                            blank++;
                        }
                        if (n.right != null && n.right.color.equals("_")) {
                            blank++;
                        }
                        if (blank == 0) {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    //calls doesZig and blocked and returns true if either returns true
    public boolean isBroke(Node[][] board) {
        if (blocked(board)) {
            return true;
        }
        if (doesZig(board)) {
            return true;
        }
        return false;
    }

    //calls doesZig, blocked, and forwardCheck. Returns true if any return true
    public boolean smartIsBroke(Node[][] board) {
        
        return true;
    }

    //implements arc consistency
    public boolean forwardCheck(){
        
        return false;
    }

    
    
}
