package malakhov.study.input_output_streams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new BufferedInputStream(System.in);
        int result = inputStream.read();
        System.out.println((byte) result);//ascii byte decimal
    }
}
