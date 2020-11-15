package N_Queen;

import java.util.ArrayList;
import java.util.Random;

public class HillClimbing {

    private Queen[] startState;
    // start state
    private Node start;

    public HillClimbing() {
        start = new Node();
        startState = new Queen[Node.getSize()];
        startState();
    }

    public HillClimbing(Queen[] s) {
        start = new Node();
        startState = new Queen[Node.getSize()];
        for (int i = 0; i < s.length; i++){
            startState[i] = new Queen(s[i].getRow(), s[i].getCol());
        }
        start.setState(startState);
        start.computeHeuristic();

    }

    public void startState() {
        Random random = new Random();
        for (int i = 0; i < Node.getSize(); i++){
            startState[i] = new Queen(random.nextInt(Node.getSize()), i);
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    public Node hillClimbing() {

        Node currentNode = start;

        while (true) {
            ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
            boolean existBetter = false;

            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).compareTo(currentNode) < 0) {
                    currentNode = successors.get(i);
                    existBetter = true;
                    break;
                }
            }

            if(!existBetter) {
                return currentNode;
            }

        }
    }

    public Node getStartNode(){
        return start;
    }


}
