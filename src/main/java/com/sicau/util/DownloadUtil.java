package com.sicau.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ResultEnum;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @program: software-market
 * @description: 下载文件
 * @author: Lee
 * @create: 2019-03-04 15:27
 **/
public class DownloadUtil {
    /**
     * @param url 路径
     * @param filename 文件名
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description 
     * @date 2019/3/6 
     */
    public ResultVO downLoad(String url, String filename, HttpServletResponse httpServletResponse) {
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAIbfETF6Wukn9l";
        String accessKeySecret = "mbu2xEul5IS284yJMOmdZRkvlA2jwC";
        String bucketName = "lucism";
        String objectName = url;
        ResultVO resultVO=new ResultVO();
        try {
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            BufferedInputStream inputStream = new BufferedInputStream(ossObject.getObjectContent());
            BufferedOutputStream outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
            httpServletResponse.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            httpServletResponse.setContentType("multipart/form-data");
            byte[] content = new byte[1024];
            int L;
            while ((L = inputStream.read(content)) != -1) {
                outputStream.write(content, 0, L);
            }
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            ossClient.shutdown();
            resultVO.setStatus(ResultEnum.SUCCESS.getCode());
            resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultVO.setStatus(ResultEnum.RESOURCE_NOT_FOUND.getCode());
            resultVO.setMsg(ResultEnum.RESOURCE_NOT_FOUND.getMessage());
        }
        return resultVO;
    }
}
