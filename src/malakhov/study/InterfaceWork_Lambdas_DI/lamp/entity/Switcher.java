package malakhov.study.InterfaceWork_Lambdas_DI.lamp.entity;

import java.util.ArrayList;

public class Switcher {
    private final ArrayList<ElectricityConsumer> listeners = new ArrayList<>();

    public void switchOn() {
        System.out.println("Switch ON!");
        for(ElectricityConsumer c : listeners){
            c.electricityOn();
        }
    }

    public void switchOff() {
        System.out.println("Switch OFF!");
        for(ElectricityConsumer c : listeners){
            c.electricityOff();
        }
    }

    public ElectricityConsumer getConsumer(int index) {
        return listeners.get(index);
    }

    public void setConsumer(ElectricityConsumer consumer) {
        listeners.add(consumer);
    }
}
