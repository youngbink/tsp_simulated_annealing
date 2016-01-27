package ai.tsp;

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
        solver.getAllFilesFromDir(String.valueOf(dirName));
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
