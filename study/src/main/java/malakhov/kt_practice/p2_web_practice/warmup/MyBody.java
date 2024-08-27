package malakhov.kt_practice.p2_web_practice.warmup;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyBody {
    String firstName;
    String lastName;
    String city;
    List<String> sports;

    public MyBody() {
    }

    public MyBody(String firstName, String lastName, String city, List<String> sports) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.sports = sports;
    }
}
