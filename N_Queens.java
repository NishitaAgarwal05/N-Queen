package N_Queen;

import java.text.NumberFormat;
import java.util.Random;

public class N_Queens {

    private static final int TEST_TIMES = 500;

	public N_Queens() {

	}
	
	public static void main(String[] args){

        for (int NODE_SIZE = 6; NODE_SIZE <= 16; NODE_SIZE++) {

            Node.setSize(NODE_SIZE);

            N_Queens board = new N_Queens();

            System.out.println("Test for " + NODE_SIZE + " Queens");


            double HillClimbing_SUM_Successes = 0;
            double HillClimbing_AVE_Successes = 0;

            double SimulatedAnnealing_SUM_Successes = 0;
            double SimulatedAnnealing_AVE_Successes = 0;


            double RandomRestartHillClimbing_SUM_Successes = 0;
            double RandomRestartHillClimbing_AVE_Successes = 0;

            System.out.print("Running : ");
            double temp2=0;
            double temp3=0;
            double temp4=0;
            double start,end;
            for (int currentTest = 1; currentTest <= TEST_TIMES; currentTest++) {

                System.out.print("|");

                Queen[] startBoard = board.generateBoard();
   
                
                start=System.currentTimeMillis();
                RandomRestartHillClimbing randomRestartHillClimber
                        = new RandomRestartHillClimbing(startBoard);
                Node randomRestartHillClimbingNode = randomRestartHillClimber.randomRestart();
                if(randomRestartHillClimbingNode.getHeuristic() == 0){
                    RandomRestartHillClimbing_SUM_Successes++;
                }
                
				end=System.currentTimeMillis();
                temp2=(temp2+(end-start));
                
                
                
                
                start=System.currentTimeMillis();
                SimulatedAnnealing simulatedAnnealer
                        = new SimulatedAnnealing(startBoard);
                Node simulatedAnnealNode = simulatedAnnealer.simulatedAnneal(28, 0.0001);
                if(simulatedAnnealNode.getHeuristic() == 0){
                    SimulatedAnnealing_SUM_Successes++;
                }
                
                
				end=System.currentTimeMillis();
                temp3=(temp3+(end-start));
     
                
                start=System.currentTimeMillis();
                HillClimbing HillClimber
                        = new HillClimbing(startBoard);
                Node HillClimbingNode = HillClimber.hillClimbing();
                if (HillClimbingNode.getHeuristic() == 0) {
                    HillClimbing_SUM_Successes++;
                }

               
				end=System.currentTimeMillis();
				temp4=(temp4+(end-start));
                

            }
            
            HillClimbing_AVE_Successes =
                    HillClimbing_SUM_Successes / TEST_TIMES;
            SimulatedAnnealing_AVE_Successes =
                    SimulatedAnnealing_SUM_Successes / TEST_TIMES;
            
            RandomRestartHillClimbing_AVE_Successes =
                    RandomRestartHillClimbing_SUM_Successes / TEST_TIMES;

            NumberFormat fmt = NumberFormat.getPercentInstance();
            fmt.setMinimumFractionDigits(3);
            fmt.setMinimumIntegerDigits(10);

            System.out.println("Finish:");
            System.out.println("HillClimbing   :"
                    + " SUCCESSES_RATE = " + HillClimbing_AVE_Successes);
            System.out.println("average time is!!!!!!!!!"+temp4/TEST_TIMES);
            System.out.println("SimulatedAnnealing        :"
                    + " SUCCESSES_RATE = " + SimulatedAnnealing_AVE_Successes);
            System.out.println("average time is!!!!!!!!!"+temp3/TEST_TIMES);
       
            System.out.println("RandomRestartHillClimbing :"
                    + " SUCCESSES_RATE = " + RandomRestartHillClimbing_AVE_Successes);
            System.out.println("average time is!!!!!!!!!"+temp2/TEST_TIMES);

	}
}

	public Queen[] generateBoard(){
		Queen[] start = new Queen[Node.getSize()];
		Random gen = new Random();

		for (int i = 0; i < Node.getSize(); i++) {
			start[i] = new Queen(gen.nextInt(Node.getSize()), i);
		}
		return start;
	}
}
