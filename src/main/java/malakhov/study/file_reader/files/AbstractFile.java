package malakhov.study.file_reader.files;

import java.util.Objects;

public abstract class AbstractFile implements File {
    private String fileName;
    private String fileExtension;

    public AbstractFile() {
    }

    public AbstractFile(String fileName, String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFile that)) return false;
        return Objects.equals(fileName, that.fileName)
                && Objects.equals(fileExtension, that.fileExtension);
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (fileExtension != null ? fileExtension.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomFile{" +
                "fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
