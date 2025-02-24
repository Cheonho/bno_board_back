package com.bno.board_back.controller;

import com.bno.board_back.dto.object.Users;
import com.bno.board_back.dto.response.user.GetLoginUserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bno.board_back.service.UserService ;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/")
@Tag(name="User", description = "User API")
public class UserController {

    final
    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<? super GetLoginUserDto> LoginPage(@RequestBody Users user,
                                                             HttpSession session, Model model) {
        ResponseEntity<? super GetLoginUserDto> response = service.loginPage(user);
        System.out.println("usermodel : " + response.getBody());

        return response;
//        if (response != null) {
//            session.setAttribute("member", response);
//            model.addAttribute("user", response.getUserName());
//            return ResponseEntity.ok(Map.of(
//                    "msg", "로그인 성공",
//                    "user", usermodel
//            ));
//        } else {
//            return ResponseEntity.status(401).body(Map.of(
//                    "msg", "로그인 실패: ID 또는 비밀번호 오류"));
//        }
    }

    @PostMapping("/join")
    public void JoinPage(@RequestBody Users user) {
        service.JoinPage(user);
    }

    @ResponseBody
    @GetMapping("/idcheck")
    public Map<String,Boolean> IdCheckPage(@RequestParam String email) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isAvailable = !service.idcheckPage(email);
        response.put("isAvailable", isAvailable) ;

        return response ;

    }

    @ResponseBody
    @GetMapping("/namecheck")
    public Map<String,Boolean> NameCheckPage(@RequestParam String userName) {
        System.out.println("닉네임 : " + userName);

        Map<String,Boolean> response = new HashMap<>() ;
        boolean isAvailable = !service.namecheckPage(userName);
        response.put("isAvailable", isAvailable) ;

        return response ;
    }
}

