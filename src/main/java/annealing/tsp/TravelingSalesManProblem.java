package annealing.tsp;


import annealing.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by youngbinkim on 1/27/16.
 *
 * Class representing TSP problem
 */
public class TravelingSalesManProblem {
    ArrayList<City> allCities = new ArrayList<>();

    // initialize..
    // create cities and edges between them
    public void initiateProblem(final String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


        //Read File Line By Line
        try {
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                String[] tokens = strLine.split(" ");
                if (tokens.length < 3) {
                    continue;
                }

                City city = new City(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                allCities.add(city);

            }
            createEdges();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception occurred while initiating the problem .. " + e);
        }
    }


    private void createEdges() {
        City c1, c2;

        for (int i = 0; i < allCities.size(); i++) {
            for (int j = 0; j < allCities.size(); j++) {
                if (i == j)
                    continue;

                c1 = allCities.get(i);
                c2 = allCities.get(j);

                c1.addCityToMap(c2);
                c2.addCityToMap(c1);

                //System.out.println("c1 " + c1.getName() + " , c2 " + c2.getName() + " dist : " + c1.getdMap().get(c2));
            }
        }
        sortEdges(allCities);
    }

    private void sortEdges(List<City> allCities) {
        for (City city : allCities) {
            city.setdMap(sortByValues(city.getdMap()));
        }
    }

    private static HashMap sortByValues(Map map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry<City, Double>) (o1)).getValue())
                .compareTo(((Map.Entry<City, Double>) (o2)).getValue()));

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public Tour createRandomTour() {
        Tour tour = new Tour();
        List<City> newPath = new ArrayList<>();

        int tourSize = allCities.size();
        int index;
        Set<Integer> seen = new HashSet<>();
        City city;

        newPath.add(allCities.get(0));
        //System.out.println("** " + allCities.get(0).getName());
        for (int i = 1; i < tourSize; i++) {
            index = (int) (Math.random() * tourSize);
            while (index == 0 || seen.contains(index)) {
                //System.out.println("in " + index);
                index = (int) (Math.random() * tourSize);
            }

            seen.add(index);
            city = allCities.get(index);
            //System.out.println("** " + index + " " + allCities.get(index).getName());
            newPath.add(city);
        }
        //System.out.println("");

        if (allCities.size() > 1) {
            //System.out.println("** last :" + allCities.get(0).getName());
            newPath.add(allCities.get(0));
        }

        tour.setPath(newPath);
        return tour;
    }
}
