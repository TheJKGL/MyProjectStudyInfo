package malakhov.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StringContainer {
    private List<String> values;
}
