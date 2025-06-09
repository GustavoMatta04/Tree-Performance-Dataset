
public class BST {
    public Node root;

    public BST() {
        root = null;
    }

    public Node insert(Node node, String key, int value, Counter counter) {
        counter.count++;
        if (node == null) return new Node(key, value);
        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key, value, counter);
        else
            node.right = insert(node.right, key, value, counter);
        return node;
    }

    public Node search(Node node, String key, Counter counter) {
        if (node == null) return null;
        counter.count++;
        if (node.key.equals(key)) return node;
        if (key.compareTo(node.key) < 0)
            return search(node.left, key, counter);
        else
            return search(node.right, key, counter);
    }

    public Node delete(Node node, String key, Counter counter) {
        if (node == null) return null;
        counter.count++;
        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key, counter);
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key, counter);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node temp = minValueNode(node.right);
            node.key = temp.key;
            node.value = temp.value;
            node.right = delete(node.right, temp.key, counter);
        }
        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
}
