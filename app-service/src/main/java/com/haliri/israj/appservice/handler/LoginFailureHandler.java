package com.haliri.israj.appservice.handler;

import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by israjhaliri on 8/30/17.
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ae) throws IOException, ServletException {
        AppUtils.getLogger(this).debug("LOGIN ERROR");

        res.setStatus(HttpStatus.UNAUTHORIZED.value());

        HttpSession session = req.getSession();
        session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, ae.getMessage());

        try (PrintWriter writer = res.getWriter()) {
            writer.write("{\"code\":\""+res.getStatus()
                    + "\", \"status\":\"ERR\", "
                    + "\"message\":\""+ae.getMessage()+"\"}");
            writer.flush();
            writer.close();
        }
    }
}
