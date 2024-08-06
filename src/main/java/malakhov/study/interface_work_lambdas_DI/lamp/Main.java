package malakhov.study.interface_work_lambdas_DI.lamp;

import malakhov.study.interface_work_lambdas_DI.lamp.entity.ElectricityConsumer;
import malakhov.study.interface_work_lambdas_DI.lamp.entity.Lamp;
import malakhov.study.interface_work_lambdas_DI.lamp.entity.Radio;
import malakhov.study.interface_work_lambdas_DI.lamp.entity.Switcher;

public class Main {

    public  void fire(){
        System.out.println("Fire");
    }

    public static void main(String[] args) {
        Switcher sw = new Switcher();
        Lamp lamp = new Lamp();
        Radio radio = new Radio();

        //event subscribe
        sw.setConsumer(lamp);
        sw.setConsumer(radio);

        //Что делать если нам нужно просто вывести сообщение при включении или выключении:
        //1 вариант
        /*class Fire implements ElectricityConsumer{

            @Override
            public void electricityOn() {
                System.out.println("Fire!!!");
            }

            @Override
            public void electricityOff() {
                System.out.println("Fire!!!");
            }
        }
        sw.setConsumer(new Fire());*/

        //2 вариант (анонимный класс)
        sw.setConsumer(new ElectricityConsumer() {
            @Override
            public void electricityOn() {
                System.out.println("Fire!");
            }

            @Override
            public void electricityOff() {
                System.out.println("Fire!");
            }
        });

        //3 вариант (лямбда выражение)
        //sw.setConsumer(() -> System.out.println("Fire"));
        //sw.setConsumer(sender -> System.out.println("Fire"));
        //sw.setConsumer(s -> Main.fire(s));
        //sw.setConsumer(new Main()::fire);//видит метод с такими же параметрами

        sw.switchOn();
        sw.switchOff();

    }
}
