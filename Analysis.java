public class Analysis {

    public static void printInOrder(Node node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.println(node.key + ": " + node.value);
        printInOrder(node.right);
    }

    public static void printInRange(Node node, int anoMin, int anoMax) {
        if (node == null) return;
        String[] parts = node.key.split("_");
        int ano = Integer.parseInt(parts[1]);
        if (ano >= anoMin && ano <= anoMax)
            System.out.println(node.key + ": " + node.value);
        printInRange(node.left, anoMin, anoMax);
        printInRange(node.right, anoMin, anoMax);
    }

    public static int totalCasos(Node node) {
        if (node == null) return 0;
        return node.value + totalCasos(node.left) + totalCasos(node.right);
    }

    public static Node findMax(Node node) {
        if (node == null) return null;
        Node max = node;
        Node leftMax = findMax(node.left);
        Node rightMax = findMax(node.right);
        if (leftMax != null && leftMax.value > max.value) max = leftMax;
        if (rightMax != null && rightMax.value > max.value) max = rightMax;
        return max;
    }
}
