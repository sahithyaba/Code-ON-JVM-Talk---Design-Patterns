// DecoratorPattern.java
interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double getCost() { return 5.0; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return decoratedCoffee.getDescription() + ", Milk"; }
    public double getCost() { return decoratedCoffee.getCost() + 1.0; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return decoratedCoffee.getDescription() + ", Sugar"; }
    public double getCost() { return decoratedCoffee.getCost() + 0.5; }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
