package malakhov.study.initialization_order;

public class Test2 {
    public static void main(String[] args) {
        Test2 test = new Test2();
        test.run();
    }

    public void run(){
        Truck truck = new Truck(2008,"Nisssan",220);
    }


}
class Car {

    public static int carCounter = 10;

    private String description = "Абстрактная машина";

    public Car() {
        System.out.println(carCounter);
    }

    public String getDescription() {
        return description;
    }
}

class Truck extends Car {

    private static int truckCount = 0;

    private int yearOfManufacture;
    private String model;
    private int maxSpeed;

    public Truck(int yearOfManufacture, String model, int maxSpeed) {
        System.out.println(carCounter);
        this.yearOfManufacture = yearOfManufacture;
        this.model = model;
        this.maxSpeed = maxSpeed;

        Car.carCounter++;
        truckCount++;
    }
}
