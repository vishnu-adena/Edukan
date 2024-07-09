package com.adena.edhukanuserservice.securityconfig.models;

import com.adena.edhukanuserservice.models.Role;
import com.adena.edhukanuserservice.models.Users;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private List<CustomGrantedAuthority> authorities;
    @Getter
    private long userId;


    public  CustomUserDetails(){

    }
    public CustomUserDetails(Users user) {
        this.username = user.getEmail();
        this.password = user.getHashedPassword();
        this.enabled =true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.userId = user.getId();

        List<CustomGrantedAuthority> authoritiesList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            CustomGrantedAuthority customGrantedAuthority = new CustomGrantedAuthority(role);
            authoritiesList.add(customGrantedAuthority);
        }
        this.authorities = authoritiesList;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //return a List<Something which is like a Granted Authority >
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        //banking websites we can use as after 3 time wrong details acoount locked
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
