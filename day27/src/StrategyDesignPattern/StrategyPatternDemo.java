package StrategyDesignPattern;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        SortingStrategy context = new SortingStrategy();

        // Adding items
        context.addItem("Stanford");
        context.addItem("Ankit");
        context.addItem("Watson");
        context.addItem("Done");

        // Alphabetical sort
        System.out.println("Alpha sorting:");
        context.setStrategy(new AlphabeticalSort());
        context.performSort();
        for (String item : context.getList()) {
            System.out.println(item);
        }

        // Lengthwise sort
        System.out.println("\nLengthwise sorting:");
        context.setStrategy(new LengthwiseSort());
        context.performSort();
        for (String item : context.getList()) {
            System.out.println(item);
        }
    }
}