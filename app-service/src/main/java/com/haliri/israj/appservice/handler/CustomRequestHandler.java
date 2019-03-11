package com.haliri.israj.appservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haliri.israj.appcore.utils.AppUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by israjhaliri on 8/30/17.
 */
public class CustomRequestHandler extends HttpServletRequestWrapper {

    public CustomRequestHandler(HttpServletRequest request) {
        super(request);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public byte[] getRequestBody() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String body = IOUtils.toString(super.getInputStream(), "UTF-8");
        Object originalPayload = parseToken(body);
        String sOrigPayload = mapper.writeValueAsString(originalPayload);

        AppUtils.getLogger(this).debug("ORIGINAL PAYLOAD FROM TOKEN [{}]", sOrigPayload);
        return sOrigPayload.getBytes("UTF-8");
    }

    public Object parseToken(String token) {
        AppUtils.getLogger(this).debug("TOKEN TO PARSE [{}]", token);

        try {
            ObjectMapper mapper = new ObjectMapper();

            Claims body = Jwts.parser()
                    .setSigningKey("footnote".getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();

            AppUtils.getLogger(this).debug("JSON PAYLOAD [{}]", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));
            return body;
        } catch (JwtException | ClassCastException | JsonProcessingException | UnsupportedEncodingException e) {
            AppUtils.getLogger(this).error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getRequestBody());

        ServletInputStream servletInputStream =
                new ServletInputStream() {
                    @Override
                    public int read() throws IOException {
                        return byteArrayInputStream.read();
                    }

                    @Override
                    public boolean isFinished() {
                        return byteArrayInputStream.available() == 0;
                    }

                    @Override
                    public boolean isReady() {
                        return true;
                    }

                    @Override
                    public void setReadListener(ReadListener rl) {
                        throw new RuntimeException("Not implemented");
                    }
                };

        return servletInputStream;
    }
}
