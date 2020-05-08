package com.securitymodule.filter;

import com.securitymodule.model.User;
import com.securitymodule.repository.UserRepository;
import com.securitymodule.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

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
                User user = userRepository.findByEmail(email);
                if (user == null) {
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"Unauthorized");
                }
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"Unauthorized");
            }
        }else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
