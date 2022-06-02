package com.gpfei.vod.controller;

import com.gpfei.commonutils.R;
import com.gpfei.vod.service.VodService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;
    //上传视频
    @PostMapping("uploadALYVideo")
    public R uploadALYVideo(MultipartFile file){
        //上传后返回视频id
        String videoId = vodService.uploadVideoALY(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("removeALYVideo/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                             @PathVariable String videoId){
        boolean isDelete = vodService.removeVideo(videoId);
        if(isDelete)
            return R.ok().message("视频删除成功");
        else
            return R.error().message("视频删除失败");
    }

    //删除多个视频
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam("videIdList") List<String> videIdList){
        vodService.removeMoreALYVideo(videIdList);
        return R.ok();

    }
}
