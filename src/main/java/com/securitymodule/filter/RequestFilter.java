package com.securitymodule.filter;

import com.securitymodule.exception.RequestUnauthorizedException;
import com.securitymodule.model.User;
import com.securitymodule.repository.UserRepository;
import com.securitymodule.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ComponentScan
@Configuration
public class RequestFilter implements Filter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String uri = httpServletRequest.getRequestURI();
        if (authenticationService.isAuthorizedUrl(uri)) {
            String email;
            try {
                email = httpServletRequest.getSession().getAttribute("LOGIN_SESSION").toString();
            } catch (Exception e) {
                throw new RequestUnauthorizedException("Unauthorized");
            }
            if (email != null) {
                User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new RequestUnauthorizedException("Unauthorized");
                }
            } else
                throw new RequestUnauthorizedException("Unauthorized");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
