package ai.tsp;


import ai.*;
import com.sun.javafx.geom.Edge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by youngbinkim on 1/27/16.
 */
public class TravelingSalesManProblem implements SearchProblem {
    Node firstState;
    City firstCity;

    public void initiateProblem(final String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        boolean isFirstNode = true;
        Set<City> unvisited = new HashSet<>();
        List<City> path = new ArrayList<>();

        //Read File Line By Line
        try {
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                String[] tokens = strLine.split(" ");
                if (tokens.length < 3) {
                   //System.out.println("Invalid tokens.. " + strLine);
                    continue;
                }

                City city = new City(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

                if (isFirstNode) {
                    firstCity = city;
                    isFirstNode = false;
                } else {
                    unvisited.add(city);
                }

            }
            this.firstState = new Node(firstCity, unvisited, new ArrayList<>());
            createEdges(unvisited, firstCity);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception occurred while initiating the problem .. " + e);
        }
    }


    private double getCityDistance(City c1, City c2) {
        return Math.sqrt(Math.pow((c1.getX() - c2.getX()), 2) +
                Math.pow((c1.getY() - c2.getY()), 2));
    }

    @Override
    public Node getInitialState() {
        return firstState;
    }

    @Override
    public boolean goalTest(final Node node) {
        return node.getCurrent() == firstCity && node.getUnvisited().isEmpty();
    }

    @Override
    public List<Node> takeActions(final Node node) {
        List<Node> neighbours = new ArrayList<>();

        Set<City> unvisited = node.getUnvisited();
        List<City> currentPath = node.getPath();
        List<City> newPath;

        Set<City> newUnvisited;

        if (unvisited.size() == 0) {
            newPath = new ArrayList<>(currentPath);
            newPath.add(node.getCurrent());
            newUnvisited = new HashSet<>(unvisited);
            neighbours.add(new Node(firstCity, newUnvisited, newPath));
            return neighbours;
        }

        for (City neighbour : unvisited) {
            newPath = new ArrayList<>(currentPath);
            newPath.add(node.getCurrent());
            newUnvisited = new HashSet<>(unvisited);

            newUnvisited.remove(neighbour);

            neighbours.add(new Node(neighbour, newUnvisited, newPath));
        }

        return neighbours;
    }

    @Override
    public double getDist(final Node node, final Node neighbour) {
        return neighbour.getCurrent().getdMap().get(node.getCurrent()).getDist();
    }

    @Override
    public void calculateF(final Node neighbour, final double tmpGScore) {
        neighbour.setFScore(heuristic(neighbour) + tmpGScore);
        //neighbour.setFScore(0 + tmpGScore);
    }

    private double heuristic(Node neighbour) {
        if (neighbour.getUnvisited().size() == 0)
            return 0.0f;
        else if (neighbour.getUnvisited().size() == 1)
            return neighbour.getCurrent().getdMap().get(firstCity).getDist();

        double dist1 = neighbour.getNearestDistFromUnvisited(neighbour.getCurrent()).getDist();
        double dist2 = calculateMST(neighbour);
        double dist3 = neighbour.getNearestDistFromUnvisited(firstCity).getDist();
        return dist1 + dist2 + dist3;

        /*
        List<City> cities = new ArrayList<>(neighbour.getUnvisited());
        List<Double> dists = new ArrayList<>();
        for (City city : cities) {
            dists.addAll(city.getdMap().values());
        }
        */
        //return 0;
    }
/*
Set<City> unvisited = neighbour.getUnvisited();
        double sum = 0.0f;
        for (City city : unvisited) {
            if (city == neighbour.getCurrent())
                continue;
            sum += neighbour.getNearestDistFromUnvisited(city).getDist();
        }
        return sum;
 */
    private double calculateMST(Node neighbour) {
        Set<City> unvisited = neighbour.getUnvisited();
        double sum = 0.0f;
        for (City city : unvisited) {
            if (city == neighbour.getCurrent())
                continue;
            sum += neighbour.getNearestDistFromUnvisited(city).getDist();
        }
        return sum;
    }

    private void createEdges(Set<City> unvisited, City firstCity) {
        List<City> allCities = new ArrayList<>(unvisited);
        allCities.add(firstCity);
        City c1, c2;
        Load load;
        double dist;

        for (int i = 0; i < allCities.size(); i++) {
            for (int j = 0; j < allCities.size(); j++) {
                if (i == j)
                    continue;

                c1 = allCities.get(i);
                c2 = allCities.get(j);

                dist = getCityDistance(c1, c2);
                load = new Load(c1, c2, dist);
                c1.addCityToMap(c2, load);
                c2.addCityToMap(c1, load);
            }
        }
        sortEdges(allCities);
    }

    private void sortEdges(List<City> allCities) {
        for (City city : allCities) {
            //System.out.println("City.. " + city.getName());
            city.setdMap(sortByValues(city.getdMap()));

            /*
            Set set2 = city.getdMap().entrySet();
            Iterator iterator2 = set2.iterator();
            while(iterator2.hasNext()) {
                Map.Entry<City, Double> me2 = (Map.Entry)iterator2.next();
                System.out.print(me2.getKey().getName() + ": ");
                System.out.println(me2.getValue());
            }
            System.out.println(" ");
            */
        }
    }

    private static HashMap sortByValues(Map map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry<City, Load>) (o1)).getValue().getDist())
                .compareTo(((Map.Entry<City, Load>) (o2)).getValue().getDist()));

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
