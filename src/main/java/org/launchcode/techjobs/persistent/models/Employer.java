package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {
    @NotBlank(message = "location is required")
    @Size(max = 500, message = "location too long!")
    private String location;
    @OneToMany
    @JoinColumn(name="employer_id")
    private List<Job> jobs = new ArrayList<>();

    public Employer(){}

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    public void setLocation(@NotBlank(message = "location is required") @Size(max = 500, message = "location too long!") String location) {
        this.location = location;
    }

    public @NotBlank(message = "location is required") @Size(max = 500, message = "location too long!") String getLocation() {
        return location;
    }
}
