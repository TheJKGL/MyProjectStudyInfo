package malakhov.study.design_patterns.BuilderDesignPattern.withgenerics;

public class Car extends Vehicle {

    private final String make;
    private final String model;

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public Car(Builder<?> builder) {
        super(builder);
        this.make = builder.make;
        this.model = builder.model;
    }

    //Here we use generic as T extends Builder<T> in order to protect Builder from other class not Builders.
    public static class Builder<T extends Builder<T>> extends Vehicle.Builder<T> {

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

        @Override
        public Car build() {
            return new Car(this);
        }
    }
}