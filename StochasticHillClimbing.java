package N_Queen;

import java.util.ArrayList;
import java.util.Random;

public class StochasticHillClimbing {

	private Queen[] startState;
	// start state
	private Node start;

	public StochasticHillClimbing() {
		start = new Node();
		startState = new Queen[Node.getSize()];
		startState();
	}

	public StochasticHillClimbing(Queen[] s) {
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
			
			Node nextNode = null;

			ArrayList<Node> betterSuccessors = new ArrayList<>();

			for (int i = 0; i < successors.size(); i++) {
				if (successors.get(i).compareTo(currentNode) < 0) {
					betterSuccessors.add(successors.get(i));
				}
			}

			if (!betterSuccessors.isEmpty()) {
				Random random = new Random();
				nextNode = betterSuccessors.get(random.nextInt(betterSuccessors.size()));
			}
			
			if(nextNode == null) {
				return currentNode;
			}

			currentNode = nextNode;
		}
	}

	public Node getStartNode(){
		return start;
	}
}
