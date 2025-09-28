package PrototypeMethodDesignPattern;


// client code of Prototype method Design pattern
public class Main {
    public static void main(String[] args) {
        Colors BlackPrototypeObj = new BlackConcreteprototype("Black Color");
        Colors WhitePrototypeObj = new WhiteConcretePrototype("white color");


        Colors clonedBlackObj = BlackPrototypeObj.clone();
        Colors clonedWhiteObj = WhitePrototypeObj.clone();

//        Colors clonedBlackObj = new BlackConcreteprototype("Black Color").clone();
//        Colors clonedWhiteObj = new WhiteConcretePrototype("white color").clone();

        clonedBlackObj.setName("dark color");
        clonedWhiteObj.setName("light color");


        System.out.println("black color is " + clonedBlackObj.getName());
        System.out.println("while color is "+ clonedWhiteObj.getName());
    }
}
