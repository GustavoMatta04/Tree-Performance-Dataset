
public class Node {
    public String key;
    public int value;
    public Node left, right;
    public int height;

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
        this.left = this.right = null;
        this.height = 1;
    }
}
