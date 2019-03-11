package com.haliri.israj.appservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by israjhaliri on 12/24/17.
 */
public class WebUtil {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public static String getUserLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return  currentPrincipalName;
    }
}
