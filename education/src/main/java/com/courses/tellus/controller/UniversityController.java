package com.courses.tellus.controller;

import com.courses.tellus.entity.University;
import com.courses.tellus.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@ComponentScan
public class UniversityController {
    @Autowired
    private transient UniversityService universityService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/getAllUniversities")
    public String getAllUniversities(Model model){
        model.addAttribute("universityList",universityService.getAll());
        return "listOfUniversities";
    }

    @GetMapping("/addUniversity")
    public String addUniversity(){
        return "addUniversity";
    }

    @PostMapping("/addUniversity")
    public String addUniversity(@ModelAttribute("university") University university){
     universityService.insert(university);
     return "redirect:/getAllUniversities";
    }

    @GetMapping("/deleteUniversity/{uniId}")
    public String deleteUniversity(@PathVariable("uniId") Long uniId){
    universityService.delete(uniId);
    return "redirect:/getAllUniversities";
    }

    @GetMapping("/updateUniversity/{uniId}")
    public String updateUniversity(@PathVariable("uniId") Long uniId, Model model){
       model.addAttribute("university", universityService.getById(uniId).get());
        return "updateUniversity";
    }

    @PostMapping("/updateUniversity")
    public String updateUniversity(@ModelAttribute("university") University university){
        universityService.update(university);
        return "redirect:/getAllUniversities";
    }

}

