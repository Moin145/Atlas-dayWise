package java_tasks.Day17;

class AVLTree01 {
    class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }
    Node root;
    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }
    private int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
    public void insert(int key) {
        root = insertNode(root, key);
    }
    private Node insertNode(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertNode(node.left, key);
        else if (key > node.key) node.right = insertNode(node.right, key);
        else return node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key) return rightRotate(node);
        if (balance < -1 && key > node.right.key) return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    public void displayInOrder() {
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    public static void main(String[] args) {
        AVLTree01 tree = new AVLTree01();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Final tree structure (in-order traversal):");
        tree.displayInOrder();
    }
}
