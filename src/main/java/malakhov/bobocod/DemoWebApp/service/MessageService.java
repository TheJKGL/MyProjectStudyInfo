package malakhov.bobocod.DemoWebApp.service;

import malakhov.bobocod.DemoWebApp.annotation.Component;
import malakhov.bobocod.DemoWebApp.annotation.Trimmed;

@Component
public class MessageService {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(@Trimmed String message) {
        this.message = message;
    }
}
