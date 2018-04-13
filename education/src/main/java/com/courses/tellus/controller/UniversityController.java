package com.courses.tellus.controller;

import com.courses.tellus.model.University;
import com.courses.tellus.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@ComponentScan
public class UniversityController {

    @Autowired
    private transient UniversityService universityServiceImpl;

    /**
     * This method returns main index.jsp page
     *
     * @return - name of jsp
     */

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * This method forwards List of universities to listOfUniversities.jsp.
     *
     * @param model - Model.
     * @return - name of jsp
     */

    @GetMapping("/getAllUniversities")
    public String getAllUniversities(Model model) {
        model.addAttribute("universityList", universityServiceImpl.getAll());
        return "listOfUniversities";
    }

    /**
     * Method to show form to create university
     *
     * @return - name of jsp
     */

    @GetMapping("/addUniversity")
    public String addUniversity() {
        return "addUniversity";
    }

    /**
     * Method forwards attributes from createUniversity.jsp to DB
     *
     * @return - name of jsp
     */

    @PostMapping("/addUniversity")
    public String addUniversity(@ModelAttribute("university") final University university) {
     universityServiceImpl.insert(university);
     return "redirect:/getAllUniversities";
    }

    /**
     * @param uniId from Http request
     * @return - name of jsp
     */

    @GetMapping("/deleteUniversity/{uniId}")
    public String deleteUniversity(@PathVariable("uniId") final Long uniId) {
    universityServiceImpl.delete(uniId);
    return "redirect:/getAllUniversities";
    }

    /**
     * @param uniId from Http request
     * @return - name of jsp
     */

    @GetMapping("/updateUniversity/{uniId}")
    public String updateUniversity(@PathVariable("uniId") final Long uniId, final Model model) {
       model.addAttribute("university", universityServiceImpl.getById(uniId).get());
        return "updateUniversity";
    }

    /**
     * Method forwards attributes from createUniversity.jsp to DB
     *
     * @return - name of jsp
     */

    @PostMapping("/updateUniversity")
    public String updateUniversity(@ModelAttribute("university") final University university) {
        universityServiceImpl.update(university);
        return "redirect:/getAllUniversities";
    }
}

