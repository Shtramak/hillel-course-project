package com.courses.tellus.controller;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.dto.SubjectDTO;
import com.courses.tellus.model.Subject;
import com.courses.tellus.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private final transient SubjectServiceImpl subjectService;
    private static final String MAIN_REDIRECT_PATH = "redirect:/subject";

    @Autowired
    public SubjectController(final SubjectServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Method provide all subjects from database to view.
     *
     * @return model with subjects and view "subject_list.jsp"
     */
    @GetMapping
    public ModelAndView getAllEntity() {
        final List<Subject> subjectList = subjectService.getAll();
        return new ModelAndView("subject_list", "subjectList", subjectList);
    }

    /**
     * Method provide view for creating new subject.
     *
     * @return view "subject_create.jsp"
     */
    @GetMapping("/add")
    public ModelAndView insertEntity() {
        return new ModelAndView("subject_create");
    }

    /**
     * Method provide creating new object and insert into database.
     *
     * @param subject dto object obtained from jsp
     * @return to /subject path
     */
    @PostMapping("/add")
    public ModelAndView insertEntity(@ModelAttribute("subject") final SubjectDTO subject) {
        subjectService.insert(subject);
        return new ModelAndView(MAIN_REDIRECT_PATH);
    }

    /**
     * Method provide delete subject from database.
     *
     * @param subjectId part of path and id of needed object
     * @return to /subject path
     */
    @GetMapping("/delete/{id:[\\d]+}")
    public ModelAndView deleteEntity(@PathVariable("id") final Long subjectId) {
        subjectService.delete(subjectId);
        return new ModelAndView(MAIN_REDIRECT_PATH);
    }

    /**
     * Method provide search in database by id and return it to view.
     *
     * @param subjectId part of path and id of needed object
     * @return model with subject and view "subject_edit.jsp" or redirect to /subject path
     */
    @GetMapping("/edit/{id:[\\d]+}")
    public ModelAndView updateEntity(@PathVariable("id") final Long subjectId) {
        final Optional<Subject> opt = subjectService.getById(subjectId);
        return opt.map(subject -> new ModelAndView("subject_edit", "subject", subject))
                .orElseGet(() -> new ModelAndView(MAIN_REDIRECT_PATH));
    }

    /**
     * Method provide updating object into database.
     *
     * @param subject dto object obtained from jsp
     * @return to /subject path
     */
    @PostMapping("/edit")
    public ModelAndView updateEntity(@ModelAttribute("subject") final SubjectDTO subject) {
        subjectService.update(subject);
        return new ModelAndView(MAIN_REDIRECT_PATH);
    }
}