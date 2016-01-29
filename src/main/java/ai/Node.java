package ai;

import ai.tsp.City;
import ai.tsp.Load;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class Node {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OPEN = 1;
    public static final int STATE_CLOSED = 2;
    private double GScore;
    private int state;
    private double FScore;
    private String name;
    private City current;
    private Set<City> unvisited;
    private List<City> path;

    public Node(City current, Set<City> unvisited, List<City> path) {
        this.current = current;
        this.unvisited = unvisited;
        this.path = path;
        /*
        System.out.println("Creating node.. ");
        System.out.println("  current: " + current.getName());
        System.out.println("  path");

        this.printPath();
        */
    }

    public double getGScore() {
        return GScore;
    }

    public int getState() {
        return state;
    }

    public void setGScore(double GScore) {
        this.GScore = GScore;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setFScore(double FScore) {
        this.FScore = FScore;
    }

    public double getFScore() {
        return FScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCurrent() {
        return current;
    }

    public Set<City> getUnvisited() {
        return unvisited;
    }

    public List<City> getPath() {
        return path;
    }

    public void printPath(){
        for (City city: path) {
            System.out.print(city.getName() + " ");
        }
        System.out.println(getCurrent().getName());
    }

    public Load getNearestDistFromUnvisited(City city) {
        Map<City, Load> map = city.getdMap();
        for (Map.Entry<City, Load> entry : map.entrySet())
        {
            if (unvisited.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
