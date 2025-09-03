package com.example.accountui.controller;

import com.example.accountui.model.Account;
import com.example.accountui.service.AccountService;
import com.example.accountui.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping({"/", "/accounts"})
    public String listAccounts(Model model) {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            model.addAttribute("accounts", accounts);
            model.addAttribute("dateUtils", new DateUtils());
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des comptes: " + e.getMessage());
            model.addAttribute("accounts", List.of());
        }
        return "accounts/list";
    }
    
    @GetMapping("/accounts/{id}")
    public String viewAccount(@PathVariable String id, Model model) {
        try {
            Account account = accountService.getAccountById(id);
            if (account != null) {
                model.addAttribute("account", account);
                model.addAttribute("dateUtils", new DateUtils());
                return "accounts/detail";
            } else {
                model.addAttribute("error", "Compte non trouvé");
                return "redirect:/accounts";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du compte: " + e.getMessage());
            return "redirect:/accounts";
        }
    }
}