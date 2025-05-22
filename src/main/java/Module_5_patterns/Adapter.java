package Module_5_patterns;

import java.util.InputMismatchException;
import java.util.Scanner;

interface CelsiusSensor {
    double getTemperatureC();
}

public class Adapter {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            FahrenheitSensor fahrenheitSensor = new FahrenheitSensor();

            System.out.println("1. Enter temperature in Fahrenheit: ");
            double temp1 = getValidTemperature(sc);
            fahrenheitSensor.setTemperatureF(temp1);
            CelsiusSensor celsiusSensor = new FahrenheitToCelsiusAdapter(fahrenheitSensor);
            System.out.println("Temperature in Celsius: " + String.format("%.2f", celsiusSensor.getTemperatureC()));

            System.out.println("2. Enter temperature in Fahrenheit: ");
            double temp2 = getValidTemperature(sc);
            fahrenheitSensor.setTemperatureF(temp2);
            System.out.println("Another Temperature in Celsius: " + String.format("%.2f", celsiusSensor.getTemperatureC()));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static double getValidTemperature(Scanner sc) {
        while (true) {
            try {
                final double ABSOLUTE_ZERO_F = -459.67;
                double temperature = sc.nextDouble();
                if (temperature < ABSOLUTE_ZERO_F) {
                    throw new BelowAbsoluteZeroException("Temperature cannot be below absolute zero (" + ABSOLUTE_ZERO_F + ")");
                }
                return temperature;
            } catch (BelowAbsoluteZeroException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please enter a valid temperature: ");
                sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number: ");
                sc.next();
            }
        }
    }
}

class FahrenheitSensor {
    private double temperatureF;

    public FahrenheitSensor() {
        this.temperatureF = 77.0; // Default temperature
    }

    public double getTemperatureF() {
        return temperatureF;
    }

    public void setTemperatureF(double temperatureF) {
        this.temperatureF = temperatureF;
    }
}

class FahrenheitToCelsiusAdapter implements CelsiusSensor {
    private FahrenheitSensor sensor;

    public FahrenheitToCelsiusAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getTemperatureC() {
        double f = sensor.getTemperatureF();
        return (f - 32) * 5 / 9; // Конвертация в °C
    }
}

class BelowAbsoluteZeroException extends Exception {
    public BelowAbsoluteZeroException(String message) {
        super(message);
    }
}