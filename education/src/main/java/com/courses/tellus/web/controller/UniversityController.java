package com.courses.tellus.web.controller;

import com.courses.tellus.entity.model.University;
import com.courses.tellus.service.simple.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private transient UniversityService serviceImpl;

    /**
     * This method forwards List of universities to listOfUniversities.jsp.
     *
     * @param model - Model.
     * @return - name of jsp
     */
    @GetMapping("/list")
    public String getAllUniversities(final Model model) {
        model.addAttribute("universityList", serviceImpl.getAll());
        return "listOfUniversities";
    }

    /**
     * Method provide view for creating new university.
     *
     * @return view "addUniversity.jsp"
     */
    @GetMapping("/add")
    public String addUniversity() {
        return "addUniversity";
    }

    /**
     * Method forwards attributes from createUniversity.jsp to DB.
     *
     * @return - name of jsp
     */
    @PostMapping("/add")
    public String addUniversity(@ModelAttribute("university") final University university) {
     serviceImpl.insert(university);
     return "redirect:/springmvc/university/list";
    }

    /**
     * @param uniId from Http request.
     * @return - name of jsp
     */
    @GetMapping("/delete/{uniId}")
    public String deleteUniversity(@PathVariable("uniId") final Long uniId) {
    serviceImpl.delete(uniId);
    return "redirect:/springmvc/university/list";
    }

    /**
     * @param uniId from Http request.
     * @return - name of jsp
     */
    @GetMapping("/edit/{uniId}")
    public String updateUniversity(@PathVariable("uniId") final Long uniId, final Model model) {
       model.addAttribute("university", serviceImpl.getById(uniId).get());
        return "updateUniversity";
    }

    /**
     * Method forwards attributes from createUniversity.jsp to DB.
     *
     * @return - name of jsp
     */

    @PostMapping("/edit")
    public String updateUniversity(@ModelAttribute("university") final University university) {
        serviceImpl.update(university);
        return "redirect:/springmvc/university/list";
    }
}

