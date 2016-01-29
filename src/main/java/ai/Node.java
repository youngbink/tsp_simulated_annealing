package ai;

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
}
