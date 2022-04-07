package hhu.propra2.group6.chicken.controller.tutor;

import hhu.propra2.group6.chicken.annotations.TutorOnly;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@TutorOnly
public class TutorController {

    @GetMapping("/tutor")
    public String tutorGet(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("user",
                principal != null ? principal.getAttribute("login") : null
        );
        return "tutor";
    }

}
