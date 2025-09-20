// FactoryMethodPizza.java

interface Pizza {
    void preparation();
    void baking();
    void cutting();
    void boxing();
}

// Concrete Product
class PepperoniPizza implements Pizza {
    public void preparation() {
        System.out.println("Preparing Pepperoni Pizza ");
    }

    public void baking() {
        System.out.println("Baking Pepperoni Pizza ");
    }

    public void cutting() {
        System.out.println("Cutting Pepperoni Pizza .");
    }

    public void boxing() {
        System.out.println("Boxing Pepperoni Pizza .");
    }
}

// Creator
abstract class PizzaFactory {
    public abstract Pizza createPizza();
}

// Concrete Creator
class PepperoniPizzaFactory extends PizzaFactory {
    public Pizza createPizza() {
        return new PepperoniPizza();
    }
}

// Driver Class
public class FactoryMethodPizza {
    public static void main(String[] args) {
        PizzaFactory pfobj = new PepperoniPizzaFactory();
        Pizza pobj = pfobj.createPizza();

        pobj.preparation();
        pobj.baking();
        pobj.cutting();
        pobj.boxing();
    }
}
