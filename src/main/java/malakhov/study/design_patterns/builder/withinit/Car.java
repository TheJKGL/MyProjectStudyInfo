package malakhov.study.design_patterns.builder.withinit;

public class Car {

    private String make;
    private String model;

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public Car(Init<?> init) {
        this.make = init.make;
        this.model = init.model;
    }

    //In such way we encapsulate generics from user and user doesn't know anything's about generics
    public static abstract class Init<T extends Init<T>> {

        protected String make;
        protected String model;

        public T make(String make) {
            this.make = make;
            return self();
        }

        public T model(String model) {
            this.model = model;
            return self();
        }

        public Car build() {
            return new Car(this);
        }

        protected abstract T self();
    }

    public static class Builder extends Car.Init<Car.Builder> {
        protected Car.Builder self() {
            return this;
        }
    }
}