/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.project.trainingweb;

import com.artivisi.project.trainingweb.security.LoginFailureHandler;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 *
 * @author adi
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    //Otomatis mengambil setting koneksi database dari file application.properties
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    //username, password, active
    private static final String SQL_LOGIN
        = "select u.username, u.password, u.is_active as active "
        + "from sec_user u "
        + "where username = ?";

    //username, authority
    private static final String SQL_ROLE
        = "select u.username, p.permission_value as authority "
        + "from sec_user u "
        + "inner join sec_role r on u.id_role = r.id "
        + "inner join sec_role_permission rp on rp.id_role = r.id "
        + "inner join sec_permission p on rp.id_permission = p.id "
        + "where u.username = ?";
    
    
    //Provider Otentikasi
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    
    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl userDetails = new JdbcDaoImpl();
        userDetails.setDataSource(dataSource);
        userDetails.setUsersByUsernameQuery(SQL_LOGIN);
        userDetails.setAuthoritiesByUsernameQuery(SQL_ROLE);
        return userDetails;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/font/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/login**").permitAll()
                .anyRequest().authenticated()
            .and()
                // some more method calls
                .formLogin().loginPage("/login").permitAll()
                .failureHandler(loginFailureHandler)
                .defaultSuccessUrl("/", true)
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
            .and()
                .csrf().disable();
    }

}
