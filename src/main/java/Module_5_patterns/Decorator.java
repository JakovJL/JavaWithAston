package Module_5_patterns;

abstract class BeverageBase {
    public enum Size { SMALL, MEDIUM, LARGE }
    protected Size size = Size.MEDIUM;
    public void setSize(Size size) {
        this.size = size;
    }
    public Size getSize() {
        return size;
    }
    protected String description = "";
    public String getDescription() {
        return description;
    }
    public abstract double getCost();
}

class BlackTea extends BeverageBase {
    public BlackTea() {
        description = "Black tea from teabag";
    }

    @Override
    public double getCost() {
        return switch (size) {
            case SMALL -> 5;
            case MEDIUM -> 6;
            case LARGE -> 7;
        };
    }
}

class GreenTea extends BeverageBase {
    public GreenTea() {
        description = "Green leaf tea";
    }

    @Override
    public double getCost() {
        return switch (size) {
            case SMALL -> 8;
            case MEDIUM -> 9;
            case LARGE -> 10;
        };
    }
}

class Espresso extends BeverageBase {
    public Espresso() {
        description = "Espresso";
    }
    @Override
    public double getCost() {
        return switch (size) {
            case SMALL -> 10;
            case MEDIUM -> 12;
            case LARGE -> 14;
        };
    }
}

abstract class CondimentsDecoratorBase extends BeverageBase {
}

class MilkCondiment extends CondimentsDecoratorBase {
    private BeverageBase beverage;
    public MilkCondiment(BeverageBase beverage) {
        this.beverage = beverage;
        description = this.beverage.getDescription() + " + Milk";
    }
    @Override
    public double getCost() {
        return beverage.getCost() + 3;
    }
}

class ChocolateCondiment extends CondimentsDecoratorBase {
    private BeverageBase beverage;
    public ChocolateCondiment(BeverageBase beverage) {
        this.beverage = beverage;
        description = this.beverage.getDescription() + " + Chocolate";
    }
    @Override
    public double getCost() {
        return beverage.getCost() + 4;
    }
}

class CinnamonCondiment extends CondimentsDecoratorBase {
    private BeverageBase beverage;
    public CinnamonCondiment(BeverageBase beverage) {
        this.beverage = beverage;
        description = beverage.getDescription() + " + Cinnamon";
    }
    @Override
    public double getCost() {
        return beverage.getCost() + 2;
    }
}

class SyrupCondiment extends CondimentsDecoratorBase {
    private BeverageBase beverage;
    public SyrupCondiment(BeverageBase beverage) {
        this.beverage = beverage;
        description = beverage.getDescription() + " + Syrup";
    }
    @Override
    public double getCost() {
        return beverage.getCost() + 3;
    }
}

class SugarCondiment extends CondimentsDecoratorBase {
    private BeverageBase beverage;
    public SugarCondiment(BeverageBase beverage) {
        this.beverage = beverage;
        description = beverage.getDescription() + " + Sugar";
    }
    @Override
    public double getCost() {
        return beverage.getCost() + 1;
    }
}

public class Decorator {
    static void printBeverage(BeverageBase beverage) {
        System.out.println("Beverage: " + beverage.getDescription()
                + ", Cost: " + beverage.getCost());
    }

    public static void main(String args[]) throws Exception {
        BeverageBase espresso = new Espresso();
        BeverageBase blackTea = new BlackTea();
        BeverageBase greenTea = new GreenTea();
        espresso.setSize(BeverageBase.Size.SMALL);
        printBeverage(espresso);
        blackTea.setSize(BeverageBase.Size.LARGE);
        printBeverage(blackTea);
        printBeverage(greenTea);

        System.out.println("========================");
        BeverageBase capuccino = new SugarCondiment(new MilkCondiment(new Espresso()));
        printBeverage(capuccino);
        BeverageBase greenTeaWithSugar = new SugarCondiment(new GreenTea());
        printBeverage(greenTeaWithSugar);

        BeverageBase mixBlackTea = new ChocolateCondiment(new CinnamonCondiment(new BlackTea()));
        printBeverage(mixBlackTea);

    }
}