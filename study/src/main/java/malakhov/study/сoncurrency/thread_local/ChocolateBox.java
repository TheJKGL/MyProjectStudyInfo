package malakhov.study.сoncurrency.thread_local;

public class ChocolateBox {
    private ThreadLocal<Integer> chocolates = ThreadLocal.withInitial(() -> 5);


    public void eat() {
        Integer availableChocolate = chocolates.get();

        if (availableChocolate == 0) {
            System.out.println("Dear " + Thread.currentThread().getName() + " You've eaten all you chocolates ");
        } else {
            chocolates.set(--availableChocolate);
            System.out.println("Dear " + Thread.currentThread().getName() + " You ate one chocolate");
        }
    }

    public void availableChocolates() {
        System.out.println("Dear " + Thread.currentThread().getName() + " You have "
                + chocolates.get() + " Chocolates");
    }

}

class KidsEatingChocolate {

    public static void main(String[] args) {

        ChocolateBox chocolateBox = new ChocolateBox();

        Thread kid1 = new Thread(() -> {
            chocolateBox.eat();
            chocolateBox.eat();
            chocolateBox.eat();
            chocolateBox.availableChocolates();
        }, "KidOne");

        Thread kid2 = new Thread(() -> {
            chocolateBox.eat();
            chocolateBox.availableChocolates();
            chocolateBox.eat();
            chocolateBox.availableChocolates();
        }, "KidTwo");

        kid1.start();
        kid2.start();
    }
}