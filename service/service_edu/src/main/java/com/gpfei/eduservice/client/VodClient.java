package com.gpfei.eduservice.client;

import com.gpfei.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/video/removeALYVideo/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable("videoId") String videoId);

    @DeleteMapping("/eduvod/video/deleteBatch")
    public R deleteBatch(@RequestParam("videIdList") List<String> videIdList);
}
