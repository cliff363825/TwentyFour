package com.onevgo.upload.beans;

public class FileUploadBean {
    private Integer id;
    private String fileName;
    private String filePath;
    private String fileDesc;

    public FileUploadBean() {
    }

    public FileUploadBean(String fileName, String filePath, String fileDesc) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileDesc = fileDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }
}
