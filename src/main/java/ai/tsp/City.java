package ai.tsp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youngbinkim on 1/28/16.
 */
public class City {
    String name;
    int x;
    int y;
    Map<City, Load> dMap = new HashMap<>();

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

    public void addCityToMap(City neighbour, Load load) {
        dMap.put(neighbour, load);
    }

    public Map<City, Load> getdMap() {
        return dMap;
    }

    public void setdMap(Map<City, Load> dMap) {
        this.dMap = dMap;
    }

    public String getName() {
        return name;
    }
}
