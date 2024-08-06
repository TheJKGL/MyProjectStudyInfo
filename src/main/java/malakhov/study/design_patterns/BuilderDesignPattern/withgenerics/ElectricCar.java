package malakhov.study.design_patterns.BuilderDesignPattern.withgenerics;

public class ElectricCar extends Car {

    private final String batteryType;

    public String getBatteryType() {
        return batteryType;
    }

    //We can remove generic from Builder if we sure that ElectricCar is tha last in hierarchy
    public ElectricCar(Builder builder) {
        super(builder);
        this.batteryType = builder.batteryType;
    }

    public static class Builder extends Car.Builder<Builder> {
        protected String batteryType;

        public Builder batteryType(String batteryType) {
            this.batteryType = batteryType;
            return self();
        }

        @Override
        public ElectricCar build() {
            return new ElectricCar(this);
        }
    }
}