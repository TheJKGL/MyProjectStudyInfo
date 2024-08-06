package malakhov.study.file_reader;

import malakhov.study.file_reader.files.File;

public interface FileReader {

    <T extends File> Object read(T file);

    Object read2(File file);
}
