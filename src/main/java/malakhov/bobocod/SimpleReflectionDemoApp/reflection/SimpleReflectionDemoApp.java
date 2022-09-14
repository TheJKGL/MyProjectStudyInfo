package malakhov.bobocod.SimpleReflectionDemoApp.reflection;


import lombok.SneakyThrows;
import malakhov.bobocod.SimpleReflectionDemoApp.jackson.dto.Address;
import malakhov.bobocod.SimpleReflectionDemoApp.jackson.dto.User;

public class SimpleReflectionDemoApp {
    @SneakyThrows
    public static void main(String[] args) {
        var user = new User(
                123L,
                "Jackson",
                "Mapper",
                new Address("Kyiv", "Shevchenka", "2A")
        );

        Class<User> userClass = User.class;
        for (var field : userClass.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + " - " + field.getType() + " - " + field.get(user));
        }
    }
}
