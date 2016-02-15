package annealing.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by youngbinkim on 2/12/16.
 *
 * tour containing the path of tour.. e.g A B C A
 */
public class Tour {
    private List<City> path = new ArrayList<>();
    private float cost = Float.NEGATIVE_INFINITY;

    public List<City> getPath() {
        return path;
    }

    public void setPath(List<City> path) {
        this.path = path;
        calculateCost();
    }

    /**
     * randomly swaps two cities in the path..
     * here, need to be careful that the first city of path must be same os the last city
     */
    public Tour randomSwap () {
        Tour neighbour = new Tour();
        List<City> newPath = new ArrayList<>(path);
        int swap1 = 0;

        while (swap1 == 0) {
            swap1 = (int) (Math.random() * (newPath.size() - 1));
        }

        int swap2 = swap1;

        while (swap2 == swap1 || swap2 == 0) {
            swap2 = (int) (Math.random() * (newPath.size() - 1));
            //System.out.println(swap2);
        }

        assert(swap1 > 0);
        assert(swap2 > 0);

        // cities to be swapped..
        City c1 = newPath.get(swap1);
        City c2 = newPath.get(swap2);

        newPath.set(swap1, c2);
        newPath.set(swap2, c1);

        neighbour.setPath(newPath);
        neighbour.calculateCost();
        return neighbour;
    }

    public float getCost() {
        if (cost == Float.NEGATIVE_INFINITY) {
            calculateCost();
        }
        return cost;
    }

    private void calculateCost() {
        cost = 0.0f;
        City cur = null;
        City next = null;

        try {
            for (int i = 0; i < getPath().size() - 1; i++) {
                cur = path.get(i);
                next = path.get(i + 1);
                //System.out.println("cur " + cur.getName());
                //System.out.println("next " + next.getName());
                cost += cur.getdMap().get(next);
            }
        } catch (Exception e) {
            System.out.println("cur : " + cur.getName() + " with next " + next.getName());
            e.printStackTrace();
        }

    }

    public void printPath() {
        System.out.println("PATH: ");
        for (City city : path) {
            System.out.print(city.getName() + " ");
        }
        System.out.println("");
    }

    public boolean equals(Tour another) {
        List<City> path2 = another.getPath();
        boolean res = false;

        if (path2.size() != path.size()) {
            return res;
        }

        res = true;
        for (int i = 0; i < path.size(); i++) {
            if (!path.get(i).getName().equals(path2.get(i).getName())) {
                return false;
            }
        }
        return res;
    }
}
