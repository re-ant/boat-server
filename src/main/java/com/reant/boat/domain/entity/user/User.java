package com.reant.boat.domain.entity.user;

import com.reant.boat.domain.enums.auth.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(updatable = false, unique = true, nullable = false, length = 64)
    private String userId;

    @Column(nullable = false, length = 191)
    private String password;

    @Column(length = 12)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public User(String userId, String password, String username, Role role) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
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

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}