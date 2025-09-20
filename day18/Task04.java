package java_tasks.Day18;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers separated by commas (e.g., 45,81,85,...): ");
        String input = scanner.nextLine();

        String[] numbers = input.split(",");
        List<Integer> array1 = new ArrayList<>();
        List<Integer> array2 = new ArrayList<>();
        List<Integer> array3 = new ArrayList<>();

        for (String numStr : numbers) {
            numStr = numStr.trim();
            try {
                int number = Integer.parseInt(numStr);
                int unitDigit = number % 10;

                if (unitDigit == 0) {
                    array1.add(number);
                } else if (unitDigit == 1) {
                    array2.add(number);
                } else if (unitDigit == 5) {
                    array3.add(number);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + numStr);
            }
        }

        System.out.println("Array 1 has : " + array1);
        System.out.println("Array 2 has : " + array2);
        System.out.println("Array 3 has : " + array3);

        scanner.close();
    }
}
