package Module_1n1;

// Интерфейсы для всех животных
interface HasSpine {
    Spine getSpine();
}

interface HasFur {
    Fur getFur();
}

interface LivesInWater {
    void swim();

    WaterEnvironment getEnvironment();
}

public class HierarchyOfAnimals {
    public static void main(String[] args) {

        System.out.println("Демонстрация поведения животных:");

        Cat cat = new Cat("Мурка", 2.5, "серый");

        // Для вывода позвонков
        Spine catSpine = cat.getSpine();
        int vertebraeCount = catSpine.getVertebraeCount();

        System.out.println("Кошка:");
        System.out.println("Имя " + cat.getName() + ", масса " + cat.getWeight() + ", цвет шерсти: " + cat.getFur().getColor()+"," +
                " у кота "+vertebraeCount+" позвонков");
        cat.eat();
        cat.sleep();
        cat.produceMilk();
        cat.maintainBodyTemperature();
        cat.meow();

        Bear bear = new Bear("Миша", 500, "коричневый");
        System.out.println("\nМедведь:");
        System.out.println("Имя " + bear.getName() + ", масса " + bear.getWeight() + ", цвет шерсти: " + bear.getFur().getColor());
        bear.eat();
        bear.hibernate();

        Whale whale = new Whale("Кит", 30000);
        System.out.println("\n" + whale.getName() + ":");
        System.out.println("Среда обитания: " + whale.getEnvironment().getType());
        whale.swim();
        whale.eat();
        whale.sleep();


        Fish fish = new Fish("Карась", 0.5, "пресная");
        System.out.println("\nРыба:");
        fish.swim();
        fish.eat();
        fish.layEggs();
    }
}

// Абстрактный класс для всех животных
abstract class Animal {
    private final String name;
    private final double weight;

    public Animal(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    // Общие абстрактные методы для всех животных
    public abstract void eat();

    public abstract void sleep();
}

// Абстрактный класс для млекопитающих
abstract class Mammal extends Animal implements HasSpine {
    private final Spine spine;

    public Mammal(String name, double weight) {
        super(name, weight);
        this.spine = new Spine();
    }

    @Override
    public Spine getSpine() {
        return spine;
    }

    // Методы, общие для всех млекопитающих
    public void produceMilk() {
        System.out.println("Производит молоко для вскармливания");
    }

    public void maintainBodyTemperature() {
        System.out.println("Поддержание постоянной температуры тела");
    }
}

// Класс для кошек
class Cat extends Mammal implements HasFur {
    private final Fur fur;

    public Cat(String name, double weight, String furColor) {
        super(name, weight);
        this.fur = new Fur(furColor);
    }

    @Override
    public Fur getFur() {
        return fur;
    }

    @Override
    public void eat() {
        System.out.println(this.getName()+" ест мясо");
    }

    @Override
    public void sleep() {
        System.out.println("Спит");
    }

    public void meow() {
        System.out.println("Мяу!");
    }
}

// Класс для медведей
class Bear extends Mammal implements HasFur {
    private final Fur fur;

    public Bear(String name, double weight, String furColor) {
        super(name, weight);
        this.fur = new Fur(furColor);
    }

    @Override
    public Fur getFur() {
        return fur;
    }

    @Override
    public void eat() {
        System.out.println(this.getName()+" ест рыбу и ягоды");
    }

    @Override
    public void sleep() {
        System.out.println("Спит в берлоге");
    }

    public void hibernate() {
        System.out.println("В спячке, не трогать!");
    }
}

// Класс для китов (млекопитающее, живущее в воде)
class Whale extends Mammal implements LivesInWater {
    private final WaterEnvironment environment;

    public Whale(String name, double weight) {
        super(name, weight);
        this.environment = new WaterEnvironment("Океан");
    }

    @Override
    public void swim() {
        System.out.println("Плывет");
    }

    @Override
    public WaterEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public void eat() {
        System.out.println("Ест планктон");
    }

    @Override
    public void sleep() {
        System.out.println("Кит спит");
    }

    public void produceSound() {
        System.out.println("Поет");
    }
}

// Класс для рыб
class Fish extends Animal implements LivesInWater {
    private final WaterEnvironment environment;

    public Fish(String name, double weight, String waterType) {
        super(name, weight);
        this.environment = new WaterEnvironment(waterType);
    }

    @Override
    public void swim() {
        System.out.println("Рыба плывет");
    }

    @Override
    public WaterEnvironment getEnvironment() {
        return environment;
    }

    @Override
    public void eat() {
        System.out.println("Рыба ест водоросли и других рыю");
    }

    @Override
    public void sleep() {
        System.out.println("Рыба спит");
    }

    public void layEggs() {
        System.out.println("Мечет икру");
    }
}

// Вспомогательные классы
class Spine {
    private final int vertebraeCount;

    public Spine() {
        this.vertebraeCount = 33; // Примерное количество для многих млекопитающих
    }

    public int getVertebraeCount() {
        return vertebraeCount;
    }
}

class Fur {
    private final String color;

    public Fur(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

class WaterEnvironment {
    private final String type;

    public WaterEnvironment(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
