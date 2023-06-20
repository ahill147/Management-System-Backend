package com.cooksys.groupfinal.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.groupfinal.dtos.ProjectDto;
import com.cooksys.groupfinal.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectService projectService;

//    @PostMapping
//    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
//        return projectService.createProject(projectDto);
//    }
//
//    @GetMapping("/{id}")
//    public ProjectDto getProjectById(@PathVariable Long id) {
//        return projectService.getProjectById(id);
//    }
//
//    @PutMapping("/{id}")
//    public ProjectDto updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
//        return projectService.updateProject(id, projectDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProject(@PathVariable Long id) {
//        projectService.deleteProject(id);
//    }
//
//    @GetMapping
//    public List<ProjectDto> getAllProjects() {
//        return projectService.getAllProjects();
//    }
}
