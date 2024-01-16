package malakhov.study.input_output_streams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DifferentWaysOfCreatingReadAndWriteStreams {

    public static void main(String[] args) throws IOException {

        //№1 read file name from console and read 1 byte from file.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        int variable = fileInputStream.read();

    }
}
