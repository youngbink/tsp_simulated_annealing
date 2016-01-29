package ai.tsp;

import ai.AStarSearch;
import ai.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class TravelingSalesManSolver {
    public static void main(String ...args) {
        TravelingSalesManSolver solver = new TravelingSalesManSolver();
        int dirName = 1;
        //solver.getAllFilesFromDir(String.valueOf(dirName));

        TravelingSalesManProblem problem = new TravelingSalesManProblem();
        problem.initiateProblem("3/instance_1.txt");
        Node node = new AStarSearch().run(problem);
        if (node != null) {
            solver.printResult(node);
            //System.out.println(" " + problem.getInitialState().getName());
        }

    }

    private void printResult(Node node) {
        /*
        if (node.getParent() != null) {
            printResult(node.getParent());
        }
        System.out.print(" " + node.getName());
        */
    }

    public List<String> getAllFilesFromDir(final String dirName) {
        List<String> fileList = new ArrayList<>();
        File folder = new File(getClass().getClassLoader().getResource(dirName).getFile());
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.err.println("What is directory doing in here? " + fileEntry.getName());
            } else {
                fileList.add(fileEntry.getName());
            }
        }

        return fileList;
    }
}
