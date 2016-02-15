package annealing.tsp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by youngbinkim on 2/12/16.
 */
public class TourTest {
    Tour tour = new Tour();
    Tour tour2 = new Tour();
    List<City> originalPath;
    City firstCity;
    @Before
    public void setup() {
        City city1 = new City("A", 0, 0);
        City city2 = new City("B", 0, 2);

        City city3 = new City("C", 3, 6);
        City city4 = new City("D", 3, 9);

        List<City> path = new ArrayList<>();

        path.add(city1);
        path.add(city2);
        path.add(city3);
        path.add(city4);

        for(int i = 0; i < path.size(); i++) {
            for (int j = 0; j < path.size(); j++) {
                if (i == j)
                    continue;

                path.get(i).addCityToMap(path.get(j));
                path.get(j).addCityToMap(path.get(i));
            }
        }

        firstCity = city1;
        tour2.setPath(new ArrayList<>(path));

        path.add(city1);
        originalPath = new ArrayList<>(path);

        tour.setPath(path);
    }

    @Test
    public void testRandomSwap() throws Exception {
        tour.randomSwap();
        List<City> newPath = tour.getPath();

        assertEquals(newPath.size(), originalPath.size(), 0.0f);

        int diff = 0;
        for (int i = 0; i < newPath.size(); i++ ) {
            if (newPath.get(i).getName().equals(originalPath.get(i).getName())) {
                diff++;
            }
        }
        assertEquals(true, diff > 0);
    }

    @Test
    public void testGetCost() throws Exception {
        assertEquals(tour.getCost(), 19.4f, 0.2f);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(tour2.equals(tour), false);
        List<City> path = tour2.getPath();
        path.add(firstCity);
        tour2.setPath(path);
        assertEquals(true, tour2.equals(tour));
    }

}