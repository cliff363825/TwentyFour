package com.onevgo.upload.db;

import com.onevgo.upload.beans.FileUploadBean;

import java.sql.Connection;
import java.util.List;


public class UploadFileDao extends DAO<FileUploadBean> {

    public List<FileUploadBean> getFiles() {

        Connection conn = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT id, file_name fileName, file_path filePath, " +
                    "file_desc fileDesc FROM upload_files";
            return getForList(conn, sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn);
        }

        return null;
    }

    public void save(List<FileUploadBean> uploadFiles) {

        Connection conn = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO upload_files (file_name, file_path, file_desc) VALUES " +
                    "(?, ?, ?)";
            for (FileUploadBean file : uploadFiles) {
                update(conn, sql, file.getFileName(), file.getFilePath(), file.getFileDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn);
        }

    }

}
