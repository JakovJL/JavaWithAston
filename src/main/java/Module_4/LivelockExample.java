package Module_4;

 class Bus {

    public String personEnteringTheBus;

}

class Traveller {
    private String name;
    private boolean isBlocked = true;
    private Bus bus;

    public Traveller(String name, Bus bus) {
        this.bus = bus;
        this.name = name;
    }

    public void moveInsideTheBus(Traveller fellowTvlr) throws InterruptedException {
        while (isBlocked) {

            System.out.println(name + ": После вас, " + fellowTvlr.name + "!");
            Thread.sleep(1500);

            if (!fellowTvlr.isBlocked()) {
                isBlocked = false;
                bus.personEnteringTheBus = name;
                System.out.println(name + " заходит в автобус!");
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

}

public class LivelockExample {

    public static void main(String[] args) {
        Bus bus = new Bus();
        Traveller tvlr1 = new Traveller("Петя", bus);
        Traveller tvlr2 = new Traveller("Ваня", bus);

        new Thread(() -> {
            try {
                tvlr1.moveInsideTheBus(tvlr2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                tvlr2.moveInsideTheBus(tvlr1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
