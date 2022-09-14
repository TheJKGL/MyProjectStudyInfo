package malakhov.bobocod.DemoWebApp;

import lombok.SneakyThrows;
import malakhov.bobocod.DemoWebApp.context.ApplicationContextImpl;
import malakhov.bobocod.DemoWebApp.service.MessageService;
import malakhov.bobocod.DemoWebApp.service.PrinterService;

public class DemoWebApp {
    @SneakyThrows
    public static void main(String[] args) {
        var context = new ApplicationContextImpl("malakhov.bobocod.DemoWebApp");
        MessageService messageService = context.getBean(MessageService.class);
        System.out.println(messageService.getClass());
        messageService.setMessage("        Hello everyone! It's been a good time    ");
        PrinterService printerService = context.getBean(PrinterService.class);
        printerService.printMessage();
    }
}
