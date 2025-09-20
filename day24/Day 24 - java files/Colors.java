package PrototypeMethodDesignPattern;

//Prototype interface
public interface Colors {
    Colors clone();
    String getName();
    void setName(String name);
}


