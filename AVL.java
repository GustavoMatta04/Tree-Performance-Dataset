
            public class AVL extends BST {

            private int height(Node n) {
                return n == null ? 0 : n.height;
            }

            private int getBalance(Node n) {
                return n == null ? 0 : height(n.left) - height(n.right);
            }

            private Node rotateRight(Node y) {
                Node x = y.left;
                Node T2 = x.right;
                x.right = y;
                y.left = T2;
                y.height = Math.max(height(y.left), height(y.right)) + 1;
                x.height = Math.max(height(x.left), height(x.right)) + 1;
                return x;
            }

            private Node rotateLeft(Node x) {
                Node y = x.right;
                Node T2 = y.left;
                y.left = x;
                x.right = T2;
                x.height = Math.max(height(x.left), height(x.right)) + 1;
                y.height = Math.max(height(y.left), height(y.right)) + 1;
                return y;
            }

            @Override
            public Node insert(Node node, String key, int value, Counter counter) {
                counter.count++;
                if (node == null) return new Node(key, value);
                if (key.compareTo(node.key) < 0)
                    node.left = insert(node.left, key, value, counter);
                else if (key.compareTo(node.key) > 0)
                    node.right = insert(node.right, key, value, counter);
                else
                    return node;

                node.height = 1 + Math.max(height(node.left), height(node.right));
                int balance = getBalance(node);

                if (balance > 1 && key.compareTo(node.left.key) < 0)
                    return rotateRight(node);
                if (balance < -1 && key.compareTo(node.right.key) > 0)
                    return rotateLeft(node);
                if (balance > 1 && key.compareTo(node.left.key) > 0) {
                    node.left = rotateLeft(node.left);
                    return rotateRight(node);
                }
                if (balance < -1 && key.compareTo(node.right.key) < 0) {
                    node.right = rotateRight(node.right);
                    return rotateLeft(node);
                }
                return node;
            }
        }
