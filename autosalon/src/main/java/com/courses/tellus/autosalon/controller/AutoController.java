package com.courses.tellus.autosalon.controller;

import java.util.Optional;

import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autosalon/auto")
public class AutoController {

    private final transient AutoService autoService;

    @Autowired
    public AutoController(final AutoService autoService) {
        this.autoService = autoService;
    }

    /**
     * Get all auto.
     * @param model model.
     * @return list of auto.
     */
    @GetMapping("/list")
    public String getAllAuto(final Model model) {
        model.addAttribute("autoList", autoService.getAll());
        return "listAuto";
    }

    /**
     *Create new auto.
     * @param auto auto.
     * @return page listAuto.
     */
    @PostMapping("/create")
    public String createAuto(final Auto auto) {
        autoService.insert(auto);
        return "redirect:list";
    }

    /**
     *Show form for create new auto.
     * @return page createAuto.
     */
    @GetMapping("/create")
    public String createAutoPage() {
        return "createAuto";
    }

    /**
     *Show auto by id.
     * @param idAuto idAuto.
     * @param model model.
     * @return page showAuto.
     */
    @GetMapping("/id/{id}")
    public String getAutoById(@PathVariable("id") final Long idAuto, final Model model) {
        final Optional<Auto> auto = autoService.getAutoById(idAuto);
        model.addAttribute("auto", auto.get());
        return "showAuto";
    }

    /**
     * Delete auto by id.
     * @param idAuto idAuto.
     * @return page listAuto.
     */
    @GetMapping("/{id}")
    public String deleteAutoByid(@PathVariable("id") final Long idAuto) {
            autoService.delete(idAuto);
            return "redirect:list";
    }

    /**
     * Show form for update auto.
     * @param idAuto idAuto.
     * @param model model.
     * @return page editAuto.
     */
    @GetMapping("/update/{id}")
    public String updateAutoById(@PathVariable("id") final Long idAuto, final Model model) {
        final Optional<Auto> auto = autoService.getAutoById(idAuto);
        model.addAttribute("auto", auto.get());
        return "editAuto";
    }

    /**
     * Update auto.
     * @param auto auto.
     * @return page listAuto.
     */
    @PostMapping("/update")
    public String updateAuto(final Auto auto) {
        autoService.update(auto);
        return "redirect:list";
    }
}
