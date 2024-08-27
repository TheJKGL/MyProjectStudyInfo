package malakhov.study.file_reader.files;

public interface File {
    void setFileName(String filename);
    String getFileName();

    void setFileExtension(String fileExtension);
    String getFileExtension();
}
