package malakhov.study.input_output_streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

//В этой задаче тебе нужно:
//Считать с консоли путь к файлу.
//Вывести в консоли (на экран) содержимое файла.
//Освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
public class ReadFileNameFromConsole {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sourceFileName = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(sourceFileName);

        Scanner scanner = new Scanner(fileInputStream);
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append("\n");
        }

        System.out.print(builder.toString());

        scanner.close();
        reader.close();
    }

    //В этой задаче тебе нужно:
    //Прочесть с консоли имя файла.
    //Считывать строки с консоли, пока пользователь не введет строку "exit".
    //Записать абсолютно все введенные в п.2 строки в файл: каждую строчку — с новой строки.
    public static void fileWriter() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String destinationFileName = reader.readLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName));

        StringBuilder builder = new StringBuilder();

        String s = "";
        while (!s.equals("exit")) {
            s = reader.readLine();
            builder.append(s).append("\n");
        }

        writer.write(builder.toString());
        writer.close();
    }
}
