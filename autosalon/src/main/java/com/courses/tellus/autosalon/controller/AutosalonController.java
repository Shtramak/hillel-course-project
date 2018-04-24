package com.courses.tellus.autosalon.controller;

import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.service.AutosalonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autosalon/autosalon")
public class AutosalonController {

    private final transient AutosalonServiceImpl autosalonService;

    @Autowired
    public AutosalonController(final AutosalonServiceImpl autosalonService) {
        this.autosalonService = autosalonService;
    }

    /**
     *Get page index.html.
     * @return page index.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     *Get list of autosalon.
     * @param model model.
     * @return page listAutosalon.
     */
    @GetMapping("/allAutosalon")
    public String getAllAutosalon(final Model model) {
        model.addAttribute("autosalon", autosalonService.getAll());
        return "allAutosalon";
    }

    /**
     *Create new autosalon.
     * @param autosalon autosalon.
     * @return page listAutosalon.
     */
    @PostMapping("/createautosalon")
    public String createAutosalon(final Autosalon autosalon) {
        autosalonService.insert(autosalon);
        return "redirect:createautosalon";
    }

    /**
     *Show form for create new autosalon.
     * @return page createAutosalon.
     */
    @GetMapping("/createautosalon")
    public String createAutosalonPage() {
        return "createautosalon";
    }
}
