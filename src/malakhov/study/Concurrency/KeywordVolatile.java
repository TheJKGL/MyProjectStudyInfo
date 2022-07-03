package malakhov.study.Concurrency;

import java.util.Scanner;

public class KeywordVolatile {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        //main - пишущий поток
        //myThread2 - читающий поток
        //Когда потоки делят общие данные, мы должны использовать volatile
        myThread2.shutdown();
    }
}

class MyThread2 extends Thread{
    //ключевое слово volatile
    private volatile boolean running = true;
    public void run(){

        while (running){
            System.out.println("Hello");
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        this.running = false;
    }
}
