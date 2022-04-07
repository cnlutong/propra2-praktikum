package hhu.propra2.group6.chicken.controller.organisator;

import hhu.propra2.group6.chicken.annotations.OrganisatorOnly;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@OrganisatorOnly
public class OrganisatorController {

    @GetMapping("/organisator")
    public String organisatorGet(Model model) {
        return "organisator";
    }
}
