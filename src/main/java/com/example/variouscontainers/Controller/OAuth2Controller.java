package com.example.variouscontainers.Controller;

import com.example.variouscontainers.Entity.Email;
import com.example.variouscontainers.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/oauth2/callback")
    public String handleOAuth2LoginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String pictureUrl = oauth2User.getAttribute("picture");

        Email emailEntity = new Email();
        emailEntity.setEmail_address(email);
        emailEntity.setName(name);
        emailEntity.setPicture(pictureUrl);
        emailRepository.save(emailEntity);

        return "redirect:/sign";
    }
}