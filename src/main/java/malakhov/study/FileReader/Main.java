package malakhov.study.FileReader;

import malakhov.study.FileReader.files.File;
import malakhov.study.FileReader.files.JsonFile;
import malakhov.study.FileReader.files.XmlFile;

public class Main {

    // Система яка вміє читати з різних типів файлів. PDF,JSON,XML. Передаю в метод який читає файл.
    // Повертає Object з контентом і форматом файлу.

    //спробувати фабрику
    public static void main(String[] args) {

        File xmlFile = new XmlFile("name1");
        File jsonFile = new JsonFile("name2");

        FileReader fileReader = new FileReaderImpl();

        fileReader.read(jsonFile);
    }
}
