package com.haliri.israj.appservice.filter;


import com.haliri.israj.appcore.utils.AppUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by israjhaliri on 8/31/17.
 */
public class RestFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        AppUtils.getLogger(this).info("INIT REST FILTER");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Set<String> clientListIp = new HashSet<>();
        clientListIp.add("127.0.0.1");
//        for localhost cause client allways give remote address
        clientListIp.add("http://localhost:8080");

        AppUtils.getLogger(this).info("REMOTE ADRESS : {}", request.getRemoteAddr());
        AppUtils.getLogger(this).info("ORIGIN ADRESS : {}", request.getHeader("Origin"));
//        localhost for tooltesting like postman
        AppUtils.getLogger(this).info("SERVER NAME : {}", request.getServerName());
        AppUtils.getLogger(this).info("PROTOTCOL USING : {}", request.getProtocol());

        Set<String> clientRemoteIp = new HashSet<>();
        clientRemoteIp.add(request.getRemoteAddr());
        clientRemoteIp.add(request.getHeader("Origin"));

        Boolean allowedStatus = false;

        for (String remoteIp : clientRemoteIp){
            if (clientListIp.contains(remoteIp)) {
                AppUtils.getLogger(this).info("ADRESS : {} ALLOWED", remoteIp);
                allowedStatus = true;
            }
        }

        if(!allowedStatus){
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Client Is Not Registered");
        }

//        use this with token payload
//        CustomRequestHandler requestHandler = new CustomRequestHandler((HttpServletRequest) servletRequest);
//        fc.doFilter(requestHandler, servletResponse);

//        use this whitout token payload
        fc.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
