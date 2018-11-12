package constraintsatisfaction;

class ConstraintSet {

    //this is where we will have all of our constraints
    //1 --> each non-startNode will have exactly 2 of the same color neighbors
    //2 --> each Node will have a color --> no "_"
    //3 --> each origin node has exactly 1 of the same color neighbors
    //always run immediately after assigning a previously blank node a new color
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
                return same > 2;
            }
        }
        return false;
    }

    public boolean blocked(Node[][] board) {
        for (Node[] row : board) {
            for (Node n : row) {
                int same = 0;
                if (n.color != "_") {
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

    public boolean checkOrigin(Node[][] board) {
        for (Node[] row : board) {
            for (Node n : row) {
                if (n.startNode) {
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
                    if (same > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean notBroken(Node[][] board) {
        if (!doesZig(board)) {
            if (!blocked(board)) {
                return (checkOrigin(board));
            }
        }
        return false;
    }

    public boolean isComplete(Node[][] board) {
        if (!notBroken(board)) {
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
}
