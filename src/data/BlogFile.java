package data;

import java.io.Serializable;

/**
 * Created by admin on 2022/7/29.
 */
public class BlogFile implements Serializable {
    private int id;
    private String fileName;
    private String filePath;
    private boolean isFile;

    public BlogFile() {
    }

    public BlogFile(int id, String fileName, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        isFile = fileName.contains(".");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        isFile = fileName.contains(".");
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isFile() {
        return isFile;
    }

    @Override
    public String toString() {
        return "BlogFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", isFile=" + isFile +
                '}';
    }
}
