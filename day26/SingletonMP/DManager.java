package SingletonMP;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DataManager {
    private static DataManager instance;
    private List<String> itemList;

    // Private constructor for singleton; throws exception if already initialized
    private DataManager() {
        if (instance != null) {
            throw new IllegalStateException("Singleton instance already created. Use getInstance() method.");
        }
        itemList = new ArrayList<>();
    }

    // Thread-safe singleton instance getter
    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Synchronized list operations
    public synchronized void addItem(String item) {
        itemList.add(item);
    }

    public synchronized void removeItem(String item) {
        itemList.remove(item);
    }

    public synchronized List<String> getList() {
        return new ArrayList<>(itemList);
    }
}

// --- Non-editable starts here ---
