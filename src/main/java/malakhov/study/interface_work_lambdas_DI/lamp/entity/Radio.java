package malakhov.study.interface_work_lambdas_DI.lamp.entity;

public class Radio implements ElectricityConsumer{

    public void radioOn(){
        System.out.println("Radio ON");
    }

    public void radioOff(){
        System.out.println("Radio OFF");
    }

    @Override
    public void electricityOn() {
        radioOn();
    }

    @Override
    public void electricityOff() {
        radioOff();
    }
}
