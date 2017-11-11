package com.artivisi.project.trainingweb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingWebApplicationTests {

    @Autowired private PasswordEncoder passwordEncoder;
    
    @Test
    public void contextLoads() {
        String plainPassword = "admin123";
        String hashPassword = passwordEncoder.encode(plainPassword);
        
        System.out.println("Password Plain --> " + plainPassword);
        System.out.println("Password Encrypted --> " + hashPassword);
        
        String pass2 = "admin1";
        System.out.println("Password2 Plain --> " + pass2);
        
        System.out.println("Password Match ? --> " + passwordEncoder.matches(pass2, hashPassword));
        
        Assert.assertTrue(passwordEncoder.matches(pass2, hashPassword));
        
    }

}
