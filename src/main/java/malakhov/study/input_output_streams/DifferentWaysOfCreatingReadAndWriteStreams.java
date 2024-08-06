package malakhov.study.input_output_streams;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class DifferentWaysOfCreatingReadAndWriteStreams {

    public static void main(String[] args) throws IOException {

        //№1 read file name from console and read 1 byte from file.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        OutputStream outputStream = new ByteArrayOutputStream();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        int variable = fileInputStream.read();

        //№2 read by one byte while it`s available
        while (fileInputStream.available() > 0) {
            int var = fileInputStream.read();
        }

        //№3 read all file into buffer
        byte[] buffer = new byte[fileInputStream.available()];
        int readSize = fileInputStream.read(buffer);

        //№4
        FileReader fileReader = new FileReader(fileName);
        while (fileReader.ready()) {
            int temp = fileReader.read();
        }

        //№5
        int symbol = fileReader.read();
        while (symbol != -1) {
            symbol = fileReader.read();
        }

        fileInputStream.close();
        fileReader.close();

        //№6
        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName));
        while (bufferedFileReader.ready()) {
            String[] splittedLine = bufferedFileReader.readLine().split(" ");
        }

        //№7
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        printWriter.print("1");

        //№8
        PrintWriter printWriter2 = new PrintWriter(outputStream);
        printWriter2.println("1");

        //№9
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("cat.dat"));

    }
}
