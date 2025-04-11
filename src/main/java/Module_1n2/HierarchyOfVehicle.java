package Module_1n2;

import java.util.Arrays;

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
    public   int getWheelCount() {
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
class Cargo{
    private final int cargoWeight;
    Cargo(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }
    public int getCargoWeight() {
        return cargoWeight;
    }
}
class Wing{
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

        System.out.println("_____________GO__________");
        Wing[] wings = { new Wing("Swept", 60),
                         new Wing("Swept", 60) };
        Cargo cargo = new Cargo(5000);
        Wheel[] wheels = { new Wheel(1),
                           new Wheel(2),
                           new Wheel(2) };
        Propeller[] propellers = { new Propeller("Turbofan"),
                                   new Propeller("Turbofan") };

        Airplane airplane = new Airplane("White",5000,3_000_000,"Boeing 747",
                15_000,20_000,wings, cargo, wheels, propellers);
        System.out.println(airplane);


    }
    
}
// Абстрактный общий класс всех транспортных средств
abstract class Vehicle {

    private final String color;
    private final int horsePower;
    private final int weight;
    public Vehicle (String color,int horsePower,int weight) {

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
                "Airplane [Model: %s | Altitude: %,d m | Range: %,d km | " +
                        "Wings: %d | Wheels: %d | Propellers: %d | Cargo: %,d kg]",
                model, maxFlightAltitude, maxDistanceRange,
                wings.length, wheels.length, propellers.length, cargo.getCargoWeight()
        );
    }
}
//    class Helicopter extends Vehicle implements HasWheels, HasPropeller, HasCargo {
//    private final String model;
//    private final int maxFlightAltitude;
//    private final int maxDistanceRange;
//
//
//    public Helicopter(String color, int horsePower, int weight, String model,
//                      int maxFlightAltitude, int maxDistanceRange) {
//        super(color, horsePower, weight);
//        this.model = model;
//        this.maxFlightAltitude = maxFlightAltitude;
//        this.maxDistanceRange = maxDistanceRange;
//    }
//    public Helicopter(){
//        super("White",3500,20_000);
//        this.model = "Boeing 747";
//        this.maxFlightAltitude = 10_000;
//        this.maxDistanceRange = 15_000;
//
//    }
//// методы интефейсов
//
////методы родителя
//    @Override
//    public void launchEngine() {
//        System.out.println("Launch airplane Engine!");
//    }
//
//    @Override
//    public void stopEngine() {
//        System.out.println("Stop airplane Engine!");
//    }
//
//}





