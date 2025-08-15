// FactoryPattern.java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() { System.out.println("○"); }
}

class Rectangle implements Shape {
    public void draw() { System.out.println("▭"); }
}

class ShapeFactory {
    public static Shape create(String type) {
        if (type.equalsIgnoreCase("circle")) return new Circle();
        if (type.equalsIgnoreCase("rect")) return new Rectangle();
        throw new IllegalArgumentException("Unknown shape");
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Shape s1 = ShapeFactory.create("circle");
        Shape s2 = ShapeFactory.create("rect");
        s1.draw();
        s2.draw();
    }
}
