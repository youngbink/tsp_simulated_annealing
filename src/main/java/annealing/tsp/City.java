package annealing.tsp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youngbinkim on 1/28/16.
 *
 * Represents city to visit for TSP problem
 */
public class City {
    String name; // name of city
    int x; // x value of city
    int y; // y value of city
    Map<City, Double> dMap = new HashMap<>(); // contains (neighbour, distance to neighbour)

    public City (String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addCityToMap(City neighbour) {
        dMap.put(neighbour, getCityDistance(this, neighbour));
    }

    private static double getCityDistance(City c1, City c2) {
        return Math.sqrt(Math.pow((c1.getX() - c2.getX()), 2) +
                Math.pow((c1.getY() - c2.getY()), 2));
    }

    public Map<City, Double> getdMap() {
        return dMap;
    }

    public void setdMap(Map<City, Double> dMap) {
        this.dMap = dMap;
    }

    public String getName() {
        return name;
    }
}
