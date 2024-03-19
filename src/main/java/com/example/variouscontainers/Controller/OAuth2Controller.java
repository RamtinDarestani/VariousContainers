package com.example.variouscontainers.Controller;
import com.example.variouscontainers.Entity.Email;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class OAuth2Controller {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/oauth2/callback")
    public String handleOAuth2LoginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");

        // Save the email to the database
        Email emailEntity = new Email();
        emailEntity.setEmailAddress(email);
        entityManager.getTransaction().begin();
        entityManager.persist(emailEntity);
        entityManager.getTransaction().commit();

        // Redirect or return a view
        return "redirect:/success";
    }
}