package java_tasks.Day14;
import java.util.Stack;

public class task07 {
    public static void main(String[] args) {
        Stack<String> names = new Stack<>();

        names.push("Apple");
        names.push("Ball");
        names.push("Cat");
        names.push("Dog");

        System.out.println("Stack: " + names);

        String topElement = names.peek();

        System.out.println("Top element (peek): " + topElement);

        System.out.println("Stack after peek: " + names);
    }
}
