package com.tassel.edu.controller;

import com.tassel.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zsy
 * @date: 2020/12/11 21:20
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/user")
public class LoginController {

    @PostMapping("/login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R getUserInfo() {
        return R.ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
