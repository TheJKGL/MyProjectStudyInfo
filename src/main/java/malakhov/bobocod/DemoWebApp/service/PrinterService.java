package malakhov.bobocod.DemoWebApp.service;


import malakhov.bobocod.DemoWebApp.annotation.Autowired;
import malakhov.bobocod.DemoWebApp.annotation.Component;

@Component
public class PrinterService {
    @Autowired
    private MessageService messageService;

    public void printMessage() {
        var message = messageService.getMessage();
        System.out.println("*" + message);
    }
}
