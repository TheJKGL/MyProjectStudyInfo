package malakhov.study.inheritance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        ReleaseB releaseB = new ReleaseB();
        releaseB.setNumber1(1);
        releaseB.setNumber2(2);

        InterfaceA interfaceA = releaseB;

        System.out.println(new ObjectMapper().writeValueAsString(interfaceA));
    }
}
