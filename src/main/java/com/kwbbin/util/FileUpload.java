package com.kwbbin.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;

public class FileUpload {

    public static StringBuffer FileUpload(MultipartFile file, String url){
        //url为文件存放路径
        //文件上传
        if (file.isEmpty()) {
            System.out.println("文件不存在");
            return new StringBuffer("");
        }else {
            String trueFileName = file.getOriginalFilename();
            String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
            String fileName = System.currentTimeMillis()+"_"+ RamdomNum.getIntegerNum()+suffix;
            String path = url;
            File targetFile = new File(path, fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            //保存
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 设置图片位置
            StringBuffer sb = new StringBuffer();
            sb.append("/blog/file/");
            sb.append(fileName);
            return sb;

        }
    }

}
