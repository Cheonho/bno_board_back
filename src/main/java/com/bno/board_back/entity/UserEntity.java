package com.bno.board_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "users")
@Builder

public class UserEntity implements UserDetails {


    @Id
    private Long id;

    @NotNull
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @NotNull
    @Column( name = "user_nickname", length = 300, nullable = false)
    private String userNickname;

    @NotNull
    private String password;

    @NotNull
    private String address;

    @NotNull
    private String role;

    @Column(name = "is_available")
    private String isAvailable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role)); // 단일 권한을 리스트로 변환
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}