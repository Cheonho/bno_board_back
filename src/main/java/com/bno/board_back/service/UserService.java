package com.bno.board_back.service;

import com.bno.board_back.dto.UserModel ;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bno.board_back.repository.UserRepository ;

import java.util.Optional;

@Service
public class UserService {

    final
    UserRepository repo ;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel loginPage(UserModel user) {
        System.out.println("아이디 비번 : " + user.getUserId() + user.getSalt());

        Optional<UserModel> model = repo.findByUserId(user.getUserId());

        if (model.isPresent()) {
            // 사용자가 존재하는 경우
            UserModel foundUser = model.get();
            if (user.getUserId().equals(foundUser.getUserId())) {
                if (passwordEncoder.matches(user.getSalt(), foundUser.getSalt())) {
                    return foundUser;
                } else {
                    // 비밀번호가 일치하지 않는 경우
                    return null;
                }
            } else {
                // 아이디가 일치하지 않는 경우
                return null;
            }
        } else {
            // 사용자가 존재하지 않는 경우
            return null;
        }

    }

    public UserModel JoinPage(UserModel user){
        String userSalt = passwordEncoder.encode(user.getUserPw()) ;
        user.setSalt(userSalt);
        System.out.println("비밀번호 : " + user.getUserPw());
        System.out.println("암호화 : " + user.getSalt());
        return repo.save(user) ;

    }

    public boolean idcheckPage(String userId) {
        return repo.existsByUserId(userId) ;

    }

    public boolean namecheckPage(String userName) {
        return repo.existsByUserName(userName) ;
    }
}
