/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingweb.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author adi
 */

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
            
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
            AuthenticationException exception) throws IOException, ServletException {
        log.warn("[AUTH] - [ERROR] Login Gagal [{}]", exception.getMessage());
        
        HttpSession session = request.getSession();
        
        if(exception instanceof SessionAuthenticationException) {
            session.setAttribute("authError", "Username sudah digunakan, ditempat lain !");
        } else if (exception instanceof BadCredentialsException) {
            session.setAttribute("authError", "Username atau Password Salah !");
        } else {
            session.setAttribute("authError", "Error tidak terdefinisi");
        }

        getRedirectStrategy().sendRedirect(request, response, "/login?error");
    }
    
}
