package cn.xtong.example.controller;

import cn.xtong.example.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseUtil<Integer> test(Integer divisor,Integer dividend) {
        int num = divisor / dividend;
        log.info("计算结果：{}", num);
        return ResponseUtil.success(num);
    }
}