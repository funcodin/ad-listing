package com.ad.security.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fan.jin on 2016-10-31.
 */

/**
 *  This class simply a User class with some extra sugar and it is called by UserDetailService.
 *  We are not calling this class anywhere in our application.
 */
public class CustomUserDetail extends User implements UserDetails {

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