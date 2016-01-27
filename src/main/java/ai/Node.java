package ai;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class Node {
    Character name;
    Node parent;


    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
