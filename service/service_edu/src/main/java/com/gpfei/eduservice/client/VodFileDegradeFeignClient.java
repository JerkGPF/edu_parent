package com.gpfei.eduservice.client;

import com.gpfei.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient{
    //出错后执行
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
