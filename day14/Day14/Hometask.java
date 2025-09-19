package java_tasks.Day14;
import java.util.*;
import java.util.stream.Collectors;

public class Hometask {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");

        List<String> stackList = new ArrayList<>(stack);
        System.out.println("Stack elements:");
        stackList.stream().forEach(System.out::println);

        Deque<String> deque = new ArrayDeque<>();
        deque.add("Dog");
        deque.add("Elephant");
        deque.add("Frog");

        List<String> dequeList = new ArrayList<>(deque);
        System.out.println("Deque elements:");
        dequeList.stream().forEach(System.out::println);
    }
}
