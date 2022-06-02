package com.gpfei.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

public class VodTest {
    public static void main(String[] args) throws Exception{
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient("LTAI5tCdFTtCYRXFa8FgEbv7", "7lqtVsc3htMIDW6iqMzlQWLe6jUrrB");

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        try {

            //设置请求参数
            request.setVideoId("d2cafd2406cc499f8020532cf7fe0e98");
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");

            System.out.print("VideoMeta.URL = " + response.getVideoMeta().getCoverURL() + "\n");

        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }

        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
