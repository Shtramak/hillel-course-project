package com.courses.tellus.controller;

import java.util.List;

import com.courses.tellus.dao.spring.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private transient SubjectDao subjectDao;

    @GetMapping
    public ModelAndView getAllEntity() {
        List<Subject> subjectList = subjectDao.getAll();
        return new ModelAndView("subject_list", "subjectList", subjectList);
    }

    @GetMapping
    public ModelAndView insertEntityGet() {
        return new ModelAndView();
    }

    @PostMapping(value = "/add")
    public ModelAndView insertEntityPost() {
        return new ModelAndView();
    }

    @GetMapping(value = "/{id}/delete")
    public ModelAndView deleteEntity(@PathVariable("id") Long subjectId) {
        return new ModelAndView();
    }

    @GetMapping(value = "/{id}/edit")
    public ModelAndView updateEntityGet(@PathVariable("id") Long subjectId) {
        return new ModelAndView();
    }
    @PostMapping(value = "/{id}/edit")
    public ModelAndView updateEntityPost() {
        return new ModelAndView();
    }
}
