package ai.tsp;

import ai.AStarSearch;
import ai.Node;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class TravelingSalesManSolver {
    public static void main(String ...args) {
        long start = System.currentTimeMillis();
        TravelingSalesManSolver solver = new TravelingSalesManSolver();
        for (int i = 14; i <= 14; i++) {
            solver.processFilesInDir(String.valueOf(i));
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (((end - start) * 1.0) / 1000));

        //solver.processFile("1/instance_1.txt");

        /*
        if (node != null) {
            solver.printResult(node);
            //System.out.println(" " + problem.getInitialState().getName());
        }
        */

        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }

    public void processFilesInDir(final String dirName) {
        File folder = new File(getClass().getClassLoader().getResource(dirName).getFile());
        double sum = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.err.println("What is directory doing in here? " + fileEntry.getName());
            } else {
                sum += processFile(dirName + "/" + fileEntry.getName());
                //fileList.add(fileEntry.getName());
            }
        }
        System.out.println("Total average for dir : " + dirName + ": " + sum / folder.listFiles().length);
    }

    public int processFile(final String fileName) {
        TravelingSalesManProblem problem = new TravelingSalesManProblem();
        problem.initiateProblem(fileName);

        AtomicInteger numNodes = new AtomicInteger(0);
        Node node = new AStarSearch().run(problem, numNodes);
        //System.out.println("# of Node: " +numNodes);
        //node.printPath();
        return numNodes.get();
    }
}
