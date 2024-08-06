package malakhov.study.design_patterns.BuilderDesignPattern.withgenerics;

public class Test {
    public static void main(String[] args) {
        Car.Builder<?> carBuilder = new Car.Builder<>();
        Car car = carBuilder.colour("red")
                .fuelType("Petrol")
                .model("F")
                .make("Ford")
                .build();

        ElectricCar.Builder ElectricCarBuilder = new ElectricCar.Builder();
        ElectricCar eCar = ElectricCarBuilder.make("Mercedes")
                .colour("White")
                .model("G")
                .fuelType("Electric")
                .batteryType("Lithium")
                .build();

    }
}
