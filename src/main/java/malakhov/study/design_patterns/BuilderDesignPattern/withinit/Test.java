package malakhov.study.design_patterns.BuilderDesignPattern.withinit;

public class Test {
    public static void main(String[] args) {
        Car car = new Car.Builder()
                .make("German")
                .model("BMW")
                .build();
        
    }
}
