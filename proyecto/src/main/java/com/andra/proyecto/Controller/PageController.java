package com.andra.proyecto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {

    // this is showing login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("page/user")
    public String showUserPage() {
        return "userPage";
    }

    @GetMapping("/page/administrator")
    public String showAdministratorPage() {
        return "administratorPage";
    }

    @GetMapping("/page/inChargeResident")
    public String showInChargeResidentPage() {
        return "inChargeResidentPage";
    }

    @GetMapping("/page/resident")
    public String showResidentPage() {
        return "residentPage";
    }

    @GetMapping("/page/visitant")
    public String showVisitantPage() {
        return "visitantPage";
    }

    @GetMapping("/page/vigilant")
    public String showVigilantPage() {
        return "vigilantPage";
    }
}
