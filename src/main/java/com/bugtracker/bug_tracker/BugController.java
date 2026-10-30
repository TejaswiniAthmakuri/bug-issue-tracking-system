package com.bugtracker.bug_tracker;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    private final BugRepository bugRepository;

    public BugController(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @GetMapping
    public List<bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @PostMapping
    public bug createBug(@RequestBody bug bug) {
        return bugRepository.save(bug);
    }
    
    @GetMapping("/{id}")
    public bug getBugById(@PathVariable Long id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with id: " + id));
    }
    
    @PutMapping("/{id}")
    public bug updateBug(@PathVariable Long id, @RequestBody bug updatedBug) {

        bug existingBug = bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with id: " + id));

        existingBug.setTitle(updatedBug.getTitle());
        existingBug.setDescription(updatedBug.getDescription());
        existingBug.setStatus(updatedBug.getStatus());
        existingBug.setPriority(updatedBug.getPriority());

        return bugRepository.save(existingBug);
    }
    
    @DeleteMapping("/{id}")
    public String deleteBug(@PathVariable Long id) {

        bug bug = bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with id: " + id));

        bugRepository.delete(bug);

        return "Bug deleted successfully";
    }
    
    
}
