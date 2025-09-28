package SingletonMP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // This first call tests the exception if you do something weird in tests
            DataManager dataManager = DataManager.getInstance();
        } catch (IllegalStateException e) {
            System.out.println("Caught IllegalStateException: " + e.getMessage());
        }

        DataManager dataManager = DataManager.getInstance();

        // Read items continuously without "done" sentinel
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.isEmpty()) break; // stop if user enters empty line
            dataManager.addItem(input);
        }

        // Read item to remove
        String itemToRemove = scanner.nextLine();
        dataManager.removeItem(itemToRemove);

        // Output final list
        for (String item : dataManager.getList()) {
            System.out.println(item);
        }

        scanner.close();
    }
}
