package com.onevgo.upload;

import com.onevgo.upload.beans.FileUploadBean;
import com.onevgo.upload.exceptions.InvalidExtNameException;
import com.onevgo.upload.db.UploadFileDao;
import com.onevgo.upload.utils.AppFileUploadProperties;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class AppFileUploadServlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "/WEB-INF/files/";

    private UploadFileDao dao = new UploadFileDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(AppFileUploadProperties.getInstance().getConfig("exts"));
        String fileMaxSize = AppFileUploadProperties.getInstance().getConfig("file.max.size");
        String requestMaxSize = AppFileUploadProperties.getInstance().getConfig("total.file.max.size");

        String path = "";

        File tempDir = new File(getServletContext().getRealPath("/temp"));
        File uploadDir = new File(getServletContext().getRealPath("/upload"));
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 500);
        factory.setRepository(tempDir);

        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setSizeMax(Long.parseLong(requestMaxSize));
        fileUpload.setFileSizeMax(Long.parseLong(fileMaxSize));
        try {
            // 把需要上传的 FileItem 都放入到该Map 中
            // 键：文件的待存放的路径， 值： 对应的 FileItem 对象
            Map<String, FileItem> uploadFiles = new HashMap<>();

            // 解析请求，得到FileItem的集合
            List<FileItem> items = fileUpload.parseRequest(request);

            // 1. 构建FileUploadBean 的集合， 同时填充 uploadFiles
            List<FileUploadBean> beans = buildFileUploadBeans(items, uploadFiles);

            // 2. 校验扩展名
            validateExtName(beans);

            // 3. 校验文件的大小： 在解析时，已经校验了，我们只需要通过异常得到结果

            // 4. 进行文件的上传操作
            upload(uploadFiles);

            // 5. 把上传的信息保存到数据库中
            saveBeans(beans);

            path = "/fileupload/success.jsp";
        } catch (Exception e) {
            e.printStackTrace();

            path = "/fileupload/app-file-upload.jsp";
            request.setAttribute("message", e.getMessage());
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    private void saveBeans(List<FileUploadBean> beans) {
        dao.save(beans);
    }

    private void upload(Map<String, FileItem> uploadFiles) {
        for (Map.Entry<String, FileItem> uploadFile : uploadFiles.entrySet()) {
            String filePath = uploadFile.getKey();
            FileItem fileItem = uploadFile.getValue();

            try {
                upload(filePath, fileItem.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void upload(String filePath, InputStream inputStream) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void validateExtName(List<FileUploadBean> beans) {
        String exts = AppFileUploadProperties.getInstance().getConfig("exts");
        List<String> extList = Arrays.asList(exts.split(","));

        for (FileUploadBean bean : beans) {
            String fileName = bean.getFileName();
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (!extList.contains(extName)) {
                throw new InvalidExtNameException(fileName + " 文件类型不合法");
            }
        }
    }

    private List<FileUploadBean> buildFileUploadBeans(List<FileItem> items, Map<String, FileItem> uploadFiles) {
        List<FileUploadBean> beans = new ArrayList<>();

        // 1. 遍历 FileItem 的集合, 先得到desc 的Map<String, String>, 其中键： fieldName(desc1, desc2)
        // 值 表单域对应字段的值
        Map<String, String> descs = new HashMap<>();
        for (FileItem item : items) {
            if (item.isFormField()) {
                try {
                    descs.put(item.getFieldName(), item.getString("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        // 2. 在遍历FileItem 的集合， 得到文件域的 FileItem 对象
        // 每个得到一个 FileItem 对象都创建一个 FileUploadBean 对象
        // 得到 fileName, 构建 filePath， 从1的Map 中得到当前 FileItem 对应的那个 desc
        // 使用 fieldName 后面的数字去匹配
        for (FileItem item : items) {
            if (!item.isFormField()) {
                String fieldName = item.getFieldName();
                String index = fieldName.substring(fieldName.length() - 1);

                String fileName = item.getName();
                String desc = descs.get("desc" + index);
                String filePath = getFilePath(fileName);

                FileUploadBean bean = new FileUploadBean(fileName, filePath, desc);
                beans.add(bean);

                uploadFiles.put(filePath, item);
            }
        }

        return beans;
    }

    private String getFilePath(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));
        Random random = new Random();
        int i = random.nextInt(10000);

        String filePath = getServletContext().getRealPath(FILE_PATH) + "/" + System.currentTimeMillis() + i + extName;

        return filePath;
    }
}
