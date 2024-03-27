package com.example.variouscontainers.Controller;

import com.example.variouscontainers.Entity.Email;
import com.example.variouscontainers.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/emails")
    public String viewEmails(Model model) {
        List<Email> emails = emailRepository.findAll();
        model.addAttribute("emails", emails);
        return "emails";
    }
}