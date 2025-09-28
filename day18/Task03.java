package java_tasks.Day18;
import java.util.Scanner;

public class Task03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        String input = scanner.nextLine();
        input = input.replace(",", "");
        try {
            Integer.parseInt(input);
            int numberOfDigits = input.length();
            System.out.println("It's a " + numberOfDigits + " digit number.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        scanner.close();
    }
}
