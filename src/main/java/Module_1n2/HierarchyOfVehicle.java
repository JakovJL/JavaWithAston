package Module_1n2;


interface HasWheels {
    Wheel[] getWheels();
}

interface HasPropeller {
    Propeller[] getPropellers();
}

interface HasWings {
    Wing[] getWings();
}

interface HasCargo {
    Cargo getCargo();
}

class Wheel {
    private final int wheelCount;


    Wheel(int wheelCount) {
        this.wheelCount = wheelCount;
    }

    public int getWheelCount() {
        return wheelCount;
    }
}

class Propeller {
    private final String typeOfPropeller;

    Propeller(String typeOfPropeller) {
        this.typeOfPropeller = typeOfPropeller;
    }

    public String getTypeOfPropeller() {
        return typeOfPropeller;
    }
}

class Cargo {
    private final int cargoWeight;

    Cargo(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }
}

class Wing {
    private final String typeOfWing;
    private final int rangeOfWing;

    Wing(String typeOfWing, int rangeOfWing) {
        this.typeOfWing = typeOfWing;
        this.rangeOfWing = rangeOfWing;
    }

    public String getTypeOfWing() {
        return typeOfWing;
    }

    public int getRangeOfWing() {
        return rangeOfWing;
    }
}

public class HierarchyOfVehicle {
    public static void main(String[] args) {
        // самолет
        Wing[] wings = {new Wing("Swept", 60),
                new Wing("Swept", 60)};

        Cargo cargo = new Cargo(5000);

        Wheel[] wheels = {new Wheel(2), // переднее сдвоенное
                          new Wheel(2), // задние сдвоенные  (справа, слева)
                          new Wheel(2)};

        Propeller[] propellers = {new Propeller("Turbofan"),
                                  new Propeller("Turbofan")};

        Airplane airplane = new Airplane("White", 5000, 3_000_000, "Boeing 747",
                15_000, 20_000, wings, cargo, wheels, propellers);
        System.out.println(airplane);
        airplane.launchEngine();
        airplane.stopEngine();

        //Вертолет
        Cargo heliCargo = new Cargo(2000);


        Wheel[] heliWheels = {new Wheel(1),
                new Wheel(1),
                new Wheel(1)};
        Propeller[] heliPropellers = {new Propeller("Main rotor"),
                                      new Propeller("Tail rotor")};

        Helicopter helicopter = new Helicopter("Green", 2000, 5000, "Mi-8",
                5500, 750, heliCargo, heliPropellers, heliWheels);
        System.out.println("\n" + helicopter);
        helicopter.launchEngine();
        helicopter.stopEngine();

        // катер
        Cargo boatCargo = new Cargo(500);
        Propeller[] boatPropellers = {new Propeller(" Main motor")};
        Boat boat = new Boat("red", 300, 2000, "Yamaha", 70, boatCargo, boatPropellers);
        System.out.println("\n" + boat);
        boat.launchEngine();
        boat.stopEngine();

        //танкер
        Tanker tanker = new Tanker("Blue",10_000, 1000000,"" +
                "Hyundai");

        // грузовик

        Truck truck = new Truck("Yellow",522,10_000,"" +
                "Man");
        // Такси

        Taxi taxi = new Taxi("Yellow",100,1200,"Lada vesta");



    }

}

// Абстрактный общий класс всех транспортных средств
abstract class Vehicle {

    private final String color;
    private final int horsePower;
    private final int weight;

    public Vehicle(String color, int horsePower, int weight) {

        this.color = color;
        this.horsePower = horsePower;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getWeight() {
        return weight;
    }

    public abstract void launchEngine();

    public abstract void stopEngine();

}

//  Класс самолет
class Airplane extends Vehicle implements HasWheels, HasPropeller, HasWings, HasCargo {
    private final String model;
    private final int maxFlightAltitude;
    private final int maxDistanceRange;
    private final Wing[] wings;
    private final Cargo cargo;
    private final Wheel[] wheels;
    private final Propeller[] propellers;


    public Airplane(String color, int horsePower, int weight, String model,
                    int maxFlightAltitude, int maxDistanceRange, Wing[] wings, Cargo cargo, Wheel[] wheels, Propeller[] propellers) {
        super(color, horsePower, weight);
        this.model = model;
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxDistanceRange = maxDistanceRange;
        this.wings = wings;
        this.cargo = cargo;
        this.wheels = wheels;
        this.propellers = propellers;
    }


    public String getModel() {
        return model;
    }

    public int getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    public int getMaxDistanceRange() {
        return maxDistanceRange;
    }

    // методы родителя
    @Override
    public void launchEngine() {
        System.out.println("Launch airplane Engine!");
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop airplane Engine!");
    }


    // методы из интерфейсов
    @Override
    public Cargo getCargo() {
        return this.cargo;
    }

    @Override
    public Propeller[] getPropellers() {
        return this.propellers;
    }

    @Override
    public Wheel[] getWheels() {
        return this.wheels;
    }

    @Override
    public Wing[] getWings() {
        return this.wings;
    }

    @Override
    public String toString() {
        return String.format(
                "Airplane \n[Model: %s | Altitude: %,d m | Range: %,d km | " +
                        "Wings: %d | Wheels: %d | Propellers: %d | Cargo: %,d kg]" +
                        "\n[Color: %s | Horse power: %d] ",
                model, maxFlightAltitude, maxDistanceRange,
                wings.length, wheels.length, propellers.length, cargo.getCargoWeight(),
                this.getColor(), this.getHorsePower()
        );
    }
}

class Helicopter extends Vehicle implements HasWheels, HasPropeller, HasCargo {
    private final String model;
    private final int maxFlightAltitude;
    private final int maxDistanceRange;
    private final Cargo cargo;
    private final Propeller[] propellers;
    private final Wheel[] wheels;


    public Helicopter(String color, int horsePower, int weight, String model, int maxFlightAltitude,
                      int maxDistanceRange, Cargo cargo, Propeller[] propellers, Wheel[] wheels) {
        super(color, horsePower, weight);
        this.model = model;
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxDistanceRange = maxDistanceRange;
        this.cargo = cargo;
        this.propellers = propellers;
        this.wheels = wheels;
    }

    //методы родителя
    @Override
    public void launchEngine() {
        System.out.println("Launch helicopter Engine!");
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop helicopter Engine!");
    }

    // методы интерфейса

    @Override
    public Cargo getCargo() {
        return this.cargo;
    }


    @Override
    public Propeller[] getPropellers() {
        return this.propellers;
    }

    @Override
    public Wheel[] getWheels() {
        return this.wheels;
    }


    @Override
    public String toString() {
        return String.format(
                "Helicopter \n[Model: %s | Altitude: %,d m | Range: %,d km | " +
                        "Wheels: %d | Propellers: %d | Cargo: %,d kg]" +
                        "\n[Color: %s | Horse power: %,d]",
                model, maxFlightAltitude, maxDistanceRange,
                wheels.length, propellers.length, cargo.getCargoWeight(),
                this.getColor(), this.getHorsePower()
        );
    }

}

class Boat extends Vehicle implements HasPropeller, HasCargo {
    private final String model;
    private final int maxSpeed;
    private final Cargo boatCargo;
    private final Propeller[] propellers;

    Boat(String color, int horsePower, int weight, String model, int maxSpeed,
         Cargo boatCargo, Propeller[] propellers) {
        super(color, horsePower, weight);
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.boatCargo = boatCargo;
        this.propellers = propellers;
    }

    @Override
    public Cargo getCargo() {
        return this.boatCargo;
    }

    @Override
    public Propeller[] getPropellers() {
        return this.propellers;
    }

    @Override
    public void launchEngine() {
        System.out.println("Launch boat Engine!");
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop boat Engine!");
    }

    @Override
    public String toString() {
        return "Boat{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", boatCargo=" + getCargo().getCargoWeight() +
                ", propellers=" + propellers.length +
                '}';
    }
}

// класс танкер
class Tanker extends Vehicle implements HasPropeller, HasCargo {
    private final String model;

    public Tanker(String color, int horsePower, int weight, String model) {
        super(color, horsePower, weight);
        this.model = model;
    }

    // интерфейс
    @Override
    public Cargo getCargo() {
        return null;
    }

    @Override
    public Propeller[] getPropellers() {
        return new Propeller[0];
    }

    // методы родителя
    @Override
    public void launchEngine() {

    }

    @Override
    public void stopEngine() {

    }

}

// класс грузовик
class Truck extends Vehicle implements HasWheels, HasCargo {
    private final String model;

    public Truck(String color, int horsePower, int weight, String model) {
        super(color, horsePower, weight);
        this.model = model;
    }

    @Override
    public Cargo getCargo() {
        return null;
    }

    @Override
    public Wheel[] getWheels() {
        return new Wheel[0];
    }

    @Override
    public void launchEngine() {

    }

    @Override
    public void stopEngine() {

    }
}

// класс такси
class Taxi extends Vehicle implements HasWheels, HasCargo {
    private final String model;

    public Taxi(String color, int horsePower, int weight, String model) {
        super(color, horsePower, weight);
        this.model = model;
    }

    @Override
    public Cargo getCargo() {
        return null;
    }

    @Override
    public Wheel[] getWheels() {
        return new Wheel[0];
    }

    @Override
    public void launchEngine() {

    }

    @Override
    public void stopEngine() {

    }
}





