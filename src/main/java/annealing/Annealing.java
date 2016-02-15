package annealing;

import annealing.tsp.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youngbinkim on 2/12/16.
 *
 * Simulated Annealing class to solve TSP
 */
public class Annealing {
    /**
     * main function ..
     */
    public static void run (double initTemp, double finalTemp, double alpha, Tour tour) {
        double curTemp = initTemp; // current temperature initialized

        float curVal;
        float newVal;

        Tour bestTour = tour; // for optimization

        Tour currentTour = tour;
        Tour nextTour;

        List<Float> res = new ArrayList<>();

        int counter = 0;
        while (curTemp > finalTemp) {

            nextTour = currentTour.randomSwap(); // get neighbour
            curVal = currentTour.getCost();
            newVal = nextTour.getCost();

            if (acceptProb(curVal, newVal, curTemp) > Math.random()) {
                currentTour = nextTour;
                if (currentTour.getCost() < bestTour.getCost()) {
                    bestTour = nextTour;
                }
            }

            if ((counter++) % 100000 == 0) {
                res.add(currentTour.getCost());
            }

            //System.out.print(currentTour.getCost() + " ");
            curTemp *= (1 - alpha);
        }
        System.out.println("");

        bestTour.printPath();
        System.out.println("BEST " + bestTour.getCost());

        System.out.println("size " + res.size());
        System.out.println(res.toString());
    }

    private static double acceptProb(float curVal, float newVal, double curTemp) {
        if (curVal > newVal) {
            return 1.0f;
        } else {
            return Math.exp((curVal - newVal) / curTemp);
        }
    }
}
