package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.UserDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.UserRole;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::user_role")
    private UserRole role;

    @OneToMany(mappedBy = "assignedUser")
    private Set<Task> tasks;

    public User(String email, String password, UserRole role, Set<Task> tasks) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.tasks = tasks;
    }

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto toDto() {
        return UserDto.builder().id(this.id).email(this.email).role(this.role).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
