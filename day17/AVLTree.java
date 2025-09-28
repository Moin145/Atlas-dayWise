package java_tasks.Day17;

public class AVLTree {

    class Node {
        int key;
        int height;
        Node left, right;
        Node(int value) {
            this.key = value;
            this.height = 1;
        }
    }
    Node root;
    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }
    private int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    private Node rightRotate(Node y) {
        System.out.println("... Performing Right Rotation on Node: " + y.key);
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }
    private Node leftRotate(Node x) {
        System.out.println("... Performing Left Rotation on Node: " + x.key);
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
    public void insert(int key) {
        System.out.println("\nInserting node: " + key);
        root = insertRecursive(root, key);
    }
    private Node insertRecursive(Node node, int key) {
        if (node == null) {
            return (new Node(key));
        }
        if (key < node.key) {
            node.left = insertRecursive(node.left, key);
        } else if (key > node.key) {
            node.right = insertRecursive(node.right, key);
        } else {
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
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
    public void display() {
        System.out.print("Current Tree (In-order): ");
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.display();

        tree.insert(20);
        tree.display();

        tree.insert(30);
        tree.display();

        tree.insert(40);
        tree.display();

        tree.insert(50);
        tree.display();

        tree.insert(25);
        tree.display();
        System.out.println("\n--- Final Tree State ---");
        tree.display();
    }
}
