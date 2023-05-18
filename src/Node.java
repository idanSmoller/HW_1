public class Node {
    private Action action;  // null if the root
    private State currState;
    private Node parent;   // null if the root

    public Node(Action action, State currState, Node parent) {
        this.action = action;
        this.currState = currState;
        this.parent = parent;
    }

    public Node(String boardInitStr) {     // initial the root Node
        this(null, new State(boardInitStr), null);
    }

    public Action getAction() {
        return action;
    }

    public State getState() {
        return currState;
    }

    public Node getParent() {
        return parent;
    }

    public Node[] expand() {
        Action[] actions = currState.actions();
        int actionNum = actions.length;
        Node[] ret = new Node[actionNum];
        for (int i = 0; i < actionNum; i++) {
            ret[i] = new Node(actions[i], currState.result(actions[i]), this);
        }

        return ret;
    }

    public int distanceFromHome(int value, int row, int col) {
        int homeRow = (value - 1) / this.currState.getBoard().getDimX();
        int homeCol = (value - 1) % this.currState.getBoard().getDimX();

        int vertDistance = Math.abs(homeRow - row);
        int horDistance = Math.abs(homeCol - col);
        return  vertDistance + horDistance;
    }

    public int heuristicValue() {
        int sum = 0;
        for (int i = 0; i < this.currState.getBoard().getDimX(); i++) {
            for (int j = 0; j < this.currState.getBoard().getDimY(); j++) {
                int dist = distanceFromHome(this.currState.getBoard().tileAt(j, i).getValue(), j, i);
                sum += dist;
            }
        }

        return sum;
    }
}
