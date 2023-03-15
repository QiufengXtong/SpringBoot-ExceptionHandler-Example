package cn.xtong.example.controller;

import cn.xtong.example.model.request.UserRequest;
import cn.xtong.example.util.Groups;
import cn.xtong.example.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/add")
    public ResponseUtil<UserRequest> add(@Validated(Groups.add.class) UserRequest userRequest) {
        System.out.println(userRequest);
        return ResponseUtil.success(userRequest);
    }

    @GetMapping("/edit")
    public ResponseUtil<UserRequest> edit(@Valid UserRequest userRequest) {
        System.out.println(userRequest);
        return ResponseUtil.success(userRequest);
    }
}
