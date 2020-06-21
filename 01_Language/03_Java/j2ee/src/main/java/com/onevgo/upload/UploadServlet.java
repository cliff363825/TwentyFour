package com.onevgo.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UploadServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File tempDir = new File(getServletContext().getRealPath("/temp"));
        File uploadDir = new File(getServletContext().getRealPath("/upload"));
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 1. 创建 DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 1.1 设置 Factory 的内存阈值，超过这个值则将内存中的数据写入到指定的临时目录中
        factory.setSizeThreshold(1024 * 1024); // 1M
        // 1.2 设置 Factory 的临时目录
        factory.setRepository(tempDir);

        // 2. 创建 ServletFileUpload
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        // 2.1 设置一个请求最大允许的大小
        fileUpload.setSizeMax(1024 * 1024 * 5); // 5M
        try {
            // 2.2 解析&处理请求
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 2.3 遍历 List<FileItem>
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    // 2.4 如果是表单域，则打印到控制台上
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    System.out.println(fieldName + ": " + value);
                } else {
                    // 2.5 如果是文件域，则保存在指定的文件目录
                    String fieldName = fileItem.getFieldName();
//                    String value = fileItem.getString(); // 二进制数据
                    String fileName = fileItem.getName();
                    String contentType = fileItem.getContentType();
                    long size = fileItem.getSize();

                    System.out.println("字段名：" + fieldName);
                    System.out.println("文件名： " + fileName);
                    System.out.println("Content-Type: " + contentType);
                    System.out.println("文件大小： " + size);

                    // 2.6 保存文件
                    File saveFile = new File(uploadDir, fileName);
                    System.out.println(saveFile);

                    InputStream is = fileItem.getInputStream();
                    FileOutputStream fos = new FileOutputStream(saveFile);
                    byte[] b = new byte[1024];
                    int len = 0;
                    while ((len = is.read(b)) != -1) {
                        fos.write(b, 0, len);
                    }
                    fos.close();
                    is.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
