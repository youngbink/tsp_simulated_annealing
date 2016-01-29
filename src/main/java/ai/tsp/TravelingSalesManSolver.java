package ai.tsp;

import ai.AStarSearch;
import ai.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class TravelingSalesManSolver {
    public static void main(String ...args) {
        TravelingSalesManSolver solver = new TravelingSalesManSolver();
        int dirName = 1;
        //solver.getAllFilesFromDir(String.valueOf(dirName));

        solver.processFile("5/instance_1.txt");

        /*
        if (node != null) {
            solver.printResult(node);
            //System.out.println(" " + problem.getInitialState().getName());
        }
        */

    }

    public void processFilesInDir(final String dirName) {
        File folder = new File(getClass().getClassLoader().getResource(dirName).getFile());
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.err.println("What is directory doing in here? " + fileEntry.getName());
            } else {
                //fileList.add(fileEntry.getName());
            }
        }
    }

    public int processFile(final String fileName) {
        TravelingSalesManProblem problem = new TravelingSalesManProblem();
        problem.initiateProblem(fileName);

        AtomicInteger numNodes = new AtomicInteger(0);
        Node node = new AStarSearch().run(problem, numNodes);
        System.out.println("# of Node: " +numNodes);
        node.printPath();
        return numNodes.get();
    }
}
