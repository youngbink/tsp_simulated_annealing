package ai.tsp;

/**
 * Created by youngbinkim on 1/29/16.
 */
public class Load {
    City c1;
    City c2;
    double dist;

    public Load(City c1, City c2, double dist) {
        this.c1 = c1;
        this.c2 = c2;
        this.dist = dist;
    }

    public City getC1() {
        return c1;
    }

    public City getC2() {
        return c2;
    }

    public double getDist() {
        return dist;
    }
}
