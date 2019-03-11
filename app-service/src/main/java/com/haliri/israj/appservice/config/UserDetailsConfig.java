package com.haliri.israj.appservice.config;


import com.haliri.israj.appcore.dao.common.UserDAO;
import com.haliri.israj.appcore.domain.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by israjhaliri on 8/25/17.
 */

@Service
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userDAO.getDataById(username);
        if (user.getId() == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            user.getRoles().forEach((users)->{
                grantedAuthorities.add(new SimpleGrantedAuthority(users.getRole()));
            });

            return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),user.getEnable(), true, true, true, grantedAuthorities);
        }
    }
}