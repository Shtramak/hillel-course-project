package com.courses.tellus.controller;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.dao.spring.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private transient SubjectDao subjectDao;

    @GetMapping
    public ModelAndView getAllEntity() {
        List<Subject> subjectList = subjectDao.getAll();
        return new ModelAndView("subject_list", "subjectList", subjectList);
    }

    @GetMapping(value = "/add")
    public ModelAndView insertEntity() {
        return new ModelAndView("subject_create");
    }

    @PostMapping(value = "/add")
    public ModelAndView insertEntity(@RequestParam("name") String name,
                                     @RequestParam("description") String description,
                                     @RequestParam("valid") boolean valid,
                                     @RequestParam("dateOfCreation") String date) {
        subjectDao.insert(new Subject(name, description, valid, date));
        return new ModelAndView("subject_create");
    }

    @GetMapping(value = "/{id:[\\d]+}/delete")
    public ModelAndView deleteEntity(@PathVariable("id") Long subjectId) {
        subjectDao.delete(subjectId);
        return new ModelAndView("redirect:/subject");
    }

    @GetMapping(value = "/{id:[\\d]+}/edit")
    public ModelAndView updateEntity(@PathVariable("id") Long subjectId, Model model) {
        Optional<Subject> opt = subjectDao.getById(subjectId);
        if (opt.isPresent()) {
            model.addAttribute("subjectId", opt.get().getSubjectId());
            model.addAttribute("name", opt.get().getName());
            model.addAttribute("description", opt.get().getDescription());
            model.addAttribute("valid", opt.get().isValid());
            model.addAttribute("dateOfCreation", opt.get().getDateOfCreation());
            return new ModelAndView("subject_edit");
        } else {
            return new ModelAndView("subject_list");
        }
    }

    @PostMapping(value = "/edit")
    public ModelAndView updateEntity(@RequestParam("subjectId") Long subjectId,
                                     @RequestParam("name") String name,
                                     @RequestParam("description") String description,
                                     @RequestParam("valid") boolean valid,
                                     @RequestParam("dateOfCreation") String date) {
        subjectDao.update(new Subject(subjectId, name, description, valid, date));
        return new ModelAndView("redirect:/subject");
    }
}