package com.haliri.israj.appservice.filter;

import com.haliri.israj.appcore.dao.common.UserDAO;
import com.haliri.israj.appcore.domain.common.User;
import com.haliri.israj.appcore.utils.AppUtils;
import com.haliri.israj.appservice.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by israjhaliri on 8/30/17.
 */
public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDAO userDAO;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getRequestURI().startsWith("/secret")) {
            AppUtils.getLogger(this).debug("REQ STARTS WITH : {}",httpRequest.getRequestURI().toString());
            String authToken = httpRequest.getHeader(this.tokenHeader);

            if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")){
                authToken = authToken.substring(7);
            }

            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            try {
                User user = userDAO.getDataById(username);
                if (user.getId() != null  && user.getToken().equals(authToken) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }catch (Exception e){
                AppUtils.getLogger(this).error("ERROR USER DETAIL FROM TOKEN : {}", e.getMessage());
            }
        }

        chain.doFilter(request, response);
    }
}
