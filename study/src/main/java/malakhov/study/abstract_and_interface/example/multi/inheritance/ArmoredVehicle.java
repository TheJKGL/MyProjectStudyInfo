package malakhov.study.abstract_and_interface.example.multi.inheritance;

/**
 * Created by Администратор on 27.01.2016.
 */
public class ArmoredVehicle implements GroundTransport , WaterTransport {

    private int speed;
    private boolean inWater;

    public ArmoredVehicle(int speed, boolean inWater) {
        this.speed = speed;
        this.inWater = inWater;
    }

    @Override
    public void move() {
        if( inWater ){
            WaterTransport.super.move();// TODO: 14.07.2021  
        }else{
            GroundTransport.super.move();
        }
    }

    @Override
    public int speed() {
        return speed;
    }
}
