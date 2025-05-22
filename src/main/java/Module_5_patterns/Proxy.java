package Module_5_patterns;

interface PackageRecipient {
    void receivePackage(String packageContent);
}

public class Proxy {
    public static void main(String[] args) {
        PackageRecipient recipient = new CourierProxy();
        recipient.receivePackage("Книга");
        recipient.receivePackage("взрывчатка");
        recipient.receivePackage(null);
        recipient.receivePackage("");
    }
}

class RealRecipient implements PackageRecipient {
    @Override
    public void receivePackage(String packageContent) {
        System.out.println("Посылка получена: " + packageContent);
    }
}

class CourierProxy implements PackageRecipient {
    private RealRecipient realRecipient;

    private boolean isPackageValid(String packageContent) {
        return packageContent != null && !packageContent.isEmpty() && !packageContent.contains("взрывчатка");
    }

    @Override
    public void receivePackage(String packageContent) {
        if (isPackageValid(packageContent)) {
            if (realRecipient == null) {
                realRecipient = new RealRecipient();
            }
            realRecipient.receivePackage(packageContent);
            System.out.println("Курьер передал посылку получателю.");
        } else {
            if (packageContent == null) {
                System.out.println("Курьер заблокировал посылку: null");
            } else if (packageContent.isEmpty()) {
                System.out.println("Курьер заблокировал посылку: пустая строка");
            } else {
                System.out.println("Курьер заблокировал посылку: " + packageContent);
            }
        }
    }
}