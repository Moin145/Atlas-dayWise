package BridgeMethod;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bridge Method Design Pattern - Structural DP!");

        // Draw square using DrawingFrame
        Shape square1 = new Square(5, new DrawingFrame());
        square1.draw();

        // Draw square using DrawingPicture
        Shape square2 = new Square(10, new DrawingPicture());
        square2.draw();
    }
}