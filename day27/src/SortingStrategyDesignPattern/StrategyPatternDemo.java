package SortingStrategyDesignPattern;
import java.util.Scanner;
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SortingStrategy context = new SortingStrategy();

        System.out.println("Enter items (type 'Done' to finish):");

        // Take input until "Done"
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Done")) {
                break;
            }
            context.addItem(input);
        }

        // Perform Alphabetical Sort
        context.setStrategyForSorting(new AlphabeticalSort());
        context.performSort();
        System.out.println("\nSorted Alphabetically (case insensitive):");
        for (String item : context.getList()) {
            System.out.println(item);
        }

        // Perform Lengthwise Sort
        context.setStrategyForSorting(new LengthwiseSort());
        context.performSort();
        System.out.println("\nSorted by Length:");
        for (String item : context.getList()) {
            System.out.println(item);
        }

        scanner.close();
    }
}