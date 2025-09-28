package CompositeMethod;

public class HR implements Company {
    private int id;
    private String name;

    public HR(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void displayName() {
        System.out.println("HR Dept: " + name);
    }
}