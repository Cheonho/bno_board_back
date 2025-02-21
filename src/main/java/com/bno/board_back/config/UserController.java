package com.bno.board_back.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bno.board_back.service.UserService ;
import com.bno.board_back.dto.UserModel ;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    final
    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginPage(@RequestBody UserModel user,
                                       HttpSession session, Model model) {
        UserModel usermodel = service.loginPage(user);
        System.out.println("usermodel : " + usermodel);

        if (usermodel != null) {
            session.setAttribute("member", usermodel);
            model.addAttribute("user", usermodel.getUserName());
            return ResponseEntity.ok(Map.of(
                    "msg", "로그인 성공",
                    "user", usermodel
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "msg", "로그인 실패: ID 또는 비밀번호 오류"));
        }
    }

    @PostMapping("/join")
    public void JoinPage(@RequestBody UserModel user) {
        service.JoinPage(user);
    }

    @ResponseBody
    @GetMapping("/idcheck")
    public Map<String,Boolean> IdCheckPage(@RequestParam String userId) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isAvailable = !service.idcheckPage(userId);
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

