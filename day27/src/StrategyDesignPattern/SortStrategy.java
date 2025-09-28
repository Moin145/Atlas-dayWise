package StrategyDesignPattern;

import java.util.*;

// Strategy interface
interface SortStrategy {
    void sort(List<String> items);
}

// Concrete Strategy 1: Alphabetical Sorting (case insensitive)
class AlphabeticalSort implements SortStrategy {
    @Override
    public void sort(List<String> items) {
        Collections.sort(items, String.CASE_INSENSITIVE_ORDER);
    }
}

// Concrete Strategy 2: Lengthwise Sorting
class LengthwiseSort implements SortStrategy {
    @Override
    public void sort(List<String> items) {
        Collections.sort(items, Comparator.comparingInt(String::length));
    }
}

// Context
class SortingStrategy {
    private SortStrategy strategy;   // current strategy
    private List<String> items;      // list of items

    public SortingStrategy() {
        items = new ArrayList<>();
    }

    // Set the sorting strategy dynamically
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    // Add item to list
    public void addItem(String item) {
        items.add(item);
    }

    // Remove item from list
    public void removeItem(String item) {
        items.remove(item);
    }

    // Perform sorting
    public void performSort() {
        if (strategy == null) {
            System.out.println("No sorting strategy set!");
            return;
        }
        strategy.sort(items);
    }

    // Get sorted list
    public List<String> getList() {
        return items;
    }
}
