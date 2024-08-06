package malakhov.study.design_patterns.BuilderDesignPattern.withoutgenerics;

public class Test {
    public static void main(String[] args) {
        Car.CarBuilder carBuilder = new Car.CarBuilder();
        Car car = carBuilder
                .make("asdas")
                .model("sdad")
                .fuelType("dasd")
                .colour("dasd")
                .build();
        System.out.println(car);

        //Note!
        //However, for a child class of Car such as ElectricCar, we must override all the methods of CarBuilder
        // and VehicleBuilder in ElectricCarBuilder. Hence, it’s not a very efficient implementation.
    }
}
