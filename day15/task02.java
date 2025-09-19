package java_tasks.Day15;

public class task02 {
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
        System.out.println("Tree contents (in order):");
        inOrderRecursive(root);
        System.out.println();
    }
    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.data + " ");
            inOrderRecursive(node.right);
        }
    }
    public static void main(String[] args) {
        task02 bst = new task02();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.printInOrder();
    }
}
