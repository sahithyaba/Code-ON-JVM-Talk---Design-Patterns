// FlyweightPattern.java
import java.util.*;

interface ShapeFly {
    void draw(int x, int y);
}

class CircleFly implements ShapeFly {
    private String color;
    public CircleFly(String color) { this.color = color; }
    public void draw(int x, int y) {
        System.out.println("Drawing " + color + " circle at (" + x + "," + y + ")");
    }
}

class ShapeFactoryFly {
    private static final Map<String, ShapeFly> circleMap = new HashMap<>();
    public static ShapeFly getCircle(String color) {
        ShapeFly circle = circleMap.get(color);
        if (circle == null) {
            circle = new CircleFly(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color " + color);
        }
        return circle;
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ShapeFly circle = ShapeFactoryFly.getCircle("Red");
            circle.draw(i, i * 2);
        }
    }
}
