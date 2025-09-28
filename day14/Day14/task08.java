package java_tasks.Day14;
import java.util.Stack;

public class task08 {
    public static void main(String[] args) {
        Stack<String> names = new Stack<>();

        System.out.println("Is stack empty? " + names.isEmpty());

        names.push("Apple");
        names.push("Ball");

        System.out.println("Stack: " + names);

        System.out.println("Is stack empty? " + names.isEmpty());

        names.pop();
        names.pop();

        System.out.println("Is stack empty after popping all? " + names.isEmpty());
    }
}