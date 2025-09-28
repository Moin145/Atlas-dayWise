import java.util.Stack;

public class task06 {
    public static void main(String[] args) {
        Stack<String> names = new Stack<>();

        names.push("Apple");
        names.push("Bob");
        names.push("Cat");
        names.push("Dog");

        System.out.println("Stack: " + names);

        String searchElement = "Cat";

        int position = names.search(searchElement);

        if (position == -1) {
            System.out.println(searchElement + " not found in the stack.");
        } else {
            System.out.println(searchElement + " found at position (from top): " + position);
        }
    }
}
//Here the search method returns the position counting from the top of the stack and if element is not found, it returns -1.
