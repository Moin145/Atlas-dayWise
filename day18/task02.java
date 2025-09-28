package java_tasks.Day18;
import java.util.Scanner;

public class task02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a 6-digit number: ");
        int number = scanner.nextInt();

        int[] digits = new int[6];
        int index = 0;

        while (number > 0 && index < 6) {
            digits[index] = number % 10;
            number /= 10;
            index++;
        }

        System.out.println("Units digit is " + digits[0]);
        System.out.println("Tens digit is " + digits[1]);
        System.out.println("Hundreds digit is " + digits[2]);
        System.out.println("Thousands digit is " + digits[3]);
        System.out.println("Ten thousands digit is " + digits[4]);
        System.out.println("Lakhs digit is " + digits[5]);

        scanner.close();
    }
}
