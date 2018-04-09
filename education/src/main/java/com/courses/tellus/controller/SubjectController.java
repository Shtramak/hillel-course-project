package com.courses.tellus.controller;

import java.util.List;

import com.courses.tellus.dao.spring.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/subject")
public class SubjectController {

    @Autowired
    private SubjectDao subjectDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllEntity() {
        List<Subject> subjectList = subjectDao.getAll();
        return new ModelAndView("", "subjectList", subjectList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView insertEntity() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteEntity() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView updateEntity() {
        return new ModelAndView();
    }

}
