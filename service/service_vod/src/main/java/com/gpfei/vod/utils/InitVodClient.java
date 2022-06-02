package com.gpfei.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

public class InitVodClient {

    public static DefaultAcsClient initVodClient(String accessKeyId,String accessKeySecret) throws Exception{
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        DefaultAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }
}
