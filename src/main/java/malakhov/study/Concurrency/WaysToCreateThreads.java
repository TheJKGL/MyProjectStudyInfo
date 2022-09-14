package malakhov.study.Concurrency;

public class WaysToCreateThreads {
    public static void main(String[] args) throws InterruptedException{
        MyThread myThread = new MyThread();
        myThread.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();

        Thread thread = new Thread(new Runner());
        thread.start();

        /*Thread myThread3= new Thread(){
            @Override
            public void run() {
                System.out.println("Hello from Thread3");
            }
        };*/
        Thread myThread3= new Thread(() -> System.out.println("Hello from Thread3"));
        myThread3.start();
    }
}
class Runner implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            System.out.println("Hello from MyThread "+i);
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 1000; i++){
            System.out.println("Hello from MyThread "+i);
        }
    }
}
