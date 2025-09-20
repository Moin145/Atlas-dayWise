package SortingStrategyDesignPattern;

import java.util.*;

// Strategy interface
interface SortStrategy {
    void sort(List<String> items);
}

// Concrete Strategy 1: Alphabetical (case-insensitive)
class AlphabeticalSort implements SortStrategy {
    @Override
    public void sort(List<String> items) {
        Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
    }
}

// Concrete Strategy 2: Lengthwise
class LengthwiseSort implements SortStrategy {
    @Override
    public void sort(List<String> items) {
        Collections.sort(items, Comparator.comparingInt(String::length));
    }
}

// Context
class SortingStrategy {
    private SortStrategy strategy;      // current strategy
    private List<String> items;         // list of items

    public SortingStrategy() {
        items = new ArrayList<>();
    }

    public void setStrategyForSorting(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void performSort() {
        if (strategy == null) {
            System.out.println("No sorting strategy set!");
            return;
        }
        strategy.sort(items);
    }

    public List<String> getList() {
        return items;
    }
}