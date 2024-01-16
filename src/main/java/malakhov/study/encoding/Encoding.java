package malakhov.study.encoding;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Encoding {
    public static void main(String[] args) throws IOException {
        String input = "The façade pattern is a software design pattern.";
        String ascii = "US-ASCII";
        System.out.println(decodeText(input, ascii));

        String utf8 = "UTF-8";
        System.out.println(decodeText(input, utf8));

        String input2 = "Ї";
        System.out.println(convertToBinary(input2, utf8));
    }

    public static String decodeText(String input, String encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(input.getBytes()), Charset.forName(encoding)));
        return reader.readLine();
    }

    public static String convertToBinary(String input, String encoding)
            throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
                .encode(input)
                .array();
        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e ^ 255))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }
}
