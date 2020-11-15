package N_Queen;

public class RandomRestartHillClimbing {
	private StochasticHillClimbing hillClimber;
	private Node start;

	public RandomRestartHillClimbing(Queen[] startBoard) {
		hillClimber = new StochasticHillClimbing(startBoard);

	}

	public Node randomRestart() {
		Node currentNode = hillClimber.getStartNode();
		setStartNode(currentNode);
		int heuristic = currentNode.getHeuristic();
				
		while(heuristic!=0){
			Node nextNode = hillClimber.hillClimbing();
			heuristic = nextNode.getHeuristic();

			// RANDOM-RESTART climbing adopts the well-known adage,
			// It conducts a series of hill-climbing searches from
			// randomly generated initial states, until a goal is found.
			// restart
			if(heuristic!=0){
				hillClimber = new StochasticHillClimbing();
			}else
				currentNode = nextNode;
		}
		return currentNode;
	}

	public void setStartNode(Node n){
		start = n;
	}

	public Node getStartNode(){
		return start;
	}

}
