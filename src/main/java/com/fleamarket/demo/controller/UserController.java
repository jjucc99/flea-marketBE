package com.fleamarket.demo.controller;


import com.fleamarket.demo.jwt.JwtTokenProvider;
import com.fleamarket.demo.model.dto.LoginRequestDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        if (this.userService.login(loginRequestDto)) {
            String token = this.jwtTokenProvider.createToken(loginRequestDto.getUsername());
            System.out.println(token);
            return token;
        } else {
            return "닉네임 또는 패스워드를 확인해주세요";
        }
    }
//    @PostMapping({"/user/signup"})
//    public String registerUser(@RequestBody @Valid SignupRequestDto requestDto) {
//        String res = this.userService.registerUser(requestDto);
//        return res.equals("") ? "회원가입 성공" : res;
//    }


    @PostMapping("/user/join/username")
    public String registerUsername(@RequestBody UserDto userDto) {
        String reu = UserService.registerUsername(userDto);
        if (reu.equals("")) {
            return "유저 이름 확인";
        } else {
            return reu;
        }
    }

    @PostMapping("/user/join/nickname")
    public String registerNickname(@RequestBody UserDto userDto) {
        String ren = UserService.registerNickname(userDto);
        if (ren.equals("")) {
            return "닉네임 확인";
        } else {
            return ren;
        }
    }

    @PostMapping("/user/join")
    public String registerUser(@Valid @RequestBody UserDto requestDto) {
        String res = UserService.registerUser(requestDto);
        if (res.equals("")) {
            return "회원가입 성공";
        } else {
            return res;
        }
    }
}


