package malakhov.study.FileReader;

import malakhov.study.FileReader.files.File;

public interface FileReader {

    <T extends File> Object read(T file);

    Object read2(File file);
}
