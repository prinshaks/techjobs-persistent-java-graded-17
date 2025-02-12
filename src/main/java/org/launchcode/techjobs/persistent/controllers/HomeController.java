package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    public EmployerRepository employerRepository;

    @Autowired
    public SkillRepository skillRepository;

    @Autowired
    public JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("jobs",jobRepository.findAll());

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
    model.addAttribute("employers",employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam(required = false) int employerId, @RequestParam(required = false)  List<Integer> skills) {

        if(skills == null || errors.hasErrors()) {
            if(skills == null){
                model.addAttribute("skills_err","Skills is required");
            }
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers",employerRepository.findAll());
            model.addAttribute("skills",skillRepository.findAll());
            //model.addAttribute(new Job());
            return "add";
        }

            Optional<Employer> result = employerRepository.findById(employerId);

            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Employer ID: " + employerId);
            } else {
                Employer employerObj = result.get();
                newJob.setEmployer(employerObj);
            }

            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);

            jobRepository.save(newJob);
            return "redirect:";

    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> result= jobRepository.findById(jobId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Job ID: " + jobId);
        } else {
            Job jobObj = result.get();
            model.addAttribute("job",jobObj);
        }


            return "view";
    }

}
