package com.cooksys.groupfinal.services;

import java.util.List;

import com.cooksys.groupfinal.dtos.ProjectDto;

public interface ProjectService {

	ProjectDto createProject(ProjectDto projectDto);

	ProjectDto getProjectById(Long id);

	ProjectDto updateProject(Long id, ProjectDto projectDto);

	void deleteProject(Long id);

	List<ProjectDto> getAllProjects();

}
