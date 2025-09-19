package java_tasks.Day15;

class Node {

    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class task01 {
    public static void main(String[] args) {
        Node root = new Node(10);

        Node leftChild = new Node(5);
        Node rightChild = new Node(20);

        root.left = leftChild;
        root.right = rightChild;

        System.out.println("Root node's data: " + root.data);
        System.out.println("Root's left child's data: " + root.left.data);
        System.out.println("Root's right child's data: " + root.right.data);
    }
}
