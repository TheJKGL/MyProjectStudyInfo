package malakhov.study.file_reader;

import malakhov.study.file_reader.files.File;
import malakhov.study.file_reader.files.JsonFile;

public class FileReaderImpl implements FileReader {

    @Override
    public <T extends File> Object read(T file) {
        if (file instanceof JsonFile) {
            return readJson(file);
        } else {
            return readXml(file);
        }

    }

    @Override
    public Object read2(File file) {
        if (file instanceof JsonFile) {
            return readJson(file);
        } else {
            return readXml(file);
        }
    }

    private Object readXml(File file) {
        System.out.println("XmlReader started reading next file:");
        System.out.println(file);
        return file;
    }

    private Object readJson(File file) {
        System.out.println("JsonReader started reading next file:");
        System.out.println(file);
        return file;
    }
}
