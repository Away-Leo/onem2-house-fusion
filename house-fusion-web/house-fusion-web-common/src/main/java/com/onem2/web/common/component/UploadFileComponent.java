package com.onem2.web.common.component;

import com.onem2.fusion.base.CwException;
import com.onem2.fusion.base.fastdfs.FastDFSFile;
import com.onem2.fusion.base.fastdfs.FileManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传组件
 * Created by Administrator on 2017/6/2.
 */
@Service
public class UploadFileComponent {

    /**
     * 文件上传到fastdfs
     * @param file
     * @return
     */
    public String upload(MultipartFile file)
    {
        try {
            FileManager client = new FileManager();
            String fileName = file.getOriginalFilename();
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            FastDFSFile fastDFSFile = new FastDFSFile(fileName, file.getBytes(), prefix);
            return client.upload(fastDFSFile);
        }catch (Exception e)
        {
            CwException.throwIt("文件上传失败");
            return null;
        }
    }
}
