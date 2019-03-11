package com.haliri.israj.appservice.handler;

import com.haliri.israj.appcore.dao.common.UserDAO;
import com.haliri.israj.appcore.domain.common.User;
import com.haliri.israj.appservice.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by israjhaliri on 8/30/17.
 */
@Component
public class LogOutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;


    @Override
    public void onLogoutSuccess(HttpServletRequest httpRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String authToken = httpRequest.getHeader(this.tokenHeader);

        if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer "))
            authToken = authToken.substring(7);

        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User user = userDAO.getDataById(username);
        if(username != null && user.getToken().equals(authToken)){
            try (PrintWriter writer = response.getWriter()) {
                userDAO.deleteToken(username);
                writer.write("{\"code\":\"" + response.getStatus()
                        + "\", \"status\":\"SUCESS\"}");
                writer.flush();
                writer.close();
            }
        }else{
            try (PrintWriter writer = response.getWriter()) {
                response.setStatus(400);
                String message = " Make sure token is correct and you have logged in";
                writer.write("{\"code\":\""+response.getStatus()
                        + "\", \"status\":\"FAILED\", "
                        + "\"message\":\""+message+"\"}");
                writer.flush();
                writer.close();
            }
        }
    }
}
