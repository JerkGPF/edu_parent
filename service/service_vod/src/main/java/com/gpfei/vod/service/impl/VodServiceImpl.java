package com.gpfei.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.gpfei.servicebase.exceptionhandler.MyExpectation;
import com.gpfei.vod.service.VodService;
import com.gpfei.vod.utils.ConstantVodUtil;
import com.gpfei.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoALY(MultipartFile file) {

        try {
            String fileName = file.getOriginalFilename();

            String title = fileName.substring(0, fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtil.ACCESS_KEY_ID,ConstantVodUtil.ACCESS_KEY_SECRET,title,fileName,inputStream);
            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            }else {
                videoId = response.getVideoId();
            }
            return videoId;
        }catch (Exception e){
            throw new MyExpectation(20001, "guli vod 服务上传失败");
        }


    }

    @Override
    public boolean removeVideo(String videoId) {
        try{
            //初始化对象
            DefaultAcsClient acsClient = InitVodClient.initVodClient(ConstantVodUtil.ACCESS_KEY_ID, ConstantVodUtil.ACCESS_KEY_SECRET);
            //创建一个删除对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            acsClient.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            throw new MyExpectation(20001,"删除失败");
        }
    }

    //删除多个视频
    @Override
    public void removeMoreALYVideo(List<String> videIdList) {

        try{
            //初始化对象
            DefaultAcsClient acsClient = InitVodClient.initVodClient(ConstantVodUtil.ACCESS_KEY_ID, ConstantVodUtil.ACCESS_KEY_SECRET);
            //创建一个删除对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            String videoIds = StringUtils.join(videIdList.toArray(), ",");

            request.setVideoIds(videoIds);
            acsClient.getAcsResponse(request);
        } catch (Exception e) {
            throw new MyExpectation(20001,"删除失败");
        }

    }
}
