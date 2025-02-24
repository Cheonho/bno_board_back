package com.bno.board_back.service;

import com.bno.board_back.dto.object.Users;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.user.GetLoginUserDto;
import com.bno.board_back.entity.UserEntity;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<? super GetLoginUserDto> loginPage(Users user) {

        Optional<UserEntity> model;
        try {
            model = repo.findByEmail(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        if (model.isPresent()) {
            // 사용자가 존재하는 경우
            UserEntity foundUser = model.get();
            if (user.getEmail().equals(foundUser.getEmail())) {
                if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                    return GetLoginUserDto.success(foundUser);
                } else {
                    // 비밀번호가 일치하지 않는 경우
                    return ResponseDto.signFailed();
                }
            } else {
                // 아이디가 일치하지 않는 경우
                return ResponseDto.signFailed();
            }
        } else {
            // 사용자가 존재하지 않는 경우
            return ResponseDto.signFailed();
        }

    }

    public void JoinPage(Users user){
        System.out.println(user);
        String userSalt = passwordEncoder.encode(user.getPassword());
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(userSalt)
                .userNickname(user.getUserName())
                .address(user.getAddress())
                .role("user")
                .build();
        System.out.println("비밀번호 : " + user.getPassword());
        System.out.println("암호화 : " + user.getPassword());
        repo.save(userEntity) ;
    }

    public boolean idcheckPage(String email) {
        return repo.existsByEmail(email) ;

    }

    public boolean namecheckPage(String userName) {
        return repo.existsByUserNickname(userName) ;
    }
}
