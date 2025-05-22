package Module_5_patterns;

interface ComputerBuilder {

    public ComputerBuilder installMotherboard();

    public ComputerBuilder installCPU();

    public ComputerBuilder installRAM();

    public ComputerBuilder installStorage();

    public Computer build();
}

public class Builder {
    public static void main(String[] args) {
        ComputerBuilder builder = new OfficeComputerBuilder();
        ComputerEngineer engineer = new ComputerEngineer(builder);
        Computer computer = engineer.assembleComputer();
        if (computer != null) {
            System.out.println("Ниже представлен собранный компьютер: ");
            System.out.println(computer);
        }
    }
}

class Computer {

    private String motherboard;
    private String cpu;
    private String ram;
    private String storage;

    public Computer() {
        super();
    }

    public Computer(String motherboard, String cpu, String ram, String storage) {
        this();
        this.motherboard = motherboard;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public boolean doQualityCheck() {
        return (motherboard != null && !motherboard.trim().isEmpty()) &&
                (cpu != null && !cpu.trim().isEmpty()) &&
                (ram != null && !ram.trim().isEmpty()) &&
                (storage != null && !storage.trim().isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Компьютер [").append(motherboard)
                .append(", ").append(cpu)
                .append(", ").append(ram)
                .append(", ").append(storage)
                .append("]");
        return builder.toString();
    }
}

class OfficeComputerBuilder implements ComputerBuilder {

    private String motherboard;
    private String cpu;
    private String ram;
    private String storage;

    public OfficeComputerBuilder() {
        super();
    }

    @Override
    public ComputerBuilder installMotherboard() {
        System.out.println("Установка материнской платы для офисного компьютера");
        this.motherboard = "Базовая офисная материнская плата";
        return this;
    }

    @Override
    public ComputerBuilder installCPU() {
        System.out.println("Установка процессора для офисного компьютера");
        this.cpu = "i3 12100";
        return this;
    }

    @Override
    public ComputerBuilder installRAM() {
        System.out.println("Установка оперативной памяти для офисного компьютера");
        this.ram = "kingston 8GB DDR4 RAM";
        return this;
    }

    @Override
    public ComputerBuilder installStorage() {
        System.out.println("Установка накопителя для офисного компьютера");
        this.storage = "kingston SSD 256GB";
        return this;
    }

    @Override
    public Computer build() {
        Computer computer = new Computer(motherboard, cpu, ram, storage);
        if (computer.doQualityCheck()) {
            return computer;
        } else {
            System.out.println("Сборка компьютера не завершена. Невозможно выпустить!");
        }
        return null;
    }
}

class HomeComputerBuilder implements ComputerBuilder {

    private String motherboard;
    private String cpu;
    private String ram;
    private String storage;

    public HomeComputerBuilder() {
        super();
    }

    @Override
    public ComputerBuilder installMotherboard() {
        System.out.println("Установка материнской платы для домашнего компьютера");
        this.motherboard = "Материнская плата для домашнего использования";
        return this;
    }

    @Override
    public ComputerBuilder installCPU() {
        System.out.println("Установка процессора для домашнего компьютера");
        this.cpu = "i5 12600";
        return this;
    }

    @Override
    public ComputerBuilder installRAM() {
        System.out.println("Установка оперативной памяти для домашнего компьютера");
        this.ram = "Crucial 16GB DDR4 RAM";
        return this;
    }

    @Override
    public ComputerBuilder installStorage() {
        System.out.println("Установка накопителя для домашнего компьютера");
        this.storage = "Crucial SSD 512GB + HDD 1TB";
        return this;
    }

    @Override
    public Computer build() {
        Computer computer = new Computer(motherboard, cpu, ram, storage);
        if (computer.doQualityCheck()) {
            return computer;
        } else {
            System.out.println("Сборка компьютера не завершена. Невозможно выпустить!");
        }
        return null;
    }
}

class GamingComputerBuilder implements ComputerBuilder {

    private String motherboard;
    private String cpu;
    private String ram;
    private String storage;

    public GamingComputerBuilder() {
        super();
    }

    @Override
    public ComputerBuilder installMotherboard() {
        System.out.println("Установка материнской платы для игрового компьютера");
        this.motherboard = "Геймерская материнская плата ROG";
        return this;
    }

    @Override
    public ComputerBuilder installCPU() {
        System.out.println("Установка процессора для игрового компьютера");
        this.cpu = "i9 12990k";
        return this;
    }

    @Override
    public ComputerBuilder installRAM() {
        System.out.println("Установка оперативной памяти для игрового компьютера");
        this.ram = "Crucial 32GB DDR5 RGB RAM";
        return this;
    }

    @Override
    public ComputerBuilder installStorage() {
        System.out.println("Установка накопителя для игрового компьютера");
        this.storage = "Crucial NVMe SSD 2TB";
        return this;
    }

    @Override
    public Computer build() {
        Computer computer = new Computer(motherboard, cpu, ram, storage);
        if (computer.doQualityCheck()) {
            return computer;
        } else {
            System.out.println("Сборка компьютера не завершена. Невозможно выпустить!");
        }
        return null;
    }
}

class ComputerEngineer {

    private ComputerBuilder builder;

    public ComputerEngineer(ComputerBuilder builder) {
        super();
        this.builder = builder;
        if (this.builder == null) {
            throw new IllegalArgumentException("Инженер не может работать без сборщика компьютеров!");
        }
    }

    public Computer assembleComputer() {
        return builder.installMotherboard().installCPU().installRAM().installStorage().build();
    }
}