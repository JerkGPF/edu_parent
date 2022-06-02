package com.gpfei.msmservice.controller;

import com.gpfei.commonutils.R;
import com.gpfei.msmservice.service.MsmService;
import com.gpfei.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private MsmService msmService;

    //发送短信
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //1.从redis获取，如果有则直接发送
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code))
            return R.ok().data("code",code);
        //2.redis没有则进行发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param= new HashMap<>();
        param.put("code",code);
        //调用service发送短信
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //发送成功，把验证码放入redis中
            //设置有效时间
            redisTemplate.opsForValue().set(phone, code,5,TimeUnit.MINUTES);
            return R.ok();
        }
        else
            return R.error().message("短信发送失败");
    }
}
