package java_tasks.Day15;

class SimpleBST {
    private Node root;
    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }
    public void insert(int data) {
        root = insertRecursive(root, data);
    }
    private Node insertRecursive(Node currentNode, int data) {
        if (currentNode == null) {
            return new Node(data);
        }
        if (data < currentNode.data) {
            currentNode.left = insertRecursive(currentNode.left, data);
        } else if (data > currentNode.data) {
            currentNode.right = insertRecursive(currentNode.right, data);
        }
        return currentNode;
    }
    public void printInOrder() {
        System.out.println("\n--- Task 3: In-Order Traversal of the Binary Search Tree ---");
        System.out.println("Result (should be sorted):");
        inOrderRecursive(this.root);
        System.out.println("\n");
    }
    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.data + " ");
            inOrderRecursive(node.right);
        }
    }
}
public class task04 {
    static class ManualNode {
        int data;
        ManualNode left;
        ManualNode right;

        ManualNode(int data) {
            this.data = data;
        }
    }
    public static void traverseManualTree(ManualNode node) {
        if (node != null) {
            traverseManualTree(node.left);
            System.out.print(node.data + " ");
            traverseManualTree(node.right);
        }
    }
    public static void main(String[] args) {

        System.out.println("--- Task 1: Manually Creating and Linking Nodes ---");

        ManualNode manualRoot = new ManualNode(100);
        ManualNode leftChild = new ManualNode(50);
        ManualNode rightChild = new ManualNode(150);
        ManualNode grandChild = new ManualNode(75);

        manualRoot.left = leftChild;
        manualRoot.right = rightChild;
        leftChild.right = grandChild;

        System.out.println("Manual tree created successfully.");
        System.out.println("Performing in-order traversal on the manual tree:");
        traverseManualTree(manualRoot); // Part of Task 3 is done here
        System.out.println("\n");

        System.out.println("--- Task 2: Creating a BST and Using its Insert Method ---");

        SimpleBST bst = new SimpleBST();

        bst.insert(50);

        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Binary Search Tree populated successfully using the .insert() method.");

        bst.printInOrder();
    }
}
