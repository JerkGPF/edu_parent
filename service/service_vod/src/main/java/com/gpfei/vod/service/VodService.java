package com.gpfei.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoALY(MultipartFile file);

    boolean removeVideo(String videoId);

    //删除多个视频
    void removeMoreALYVideo(List<String> videIdList);
}
