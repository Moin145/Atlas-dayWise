package AbstractFact;

// create samsung and oneplus classes..
public class Apple {
    private Apple() {
        // private constructor to prevent instantiation
    }

    public static Mobile getMobile(String model) {
        if ("iphone16".equalsIgnoreCase(model)) {
            return new Mobile("Here is your iPhone 16");
        } else if ("iphone16MaxPro".equalsIgnoreCase(model)) {
            return new Mobile("Here is your iPhone 16 Max Pro");
        } else {
            return new NoMobile();
        }
    }
}


