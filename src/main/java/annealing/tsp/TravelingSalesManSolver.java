package annealing.tsp;

import annealing.Annealing;

import java.io.File;

/**
 * Created by youngbinkim on 1/26/16.
 *
 * Main class triggering other classes to solve TSP problem..
 */
public class TravelingSalesManSolver {
    public static void main(String ...args) {

        TravelingSalesManSolver solver = new TravelingSalesManSolver();

        /*

        for (int i = 1; i <= 5; i++) {
            long start = System.currentTimeMillis();
            //solver.processFilesInDir(String.valueOf(i));
            solver.processFile("16/instance_1.txt");
            long end = System.currentTimeMillis();
            System.out.println("Time taken : " + (((end - start) * 1.0) / 1000));
        }

*/

        for (int i = 0; i < 5; i++ ){
            long start = System.currentTimeMillis();
            //solver.processFile("10/instance_1.txt");
            solver.processFile("problem36");
            long end = System.currentTimeMillis();
            System.out.println("Time taken : " + (((end - start) * 1.0) / 1000));
        }

    }

    public void processFilesInDir(final String dirName) {
        File folder = new File(getClass().getClassLoader().getResource(dirName).getFile());
        double sum = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.err.println("What is directory doing in here? " + fileEntry.getName());
            } else {
                sum += processFile(dirName + "/" + fileEntry.getName());
            }
        }
        System.out.println("Total average for dir : " + dirName + ": " + sum / folder.listFiles().length);
    }

    public int processFile(final String fileName) {
        // initialize the problem..
        TravelingSalesManProblem problem = new TravelingSalesManProblem();
        problem.initiateProblem(fileName);

        //problem.createRandomTour().printPath();
        //Annealing.run(5000, 1, 0.03f, problem.createRandomTour());
        Annealing.run(5000, 1, 0.0000005f, problem.createRandomTour());
        // Annealing.run(5000, 1, 0.00f, problem.createRandomTour());
        //AtomicInteger numNodes = new AtomicInteger(1);

        //new AStarSearch().run(problem, numNodes);
        return 0; //numNodes.get();
    }
}
