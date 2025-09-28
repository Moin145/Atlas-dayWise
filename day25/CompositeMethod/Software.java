package CompositeMethod;
// Leaf component
public class Software implements Company {
    private int id;
    private String name;

    public Software(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void displayName() {
        System.out.println("Software Dept: " + name);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Optional: for debugging
    @Override
    public String toString() {
        return "Software{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}